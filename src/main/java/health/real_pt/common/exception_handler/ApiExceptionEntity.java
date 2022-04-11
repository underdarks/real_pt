package health.real_pt.common.exception_handler;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * 공통 예외 엔티티(일관성 유지)
 */
@Getter
@ToString
public class ApiExceptionEntity {

    private String errorCode;
    private String errorMessage;

    @Builder
    public ApiExceptionEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
