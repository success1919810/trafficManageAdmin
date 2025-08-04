package org.example.trafficmanageadmin.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum WorkOrderEnum {
    PENDING("pending"),
    PROCESSING("processing"),
    COMPLETED("completed"),
    EVALUATED("evaluated");

    @EnumValue
    private final String value;

    WorkOrderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 根据数据库中的值获取枚举
    public static WorkOrderEnum fromValue(String value) {
        for (WorkOrderEnum status : WorkOrderEnum.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
