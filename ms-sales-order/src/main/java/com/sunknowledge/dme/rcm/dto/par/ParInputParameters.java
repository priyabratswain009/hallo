package com.sunknowledge.dme.rcm.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParInputParameters {
    private Long parId;
    private Long salesorderId;
    private Long itemId;
    @NotEmpty(message = "Item no should not be null or empty!")
    private String itemNo;
    @NotEmpty(message = "Item UOM should not be null or empty!")
    private String itemUom;
    private Double itemQuantity;
}
