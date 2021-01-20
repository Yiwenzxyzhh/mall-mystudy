package com.yiwen.mall.service;

import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ywxie
 * @date 2020/10/21 14:08
 * @describe 前台订单管理Service
 */
public interface OmsPortalOrderService {
    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
