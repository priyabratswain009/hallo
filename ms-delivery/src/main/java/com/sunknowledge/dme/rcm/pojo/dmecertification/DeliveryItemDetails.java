package com.sunknowledge.dme.rcm.pojo.dmecertification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryItemDetails {
    private String proccode;
    private String description;
    private String serial;
}
