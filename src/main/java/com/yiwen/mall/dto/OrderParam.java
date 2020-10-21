package com.yiwen.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2020/10/21 14:17
 * @describe 生成订单时传入的参数
 */
@Getter
@Setter
public class OrderParam {
    //收货地址id
    private Long memberReceiveAddressId;
    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;

    public Long getMemberReceiveAddressId() {
        return memberReceiveAddressId;
    }

    public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
        this.memberReceiveAddressId = memberReceiveAddressId;
    }
}
