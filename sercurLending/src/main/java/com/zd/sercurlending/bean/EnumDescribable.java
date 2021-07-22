package com.zd.sercurlending.bean;



import java.io.Serializable;

/**
 * 错误码描述接口
 *
 * <p>File：ErrorCodeDescribable.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014年3月6日 下午3:06:33</p>
 * @author vanilla
 * @version 1.0
 */
public interface EnumDescribable extends Serializable
{
    /**
     * 获取异常代码
     * @return java.lang.Integer
     * @author vanilla
     */
    Integer getCode();

    /**
     * 获取异常代码描述
     * @return java.lang.String
     * @author vanilla
     */
    String getMessage();
}