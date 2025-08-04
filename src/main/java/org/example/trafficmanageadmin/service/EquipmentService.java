package org.example.trafficmanageadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.entity.vo.EquipmentVO;

public interface EquipmentService extends IService<EquipmentPO> {
    EquipmentVO getEquipment(String equipmentCode);
}
