package com.sunknowledge.dme.rcm.web.rest.localcoveragedeterminations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.dto.lcd.ChecklistResultOutcomeDTO;
import com.sunknowledge.dme.rcm.dto.lcd.ChecklistInputDTO;
import com.sunknowledge.dme.rcm.service.lcd.LocalCoverageDeterminationsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Bimal K Sahoo
 * @since 06/03/2023
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class LocalCoverageDeterminationsResource {
    @Autowired
    private LocalCoverageDeterminationsService localCoverageDeterminationsService;

    @ApiOperation(value = "Create LCD Document Check List")
    @GetMapping(value="/createLCDDocumentCheckList")
    @ResponseBody
    public Mono<ChecklistResultOutcomeDTO> createLCDCheckList(@RequestParam("salesOrderId") Long salesOrderId, @RequestParam("hcpcsCode") String hcpcsCode) {
        Mono<ChecklistResultOutcomeDTO> checklistResultOutcome = null;
        try {
            log.info("============>hcpcsCode===>"+hcpcsCode);
            log.info("============>salesOrderId===>"+salesOrderId);
            checklistResultOutcome = localCoverageDeterminationsService.createLCDDocumentNCoverageCheckListOnSalesOrderNhcpcsCode(salesOrderId, hcpcsCode);
            System.out.println("=======================createLCDCheckList=========================");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return checklistResultOutcome;
    }

    @ApiOperation(value = "Save LCD Document Checked List")
    @PostMapping(value="/saveLCDDocumentCheckedList")
    @ResponseBody
    public Flux<ChecklistResultOutcomeDTO> saveLCDCheckedList(@RequestBody ChecklistInputDTO checklistInput) {
        Flux<ChecklistResultOutcomeDTO> checklistResultOutcomeDTO = null;
        try {
            checklistResultOutcomeDTO = localCoverageDeterminationsService.saveLCDDocumentCheckedList(checklistInput);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(checklistInput);
            System.out.println("========>"+json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return checklistResultOutcomeDTO;
    }
}
