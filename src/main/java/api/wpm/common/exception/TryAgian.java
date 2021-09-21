package api.wpm.common.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TryAgian extends RuntimeException {
    public TryAgian(final String message) {
        super(message);
        log.error(message);
    }
}
