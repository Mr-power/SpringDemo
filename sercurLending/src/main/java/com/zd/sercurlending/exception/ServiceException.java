package com.zd.sercurlending.exception;

import com.zd.sercurlending.bean.EnumDescribable;

public class ServiceException extends Throwable {

    private EnumDescribable describable;
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(EnumDescribable describable) {
        this.describable = describable;
    }

    public ServiceException(String message, EnumDescribable describable) {
        super(message);
        this.describable = describable;
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public EnumDescribable getDescribable() {
        return describable;
    }

    public void setDescribable(EnumDescribable describable) {
        this.describable = describable;
    }
}
