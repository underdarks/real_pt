package health.real_pt.exception.exception_handler;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * 공통 에러 응답을 위한 엔티티(통일성 유지)
 *
 */
@Getter
@ToString
public class ErrorResponse {

    private String errorCode;     //에러 코드
    private String errorMessage;       //에러 메시지
    private String detail;        //상세 오류

    @Builder
    public ErrorResponse(String errorCode, String errorMessage, String detail) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.detail = detail;
    }
}
