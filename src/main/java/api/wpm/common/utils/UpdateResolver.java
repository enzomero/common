package api.wpm.common.utils;

import api.wpm.common.domain.TelegramRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateResolver {
    private static final String CALLMERAUS = "callmeraus";

    public static final String REGEX_SCRY_FALL_LINKS = "^https:\\/\\/scryfall.com\\/card\\/\\w{3}\\/\\d{1,4}\\/.+(\\s|)";
    public static final String REGEX_REPLICATOR = "^([\\/][p]\\s|\\.\\.\\s*)";
    private static final Pattern PATTERN = Pattern
            .compile("([1-60]*[ A-Za-z]*['s]?[-, ]?([A-Z]*[a-z]*['s]?[-, \\/\\/]?)*[Sideboard]?[:]?\\n?){10,100}",
                    Pattern.MULTILINE
            );

    public static boolean isPriceRequest(final Optional<TelegramRequest> request) {
        return request
                .map(TelegramRequest::getRequestBody)
                .filter(s -> Pattern.compile(REGEX_REPLICATOR).matcher(s).find())
                .isPresent();
    }

    public static Optional<TelegramRequest> getRequest(final Update update) {
        final Long updateId = Optional.ofNullable(update.getMessage())
                .map(Message::getChatId)
                .orElseGet(() -> update.getCallbackQuery().getMessage().getChatId());

        final Integer inlineId = Optional.ofNullable(update.getMessage())
                .map(Message::getMessageId)
                .orElseGet(() -> update.getCallbackQuery().getMessage().getMessageId());

        final Boolean aBoolean = Optional.ofNullable(update.getMessage())
                .map(Message::getFrom)
                .map(User::getIsBot)
                .orElse(true);

        return List.of(getMsgText(update), getCallBack(update))
                .stream()
                .filter(Optional::isPresent)
                .map(s -> new TelegramRequest(s.get().toLowerCase(), String.valueOf(updateId), String.valueOf(inlineId), aBoolean))
                .findFirst();
    }

    public static boolean isMyIdRequest(final Update update) {
        return getMsgText(update)
                .filter(s -> s.contains("/myId"))
                .isPresent() && hasPermissions(update);
    }

    public static boolean isCsvLoad(Update update) {
        return Optional.of(update)
                .filter(Update::hasMessage)
                .map(Update::getMessage)
                .filter(Message::hasDocument)
                .map(Message::getDocument)
                .map(Document::getMimeType)
                .filter(document -> document.equalsIgnoreCase("text/csv") || document.equalsIgnoreCase("text/comma-separated-values"))
                .isPresent();
    }

    public static boolean isImage(Update update) {
        return Optional.of(update)
                .filter(Update::hasMessage)
                .filter(update1 -> !update.getMessage().getFrom().getIsBot())
                .map(Update::getMessage)
                .filter(Message::hasPhoto)
                .map(Message::getPhoto)
                .isPresent();
    }

    public static boolean hasPermissions(final Update update) {
        return Optional.of(update).
                filter(Update::hasMessage)
                .map(Update::getMessage)
                .map(Message::getFrom)
                .map(User::getUserName)
                .filter(s -> s.equals(CALLMERAUS))
                .isPresent();
    }

    public static Optional<String> getCommandAndText(final Update update, final String regex) {
        return getMsgText(update)
                .filter(msg -> msg.matches(regex));
    }

    public static boolean isMarket(final Update update) {
        return getMsgText(update)
                .filter(message -> message.equals("market"))
                .isPresent();
    }

    public static boolean isScryFallLink(final Update update) {
        return getMsgText(update)
                .filter(message -> message.matches(REGEX_SCRY_FALL_LINKS))
                .isPresent();
    }

    private static Optional<String> getMsgText(final Update upd) {
        return Optional.of(upd)
                .filter(Update::hasMessage)
                .map(Update::getMessage)
                .filter(Message::hasText)
                .map(Message::getText);
    }

    private static Optional<String> getCallBack(final Update upd) {
        return Optional.ofNullable(upd)
                .filter(Update::hasCallbackQuery)
                .map(Update::getCallbackQuery)
                .map(CallbackQuery::getData);
    }

    public static boolean isCallbackData(final Update upd, final String want) {
        return Optional.of(upd)
                .map(Update::getCallbackQuery)
                .map(CallbackQuery::getData)
                .filter(s -> s.equals(want))
                .isPresent();
    }

    public static boolean isDeckCheck(final Update update) {
        return Optional.ofNullable(update.getMessage())
                .filter(message -> message.getChat().isUserChat())
                .map(Message::getText)
                .map(s -> PATTERN.matcher(s).matches())
                .orElse(false);
    }
}
