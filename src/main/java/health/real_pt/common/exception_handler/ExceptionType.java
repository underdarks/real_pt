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
    ENTITY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"E404","엔티티를 찾을 수 없습니다."),
    DUPLICATE_KEY_EXCEPTION(HttpStatus.BAD_REQUEST,"E400","중복된 값이 존재합니다."),
    FILE_UPLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500","파일 업로드에 실패하였습니다"),
    FILE_DOWNLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500","파일 다운로드에 실패하였습니다"),


    ;

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
