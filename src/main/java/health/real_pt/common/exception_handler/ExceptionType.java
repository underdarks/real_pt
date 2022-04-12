package health.real_pt.common.exception_handler;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 공통 오류 코드 타입
 */
@Getter
@ToString
public enum ExceptionType {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST,"E0001","dsd"),
    NO_SUCH_ELEMENT_EXCEPTION(HttpStatus.NOT_FOUND,"404","객체를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionType(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionType(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
