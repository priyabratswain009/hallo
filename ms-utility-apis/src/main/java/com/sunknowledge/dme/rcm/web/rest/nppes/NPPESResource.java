package com.sunknowledge.dme.rcm.web.rest.nppes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.dto.nppes.NPPESIndividualsInputCriteria;
import com.sunknowledge.dme.rcm.dto.nppes.NPPESInputNPICriteria;
import com.sunknowledge.dme.rcm.dto.nppes.ResultNPIOutcome;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import com.sunknowledge.dme.rcm.service.common.DoctorMasterAndMedicalInfoProjection;
import com.sunknowledge.dme.rcm.service.nppes.NPPESService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nppes")
public class NPPESResource {
    private static final Logger log = LoggerFactory.getLogger(NPPESResource.class);

    @Autowired
    private NPPESService nppesService;

    @ApiOperation(value = "Get NPI Individual Data")
    @GetMapping(path = "/nppesNPIRestIndividualData", produces = "application/json")
    @ResponseBody
    public String nppesNPIRestIndividualData(NPPESInputNPICriteria nppesInputNPICriteria) {
        log.info("=======================POST for NPI Individual Record==========================");
        String resultOutcomeJson = "";
        ServiceOutcome<ResultNPIOutcome> persistResultOutcome = null;
        ServiceOutcome searchResultOutcome = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        ServiceOutcome outcomeDoctorMaster = new ServiceOutcome<>();
        try {
            NPPESIndividualsInputCriteria nppesIndividualsInputCriteria = new NPPESIndividualsInputCriteria();
            nppesIndividualsInputCriteria = nppesService.setupNullValuesForInputCriterias(nppesIndividualsInputCriteria);
            nppesIndividualsInputCriteria.setNpiNumber(nppesInputNPICriteria.getNpiNumber());
            nppesIndividualsInputCriteria.setEnumerationType("NPI-1");
            //if(nppesInputNPICriteria.getOperationType().equals("Search")) {
                outcomeDoctorMaster = nppesService.getNppesDoctorDetailsOnNpiNumber(nppesInputNPICriteria);
                if(outcomeDoctorMaster.getOutcome()) {
                    resultOutcomeJson = ow.writeValueAsString(outcomeDoctorMaster);
                }else{
                    persistResultOutcome = nppesService.persistNPPESNPIRecord(nppesIndividualsInputCriteria);
                    if(persistResultOutcome.getOutcome()){
                        outcomeDoctorMaster = nppesService.saveNUpdateNPIData(persistResultOutcome);
                        resultOutcomeJson = ow.writeValueAsString(outcomeDoctorMaster);
                    }
                    else{
                        return "Wrong NPI Given";
                    }
                    /*searchResultOutcome = nppesService.searchNPPESNPIRecord(nppesIndividualsInputCriteria);
                    resultOutcomeJson = ow.writeValueAsString(searchResultOutcome);*/
                }
            /*}
            else if(nppesInputNPICriteria.getOperationType().equals("Persist")) {
                persistResultOutcome = nppesService.persistNPPESNPIRecord(nppesIndividualsInputCriteria);
                if(persistResultOutcome != null) {
                    if(persistResultOutcome.getOutcome()){
                        outcomeDoctorMaster = nppesService.saveNUpdateNPIData(persistResultOutcome);
                        resultOutcomeJson = ow.writeValueAsString(outcomeDoctorMaster);
                    }
                    else{
                        outcomeDoctorMaster = nppesService.getNppesDoctorDetailsOnNpiNumber(nppesInputNPICriteria);
                        resultOutcomeJson = ow.writeValueAsString(outcomeDoctorMaster);
                    }
                }
            }*/
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("searchResultOutcome "+ searchResultOutcome);

        System.out.println("outcomeDoctorMaster "+ outcomeDoctorMaster);
        if(outcomeDoctorMaster != null && outcomeDoctorMaster.getOutcome() != null && outcomeDoctorMaster.getOutcome()) {
            DoctorMasterDTO doctorMasterDTO = (DoctorMasterDTO) outcomeDoctorMaster.getData();
            System.out.println("doctorMasterDTO 2"+ doctorMasterDTO);
            List<DoctorMasterAndMedicalInfoProjection> obj = nppesService.getDoctorMasterWithMedicalInfo(doctorMasterDTO.getDoctorId());
            System.out.println("Mereged Data "+ obj.get(0));
            outcomeDoctorMaster.setData(obj);
            try {
                resultOutcomeJson = ow.writeValueAsString(outcomeDoctorMaster);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return resultOutcomeJson;
    }

    @ApiOperation(value = "Get NPI Organizational Data")
    @GetMapping(path = "/nppesNPIRestOrganizationalData", produces = "application/json")
    @ResponseBody
    public String nppesNPIRestOrganizationalData(NPPESInputNPICriteria nppesInputNPICriteria) {
        log.info("=======================POST for NPI Organizational Record==========================");
        String resultOutcomeJson = "";
        try {
            NPPESIndividualsInputCriteria nppesIndividualsInputCriteria = new NPPESIndividualsInputCriteria();
            nppesIndividualsInputCriteria = nppesService.setupNullValuesForInputCriterias(nppesIndividualsInputCriteria);
            nppesIndividualsInputCriteria.setNpiNumber(nppesInputNPICriteria.getNpiNumber());
            nppesIndividualsInputCriteria.setEnumerationType("NPI-2");
            ServiceOutcome<ResultNPIOutcome> resultOutcome = nppesService.persistNPPESNPIRecord(nppesIndividualsInputCriteria);
            if(resultOutcome != null) {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                resultOutcomeJson = ow.writeValueAsString(resultOutcome.getData().getResultNPIDataset());
            }
            System.out.println("JSON RESULT:"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

    @ApiOperation(value = "Get Doctor Details")
    @GetMapping(path = "/getDoctorDetailsByName", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<List<String>> getDoctorDetailsByName(String searchText) {
        log.info("=======================GET for Doctor's Detail Record==========================");
        ServiceOutcome<List<String>> outcome = null;
        try {
            outcome = nppesService.getDoctorDetailsByName(searchText);
            System.out.println("JSON RESULT:"+outcome);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Get Doctor Details")
    @GetMapping(path = "/getDoctorDetailsByNPINumber", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<List<String>> getDoctorDetailsByNPINumber(String searchNPINumber) {
        log.info("=======================GET for Doctor's Detail Record==========================");
        ServiceOutcome<List<String>> outcome = null;
        try {
            outcome = nppesService.getDoctorDetailsByNPINumber(searchNPINumber);
            System.out.println("JSON RESULT:"+outcome);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Get All Doctor Details")
    @GetMapping(path = "/getAllDoctorDetails", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<List<DoctorMaster>> getAllDoctorDetails() {
        log.info("=======================GET for Doctor's Detail Record==========================");
        ServiceOutcome<List<DoctorMaster>> outcome = null;
        try {
            outcome = nppesService.getAllDoctorDetails();
            System.out.println("JSON RESULT:"+outcome);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }
}
