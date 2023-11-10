package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchUserMapUpdateDTO {
    @NotNull(message = "Branch_Id must be provided.")
    @Min(value=1, message="Branch_Id must be greater than or equal to 1")
    private Long branchId;
    @NotNull(message = "User_Id must be provided.")
    @Min(value=1, message="User_Id must be greater than or equal to 1")
    private Long userId;
}
