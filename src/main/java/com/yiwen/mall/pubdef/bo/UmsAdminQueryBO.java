package com.yiwen.mall.pubdef.bo;

/**
 * @author ywxie
 * @date 2020/10/14 15:44
 * @describe 后台用户管理查询bo
 */
public class UmsAdminQueryBO {

    private Long id;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UmsAdminQueryBO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
