package api.wpm.common.objects.vk.market;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.market.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    int id;
    @SerializedName("group_id")
    int groupId;
    @SerializedName("user_id")
    int userId;
    long date;
    long status;
    @SerializedName("items_count")
    int itemsCount;
    @SerializedName("total_price")
    Price totalPrice;
    @SerializedName("display_order_id")
    String displayOrderId;
    String comment;
    @SerializedName("preview_order_items")
    List<OrderProduct> previewOrderItems;
    @SerializedName("delivery")
    Delivery delivery;
    @SerializedName("recipient")
    Recipient recipient;
    Payment payment;
    @SerializedName("price_details")
    List<PriceDetail> priceDetails;
    Seller seller;
}
