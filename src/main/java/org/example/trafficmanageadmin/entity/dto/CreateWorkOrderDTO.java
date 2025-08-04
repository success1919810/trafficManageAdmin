package org.example.trafficmanageadmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 创建工单数据,只负责工单的创建
 * **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkOrderDTO {
    private String orderNo;
    private Integer equipmentId;
    private String reporterName;
    private LocalDateTime reportTime;
    private String faultType;
    private String faultDescription;
    private List<String> faultImages;
}
