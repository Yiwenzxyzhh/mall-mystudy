package com.yiwen.mall.pubdef.pubenum;

/**
 * @author ywxie
 * @date 2020/10/12 13:59
 * @describe
 */
public enum DeletedFlagEnum {

    DELETED((byte)1),   //已删除
    NOT_DELETED((byte)0),   //未删除
    ;

    private Byte deletedFlag;
    DeletedFlagEnum(Byte manualDeletedFlag) {
        this.deletedFlag = manualDeletedFlag;
    }
    public Byte value(){
        return deletedFlag;
    }
}
