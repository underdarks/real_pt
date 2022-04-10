package health.real_pt.common;

import lombok.Builder;
import lombok.Data;

/**
 * 공통 Response DTO
 * @param <T>
 */
@Data
public class CommonResDto<T> {

    private int statusCode;         //응답 코드
    private String responseMsg;     //응답 메시지
    private T data;                 //응답 data

    public CommonResDto(final int statusCode, final String responseMsg) {
        this.statusCode = statusCode;
        this.responseMsg = responseMsg;
        this.data = null;
    }


    public static<T> CommonResDto<T> createResponse(final int statusCode, final String responseMsg){
        return createResponse(statusCode,responseMsg,null);
    }


    @Builder
    public CommonResDto(final int statusCode, final String responseMsg, final T data) {
        this.statusCode = statusCode;
        this.responseMsg = responseMsg;
        this.data = data;
    }

    public static<T> CommonResDto<T> createResponse(final int statusCode, final String responseMsg, final T data){
        return CommonResDto.<T>builder()
                .statusCode(statusCode)
                .responseMsg(responseMsg)
                .data(data)
                .build();
    }

}
