package api.wpm.common.domain;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;

@Data
public class MutateMessage {
    private long id;
    private SendMediaGroup mediaGroup;
    private SendMessage sendMessage;
    private SendChatAction sendChatAction;
    private DeleteMessage deleteMessage;
    private SendPhoto sendPhoto;
    private SendDocument sendDocument;
    private EditMessageText editMessageText;
    private EditMessageReplyMarkup editMessageReplyMarkup;
    private EditMessageCaption editMessageCaption;
    private EditMessageMedia editMessageMedia;

    public <T extends PartialBotApiMethod> MutateMessage(T message){
        id = System.currentTimeMillis();
        if (message instanceof SendMediaGroup)
            this.mediaGroup = (SendMediaGroup) message;
        if (message instanceof SendMessage)
            this.sendMessage = (SendMessage) message;
        if (message instanceof EditMessageText)
            this.editMessageText = (EditMessageText) message;
        if (message instanceof SendChatAction)
            this.sendChatAction = (SendChatAction) message;
        if (message instanceof DeleteMessage)
            this.deleteMessage = (DeleteMessage) message;
        if (message instanceof SendPhoto)
            this.sendPhoto = (SendPhoto) message;
        if (message instanceof EditMessageReplyMarkup)
            this.editMessageReplyMarkup = (EditMessageReplyMarkup) message;
        if (message instanceof EditMessageCaption)
            this.editMessageCaption = (EditMessageCaption) message;
        if (message instanceof SendDocument)
            this.sendDocument = (SendDocument) message;
        if (message instanceof EditMessageMedia)
            this.editMessageMedia = (EditMessageMedia) message;
    }
}
