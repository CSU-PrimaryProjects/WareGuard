package com.school.manager.exception;

import com.school.manager.model.ResultType;
import lombok.Data;

public class BaseDataException extends RuntimeException {

    private String message;

    private int code;

    public BaseDataException(ResultType resultType)
    {
        this.message = resultType.getInfo();
        this.code = resultType.getCode();

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
