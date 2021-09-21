package api.wpm.common.api;

import api.wpm.common.domain.MutateMessage;
import api.wpm.common.domain.TelegramRequest;

import reactor.core.publisher.Flux;

public interface CommonPublisher {
    Flux<MutateMessage> send(final TelegramRequest request);
}
