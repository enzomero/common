package api.wpm.common.objects.vk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CallBackResponse<T> {
    private String type;
    @SerializedName(value = "object", alternate = {"order"})
    private T object;
    @SerializedName(value = "group_id")
    private int groupId;
    @SerializedName(value = "event_id")
    private String eventId;
}
