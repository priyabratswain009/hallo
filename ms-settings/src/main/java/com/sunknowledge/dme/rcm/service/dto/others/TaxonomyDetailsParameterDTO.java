package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxonomyDetailsParameterDTO {
    @NotBlank(message = "Taxonomy_Code must not be blank.")
    @NotNull(message = "Taxonomy_Code must not be blank.")
    private String taxonomyCode;
    @NotBlank(message = "Taxonomy_Code must not be blank.")
    @NotNull(message = "Taxonomy_Code must not be blank.")
    private String taxonomyName;
    private String taxonomyDetails;
    private String status;
    private UUID taxonomyDetailsUuid;
}
