package com.yiwen.mall.pubdef.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2020/11/9 14:23
 * @describe
 */
@Getter
@Setter
public class UmsMemberQueryBO {
    private String username;
    private String phone;

    public UmsMemberQueryBO() {
    }

    public UmsMemberQueryBO(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }
}
