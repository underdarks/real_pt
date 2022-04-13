package health.real_pt.common.exceptions;


import health.real_pt.common.exception_handler.ExceptionType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CommonApiExceptions extends RuntimeException {

    private ExceptionType error;
    private String detail;

    @Builder
    public CommonApiExceptions(ExceptionType error, String detail) {
        this.error=error;
        this.detail=detail;
    }
}
