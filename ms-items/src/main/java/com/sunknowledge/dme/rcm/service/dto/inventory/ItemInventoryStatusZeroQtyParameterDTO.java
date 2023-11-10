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

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemInventoryStatusZeroQtyParameterDTO {
    private Long itemInventoryStatusId;
    @NotNull(message="Item_Id must be provided.")
    @Min(value=1, message="Item_Id must be greater than and equals to 1.")
    private Long itemId;
    @NotNull(message="Item_Location_Id must be provided.")
    @Min(value=1, message="Item_Location_Id must be greater than and equals to 1.")
    private Long itemLocationId;
    private String status;
    @NotNull(message="Item_No must be provided.")
    @NotBlank(message="Item_No must be provided.")
    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX,
        message = "Provide only appropriate ItemNo")
    private String itemNo;
    @NotNull(message="Item_Name must be provided.")
    @NotBlank(message="Item_Name must be provided.")
    private String itemName;
    private String itemDescription;
    @NotNull(message="Item_Location_Name must be provided.")
    @NotBlank(message="Item_Location_Name must be provided.")
    private String itemLocationName;
    private String binNo;
    private String whetherSerialised;
    private String whetherAssetTagged;
}
