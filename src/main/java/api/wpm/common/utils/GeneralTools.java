package api.wpm.common.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralTools {
    public static final Gson GSON = new Gson();
    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
}
