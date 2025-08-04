package org.example.trafficmanageadmin.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum WorkOrderHistoryEnum {
    PENDING("pending"),
    ASSIGNED("assigned"),
    PROCESSING("processing"),
    COMPLETED("completed"),
    EVALUATED("evaluated");

    @EnumValue
    private final String value;

    WorkOrderHistoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 根据数据库中的值获取枚举
    public static WorkOrderHistoryEnum fromValue(String value) {
        for (WorkOrderHistoryEnum status : WorkOrderHistoryEnum.values()) {
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
