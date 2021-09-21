package api.wpm.common.objects.vk.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.ads.Category;
import com.vk.api.sdk.objects.base.Likes;
import com.vk.api.sdk.objects.base.RepostsInfo;
import com.vk.api.sdk.objects.market.Price;
import com.vk.api.sdk.objects.photos.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private double availability;
    private Category category;
    private String description;
    private double id;
    private double ownerId;
    private Price price;
    private String title;
    private double date;
    @SerializedName("thumb_photo")
    private String thumbPhoto;
    private double cartQuantity;
    private DeliveryInfo deliveryInfo;
    private String sku;
    private Dimension dimensions;
    private double weight;
    @SerializedName("albums_ids")
    private List<Integer> albumsIds;
    private List<Photo> photos;
    private double canComment;
    private double canRepost;
    private Likes likes;
    private RepostsInfo reposts;
    private double viewsCount;
}
