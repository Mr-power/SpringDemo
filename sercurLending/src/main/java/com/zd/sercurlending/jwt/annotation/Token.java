package com.zd.sercurlending.jwt.annotation;

import java.lang.annotation.*;

/**
 * @description: TODO 是否必须验证Jwt
 * @author Meko
 * @date 2021/7/9 2:28 下午
 * @version 1.0
 */

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    String value() default "";
    boolean mustCheck() default true;
}
