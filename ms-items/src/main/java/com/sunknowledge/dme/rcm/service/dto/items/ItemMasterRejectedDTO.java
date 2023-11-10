package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterRejectedDTO {

    private String message;

    private String itemName;

    private String itemDescription;

    private Long itemTypeId;

    private String manufacturerName;

    private Long manufacturerId;

    private String manufacturerBarcode;

    private Long defaultVendorId;

    private String status;

    private String itemNumber;

    private String itemGroupName;

    private String itemTypeName;
}
