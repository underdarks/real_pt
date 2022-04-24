# 들어가기전..

먼저, 각 API 기능들은 추후 앱과 통신을 위하여 설계 및 구현 되었습니다.<br>
여기서 API들의 Request, Response, 예외처리, 에러  대한 공통적인 부분에 대해서 설명할 것입니다.<br>
그리고 해당 문서는 지속적으로 최신화 진행 중 입니다.<br>

## API Request
API 요청에서는 Request Data, 연관관계가 있는 데이터를 전송하기 위해 **applicatioin/json** 방식을 사용하고 있으며, 파일 업로드가 필요한 경우 **multipart/form-data** 형식으로 파일과 데이터를 함께 전송하여 기본적인 기능을 제공합니다.<br><br>
기본적으로 REST API 가이드에 따라 URI 및 Http Method를 제공하고 있습니다. GET/POST/PATCH/DELETE Method를 제공하고 있으며,
계층 관계를 나타날 때에는 URI에 '/'를 사용하고 있으며 모든 URI 경로는 소문자를 준수하고 있습니다.


## API Response
API 응답에 대해서는 공통 Response를 이용하여 응답 하고 있으며, 아래는 공통 Resonse에 대한 설명과 예시입니다.

|필드|타입|설명|
|------|---|---|
|responseCode|Integer|API 호출 실행 결과 코드|
|responseMessage|String|API 응답 메시지|
|data|Object|API 응답 데이터(POST,DELETE Method 요청 시 data 필드 null 반환)|

<br>

예시 1) POST, DELETE 요청

![t](https://user-images.githubusercontent.com/41244406/164991685-3eff8243-1fb9-44e9-b091-26f9cd4bdc39.PNG)


예시 2) GET, PATCH 요청

![t2](https://user-images.githubusercontent.com/41244406/164991804-1366b52d-c624-4830-9a4f-5964c9d334b3.PNG)


<details>
  <summary>공통 Response 코드</summary>
  
~~~java
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

    public CommonResEntity(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }


    public static<T> CommonResEntity<T> createResponse(final int statusCode, final String responseMsg){
        return createResponse(statusCode,responseMsg,null);
    }


    @Builder
    public CommonResEntity(final int statusCode, final String responseMessage, final T data) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static<T> CommonResEntity<T> createResponse(final int statusCode, final String responseMsg, final T data){
        return CommonResEntity.<T>builder()
                .statusCode(statusCode)
                .responseMessage(responseMsg)
                .data(data)
                .build();
    }

}
~~~
</details>
<br>
  
  
## 공통 Error
 API 호출이 실패한 경우, 공통 Error Response를 이용하여 에러에 대한 상세 정보를 반환합니다.
  
  
|필드|타입|설명|
|------|---|---|
|errorCode|String|오류 상황을 구분하는 코드|
|errorMessage|String|오류 메시지|
|detail|String|오류 상세 원인|

<br>

현재 오류 상황을 구분하는 코드들은 아래와 같으며, 지속적으로 개선 및 추가할 예정입니다.<br>
~~~ java
    /**
     *                      Rule
     *
     * ErrorCode  = E + HTTP 응답코드 + 시퀀스(001 ~ 999)
     */

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST,"E0001","dsd"),
    ENTITY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"E404002","엔티티를 찾을 수 없습니다."),
    DUPLICATE_KEY_EXCEPTION(HttpStatus.BAD_REQUEST,"E400001","중복된 값이 존재합니다."),
    FILE_UPLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500001","파일 업로드에 실패하였습니다"),
    FILE_DOWNLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500002","파일 다운로드에 실패하였습니다"),
    PARAMETER_VALUE_ILLEGAL(HttpStatus.BAD_REQUEST,"E400002","인자값이 부적절합니다."),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST,"E404001","로그인에 실패하였습니다.")
~~~
  
예시)

![t3](https://user-images.githubusercontent.com/41244406/164992274-944b0c7e-8a16-4a17-a2a7-33b0570039a0.PNG)

<details>
  <summary>Error 처리 코드</summary>
  

공통 에러 응답 엔티티
  
~~~java
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
    private String errorMessage;  //에러 메시지
    private String detail;        //상세 오류

    @Builder
    public ErrorResponse(String errorCode, String errorMessage, String detail) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.detail = detail;
    }
}

~~~
<br>
  

~~~ java
    public class CommonApiExceptions extends RuntimeException {

    private ExceptionType error;
    private String detail;

    @Builder
    public CommonApiExceptions(ExceptionType error, String detail) {
        this.error=error;
        this.detail=detail;
    }
}
  
~~~
<br>  

공통 오류 코드 타입
~~~ java
  package health.real_pt.exception.exception_handler;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 공통 오류 코드 타입
 */
@Getter
@ToString
public enum ExceptionType {

    /**
     *                      Rule
     *
     * ErrorCode  = E + HTTP 응답코드 + 시퀀스(001 ~ 999)
     */

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST,"E0001","dsd"),
    ENTITY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"E404002","엔티티를 찾을 수 없습니다."),
    DUPLICATE_KEY_EXCEPTION(HttpStatus.BAD_REQUEST,"E400001","중복된 값이 존재합니다."),
    FILE_UPLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500001","파일 업로드에 실패하였습니다"),
    FILE_DOWNLOAD_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"E500002","파일 다운로드에 실패하였습니다"),
    PARAMETER_VALUE_ILLEGAL(HttpStatus.BAD_REQUEST,"E400002","인자값이 부적절합니다."),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST,"E404001","로그인에 실패하였습니다.")


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

~~~
<br>  
  
예외처리 
~~~ java
  package health.real_pt.exception.exception_handler;

import health.real_pt.exception.exceptions.CommonApiExceptions;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 공통 예외처리 핸들러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 공통 예외 핸들러
     */
    @ExceptionHandler(value = {CommonApiExceptions.class})
    public ResponseEntity<ErrorResponse> commonApiExceptions(final CommonApiExceptions e){

//        System.out.println("e.getMessage() = " + e.getMessage());
//        System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getError().getCode())
                                .errorMessage(e.getError().getMessage())
                                .detail(e.getDetail())
                                .build()
                );
    }

    /**
     *  중복 예외 처리
     */
    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<ErrorResponse> duplicateKeyException(final DuplicateKeyException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(ExceptionType.DUPLICATE_KEY_EXCEPTION.getCode())
                                .errorMessage(ExceptionType.DUPLICATE_KEY_EXCEPTION.getMessage())
                                .detail(e.getMessage())
                                .build()
                );

    }

    /**
     *
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> IllegalArgumentException(final IllegalArgumentException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(ExceptionType.PARAMETER_VALUE_ILLEGAL.getCode())
                                .errorMessage(ExceptionType.PARAMETER_VALUE_ILLEGAL.getMessage())
                                .detail(e.getMessage())
                                .build()
                );

    }


}

  
~~~
  
</details>
  
