package com.guavus.raf.compile.model;

import com.guavus.raf.compile.utils.ErrorCodes;
import org.springframework.http.HttpStatus;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.beans.Transient;
import java.util.Objects;

@SuppressWarnings("unused")
public class ServiceResponse<T> {

    private T response;
    private Integer httpStatusCode;
    private String errorCode;
    private String errorMessage;

    @Deprecated
    public ServiceResponse() {
    }

    public ServiceResponse(HttpStatus httpStatusCode, T response) {
        this.response = response;
        this.httpStatusCode = httpStatusCode.value();
    }

    public ServiceResponse(HttpStatus httpStatusCode, ErrorCodes errorCode) {
        this.httpStatusCode = httpStatusCode.value();
        this.errorMessage = errorCode.getDescription();
        this.errorCode = errorCode.getCode();
    }

    public ServiceResponse(HttpStatus httpStatusCode, ErrorCodes errorCode,T response) {
        this.response = response;
        this.httpStatusCode = httpStatusCode.value();
        this.errorMessage = errorCode.getDescription();
        this.errorCode = errorCode.getCode();
    }

    public ServiceResponse(HttpStatus httpStatusCode, String errorCode, String errorMessage) {
        this.httpStatusCode = httpStatusCode.value();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ServiceResponse(HttpStatus httpStatusCode,String errorCode, String errorMessage,T response) {
        this.response = response;
        this.httpStatusCode = httpStatusCode.value();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ServiceResponse(ServiceResponse serviceResponse) {
        this.httpStatusCode = serviceResponse.httpStatusCode;
        this.errorMessage = serviceResponse.getErrorMessage();
        this.errorCode = serviceResponse.getErrorCode();
    }

    public ServiceResponse(ServiceResponse serviceResponse,T response) {
        this.httpStatusCode = serviceResponse.httpStatusCode;
        this.errorMessage = serviceResponse.getErrorMessage();
        this.errorCode = serviceResponse.getErrorCode();
        this.response = response;
    }

    public ServiceResponse(T response, HttpStatus status){
        this.httpStatusCode = status.value();
        this.response = response;
    }



    public T getResponse() {
        return response;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Transient
    @JsonIgnore
    public boolean isError() {
        return ((Objects.nonNull(getErrorCode()) && !getErrorCode().isEmpty())
                || (Objects.nonNull(getErrorMessage()) && !getErrorMessage().isEmpty()));
    }

    @Transient
    @JsonIgnore
    public boolean isSuccess() {
        return !isError();
    }


}
