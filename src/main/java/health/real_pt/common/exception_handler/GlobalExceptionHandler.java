package health.real_pt.common.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * 공통 예외처리 핸들러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 조회 시 엔티티를 찾지 못햇 을 때 발생하는 예외 처리
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> noSuchElementException(final NoSuchElementException e){

//        System.out.println("e.getMessage() = " + e.getMessage());
//        System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .errorCode(ExceptionType.NO_SUCH_ELEMENT_EXCEPTION.getCode())
                                .errorMessage(ExceptionType.NO_SUCH_ELEMENT_EXCEPTION.getMessage())
                                .errorTarget(e.getMessage())
                                .build()
                );
    }

}
