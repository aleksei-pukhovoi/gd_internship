package spring.exception.errorCode;

import org.springframework.http.HttpStatus;

public enum UserServiceErrorCode implements ErrorCode{

    USER_NOT_EXIST("User doesn't exist in database", HttpStatus.BAD_REQUEST);

    private final int number = ordinal() + 1;

    private final String message;

    private final HttpStatus httpStatus;

    UserServiceErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
