package org.example.trafficmanageadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.trafficmanageadmin.entity.dto.CreateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.DeleteWorkOrderDTO;
import org.example.trafficmanageadmin.entity.dto.UpdateWorkOrderDTO;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardDetailVO;
import org.example.trafficmanageadmin.entity.vo.MyReportCardVO;

import java.util.List;

public interface WorkOrdersService extends IService<WorkOrdersPO> {
    boolean createWorkOrder(CreateWorkOrderDTO createWorkOrderDTO);

    boolean updateWorkOrder(UpdateWorkOrderDTO updateWorkOrderDTO);


    boolean removeAll(DeleteWorkOrderDTO deleteWorkOrderDTO);

    List<MyReportCardVO> getByName(String reporterName);

    MyReportCardDetailVO getMyReportCardDetails(Integer id);
}
