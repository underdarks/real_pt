package health.real_pt.common.exception_handler;


import lombok.Getter;

//공통 예외 처리 핸들러
@Getter
public class APIException extends RuntimeException{
    private ExceptionEnum error;

    public APIException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }
}
