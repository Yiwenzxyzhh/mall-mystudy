package com.yiwen.mall.pubdef.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2021/2/19 14:40
 * @describe 角色queryBO
 */
@Getter
@Setter
public class UmsRoleQueryBO {

    private String name;//role name
    private Integer status;
    private Boolean likeFindFlag;//模糊查询标记

    public UmsRoleQueryBO() {
    }

    public UmsRoleQueryBO(String name, Integer status, Boolean likeFindFlag) {
        this.name = name;
        this.status = status;
        this.likeFindFlag = likeFindFlag;
    }
}
