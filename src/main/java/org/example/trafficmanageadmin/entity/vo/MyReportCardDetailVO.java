package org.example.trafficmanageadmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyReportCardDetailVO {
    private Integer id;
    private String location;
    private String equipmentId;
    private String equipmentType;
    private String responsiblePerson;
    private String contactPhone;
    private String faultType;
    private String faultDescription;
    private List<String> faultImages;
}
