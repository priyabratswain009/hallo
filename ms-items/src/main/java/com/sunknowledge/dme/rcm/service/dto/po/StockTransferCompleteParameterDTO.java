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
public class StockTransferCompleteParameterDTO {
    @NotNull(message="Item_Id must be provided.")
    @Min(value=1, message="Item_Id must be greater than and equals to 1.")
    Long itemId;
    @NotNull(message="Transfer_Id must be provided.")
    @Min(value=1, message="Transfer_Id must be greater than and equals to 1.")
    Long transferId;
    @NotNull(message="Item_Qty must be provided.")
    @Min(value=1, message="Item_Qty must be greater than and equals to 1.")
    Long itemQty;
    @NotNull(message="Whether_Serialised (Y/N) must be provided.")
    @NotBlank(message="Whether_Serialised (Y/N) must be provided.")
    String whetherSerialised;
    String serialNos;
}
