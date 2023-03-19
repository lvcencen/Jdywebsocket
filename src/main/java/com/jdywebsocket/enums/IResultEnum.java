package com.jdywebsocket.enums;

/**
 * @author: lvChen
 * @date: 2022/9/13 11:14
 * @description: 方法裁剪
 */
public interface IResultEnum {

    /**
     * 查询返回结果的Code码
     *
     * @return 提示码
     */
    int getCode();


    /**
     * 查询返回结果的提示语
     *
     * @return 提示消息
     */
    String getMessage();
}
