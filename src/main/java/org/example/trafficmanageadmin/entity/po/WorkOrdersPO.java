package org.example.trafficmanageadmin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.trafficmanageadmin.entity.enums.WorkOrderEnum;

import java.time.LocalDateTime;
import java.util.List;
/*
* 工单信息信息持久化对象po
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "work_orders",autoResultMap = true)
public class WorkOrdersPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String orderNo;
    private String equipmentId;
    private String reporterName;
    private LocalDateTime reportTime;
    private String faultType;
    private String faultDescription;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> faultImages;
    private String maintenancePerson;
    private LocalDateTime processTime;
    private LocalDateTime completeTime;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> sceneImages;
    private WorkOrderEnum status;
    private Integer rating;
    private String evaluationComment;
    private LocalDateTime evaluationTime;
    private LocalDateTime createdAt;
}
