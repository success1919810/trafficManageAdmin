package org.example.trafficmanageadmin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.trafficmanageadmin.entity.enums.EquipmentEnum;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "equipment",autoResultMap = true)
public class EquipmentPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String equipmentCode;
    private String equipmentType;
    private String location;
    private String responsiblePerson;
    private String contactPhone;
    private EquipmentEnum status;
    private LocalDateTime createdAt;
}
