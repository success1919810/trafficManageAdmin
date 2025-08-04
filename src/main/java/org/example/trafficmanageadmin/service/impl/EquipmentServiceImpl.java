package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.entity.vo.EquipmentVO;
import org.example.trafficmanageadmin.mapper.EquipmentMapper;
import org.example.trafficmanageadmin.service.EquipmentService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, EquipmentPO> implements EquipmentService {
    @Override
    public EquipmentVO getEquipment(String equipmentCode) {
        QueryWrapper<EquipmentPO> equipmentPOQueryWrapper = new QueryWrapper<>();
        equipmentPOQueryWrapper.eq("equipment_code", equipmentCode);
        EquipmentPO one = this.getOne(equipmentPOQueryWrapper);
        return new EquipmentVO(one.getId(),one.getEquipmentCode(),
                one.getEquipmentType(), one.getLocation(),
                one.getResponsiblePerson(), one.getContactPhone());
    }
}
