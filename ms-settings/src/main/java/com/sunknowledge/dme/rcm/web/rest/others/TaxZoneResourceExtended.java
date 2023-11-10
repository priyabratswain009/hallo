package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxZoneParameterDTO;
import com.sunknowledge.dme.rcm.service.others.TaxZoneServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/api")
public class TaxZoneResourceExtended {
    public static String TYPE = "text/csv";
    private final Logger log = LoggerFactory.getLogger(TaxZoneResourceExtended.class);
    @Autowired
    TaxZoneServiceExtended taxZoneServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PatchMapping(value = "saveTaxZone", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveTaxZone(@RequestBody TaxZoneParameterDTO taxZoneParameterDTO) {
        return taxZoneServiceExtended.saveTaxZone(taxZoneParameterDTO);
    }

    //===== String opType = "StateName";
    //===== String opType = "StateCodeId";
    //===== String opType = "StateCode";
    @GetMapping("/getTaxZoneWiseRateByParam")
    public ResponseDTO getTaxZoneWiseRateByParam(
        /*@NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")*/
        @RequestParam("parameterValue") String parameterValue,
       /* @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")*/
        @RequestParam("opType") String opType) {
        return taxZoneServiceExtended.getTaxZoneWiseRateByParam(
            parameterValue, opType);

    }

    @GetMapping("/getAllTaxZoneInfo")
    public ResponseDTO getAllTaxZoneInfo() {
        List<TaxZoneDTO> obj = taxZoneServiceExtended.getAllTaxZoneInfo();
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    /**
     * This API will help to find the Tax Zone Details Information for a particular Tax Zone ID.
     *
     * @param id
     * @return ResponseDTO
     */
    @GetMapping("/getTaxZoneInfoByID")
    public ResponseDTO getTaxZoneInfoByID(
        @NotNull(message = "Tax Zone ID must be provided")
        @RequestParam("id") Long id) {
        TaxZoneDTO obj = taxZoneServiceExtended.getTaxZoneInfoByID(id);
        return (new ResponseDTO(Objects.nonNull(obj.getTaxZoneId()) ? true : false, Objects.nonNull(obj.getTaxZoneId()) ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    /**
     * This API will help to find All ID as ID, State_Name as title and State_Code for all Active Status.
     *
     * @return ResponseDto
     */
    @GetMapping("/getTaxZoneForDropdown")
    public ResponseDTO getTaxZoneForDropdown() {
        return taxZoneServiceExtended.getTaxZoneForDropdown();
    }
}