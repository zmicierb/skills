package com.barysevich.project.controller.dto;

import org.springframework.context.MessageSource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Locale;

/**
 * Created by BarysevichD on 2017-03-15.
 */
public class Response<T> {

    private static String SUCCESS_TEXT = "Completed successfully";
    private Boolean success;
    private String message;
    private MultiValueMap<String, String> errors;
    private Long total;
    private T data;

    private Response() {
    }

    public static Response<Object> error(Errors result, MessageSource ms, Locale locale) {
        Response<Object> r = new Response<Object>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<String, String>();

        if (result.hasGlobalErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe : result.getGlobalErrors()) {
                sb.append(ms.getMessage(oe, locale)).append(" ");
            }
            r.setMessage(sb.toString());
        }
        for (FieldError fe : result.getFieldErrors()) {
            errors.add(fe.getField(), ms.getMessage(fe, locale));
        }
        r.setErrors(errors);
        return r;
    }

    public static Response<Object> error(Errors result) {
        Response<Object> r = new Response<Object>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<String, String>();

        if (result.hasGlobalErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe : result.getGlobalErrors()) {
                sb.append(oe.getDefaultMessage()).append(" ");
            }
            r.setMessage(sb.toString());
        }
        for (FieldError fe : result.getFieldErrors()) {
            errors.add(fe.getField(), fe.getDefaultMessage());
        }
        r.setErrors(errors);
        return r;
    }

    public static <T> Response<T> success(T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    public static <T> Response<T> success(T data, Long total) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setTotal(total);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    public static <T> Response<T> error(T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(false);
        r.setData(data);
        return r;
    }

    public static <T> Response<T> success(T data, String message) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setMessage(message == null ? SUCCESS_TEXT : message);
        return r;
    }

    public static <T> Response<T> error(T data, String message) {
        Response<T> r = new Response<T>();
        r.setSuccess(false);
        r.setData(data);
        r.setMessage(message);
        return r;
    }

    public static Response success(String msg) {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(msg);
        return r;
    }

    public static Response error(String msg) {
        Response r = new Response<Object>();
        r.setSuccess(false);
        r.setMessage(msg);
        return r;
    }

    public static Response success() {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultiValueMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(MultiValueMap<String, String> errors) {
        this.errors = errors;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
