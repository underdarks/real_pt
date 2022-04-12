package health.real_pt.common.exception_handler;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * 공통 예외 엔티티(일관성 유지)
 * 에러 응답
 */
@Getter
@ToString
public class ErrorResponse {

    private String errorCode;       //에러 코드
    private String errorMessage;    //에러 메시지
    private String errorTarget;     //에러 원인 대상자

    @Builder
    public ErrorResponse(String errorCode, String errorMessage, String errorTarget) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTarget = errorTarget;
    }
}
