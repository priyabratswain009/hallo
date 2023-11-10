package com.sunknowledge.dme.rcm.web.rest.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.service.common.UszipMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.common.DropdownDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UszipMasterResourceExtended {

    @Autowired
    UszipMasterServiceExtended uszipMasterServiceExtended;

    @PatchMapping(value = "/getUsZipDetails")
    public ServiceOutcome getUsZipDetails(
        @NotNull(message = "Zip_Code should not be null")
        @Min(value=1, message="Zip_Code must be greater than or equal to 1")
        @RequestParam Long zipCode){
        return uszipMasterServiceExtended.getUsZipDetails(zipCode);
    }

    /***********************Using LIKE Clause*************************/
    @GetMapping(value = "/getUSZipDetailsByZipCode")
    public ServiceOutcome getUSZipDetailsByZipCode(
        @RequestParam(required = false) Long zipCode) {
        if (zipCode==null) {
            return new ServiceOutcome<>(null,false,"Zip_Code should not be null");
        }
        return uszipMasterServiceExtended.getUSZipDetailsByZipCode(zipCode);
    }

    /***********************Using WHERE Clause [Return Specific Value]*************************/
    @GetMapping(value = "/getUSZipDetailByIndividualZipCode")
    public ServiceOutcome getUSZipDetailByIndividualZipCode(
        @NotNull(message = "Zip_Code should not be null")
        @Min(value=1, message="Zip_Code must be greater than or equal to 1")
        @RequestParam Long zipCode){
        return uszipMasterServiceExtended.getUSZipDetailByIndividualZipCode(zipCode);
    }

    @GetMapping(value = "/getStateDropdown")
    public ServiceOutcome getStateDropdown(){
        List<Map> maps = uszipMasterServiceExtended.getStateDropdown();
        return new ServiceOutcome<>(maps.size()>0?maps:null,maps.size()>0? true:false,maps.size()>0?"Data Found.":"Data Not Found");
    }
}
