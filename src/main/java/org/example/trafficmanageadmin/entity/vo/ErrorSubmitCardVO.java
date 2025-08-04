package org.example.trafficmanageadmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.trafficmanageadmin.entity.po.WorkOrderHistoryPO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
* 卡片处的vo数据或者之后整合成为一整个呢
* */
public class ErrorSubmitCardVO {
    private Integer id;
    private String orderNo;
    private String equipmentCode;
    private String equipmentType;
    private String faultType;
    private String location;
    private LocalDateTime reportTime;
    private String reporterName;
    private String status;
    private String responsiblePerson;
    private String contactPhone;
    private String faultDescription;
    private List<String> faultImages;
    private String maintenancePerson;
    private LocalDateTime processTime;
    private LocalDateTime completeTime;
    private List<String> sceneImages;
    private Integer rating;
    private String evaluationComment;
    private List<WorkOrderHistoryPO> workOrderHistory;
}
