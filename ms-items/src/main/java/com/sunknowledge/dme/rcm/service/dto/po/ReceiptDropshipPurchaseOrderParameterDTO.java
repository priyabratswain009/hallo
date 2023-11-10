package com.sunknowledge.dme.rcm.service.dto.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDropshipPurchaseOrderParameterDTO {
    @NotNull(message="PO_Number must be provided.")
    @NotBlank(message="PO_Number must be provided.")
    String pono;
    @NotNull(message="Item_Id must be provided.")
    @NotBlank(message="Item_Id must be provided.")
    String itemId;
    @NotNull(message="Received_Qty must be provided.")
    @NotBlank(message="Received_Qty must be provided.")
    String receivedqty;
    @NotNull(message="Whether_Serialised must be provided.")
    @NotBlank(message="Whether_Serialised must be provided.")
    String whetherSerialised;
    @NotNull(message="Serial_Nos must be provided.")
    @NotBlank(message="Serial_Nos must be provided.")
    String serialnos;
    @NotNull(message="Lot_Nos must be provided.")
    @NotBlank(message="Lot_Nos must be provided.")
    String lotnos;
    @NotNull(message="Mfg_Dates must be provided.")
    @NotBlank(message="Mfg_Dates must be provided.")
    String mfgdates;
    @NotNull(message="Received_Date must be provided.")
    LocalDate receiveddate;
    String note;
}
