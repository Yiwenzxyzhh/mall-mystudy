package com.yiwen.mall.controller;

import com.google.common.collect.ImmutableMap;
import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.nosql.mongobd.document.MemberReadHistory;
import com.yiwen.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/20 14:53
 * @describe 会员商品浏览记录管理Controller
 */
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
@RestController
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping(value = "")
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        return CommonResult.success(count);
    }

    @ApiOperation("展示浏览记录")
    @GetMapping(value = "/list")
    @ResponseBody
    public CommonResult list(Long memberId) {
        return CommonResult.success(ImmutableMap.of("member_read_history_list", memberReadHistoryService.list(memberId)));
    }

}
