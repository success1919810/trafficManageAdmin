package org.example.trafficmanageadmin.entity.vo;

import java.time.LocalDateTime;

/*
* 卡片处的vo数据
* */
public class ErrorSubmitCardVO {
    public Integer id;
    public String order_no;
    public String equipment_code;
    public String equipment_type;
    public String fault_type;
    public String location;
    public LocalDateTime report_time;
    public String reporter_name;
    public String status;
}
