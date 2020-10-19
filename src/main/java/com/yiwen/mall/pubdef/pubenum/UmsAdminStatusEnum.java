package com.yiwen.mall.pubdef.pubenum;

/**
 * @author ywxie
 * @date 2020/10/14 14:03
 * @describe 帐号启用状态：0->禁用；1->启用
 */
public enum UmsAdminStatusEnum {
    EFFECTIVE(1),
    NONEFFECTIVE(0),
    ;
    private Integer status;

    UmsAdminStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
