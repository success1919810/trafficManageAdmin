package org.example.trafficmanageadmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyReportCardVO {
    private Integer id;
    private String errorType;
    private String location;
    private String equipmentCode;
    private String equipmentType;
    private LocalDateTime reportTime;
}
