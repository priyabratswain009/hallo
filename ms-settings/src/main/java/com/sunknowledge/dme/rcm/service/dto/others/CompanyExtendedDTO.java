package com.sunknowledge.dme.rcm.service.dto.others;

import com.sunknowledge.dme.rcm.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyExtendedDTO extends Company {
    private Long id;
    private String title;
}
