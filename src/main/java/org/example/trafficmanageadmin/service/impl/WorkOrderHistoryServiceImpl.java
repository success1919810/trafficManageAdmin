package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.po.WorkOrderHistoryPO;
import org.example.trafficmanageadmin.mapper.WorkOrderHistoryMapper;
import org.example.trafficmanageadmin.service.WorkOrderHistoryService;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderHistoryServiceImpl extends ServiceImpl<WorkOrderHistoryMapper, WorkOrderHistoryPO> implements WorkOrderHistoryService {
}
