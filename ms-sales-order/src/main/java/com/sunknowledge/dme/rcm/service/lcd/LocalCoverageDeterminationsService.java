package com.sunknowledge.dme.rcm.service.lcd;

import com.sunknowledge.dme.rcm.dto.lcd.ChecklistInputDTO;
import com.sunknowledge.dme.rcm.dto.lcd.ChecklistResultOutcomeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface LocalCoverageDeterminationsService {
    Mono<ChecklistResultOutcomeDTO> createLCDDocumentNCoverageCheckListOnSalesOrderNhcpcsCode(Long salesOrderId, String hcpcsCode) throws ExecutionException, InterruptedException;
    Flux<ChecklistResultOutcomeDTO> saveLCDDocumentCheckedList(ChecklistInputDTO checklistInput) throws ExecutionException, InterruptedException;
}
