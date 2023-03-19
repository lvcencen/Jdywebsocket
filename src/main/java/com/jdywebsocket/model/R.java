package com.jdywebsocket.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.jdywebsocket.enums.IResultEnum;
import com.jdywebsocket.enums.ResultEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author: lvChen
 * @date: 2022/9/13 11:14
 * @description: 简化返回信息，使用泛型
 */

public class R<T> implements Serializable {
    private static final long serialVersionUID = 669L;

    private int code;

    private boolean success = false;

    @JsonAlias("message")
    private String msg;

    @JsonAlias({"result", "retVal"})
    private T data;

    /**
     * 使用ok和error方法，此方法只是为了Spring反序列化使用
     */
    public R() {
    }

    private R(IResultEnum resultEnum) {
        this.setResult(resultEnum);
    }

    private R(IResultEnum resultEnum, T data) {
        this.setResult(resultEnum);
        this.setData(data);
    }

    private R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 从源复制返回代码，消息，成功标志
     *
     * @param source 源
     * @param <T>    泛型
     * @return 复制了code，msg，success的R
     */
    public static <T> R<T> copy(R<?> source) {
        R<T> r = new R<>();
        r.setCode(source.getCode());
        r.setMsg(source.getMsg());
        r.setSuccess(source.isSuccess());
        return r;
    }

    /**
     * 生成无返回对象成功返回值，使用ResultEnum.SUCCESS
     *
     * @param <T> 泛型
     * @return 成功返回值
     */
    public static <T> R<T> ok() {
        return new R<>(ResultEnum.SUCCESS);
    }

    /**
     * 设置数据.
     * 如果数据为NULL或为空，则设置状态码为无数据，
     * 如果有数据，设置为成功
     *
     * @param data 数据,null时等于R.error(ResultEnum.NO_DATA)
     * @param <T>  泛型
     * @return 成功返回值
     */
    public static <T> R<T> ok(T data) {
        R<T> result;
        if (isEmpty(data)) {
            result = new R<>(ResultEnum.NO_DATA);
        } else {
            result = new R<>(ResultEnum.SUCCESS, data);
        }
        return result;
    }

    /**
     * 忽略IResultEnum是否为SUCCESS，强行将success字段设置为true。
     * code和msg设置为IResultEnum对应值。
     * data设置为data。
     *
     * @param resultEnum 状态枚举
     * @param data       数据
     * @param <T>        泛型
     * @return 成功返回值
     */
    public static <T> R<T> ok(IResultEnum resultEnum, T data) {
        R<T> result = new R<>();
        result.setSuccess(true);
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 生成失败返回结果，使用<code>msg</code>设置失败消息
     *
     * @param msg 提示消息
     * @param <T> 泛型
     * @return 失败返回值
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(ResultEnum.FAST_FAIL.getCode(), msg);
    }

    /**
     * 生成失败返回结果。
     * 使用<code>msg</code>设置失败消息
     * 使用<code>data</code>设置返回值
     *
     * @param msg  提示消息
     * @param data 错误返回数据
     * @param <T>  泛型
     * @return 失败返回值
     */
    public static <T> R<T> fail(String msg, T data) {
        return new R<>(ResultEnum.FAST_FAIL.getCode(), msg, data);
    }

    /**
     * 忽略IResultEnum是否为SUCCESS，强行将success字段设置为false。
     * code和msg设置为IResultEnum对应值。
     * data设置为data。
     *
     * @param resultEnum 状态枚举
     * @param data       数据
     * @param <T>        泛型
     * @return 失败返回值
     */
    public static <T> R<T> fail(IResultEnum resultEnum, T data) {
        R<T> result = new R<>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        result.setData(data);
        return result;
    }


    /**
     * 根据resultEnum创建一个返回实例，成功和失败根据resultEnum来决定。
     * 当IResultEnum为ResultEnum.SUCCESS时成功，其他失败
     *
     * @param resultEnum IResultEnum实现
     * @return 返回值
     */
    public static <T> R<T> instance(IResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    /**
     * 判断数据是否为空。可判断对象，集合，map
     *
     * @param data 数据
     * @return true-空数据
     */
    public static <T> boolean isEmpty(T data) {
        boolean empty = null == data;
        if (!empty) {
            if (data instanceof Collection) {
                empty = ((Collection<?>) data).isEmpty();
            } else if (data instanceof Map) {
                empty = ((Map<?, ?>) data).isEmpty();
            }
        }
        return empty;
    }

    /**
     * 设置返回码和是否成功以及提示消息
     *
     * @param resultEnum ResultEnum
     */
    private void setResult(IResultEnum resultEnum) {
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            this.setSuccess(true);
        }
        this.setCode(resultEnum.getCode());
        this.setMsg(resultEnum.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
