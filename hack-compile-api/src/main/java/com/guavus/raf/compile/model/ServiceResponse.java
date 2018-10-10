package com.guavus.raf.compile.model;

import org.springframework.http.HttpStatus;

public class ServiceResponse<T> {
    private T response;
    private String error;
    private String status;

    public ServiceResponse(T response, HttpStatus status) {
        this.response = response;
        this.status = status.toString();
    }

    public ServiceResponse(HttpStatus status, String error) {
        this.error = error;
        this.status = status.toString();
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
