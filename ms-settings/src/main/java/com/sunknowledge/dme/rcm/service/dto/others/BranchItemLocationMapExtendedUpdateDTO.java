package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchItemLocationMapExtendedUpdateDTO {
    @NotNull(message = "ItemLocation_Id must be provided.")
    @Min(value=1, message="ItemLocation_Id must be greater than or equal to 1")
    private Long itemLocationId;
    @NotNull(message = "Branch_Id must be provided.")
    @Min(value=1, message="Branch_Id must be greater than or equal to 1")
    private Long branchId;
}
