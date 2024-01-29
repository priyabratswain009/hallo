package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterCombinedSearchInputDTO {
    //@NotNull(message = "Primary_Procedure_Code must be provided.")
    //@NotBlank(message = "Primary_Procedure_Code must be provided.")
    private String itemNumber;

    private String itemName;

    private String manufacturerName;

    private String itemGroupName;

    private String itemTypeName;
}
