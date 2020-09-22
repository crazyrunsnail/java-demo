package person.davino.basic.exception;


class BusinessException extends RuntimeException{

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


public class CauseExceptionTest {

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception ex) {
            //throw new BusinessException(ex.getMessage()); // Exception in thread "main" person.davino.basic.exception.BusinessException: / by zero
            //throw new BusinessException(ex); // Caused by: java.lang.ArithmeticException: / by zero
            BusinessException cal_errro = new BusinessException("Cal errro");
            cal_errro.initCause(ex); // ex.getCause;;
            throw cal_errro;
        }
    }
}
