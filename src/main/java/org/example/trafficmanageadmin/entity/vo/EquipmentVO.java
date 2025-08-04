package org.example.trafficmanageadmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentVO {
    private Integer id;
    private String equipmentCode;
    private String equipmentType;
    private String location;
    private String responsiblePerson;
    private String contactPhone;
}
