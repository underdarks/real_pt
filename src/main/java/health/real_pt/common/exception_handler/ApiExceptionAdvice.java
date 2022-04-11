package health.real_pt.common.exception_handler;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(value = {InvalidConfigurationPropertyValueException.class})
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionEntity> resourceNotFoundException(final InvalidConfigurationPropertyValueException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiExceptionEntity.builder()
                                .errorCode(ExceptionEnum.RESOURCE_NOT_FOUND_EXCEPTION.getCode())
                                .errorMessage(ExceptionEnum.RESOURCE_NOT_FOUND_EXCEPTION.getMessage())
                                .build()
                );
    }

}
