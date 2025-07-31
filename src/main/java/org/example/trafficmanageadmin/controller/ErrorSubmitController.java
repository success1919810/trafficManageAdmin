package org.example.trafficmanageadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.Result;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
import org.example.trafficmanageadmin.entity.vo.ErrorSubmitCardVO;
import org.example.trafficmanageadmin.service.EquipmentService;
import org.example.trafficmanageadmin.service.WorkOrderHistoryService;
import org.example.trafficmanageadmin.service.WorkOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/errorSubmit")
public class ErrorSubmitController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private WorkOrdersService workOrdersService;
    @Autowired
    private WorkOrderHistoryService workOrderHistoryService;

    @GetMapping("/{status}")
    public Result getByStatus(@PathVariable String status){
        log.info("有人发起了请求");
        //使用MybatisPlus的封装查询,这里返回符合状态的多条位于工单信息表中的数据
        QueryWrapper<WorkOrdersPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        List<WorkOrdersPO> workOrdersPOList = workOrdersService.list(queryWrapper);
        List<ErrorSubmitCardVO>errorSubmitCardVOS=new ArrayList<>();
        //封装成为VO对象返回给前端
        for(WorkOrdersPO workOrdersPO:workOrdersPOList){
            ErrorSubmitCardVO errorSubmitCardVO = new ErrorSubmitCardVO();
            errorSubmitCardVO.id=workOrdersPO.getId();
            errorSubmitCardVO.order_no=workOrdersPO.getOrderNo();
            errorSubmitCardVO.fault_type=workOrdersPO.getFaultType();
            errorSubmitCardVO.equipment_code = workOrdersPO.getEquipmentId();
            errorSubmitCardVO.report_time=workOrdersPO.getReportTime();
            errorSubmitCardVO.reporter_name=workOrdersPO.getReporterName();
            errorSubmitCardVO.status=workOrdersPO.getStatus().getValue();
            //查询设备信息一起进行封装
            QueryWrapper<EquipmentPO> equipmentQuery = new QueryWrapper<>();
            equipmentQuery.eq("id", workOrdersPO.getEquipmentId());
            EquipmentPO equipmentPO = equipmentService.getOne(equipmentQuery);
            errorSubmitCardVO.equipment_code=equipmentPO.getEquipmentCode();
            errorSubmitCardVO.equipment_type=equipmentPO.getEquipmentType();
            errorSubmitCardVO.location=equipmentPO.getLocation();
            errorSubmitCardVOS.add(errorSubmitCardVO);
        }
        log.info("返回的数据有：{}",errorSubmitCardVOS);
        return Result.success(errorSubmitCardVOS);
    }
}
