package health.real_pt.common.exception_handler;


import lombok.Getter;


@Getter
public class APIException extends RuntimeException{
    private ExceptionType error;

    public APIException(ExceptionType error) {
        super(error.getMessage());
        this.error = error;
    }
}
