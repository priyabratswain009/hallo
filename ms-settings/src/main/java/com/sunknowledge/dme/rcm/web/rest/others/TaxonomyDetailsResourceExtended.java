package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxonomyDetailsParameterDTO;
import com.sunknowledge.dme.rcm.service.others.TaxonomyDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
public class TaxonomyDetailsResourceExtended {

    @Autowired
    TaxonomyDetailsServiceExtended taxonomyDetailsServiceExtended;


    @PatchMapping(value = "/saveUpdateTaxonomyDetails",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveUpdateTaxonomyDetails(@Valid @RequestBody TaxonomyDetailsParameterDTO
                                          taxonomyDetailsParameterDTO){

        return taxonomyDetailsServiceExtended.saveUpdateTaxonomyDetails(taxonomyDetailsParameterDTO);
    }

    //    operationType should be
    //    -taxonomyName
    //    -taxonomyCode
    //    -taxonomyUUID
    @GetMapping("/getTaxonomyDetailsByNameOrCodeOrUUID")
    public ResponseDTO getTaxonomyDetailsByNameOrCodeOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return taxonomyDetailsServiceExtended.getTaxonomyDetailsByNameOrCodeOrUUID(data,operationType);
    }
}
