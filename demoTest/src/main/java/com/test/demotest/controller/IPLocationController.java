package com.test.demotest.controller;



import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@Slf4j
@RequestMapping("/ip")
public class IPLocationController {

    @ApiOperation(value = "选择最优服务器【数据库】[修复移动端对接vpn问题--待校验--完结]", notes = "选择最优服务器【数据库】[修复移动端对接vpn问题--待校验--完结]")
    @RequestMapping(value = "/bestServer",method = RequestMethod.GET)
    @ResponseBody
    public String getWhichOneNew(HttpServletRequest request) throws IOException {
        String ip=request.getRemoteAddr();
        String [] ipsz=ip.split(",");
        if (ipsz.length>1) {
         ip = ipsz[ipsz.length-1];
         log.info("该vpn用户的IP：{}",ip);
        }
        log.info("获取最终的IP：{}",ip);
        return ip;
    }
}
