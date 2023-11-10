package com.sunknowledge.dme.rcm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTemplateFormInputs {
    private String formName;
    private Long deliveryTicketId;
    private List<String> serialNo;
}
