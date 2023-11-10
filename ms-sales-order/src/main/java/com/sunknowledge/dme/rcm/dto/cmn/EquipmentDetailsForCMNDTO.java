package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDetailsForCMNDTO {
    private String itemId;
    private String qty;
    private String procCode;
    private String itemName;
    private String count;
    private String interval;
}
