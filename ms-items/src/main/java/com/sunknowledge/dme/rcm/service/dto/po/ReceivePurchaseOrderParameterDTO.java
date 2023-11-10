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
public class ReceivePurchaseOrderParameterDTO {
    @NotNull(message = "PO_Id must be provided.")
    @Min(value = 1, message = "PO_Id must be greater than and equals to 1.")
    Long poid;
    @NotNull(message = "Location_Id must be provided.")
    @Min(value = 1, message = "Location_Id must be greater than and equals to 1.")
    Long locationid;
    @NotNull(message = "Proper Item_Ids must be selected.")
    @NotBlank(message = "Proper Item_Ids must be selected.")
    String itemids;
    @NotNull(message = "Proper Received_Quantities must be provided.")
    @NotBlank(message = "Proper Received_Quantities must be provided.")
    String receivedqtys;
    @NotNull(message = "Whether_Serialised_Statuses must be provided.")
    @NotBlank(message = "Whether_Serialised_Statuses must be provided.")
    String whetherserialised;
    @NotNull(message = "Serial_Nos must be provided.")
    @NotBlank(message = "Serial_Nos must be provided.")
    String serialnos;
    @NotNull(message = "Lot_Nos must be provided.")
    @NotBlank(message = "Lot_Nos must be provided.")
    String lotnos;
    @NotNull(message = "Manufacturing_Dates must be provided.")
    @NotBlank(message = "Manufacturing_Dates must be provided.")
    String mfgdates;
    @NotNull(message = "Received_Date must be provided.")
    LocalDate receiveddate;
}
