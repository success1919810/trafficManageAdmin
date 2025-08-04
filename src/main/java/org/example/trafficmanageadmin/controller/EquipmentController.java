package org.example.trafficmanageadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.Result;
import org.example.trafficmanageadmin.entity.vo.EquipmentVO;
import org.example.trafficmanageadmin.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/{equipmentCode}")
    public Result getEquipment(@PathVariable String equipmentCode){
        EquipmentVO equipmentVO =equipmentService.getEquipment(equipmentCode);
        if(equipmentVO!=null){
            return Result.success(equipmentVO);
        }else {
            return Result.error("未找到该设备");
        }
    }
}
