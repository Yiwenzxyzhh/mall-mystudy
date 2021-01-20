package com.yiwen.mall.pubdef.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2020/11/16 16:48
 * @describe
 */
@Getter
@Setter
public class UmsMemberLevelQueryBO {

    private Integer defaultStatus;

    public UmsMemberLevelQueryBO() {
    }

    public UmsMemberLevelQueryBO(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }
}
