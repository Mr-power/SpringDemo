package com.zd.sercurlending.controller;

import com.zd.sercurlending.bean.Result;
import com.zd.sercurlending.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profit")
public class ProfitController {
    @ApiOperation(value = "加入收益计划", notes = "加入收益计划")
    @RequestMapping(value = "/joinProfit",method = RequestMethod.POST)
    public Result joinProfit(){
        //1、先判断是否、登陆股票实盘账户、  或 已绑定股票账户 的 综合账户
        return ResultUtil.success();
    }
}
