package com.zd.sercurlending.controller;

import com.zd.sercurlending.bean.Result;
import com.zd.sercurlending.service.ProfitServiceImp;
import com.zd.sercurlending.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/profit")
public class ProfitController {
    @Autowired
    private ProfitServiceImp profitServiceImp;

    @ApiOperation(value = "加入收益计划", notes = "加入收益计划")
    @RequestMapping(value = "/joinProfit",method = RequestMethod.POST)
    public Result joinProfit(String clientNo){
        //1、先判断是否、登陆股票实盘账户、  或 已绑定股票账户 的 综合账户
        return ResultUtil.success(profitServiceImp.getUserInfoByClientNo(clientNo));
    }

    @ApiOperation(value = "获取ip地址", notes = "获取ip地址")
    @RequestMapping(value = "/ip",method = RequestMethod.POST)
    public String getProfit(HttpServletRequest request){
        //1、先判断是否、登陆股票实盘账户、  或 已绑定股票账户 的 综合账户
        String ip=null;
        try {
            ip= request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip != null && ip.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(ip.length()-15, ip.length()+1);
                    log.info("该vpn用户的IP：{}",ip);
                }
            }
            log.info("该用户的IP：{}",ip);
        } catch (Exception e) {
            ip="";
        }
        return ip;
    }
}
