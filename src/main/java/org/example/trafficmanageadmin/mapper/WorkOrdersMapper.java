package org.example.trafficmanageadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.trafficmanageadmin.entity.po.WorkOrdersPO;
@Mapper
public interface WorkOrdersMapper extends BaseMapper<WorkOrdersPO> {
}
