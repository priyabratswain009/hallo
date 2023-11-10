package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendorMapExtendedDTO {
    @NotNull(message = "Vendor_Id must be provided.")
    @Min(value=1, message="Vendor_Id must be greater than or equal to 1")
    private Long vendorId;
    private List<Long> itemIdList;
}
