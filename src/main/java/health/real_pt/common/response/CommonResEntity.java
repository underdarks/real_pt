package health.real_pt.common.response;

import lombok.Builder;
import lombok.Data;

/**
 * 공통 Response DTO
 * @param <T>
 */
@Data
public class CommonResEntity<T> {

    private int responseCode;             //응답 코드
    private String responseMessage;     //응답 메시지
    private T data;                     //응답 data

    public CommonResEntity(final int responseCode, final String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }


    public static<T> CommonResEntity<T> createResponse(final int statusCode, final String responseMsg){
        return createResponse(statusCode,responseMsg,null);
    }


    @Builder
    public CommonResEntity(final int responseCode, final String responseMessage, final T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static<T> CommonResEntity<T> createResponse(final int statusCode, final String responseMsg, final T data){
        return CommonResEntity.<T>builder()
                .responseCode(statusCode)
                .responseMessage(responseMsg)
                .data(data)
                .build();
    }

}
