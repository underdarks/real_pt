package health.real_pt.common.exceptions;

import health.real_pt.common.exception_handler.ExceptionType;

public abstract class BaseException extends RuntimeException {

    ExceptionType error;
    String detail;

}
