package com.yiwen.mall.service.impl;

import com.yiwen.mall.dao.mapper.UmsPermissionMapper;
import com.yiwen.mall.dao.model.UmsPermission;
import com.yiwen.mall.pubdef.dto.UmsPermissionNode;
import com.yiwen.mall.service.UmsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ywxie
 * @date 2020/10/28 15:28
 * @describe
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UmsPermissionMapper permissionMapper;

    @Override
    public void streamTest() {
        List<UmsPermission> permissionList = permissionMapper.selectAll();
        //filter操作：获取权限类型为目录的权限
        List<UmsPermission> dirList = permissionList.stream()
                .filter(permission -> permission.getType() == 0)
                .collect(Collectors.toList());
        LOGGER.info("filter操作：{}",dirList);

        //map操作：获取所有权限的id
        List<Long> idList = permissionList.stream()
                .map(permission -> permission.getId())
                .collect(Collectors.toList());
        LOGGER.info("map操作：{}",idList);

        //limit操作：获取前5个权限
        List<UmsPermission> firstFiveList = permissionList.stream()
                .limit(5)
                .collect(Collectors.toList());
        LOGGER.info("limit操作：{}",firstFiveList);

        //count操作：获取所有权限目录权限的个数
        long dirPermissionCount = permissionList.stream()
                .filter(permission -> permission.getType() == 0)
                .count();
        LOGGER.info("count操作：{}",dirPermissionCount);

        //sorted操作：将所有权限按先目录后菜单再按钮的顺序排序
        List<UmsPermission> sortedList = permissionList.stream()
//                .sorted((permission1,permission2)->{return permission1.getType().compareTo(permission2.getType());})
                .sorted(Comparator.comparing(UmsPermission::getType))
                .collect(Collectors.toList());
        LOGGER.info("sorted操作：{}",sortedList);

        //skip操作：跳过前5个元素，返回后面的
        List<UmsPermission> skipList = permissionList.stream()
                .skip(5)
                .collect(Collectors.toList());
        LOGGER.info("skip操作：{}",skipList);

        //collect转map操作：将权限列表以id为key，以权限对象为值转换成map
        Map<Long, UmsPermission> permissionMap = permissionList.stream()
                .collect(Collectors.toMap(permission -> permission.getId(), permission -> permission));
        LOGGER.info("collect转map操作：{}",permissionMap);
        //forEach操作，对集合中的元素进行迭代
        permissionList.stream().forEach(permission-> LOGGER.info("forEach操作{}",permission));
    }

    /**
     * 以层级结构返回所有权限
     * 先过滤出pid为0的顶级权限，然后给每个顶级权限设置其子级权限，
     * covert方法的主要用途就是从所有权限中找出相应权限的子级权限。
     */
    @Override
    public List<UmsPermissionNode> treeList() {
        List<UmsPermission> permissionList = permissionMapper.selectAll();
        List<UmsPermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private UmsPermissionNode covert(UmsPermission permission, List<UmsPermission> permissionList) {
        UmsPermissionNode node = new UmsPermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<UmsPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
