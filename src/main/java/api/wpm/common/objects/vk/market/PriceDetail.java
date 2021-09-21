package api.wpm.common.objects.vk.market;

import com.vk.api.sdk.objects.market.Price;
import lombok.Data;

@Data
public class PriceDetail {
    String title;
    Price price;
}
