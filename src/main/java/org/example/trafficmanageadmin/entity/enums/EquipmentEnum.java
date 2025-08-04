package org.example.trafficmanageadmin.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

//用于设备状态的枚举
public enum EquipmentEnum {
    FAULT("fault"),
    NORMAL("normal"),
    MAINTENANCE("maintenance");

    @EnumValue
    private final String value;

    EquipmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 根据数据库中的值获取枚举
    public static EquipmentEnum fromValue(String value) {
        for (EquipmentEnum status : EquipmentEnum.values()) {
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
