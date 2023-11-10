package com.sunknowledge.dme.rcm.service.dto.inventory;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemInventoryStatusParameterDTO {
    private Long itemInventoryStatusId;
    @NotNull(message="Item_Id must be provided.")
    @Min(value=1, message="Item_Id must be greater than and equals to 1.")
    private Long itemId;
    @NotNull(message="Item_Location_Id must be provided.")
    @Min(value=1, message="Item_Location_Id must be greater than and equals to 1.")
    private Long itemLocationId;
    private Long onhandQty;
    private Long onrentQty;
    private Long comittedQty;
    private Long inorderQty;
    private String status;
    @NotNull(message="Item_No must be provided.")
    @NotBlank(message="Item_No must be provided.")
    private String itemNo;
    @NotNull(message="Item_Name must be provided.")
    @NotBlank(message="Item_Name must be provided.")
    @Pattern(regexp = RegexConstant.NAME_REGEX,
        message = "Provide only appropriate Item Name")
    private String itemName;
    private String itemDescription;
    @NotNull(message="Item_Location_Name must be provided.")
    @NotBlank(message="Item_Location_Name must be provided.")
    private String itemLocationName;
    private String binNo;
    private String whetherSerialised;
    private String whetherAssetTagged;
}
