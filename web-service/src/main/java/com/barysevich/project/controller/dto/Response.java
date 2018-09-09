package com.barysevich.project.controller.dto;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Locale;

public class Response<T> {

    private static final String SUCCESS_TEXT = "Completed successfully";
    private Boolean success;
    private String message;
    private MultiValueMap<String, String> errors;
    private T data;
    private boolean first;
    private boolean last;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private int numberOfElements;
    private Sort sort;

    private Response()
    {
    }

    public static Response<Object> error(final Errors result, final MessageSource ms, final Locale locale)
    {
        Response<Object> r = new Response<>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<>();

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

    public static Response<Object> error(final Errors result)
    {
        Response<Object> r = new Response<>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<>();

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

    public static <T> Response<T> success(final T data)
    {
        Response<T> r = new Response<>();
        r.setSuccess(true);
        r.setMessage(SUCCESS_TEXT);
        r.setDataForPageable(data);
        return r;
    }

    public static <T> Response<T> error(final T data)
    {
        Response<T> r = new Response<>();
        r.setSuccess(false);
        r.setDataForPageable(data);
        return r;
    }

    public static <T> Response<T> success(final T data, final String message)
    {
        Response<T> r = new Response<>();
        r.setSuccess(true);
        r.setMessage(message == null ? SUCCESS_TEXT : message);
        r.setDataForPageable(data);
        return r;
    }

    public static <T> Response<T> error(final T data, final String message)
    {
        Response<T> r = new Response<>();
        r.setSuccess(false);
        r.setMessage(message);
        r.setDataForPageable(data);
        return r;
    }

    public static Response success(final String msg)
    {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(msg);
        return r;
    }

    public static Response error(final String msg)
    {
        Response r = new Response<Object>();
        r.setSuccess(false);
        r.setMessage(msg);
        return r;
    }

    public static Response success()
    {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    private void setDataForPageable(final T data)
    {
        if (data instanceof Page) {
            this.setData((T) ((Page) data).getContent());
            this.setFirst(((Page) data).isFirst());
            this.setLast(((Page) data).isLast());
            this.setTotalPages(((Page) data).getTotalPages());
            this.setTotalElements(((Page) data).getTotalElements());
            this.setSize(((Page) data).getSize());
            this.setNumber(((Page) data).getNumber());
            this.setNumberOfElements(((Page) data).getNumberOfElements());
            this.setSort(((Page) data).getSort());
        } else {
            this.setData(data);
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public MultiValueMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(final MultiValueMap<String, String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(final boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(final boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(final int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(final Sort sort) {
        this.sort = sort;
    }
}
