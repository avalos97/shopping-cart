package com.pack.shopping.cart.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public CustomerNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public CustomerNotFoundException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.CUSTOMER_NOT_FOUND.getErrCode();
    }
}
