package org.example.trafficmanageadmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 只负责表单的更新*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWorkOrderDTO {
    private Integer id;
    private String equipmentId;
    private String reporterName;
    private LocalDateTime reportTime;
    private String faultType;
    private String faultDescription;
    private List<String> faultImages;
}
