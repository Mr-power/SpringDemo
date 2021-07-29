package com.zd.sercurlending.service;

import com.zd.sercurlending.bean.UserInfo;
import com.zd.sercurlending.mapper.ClientBaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitServiceImp implements ProfitService{

    @Autowired
    private ClientBaseInfoMapper clientBaseInfoMapper;

    @Override
    public boolean joinProfit() {
        return false;
    }

    @Override
    public UserInfo getUserInfoByClientNo(String clientNo) {
        UserInfo c1= clientBaseInfoMapper.getInfoById(clientNo);
        return c1;
    }
}
