package org.example.trafficmanageadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.trafficmanageadmin.entity.Result;
import org.example.trafficmanageadmin.entity.dto.CreateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.DeleteWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.UpdateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.entity.po.WorkOrderHistoryPO;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
import org.example.trafficmanageadmin.entity.vo.ErrorSubmitCardVO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardDetailVO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardVO;
import org.example.trafficmanageadmin.service.EquipmentService;
import org.example.trafficmanageadmin.service.WorkOrderHistoryService;
import org.example.trafficmanageadmin.service.WorkOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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
    public Result getByStatus(@PathVariable String status,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize){
        log.info("有人发起请求了");
        //使用MybatisPlus的封装查询,这里返回符合状态的多条位于工单信息表中的数据
        Page<WorkOrdersPO> page = new Page<>(pageNum,pageSize);
        QueryWrapper<WorkOrdersPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        Page<WorkOrdersPO> workOrdersPOPage = workOrdersService.page(page, queryWrapper);
        List<WorkOrdersPO> workOrdersPOList = workOrdersPOPage.getRecords();
        List<ErrorSubmitCardVO>errorSubmitCardVOS=new ArrayList<>();
        //封装成为VO对象返回给前端
        for(WorkOrdersPO workOrdersPO:workOrdersPOList){
            ErrorSubmitCardVO errorSubmitCardVO = new ErrorSubmitCardVO();
            errorSubmitCardVO.setId(workOrdersPO.getId());
            errorSubmitCardVO.setOrderNo(workOrdersPO.getOrderNo());
            errorSubmitCardVO.setFaultType(workOrdersPO.getFaultType());
            errorSubmitCardVO.setEquipmentCode(workOrdersPO.getEquipmentId());
            errorSubmitCardVO.setReportTime(workOrdersPO.getReportTime());
            errorSubmitCardVO.setReporterName(workOrdersPO.getReporterName());
            errorSubmitCardVO.setStatus(workOrdersPO.getStatus().getValue());
            errorSubmitCardVO.setFaultDescription(workOrdersPO.getFaultDescription());
            errorSubmitCardVO.setFaultImages(workOrdersPO.getFaultImages());
            errorSubmitCardVO.setMaintenancePerson(workOrdersPO.getMaintenancePerson());
            errorSubmitCardVO.setProcessTime(workOrdersPO.getProcessTime());
            errorSubmitCardVO.setCompleteTime(workOrdersPO.getCompleteTime());
            errorSubmitCardVO.setSceneImages(workOrdersPO.getSceneImages());
            errorSubmitCardVO.setRating(workOrdersPO.getRating());
            errorSubmitCardVO.setEvaluationComment(workOrdersPO.getEvaluationComment());
            //查询设备信息一起进行封装
            QueryWrapper<EquipmentPO> equipmentQuery = new QueryWrapper<>();
            equipmentQuery.eq("id", workOrdersPO.getEquipmentId());
            EquipmentPO equipmentPO = equipmentService.getOne(equipmentQuery);
            errorSubmitCardVO.setEquipmentCode(equipmentPO.getEquipmentCode());
            errorSubmitCardVO.setEquipmentType(equipmentPO.getEquipmentType());
            errorSubmitCardVO.setLocation(equipmentPO.getLocation());
            errorSubmitCardVO.setResponsiblePerson(equipmentPO.getResponsiblePerson());
            errorSubmitCardVO.setContactPhone(equipmentPO.getContactPhone());
            //查询工单历史信息
            QueryWrapper<WorkOrderHistoryPO> workOrderHistoryQuery = new QueryWrapper<>();
            workOrderHistoryQuery.eq("work_order_id", workOrdersPO.getId());
            List<WorkOrderHistoryPO> workOrderHistoryPOList = workOrderHistoryService.list(workOrderHistoryQuery);
            errorSubmitCardVO.setWorkOrderHistory(workOrderHistoryPOList);
            errorSubmitCardVOS.add(errorSubmitCardVO);
        }
        Map<String, Object> pageData = new HashMap<>();
        pageData.put("current", workOrdersPOPage.getCurrent());
        pageData.put("size", workOrdersPOPage.getSize());
        pageData.put("total", workOrdersPOPage.getTotal());
        pageData.put("records", errorSubmitCardVOS);
        return Result.success(pageData);
    }
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody CreateWorkOrderDTO createWorkOrderDTO){
        log.info("创建工单：{}",createWorkOrderDTO);
        boolean result = workOrdersService.createWorkOrder(createWorkOrderDTO);
        if (result) {
            return Result.success("工单创建成功");
        } else {
            return Result.error("工单创建失败");
        }
    }
    @PostMapping("/updateOrder")
    public Result updateOrder(@RequestBody UpdateWorkOrderDTO updateWorkOrderDTO){
        log.info("更新工单：{}",updateWorkOrderDTO);
        boolean result = workOrdersService.updateWorkOrder(updateWorkOrderDTO);
        if(result){
            return Result.success("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }
    @DeleteMapping("/deleteOrder/{id}")
    public Result deleteOrder(@PathVariable Integer id){
        DeleteWorkOrderDTO deleteWorkOrderDTO = new DeleteWorkOrderDTO();
        deleteWorkOrderDTO.setId(id);
        boolean result = workOrdersService.removeAll(deleteWorkOrderDTO);
        if(result){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
    @GetMapping("/getByName")
    public Result getByName(String reporterName){
        List<MyReportCardVO> cardVOS =workOrdersService.getByName(reporterName);
        if(cardVOS!=null){
            return Result.success(cardVOS);
        }else {
            return Result.error("您尚未创建过任何上报内容");
        }
    }
    @GetMapping("/getDetails")
    public Result getDetails(Integer id){
        MyReportCardDetailVO myReportCardDetailVO=workOrdersService.getMyReportCardDetails(id);
        if(myReportCardDetailVO!=null){
            return Result.success(myReportCardDetailVO);
        }else {
            return Result.error("不知名错误，可能是传参错误");
        }
    }

}
