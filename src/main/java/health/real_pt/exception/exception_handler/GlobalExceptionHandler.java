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
