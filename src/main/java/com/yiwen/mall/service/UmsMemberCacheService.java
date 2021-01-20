package com.yiwen.mall.service;

/**
 * @author ywxie
 * @date 2020/11/16 15:51
 * @describe
 */
public interface UmsMemberCacheService {

    void setAuthCode(String telephone, String authCode);

    String getAuthCode(String telephone);

}
