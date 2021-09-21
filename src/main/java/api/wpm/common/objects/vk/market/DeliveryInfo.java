package api.wpm.common.objects.vk.market;

import com.vk.api.sdk.objects.market.Price;
import lombok.Data;

@Data
public class DeliveryInfo {
    private String text;
    private double days;
    private Price cost;
}
