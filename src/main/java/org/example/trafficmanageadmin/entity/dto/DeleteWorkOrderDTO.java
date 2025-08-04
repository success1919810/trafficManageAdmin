package org.example.trafficmanageadmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteWorkOrderDTO {
    private String orderNo;
    private Integer id;
}
