package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.mapper.EquipmentMapper;
import org.example.trafficmanageadmin.service.EquipmentService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, EquipmentPO> implements EquipmentService {
}
