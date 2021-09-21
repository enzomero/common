package api.wpm.common.objects.local;

import com.vk.api.sdk.objects.market.Price;
import lombok.Builder;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
public class OrderNotify {
    private String title;
    private InputMedia inputMedia;
    private String owner;
    private BigDecimal price;

    @Builder
    public OrderNotify(String title, String url, String sku, Price price) {
        this.title = title;
        this.owner = Optional.ofNullable(sku).map(s -> s.split(":")[1]).orElse("unknown");
        this.inputMedia = InputMediaPhoto.builder()
                .media(url)
                .caption(String.format("%s [%s]", title, price.getText()))
                .build();
        this.price = new BigDecimal(price.getText().replaceAll("[^0-9,]", "").replace(",", "."));
    }
}
