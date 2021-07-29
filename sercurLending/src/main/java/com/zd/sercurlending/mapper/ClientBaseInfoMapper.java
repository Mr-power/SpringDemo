package com.zd.sercurlending.mapper;


import com.zd.sercurlending.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientBaseInfoMapper {
    UserInfo getInfoById(String clientNo);
}
