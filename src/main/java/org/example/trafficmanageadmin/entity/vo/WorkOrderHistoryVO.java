package org.example.trafficmanageadmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderHistoryVO {
    private String status;
    private String description;
    private String operatorName;
    private LocalDateTime createdAt;
}
