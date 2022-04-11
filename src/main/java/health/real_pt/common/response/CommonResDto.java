package health.real_pt.common.response;

import lombok.Builder;
import lombok.Data;

/**
 * 공통 Response DTO
 * @param <T>
 */
@Data
public class CommonResDto<T> {

    private int statusCode;             //응답 코드
    private String responseMessage;     //응답 메시지
    private T data;                     //응답 data

    public CommonResDto(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }


    public static<T> CommonResDto<T> createResponse(final int statusCode, final String responseMsg){
        return createResponse(statusCode,responseMsg,null);
    }


    @Builder
    public CommonResDto(final int statusCode, final String responseMessage, final T data) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static<T> CommonResDto<T> createResponse(final int statusCode, final String responseMsg, final T data){
        return CommonResDto.<T>builder()
                .statusCode(statusCode)
                .responseMessage(responseMsg)
                .data(data)
                .build();
    }

}
