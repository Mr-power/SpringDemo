package com.zd.sercurlending.service;

import com.zd.sercurlending.bean.UserInfo;

public interface ProfitService {
    boolean joinProfit();
    UserInfo getUserInfoByClientNo(String clientNo);
}
