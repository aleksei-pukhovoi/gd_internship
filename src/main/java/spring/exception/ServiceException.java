package spring.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import spring.exception.errorCode.ErrorCode;

import java.time.Instant;
import java.util.Objects;

@JsonPropertyOrder({"code", "datetime", "message"})
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed", "errorCode"})
public class ServiceException extends RuntimeException{

    private static final String ERROR_CODE_FORMAT = "%s - %d";

    private final ErrorCode errorCode;

    private final Instant dateTime;

    private final String code;

    public ServiceException(ErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        code = getExceptionCode();
        dateTime = Instant.now();
    }

    private String getExceptionCode() {
        return String.format(ERROR_CODE_FORMAT, errorCode.getClass().getSimpleName(), getCodeNumber());
    }

    private int getCodeNumber() {
        return Objects.nonNull(errorCode) ? errorCode.getNumber() : 0;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public String getCode() {
        return code;
    }
}
