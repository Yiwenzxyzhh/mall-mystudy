package com.yiwen.mall.pubdef.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2020/10/14 15:44
 * @describe 后台用户管理查询bo
 */
@Getter
@Setter
public class UmsAdminQueryBO {

    private Long id;

    private String username;

    @Override
    public String toString() {
        return "UmsAdminQueryBO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
