package com.staxter.error;

import java.io.Serializable;

/**
 * Created by serge on 6/5/2018.
 */
public class ErrorResponse implements Serializable {
    public String code;
    public String description;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
