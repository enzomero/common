package api.wpm.common.utils;


import api.wpm.common.objects.local.OrderNotify;
import api.wpm.common.objects.vk.market.OrderProduct;
import api.wpm.common.objects.vk.market.Product;
import api.wpm.common.objects.vk.BunchResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VkToolKit {

    private static boolean isWaiting;

    public static void throttlingVk() {
        if(isWaiting){
            return;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } finally {
            isWaiting = false;
        }
    }

    public static void throttlingVk(Integer time) {
        if(isWaiting){
            return;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } finally {
            isWaiting = false;
        }
    }

    public static double pricesToDouble(final String textPrice) {
        if (Objects.isNull(textPrice)){
            return 0.0;
        }
        return Double.parseDouble(textPrice.replaceAll("[^0-9,]", "").replace(",", "."));
    }

    public static Product getSku(final Product product, final int q) {
        final long quantity = Long.parseLong(product.getSku().split(":")[0]) + q;
        final String owner = product.getSku().split(":")[1];
        product.setSku(String.format("%d:%s", quantity, owner));
        return product;
    }

    public static String getSku(Product product) {
        String sku = product.getSku();
        if (isNull(sku) || sku.split(":").length != 2 || sku.contains("unknown")){
            sku = String.format("1:%s", giveMeOwner(product));
        }
        return sku;
    }

    public static boolean hasOwner(final String owner, final Product product) {
        if (isNull(product.getSku())){
            return false;
        }
        return product.getSku().contains(owner);
    }

    private static String giveMeOwner(final Product product) {
        if (product.getAlbumsIds().contains(14) || product.getAlbumsIds().contains(16)){
            return "callmeraus";
        } else if (product.getAlbumsIds().contains(6)){
            return "gambitmaslovski";
        }
        return "unknown";
    }

    public static Map<String, List<OrderNotify>> getNotifyByOwner(final BunchResponse<OrderProduct> orderProducts) {
        return orderProducts
                .getItems()
                .stream()
                .map(VkToolKit::getNotify)
                .collect(Collectors.groupingBy(OrderNotify::getOwner, Collectors.toList()));
    }

    private static OrderNotify getNotify(final OrderProduct orderItem) {
        return OrderNotify.builder()
                .title(orderItem.getTitle())
                .url(orderItem.getItem().getThumbPhoto())
                .sku(orderItem.getItem().getSku())
                .price(orderItem.getPrice()).build();
    }
}
