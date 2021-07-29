package com.zd.sercurlending.jwt.controller;


import com.zd.sercurlending.bean.CommonConst;
import com.zd.sercurlending.bean.UserInfo;
import com.zd.sercurlending.exception.ServiceException;
import com.zd.sercurlending.jwt.annotation.Token;
import com.zd.sercurlending.service.ProfitService;
import com.zd.sercurlending.service.ProfitServiceImp;
import com.zd.sercurlending.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import com.zd.sercurlending.bean.LoginInfo;
import com.zd.sercurlending.bean.Result;
import com.zd.sercurlending.jwt.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author meko
 * @version 1.0
 * @description: TODO
 * @date 2021/6/23 4:41 下午
 */

@Api(tags = {"Jwt Token获取  /jwt"})
@Slf4j
@RestController
@RequestMapping("/jwt")
public class JwtController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProfitServiceImp profitServiceImp;


    @ApiOperation(value = "根据用户名秘密获取token", notes = "获取token,此接口无需在header中添加token参数")
    @PostMapping("/login")
    private Result getToken(@RequestBody LoginInfo loginInfo) throws ServiceException {

        String clientNo = loginInfo.getClientNo();
        Integer integer = (Integer) redisTemplate.opsForValue().get(clientNo);
        if (integer == null) {
            integer = 0;
            redisTemplate.opsForValue().set(clientNo, integer);
        }
        if (integer < 6) {
            if (StringUtils.isBlank(loginInfo.getClientNo()) || StringUtils.isBlank(loginInfo.getPwd())) {
                throw new ServiceException(CommonConst.PARAM_ERROR);
            }
            /**
             **/
            UserInfo userInfoVo = profitServiceImp.getUserInfoByClientNo(loginInfo.getClientNo());
            String pwdInBase=userInfoVo.getFpassword();
            String pwdEnCode = "";
            try {
                pwdEnCode = loginInfo.getPwd();
            } catch (Exception e) {
                log.error("密码加密异常:{}", e.getMessage());
            }
            if (pwdEnCode.equals(pwdInBase)) {
                return ResultUtil.success("登陆成功", jwtTokenUtil.generateToken(loginInfo));
            } else {
                integer++;
                redisTemplate.opsForValue().set(clientNo, integer, 3600, TimeUnit.SECONDS);
                throw new ServiceException(CommonConst.USER_LOGIN_FAULT);
            }
        }
        throw new ServiceException(CommonConst.PW_ERROR_TOO_MANY_TIMES);
    }

    @ApiOperation(value = "根据Token获取用户信息", notes = "根据Token获取用户信息")
    @PostMapping("/loginInfo")
    @Token
    private Result veToken(HttpServletRequest request) {
        final Object loginInfo = request.getAttribute("loginInfo");
        return ResultUtil.success(loginInfo);
    }

}
