package health.real_pt.common.exception_handler;

import health.real_pt.common.exceptions.EntityNotFoundException;
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
     * 조회 시 엔티티를 찾지 못햇 을 때 발생하는 예외 처리
     */
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(final EntityNotFoundException e){

//        System.out.println("e.getMessage() = " + e.getMessage());
//        System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .error(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION.getCode())
                                .message(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION.getMessage())
                                .detail()
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
                                .error(ExceptionType.DUPLICATE_KEY_EXCEPTION.getCode())
                                .message(ExceptionType.DUPLICATE_KEY_EXCEPTION.getMessage())
                                .detail(e.getMessage())
                                .build()
                );

    }

}
