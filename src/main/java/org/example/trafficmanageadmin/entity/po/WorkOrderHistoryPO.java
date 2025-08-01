package org.example.trafficmanageadmin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.trafficmanageadmin.entity.enums.WorkOrderHistoryEnum;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("work_order_history")
public class WorkOrderHistoryPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer workOrderId;
    private WorkOrderHistoryEnum status;
    private String description;
    private String operatorName;
    private LocalDateTime createdAt;
}
