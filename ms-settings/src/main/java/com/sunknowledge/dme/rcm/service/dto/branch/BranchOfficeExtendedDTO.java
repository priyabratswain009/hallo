package com.sunknowledge.dme.rcm.service.dto.branch;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchOfficeExtendedDTO extends BranchOffice {
    private Long id;
    private String title;
}
