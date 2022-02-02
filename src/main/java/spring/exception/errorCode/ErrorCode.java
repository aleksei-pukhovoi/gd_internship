package spring.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    int getNumber();

    String getMessage();

    HttpStatus getHttpStatus();
}
