package com.unimayor.calendar_uni_service.core.exeption;

/**
 * Class to control business controlled errors
 *
 * @author Brayan Alexis Alvarado
 * @version 1.0
 * @since 2024-11-20
 */
public class BusinessException extends RuntimeException {
    // Constructor sin mensaje ni causa
    public BusinessException() {
        super();
    }

    // Constructor con mensaje
    public BusinessException(String message) {
        super(message);
    }

    // Constructor con mensaje y causa
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con causa
    public BusinessException(Throwable cause) {
        super(cause);
    }
}
