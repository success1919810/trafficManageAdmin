package org.example.trafficmanageadmin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "work_order_history",autoResultMap = true)
public class WorkOrderHistoryPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer workOrderId;
    private String status;
    private String description;
    private String operatorName;
    private LocalDateTime createdAt;
}
