package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
import org.example.trafficmanageadmin.mapper.WorkOrdersMapper;
import org.example.trafficmanageadmin.service.WorkOrdersService;
import org.springframework.stereotype.Service;

@Service
public class WorkOrdersServiceImpl extends ServiceImpl<WorkOrdersMapper, WorkOrdersPO> implements WorkOrdersService {
}
