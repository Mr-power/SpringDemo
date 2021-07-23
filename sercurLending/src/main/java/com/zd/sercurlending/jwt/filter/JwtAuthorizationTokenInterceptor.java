package com.zd.sercurlending.jwt.filter;


import com.alibaba.fastjson.JSON;
import com.da.ipo_server.bean.CommonConst;
import com.da.ipo_server.exception.ServiceException;
import com.da.ipo_server.jwt.annotation.Token;
import com.da.ipo_server.jwt.util.JwtTokenUtil;
import com.da.ipo_server.model.UserInfoVo;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @description: TODO Jwt权限验证拦截器
 * @author Meko
 * @date 2021/6/24 10:45 上午
 * @version 1.0
 */
@Slf4j
public class JwtAuthorizationTokenInterceptor extends HandlerInterceptorAdapter {

    private final JwtTokenUtil jwtTokenUtil;


    public JwtAuthorizationTokenInterceptor( JwtTokenUtil jwtTokenUtil) {

        this.jwtTokenUtil = jwtTokenUtil;

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod && verificationJwt((HandlerMethod) handler)){
            final String header = jwtTokenUtil.getHeader();
            final String requestHeader = request.getHeader(header);
            final String tokenHeader = jwtTokenUtil.getTokenHeader();
            //需要验证并且无需登陆也能访问
            if (mustVerificationJwt((HandlerMethod) handler,requestHeader)){
                return true;
            }
            String authToken;
            UserInfoVo jwtUser;
            if (requestHeader != null && requestHeader.startsWith(tokenHeader)) {
                authToken = requestHeader.substring(tokenHeader.length() + 1);
                try {
                    jwtUser = JSON.parseObject(JSON.toJSONString(jwtTokenUtil.getAllClaimsFromToken(authToken).get("loginInfo")), UserInfoVo.class);
                } catch (Exception e) {
                    if (e instanceof ExpiredJwtException){
                        throw new ServiceException(CommonConst.LOGIN_HAS_EXPIRED);
                    }else {
                        throw new ServiceException(CommonConst.PARAMS_VALID_ERR);
                    }
                }
                if (jwtUser == null){
                    throw new ServiceException(CommonConst.CHECK_FAULT);
                }
                request.setAttribute("loginInfo",jwtUser);
            }else {
                throw new ServiceException(CommonConst.FAIL);
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    private boolean verificationJwt(HandlerMethod method){
        final Token checkJwt = method.getMethodAnnotation(Token.class);
        if( checkJwt != null){
           return true;
       }
        return false;
    }

    private boolean mustVerificationJwt(HandlerMethod method,String requestHeader){
        if (requestHeader == null){
            requestHeader = "";
        }
        final Token checkJwt = method.getMethodAnnotation(Token.class);
        if(!checkJwt.mustCheck() && "".equals(requestHeader)){
            return true;
        }
        return false;
    }
}
