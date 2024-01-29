package com.sunknowledge.dme.rcm.web.rest.claimcontroller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.ApplicationPropertiesSetup;
import com.sunknowledge.dme.rcm.application.properties.AsyncConfigurationSetup;
import com.sunknowledge.dme.rcm.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimTransactionOutcome;
import com.sunknowledge.dme.rcm.service.claimservice.ClaimResponsesAndReportsV2Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * @author Bimal K Sahoo
 * @since 26/05/2022
 *
 */
@RestController
@RequestMapping("/api/claimresponsereportv2")
@Slf4j
public class ClaimResponsesAndReportsV2Controller {
	@Autowired
	private ClaimResponsesAndReportsV2Service claimResponsesAndReportsV2Service;

	@ApiOperation(value = "Process Medical Network Claims Responses and Reports V2 API(Change Healthcare)")
	@GetMapping(path = "/processClaimsResponsesReports", produces = "application/json")
	@ResponseBody
	public String processActualClaimResponsesReports(@RequestParam("fileName") String fileName) {
		log.info("=======================POST(CONTROLLER) Process Medical Network Claims Responses and Reports V2 API(Change Healthcare)==========================");
		String resultOutcomeJson = "";
		try {
//			ServiceOutcome<ArrayList<ActualClaimResponseReport>> routcome = claimResponsesAndReportsV2Service.processActualClaimResponsesReport();
//			if(routcome != null) {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.registerModule(new JavaTimeModule());
//                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//				resultOutcomeJson = ow.writeValueAsString(routcome);
//
//				ClaimTransactionOutcome transactionOutcome = claimResponsesAndReportsV2Service.saveClaimTransaction(routcome.getData());
//
//				if(transactionOutcome != null) {
//					resultOutcomeJson = ow.writeValueAsString(transactionOutcome);
//					claimResponsesAndReportsV2Service.claimSubmissionStatusOperation(transactionOutcome);
//				}
//			}


            ObjectMapper mapper = new ObjectMapper();//readsecondaryclaimstatus
            JSONParser parser = new JSONParser();
            try (Reader reader = new FileReader("D:\\Project Data\\Claims\\Demonstration\\crossover\\"+fileName)) {//readprimaryclaimstatus
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                System.out.println(jsonObject);
                String inputData = jsonObject.toJSONString();
                TypeReference<ServiceOutcome<ArrayList<ActualClaimResponseReport>>> tRef = new TypeReference<>() {};
                ServiceOutcome<ArrayList<ActualClaimResponseReport>> routcome = mapper.readValue(inputData, tRef);
                if(routcome != null) {
                    mapper.registerModule(new JavaTimeModule());
                    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                    resultOutcomeJson = ow.writeValueAsString(routcome.getData());
                    System.out.println("=====>"+routcome.getMessage());
                    System.out.println("=====>"+routcome.getOutcome());
                    ClaimTransactionOutcome transactionOutcome = claimResponsesAndReportsV2Service.saveClaimTransaction(routcome.getData());
                    if(transactionOutcome != null) {
                        resultOutcomeJson = ow.writeValueAsString(transactionOutcome);
                        claimResponsesAndReportsV2Service.claimSubmissionStatusOperation(transactionOutcome);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            claimResponsesAndReportsV2Service.process835CrossoverInsurance();


//            List<ClaimsCOB835Data> claimsCOB835MasterList = claimResponsesAndReportsV2Service.getAllClaimsCOB835MasterData();
//            List<ClaimsStatus277Data> claimsStatus277Data = claimResponsesAndReportsV2Service.getAllClaimsStatus277MasterData();
//            ClaimTransactionOutcome transactionOutcome = new ClaimTransactionOutcome();
//            transactionOutcome.setClaimsCOB835MasterList(claimsCOB835MasterList);
//            transactionOutcome.setClaimsStatus277MasterList(claimsStatus277Data);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//            if(transactionOutcome != null) {
//                resultOutcomeJson = ow.writeValueAsString(transactionOutcome);
//                claimResponsesAndReportsV2Service.claimSubmissionStatusOperation(transactionOutcome);
//            }
			System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}

    @ApiOperation(value = "Prepare Primary Claim Submission Health Insurance Form")
    @PostMapping(path = "/preparePrimaryClaimSubmissionHealthInsuranceForm", produces = "application/json")
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ServiceOutcome<String>> preparePrimaryClaimSubmissionHealthInsuranceForm(@RequestParam("claimControlNumber") String claimControlNumber) {
        log.info("=======================POST(CONTROLLER) Prepare Primary Claim Submission Health Insurance Form==========================");
        try {
            return claimResponsesAndReportsV2Service.preparePrimaryClaimSubmissionHealthInsuranceForm(claimControlNumber);
        }
        catch(Exception e) {
            log.error("Unable to prepare primary claim submission health insurance form");
            return CompletableFuture.completedFuture(new ServiceOutcome<>("Unable to prepare primary claim submission health insurance form", false, "Failed to prepare primary claim submission health insurance form"));
        }
    }

    @ApiOperation(value = "Prepare Secondary Claim Submission Health Insurance Form")
    @PostMapping(path = "/prepareSecondaryClaimSubmissionHealthInsuranceForm", produces = "application/json")
    public String prepareSecondaryClaimSubmissionHealthInsuranceForm(@RequestParam("claimControlNumber") String claimControlNumber) {
        log.info("=======================POST(CONTROLLER) Prepare Secondary Claim Health Insurance Form==========================");
        String resultOutcomeJson = "";
        try {
            claimResponsesAndReportsV2Service.prepareSecondaryClaimSubmissionHealthInsuranceForm(claimControlNumber);
            System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

    @ApiOperation(value = "Prepare Primary Resubmission Claim Health Insurance Form")
    @PostMapping(path = "/preparePrimaryClaimReSubmissionHealthInsuranceForm", produces = "application/json")
    public String preparePrimaryClaimReSubmissionHealthInsuranceForm(@RequestParam("claimControlNumber") String claimControlNumber) {
        log.info("=======================POST(CONTROLLER) Prepare Primary Re-Submission Claim Health Insurance Form==========================");
        String resultOutcomeJson = "";
        try {
            claimResponsesAndReportsV2Service.preparePrimaryClaimReSubmissionHealthInsuranceForm(claimControlNumber);
            System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }
}
