package health.real_pt.common.exceptions;


import health.real_pt.common.exception_handler.ExceptionType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class EntityNotFoundException extends BaseException {

    @Builder
    public EntityNotFoundException(ExceptionType error, String detail) {
        this.error=error;
        this.detail=detail;
    }
}
