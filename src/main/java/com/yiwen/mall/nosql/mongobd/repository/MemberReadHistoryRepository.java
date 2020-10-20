package com.yiwen.mall.nosql.mongobd.repository;

import com.yiwen.mall.nosql.mongobd.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/20 14:46
 * @describe 会员商品浏览历史Repository
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    /**
     * 根据会员id按时间倒序获取浏览记录
     * @param memberId 会员id
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
    //使用@Query注解可以用Mongodb的JSON查询语句进行查询
    @Query("{ 'memberId' : ?0 }")
    List<MemberReadHistory> findByMemberId(Long memberId);
}
