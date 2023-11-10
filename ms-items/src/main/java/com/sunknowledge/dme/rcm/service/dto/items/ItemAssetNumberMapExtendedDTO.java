package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAssetNumberMapExtendedDTO {
    private UUID itemAssetNumberUuid;

    @NotNull(message = "Item Id must be provided")
    @Min(value = 0, message = "Item Id should not be less than 1")
    private Long itemId;

    @NotNull(message = "Item No should not be null")
    @NotBlank(message = "Item No must be provided")
    private String itemNo;

    @NotNull(message = "Item Name should not be null")
    @NotBlank(message = "Item Name must be provided")
    private String itemName;

    @NotNull(message = "Location Id must be provided")
    @Min(value = 0, message = "Location Id should not be less than 1")
    private Long locationId;

    @NotNull(message = "Location Name should not be null")
    @NotBlank(message = "Location Name must be provided")
    private String locationName;

    @NotNull(message = "Asset Number should not be null")
    @NotBlank(message = "Asset Number must be provided")
    private String assetNumber;

    @NotNull(message = "On Hand Status should not be null")
    @NotBlank(message = "On Hand Status must be provided")
    @Min(value = 0, message = "On Hand Status should not be less than 0")
    private String onHandStatus;

    @NotNull(message = "On Rent Status should not be null")
    @NotBlank(message = "On Rent Status must be provided")
    @Min(value = 0, message = "On Rent Status should not be less than 0")
    private String onRentStatus;

    @NotNull(message = "Purchase Date must be provided")
    private LocalDate purchaseDate;

    @NotNull(message = "Sale Date must be provided")
    private LocalDate saleDate;

    private String depreciationReadyStatus;

    private String depreciationStatus;

    private Long usefulLifeInYears;

    private LocalDate startDepreciationDate;

    private Double originalCost;

    private Double bookValue;

    private Double accumulatedDepreciation;

    private Double residualValue;

    private String userValue1;

    private String userValue2;

    private String userValue3;

    private String userValue4;

    private String lotBatchNo;

    private LocalDate lotBatchDate;

    @NotNull(message = "Status should not be null")
    @NotBlank(message = "Status must be provided")
    private String status;
}
