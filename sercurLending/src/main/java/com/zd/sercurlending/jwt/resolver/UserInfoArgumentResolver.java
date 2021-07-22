package com.zd.sercurlending.jwt.resolver;


import com.zd.sercurlending.bean.UserInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author meko
 * @version 1.0
 * @description: TODO
 * @date 2021/7/2 4:39 下午
 */

public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == UserInfo.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        UserInfo userInfoVo = new UserInfo();
        final Object loginInfo = nativeWebRequest.getAttribute("loginInfo", 0);
        if (loginInfo != null){
            return loginInfo;
        }
        return userInfoVo;
    }

}
