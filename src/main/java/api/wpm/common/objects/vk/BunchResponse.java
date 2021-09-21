package api.wpm.common.objects.vk;

import lombok.Data;

import java.util.List;

@Data
public class BunchResponse<T> {
    private long count;
    private long viewType;
    private List<T> items;
}
