package com.sunknowledge.dme.rcm.service.dto.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelPartialPurchaseOrderParameterDTO {
    @NotNull(message="Item_Location_Id must be provided.")
    @Min(value=1, message="Item_Location_Id must be greater than and equals to 1.")
    Long poid;
    @NotNull(message="Item_Ids must be provided.")
    @NotBlank(message="Item_Ids must be provided.")
    String itemids;
    @NotNull(message="Item_Order_Quantities must be provided.")
    @NotBlank(message="Item_Order_Quantities must be provided.")
    String itemorderqtys;
    @NotNull(message="Item_Cancel_Quantities must be provided.")
    @NotBlank(message="Item_Cancel_Quantities must be provided.")
    String itemcancelqtys;
}
