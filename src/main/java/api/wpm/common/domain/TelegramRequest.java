package api.wpm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TelegramRequest{
        String requestBody;
        String chatId;
        String messageId;
        boolean isBot;
}
