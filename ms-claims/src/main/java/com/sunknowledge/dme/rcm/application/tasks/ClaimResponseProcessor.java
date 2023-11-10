package com.sunknowledge.dme.rcm.application.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimTransactionOutcome;
import com.sunknowledge.dme.rcm.service.claimservice.ClaimResponsesAndReportsV2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
/**
 * @author Bimal K Sahoo
 * @since 04/08/2023
 *
 */
@Configuration
@EnableScheduling
@Component(value = "claimResponseProcessor")
@Slf4j
public class ClaimResponseProcessor {
    @Autowired
    private ClaimResponsesAndReportsV2Service claimResponsesAndReportsV2Service;

    public void processActualClaimResponsesReports() {
        log.info("=======================POST(CONTROLLER) Process Medical Network Claims Responses and Reports V2 API(Change Healthcare)==========================");
        String resultOutcomeJson = "";
        try {
            ServiceOutcome<ArrayList<ActualClaimResponseReport>> routcome = claimResponsesAndReportsV2Service.processActualClaimResponsesReport();
            if(routcome != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                resultOutcomeJson = ow.writeValueAsString(routcome);

                ClaimTransactionOutcome transactionOutcome = claimResponsesAndReportsV2Service.saveClaimTransaction(routcome.getData());
                if(transactionOutcome != null) {
                    resultOutcomeJson = ow.writeValueAsString(transactionOutcome);
                    claimResponsesAndReportsV2Service.claimSubmissionStatusOperation(transactionOutcome);
                }
            }
            System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
