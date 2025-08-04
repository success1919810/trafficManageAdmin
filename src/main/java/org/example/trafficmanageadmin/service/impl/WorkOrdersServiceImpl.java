package org.example.trafficmanageadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.trafficmanageadmin.entity.dto.CreateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.DeleteWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.UpdateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.enums.WorkOrderEnum;
import org.example.trafficmanageadmin.entity.po.EquipmentPO;
import org.example.trafficmanageadmin.entity.po.WorkOrderHistoryPO;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardDetailVO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardVO;
import org.example.trafficmanageadmin.mapper.WorkOrdersMapper;
import org.example.trafficmanageadmin.service.WorkOrderHistoryService;
import org.example.trafficmanageadmin.service.WorkOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrdersServiceImpl extends ServiceImpl<WorkOrdersMapper, WorkOrdersPO> implements WorkOrdersService {
    @Autowired
    private WorkOrderHistoryService workOrderHistoryService;
    @Autowired
    private EquipmentServiceImpl equipmentService;

    @Override
    @Transactional
    public boolean createWorkOrder(CreateWorkOrderDTO createWorkOrderDTO) {
        // 保存工单信息
        WorkOrdersPO workOrdersPO = new WorkOrdersPO();
        workOrdersPO.setOrderNo(createWorkOrderDTO.getOrderNo());
        workOrdersPO.setEquipmentId(String.valueOf(createWorkOrderDTO.getEquipmentId()));
        workOrdersPO.setReporterName(createWorkOrderDTO.getReporterName());
        workOrdersPO.setReportTime(createWorkOrderDTO.getReportTime());
        workOrdersPO.setFaultType(createWorkOrderDTO.getFaultType());
        workOrdersPO.setFaultDescription(createWorkOrderDTO.getFaultDescription());
        workOrdersPO.setFaultImages(createWorkOrderDTO.getFaultImages());
        workOrdersPO.setStatus(WorkOrderEnum.PENDING);
        workOrdersPO.setCreatedAt(LocalDateTime.now());

        // 保存工单
        boolean saveResult = this.save(workOrdersPO);

        if (saveResult) {
            // 添加工单历史记录
            WorkOrderHistoryPO workOrderHistoryPO = new WorkOrderHistoryPO();
            workOrderHistoryPO.setWorkOrderId(workOrdersPO.getId());
            workOrderHistoryPO.setStatus("派单");
            workOrderHistoryPO.setDescription("工单已创建");
            workOrderHistoryPO.setOperatorName(createWorkOrderDTO.getReporterName());
            workOrderHistoryPO.setCreatedAt(createWorkOrderDTO.getReportTime());

            workOrderHistoryService.save(workOrderHistoryPO);

            return true;
        }

        return false;
    }

    @Override
    public boolean updateWorkOrder(UpdateWorkOrderDTO updateWorkOrderDTO) {
        WorkOrdersPO workOrdersPO = new WorkOrdersPO();
        workOrdersPO.setId(updateWorkOrderDTO.getId());
        workOrdersPO.setEquipmentId(updateWorkOrderDTO.getEquipmentId());
        workOrdersPO.setReporterName(updateWorkOrderDTO.getReporterName());
        workOrdersPO.setReportTime(updateWorkOrderDTO.getReportTime());
        workOrdersPO.setFaultType(updateWorkOrderDTO.getFaultType());
        workOrdersPO.setFaultDescription(updateWorkOrderDTO.getFaultDescription());
        UpdateWrapper<WorkOrdersPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", updateWorkOrderDTO.getId());
        return this.update(workOrdersPO,updateWrapper);
    }

    @Override
    @Transactional
    public boolean removeAll(DeleteWorkOrderDTO deleteWorkOrderDTO) {
        // 先删除相关的工单历史记录
        QueryWrapper<WorkOrderHistoryPO> historyQueryWrapper = new QueryWrapper<>();
        historyQueryWrapper.eq("work_order_id", deleteWorkOrderDTO.getId());
        workOrderHistoryService.remove(historyQueryWrapper);

        // 再删除工单本身
        return this.removeById(deleteWorkOrderDTO.getId());
    }

    @Override
    public List<MyReportCardVO> getByName(String reporterName) {
        QueryWrapper<WorkOrdersPO> woq = new QueryWrapper<>();
        woq.eq("reporter_name",reporterName);
        List<WorkOrdersPO> workOrdersPOList = this.list(woq);
        List<MyReportCardVO> mrcvList=new ArrayList<>();
        for(WorkOrdersPO workOrdersPO:workOrdersPOList){
            MyReportCardVO myReportCardVO = new MyReportCardVO();
            myReportCardVO.setId(workOrdersPO.getId());
            myReportCardVO.setReportTime(workOrdersPO.getReportTime());
            myReportCardVO.setErrorType("设备故障");
            myReportCardVO.setEquipmentCode(workOrdersPO.getEquipmentId());
            QueryWrapper<EquipmentPO> epo = new QueryWrapper<>();
            epo.eq("id",workOrdersPO.getEquipmentId());
            EquipmentPO equipment = equipmentService.getOne(epo);
            myReportCardVO.setEquipmentType(equipment.getEquipmentType());
            myReportCardVO.setEquipmentCode(equipment.getEquipmentCode());
            myReportCardVO.setLocation(equipment.getLocation());
            mrcvList.add(myReportCardVO);
        }
        return mrcvList;
    }

    @Override
    public MyReportCardDetailVO getMyReportCardDetails(Integer id) {
        QueryWrapper<WorkOrdersPO> workOrdersPOQueryWrapper=new QueryWrapper<>();
        workOrdersPOQueryWrapper.eq("id",id);
        WorkOrdersPO workOrdersPO = this.getOne(workOrdersPOQueryWrapper);
        MyReportCardDetailVO myReportCardDetailVO=new MyReportCardDetailVO(workOrdersPO.getId(),
                workOrdersPO.getEquipmentId(),workOrdersPO.getEquipmentId(),
                workOrdersPO.getEquipmentId(),workOrdersPO.getReporterName(),workOrdersPO.getReporterName(),
                workOrdersPO.getFaultType(),workOrdersPO.getFaultDescription(),workOrdersPO.getFaultImages());
        QueryWrapper<EquipmentPO> equipmentPOQueryWrapper=new QueryWrapper<>();
        equipmentPOQueryWrapper.eq("id",workOrdersPO.getEquipmentId());
        EquipmentPO equipmentPO = equipmentService.getOne(equipmentPOQueryWrapper);
        myReportCardDetailVO.setLocation(equipmentPO.getLocation());
        myReportCardDetailVO.setEquipmentType(equipmentPO.getEquipmentType());
        myReportCardDetailVO.setResponsiblePerson(equipmentPO.getResponsiblePerson());
        myReportCardDetailVO.setContactPhone(equipmentPO.getContactPhone());
        return myReportCardDetailVO;
    }
}
