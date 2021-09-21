package api.wpm.common.objects.vk.market;

import com.vk.api.sdk.objects.market.Price;
import com.vk.api.sdk.objects.photos.Photo;
import lombok.Data;

import java.util.List;

@Data
public class OrderProduct {
    int owner_id;
    int item_id;
    Price price;
    int quantity;
    Product item;
    String title;
    Photo photo;
    List<String> variants;
}
