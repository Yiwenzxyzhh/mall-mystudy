package com.yiwen.mall.service;

import com.yiwen.mall.nosql.mongobd.document.MemberReadHistory;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/20 14:50
 * @describe 会员浏览记录管理Service
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
