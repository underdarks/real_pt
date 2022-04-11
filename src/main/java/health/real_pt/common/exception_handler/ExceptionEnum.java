package health.real_pt.common.exception_handler;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 공통 오류 메시지 enum class
 */
@Getter
@ToString
public enum ExceptionEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST,"E0001",""),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED,)


        





    private final HttpStatus status;
    private final String code;
    private String message;

    public ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    public ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
