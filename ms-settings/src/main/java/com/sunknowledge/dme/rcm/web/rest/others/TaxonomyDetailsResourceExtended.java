package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxonomyDetailsExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxonomyDetailsParameterDTO;
import com.sunknowledge.dme.rcm.service.others.TaxonomyDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/getAllTaxonomyDetailsData")
    public ResponseDTO getAllTaxonomyDetailsData(){
        List<TaxonomyDetails> taxonomyDetailsExtendedDTOList= taxonomyDetailsServiceExtended.getAllTaxonomyDetailsData();
        return new ResponseDTO(taxonomyDetailsExtendedDTOList.size()>0?true:false,
            taxonomyDetailsExtendedDTOList.size()>0?"":"Data Not Found",
            taxonomyDetailsExtendedDTOList.size()>0?taxonomyDetailsExtendedDTOList:null,200);
    }
    @PutMapping(value = "/setTaxonomyDetailsStatusByUuid")
    public ResponseDTO setTaxonomyDetailsStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                      @RequestParam("status") String status){
        return taxonomyDetailsServiceExtended.setTaxonomyDetailsStatusByUuid(uuid,status);
    }

    @GetMapping("/getAllTaxonomyDetailsDataForDropdown")
    public ResponseDTO getAllTaxonomyDetailsDataForDropdown(){
        List<Map<String, Object>> taxonomyDetailsExtendedDTOList= taxonomyDetailsServiceExtended.getAllTaxonomyDetailsDataForDropdown();
        return new ResponseDTO(taxonomyDetailsExtendedDTOList.size()>0?true:false,
            taxonomyDetailsExtendedDTOList.size()>0?"":"Data Not Found",
            taxonomyDetailsExtendedDTOList.size()>0?taxonomyDetailsExtendedDTOList:null,200);
    }
}
