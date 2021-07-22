package com.zd.sercurlending.util;

import com.zd.sercurlending.bean.Result;
import com.zd.sercurlending.config.ResultEnum;

public class ResultUtil {

    /**成功且带数据**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode().toString());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){
        return success(null);
    }

    /**成功带信息带数据**/
    public static Result success(String msg,Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode().toString());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**成功带信息带数据**/
    public static Result success(String code,String msg,Object object){
        Result result = new Result();
        result.setCode( code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**失败**/
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code + "");
        result.setMsg(msg);
        return result;
    }

}
