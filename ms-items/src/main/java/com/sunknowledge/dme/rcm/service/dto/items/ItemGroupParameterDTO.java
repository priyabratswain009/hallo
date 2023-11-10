package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemGroupParameterDTO {

    private Long itemGroupId;

    @NotBlank(message = "Item_Group_Name must be provided")
    @NotNull(message = "Item_Group_Name must be provided")
    private String itemGroupName;

    @NotBlank(message = "Status must be provided")
    @NotNull(message = "Status must be provided")
    private String status;
}
