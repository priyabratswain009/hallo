package com.sunknowledge.dme.rcm.service.dto.others;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxonomyDetailsExtendedDTO extends TaxonomyDetails {
    private Long id;
    private String title;
}
