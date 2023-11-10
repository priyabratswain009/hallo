package com.sunknowledge.dme.rcm.service.impl.lcd;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import com.sunknowledge.dme.rcm.dto.lcd.*;
import com.sunknowledge.dme.rcm.repository.lcd.DmeGroupChecklistMasterRepo;
import com.sunknowledge.dme.rcm.repository.lcd.SoLcdCoverageCriteriaTransactionRepo;
import com.sunknowledge.dme.rcm.repository.lcd.SoLcdDocRefTransactionRepo;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import com.sunknowledge.dme.rcm.service.lcd.LocalCoverageDeterminationsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class LocalCoverageDeterminationsServiceImpl implements LocalCoverageDeterminationsService {
    @Autowired
    private DmeGroupChecklistMasterRepo dmeGroupChecklistMasterRepository;

    @Autowired
    private SoLcdDocRefTransactionRepo soLcdDocRefTransactionRepository;

    @Autowired
    private SoLcdCoverageCriteriaTransactionRepo soLcdCoverageCriteriaTransactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Mono<ChecklistResultOutcomeDTO> createLCDDocumentNCoverageCheckListOnSalesOrderNhcpcsCode(Long salesOrderId, String hcpcsCode) throws ExecutionException, InterruptedException {
        log.info("=========================createLCDDocumentCheckListOnSalesOrderNhcpcsCode================================");
        List<DocumentChecklistDTO> documentChecklist = null;
        List<CoverageCriteriaChecklistDTO> coverageCriteriaChecklist = null;
        ChecklistResultOutcomeDTO checklistResultOutcome = new ChecklistResultOutcomeDTO();
        List<SoLcdDocRefTransactionDTO> soLcdDocRefTransactionDTOS = new ArrayList<SoLcdDocRefTransactionDTO>();
        List<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactionDTOS = new ArrayList<SoLcdCoverageCriteriaTransactionDTO>();
        try {
            List<SoLcdDocRefTransactionDTO> soLcdDocRefTransactions = soLcdDocRefTransactionRepository.getSoLcdDocRefTransactionsOnSalesOrderHcpcsCode(salesOrderId, hcpcsCode).collectList().toFuture().get();
            List<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactions = soLcdCoverageCriteriaTransactionRepository.getSoLcdCoverageCriteriaTransactionsOnSalesOrderHcpcsCode(salesOrderId, hcpcsCode).collectList().toFuture().get();

            if(soLcdDocRefTransactions.size() >= 1) {
                soLcdDocRefTransactions.stream().forEach(docRef -> {
                    SoLcdDocRefTransactionDTO soLcdDocRefTransaction = new SoLcdDocRefTransactionDTO();
                    BeanUtils.copyProperties(docRef, soLcdDocRefTransaction);
                    soLcdDocRefTransactionDTOS.add(soLcdDocRefTransaction);
                });
                checklistResultOutcome.setSoLcdDocRefTransactionDTOS(soLcdDocRefTransactionDTOS);
            }
            if(soLcdCoverageCriteriaTransactions.size() >= 1) {
                soLcdCoverageCriteriaTransactions.stream().forEach(coverageCriteria -> {
                    SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransactionDTO();
                    BeanUtils.copyProperties(coverageCriteria, soLcdCoverageCriteriaTransaction);
                    soLcdCoverageCriteriaTransactionDTOS.add(soLcdCoverageCriteriaTransaction);
                });
                checklistResultOutcome.setSoLcdCoverageCriteriaTransactionDTOS(soLcdCoverageCriteriaTransactionDTOS);
            }

            if(checklistResultOutcome.getSoLcdDocRefTransactionDTOS() != null || checklistResultOutcome.getSoLcdCoverageCriteriaTransactionDTOS() != null){
                log.info("================<<<<<<IF>>>>========================");
            }
            else{
                List<HcpcsDmeGroupItemDetailsDTO> hcpcsDmeGroupItemDetails = dmeGroupChecklistMasterRepository.getHcpcsDmeGroupItemDetailsOnSalesOrderNhcpcs(salesOrderId, hcpcsCode).collectList().toFuture().get();
                log.info("====hcpcsDmeGroupItemDetails SIZE=====>" +hcpcsDmeGroupItemDetails.size());
                if (hcpcsDmeGroupItemDetails.size() >= 1) {
                    log.info("================<<<<<<<ELSE>>>>>>========================");
                    for (HcpcsDmeGroupItemDetailsDTO hcpcsDmeGroupItem : hcpcsDmeGroupItemDetails) {
                        documentChecklist = dmeGroupChecklistMasterRepository.dmeGroupChecklistDocumentReferenceListOnCheckList(hcpcsDmeGroupItem.getChecklist_id(), hcpcsDmeGroupItem.getHcpcs_code()).collectList().toFuture().get();
                        coverageCriteriaChecklist = dmeGroupChecklistMasterRepository.dmeGroupChecklistCoverageCriteriaListOnCheckList(hcpcsDmeGroupItem.getChecklist_id(), hcpcsDmeGroupItem.getHcpcs_code()).collectList().toFuture().get();
                    }
                    checklistResultOutcome.setDocumentChecklist(documentChecklist);
                    checklistResultOutcome.setCoverageCriteriaChecklist(coverageCriteriaChecklist);
                    log.info("=====SIZE====>" + checklistResultOutcome.getDocumentChecklist().size());
                    log.info("=====SIZE====>" + checklistResultOutcome.getCoverageCriteriaChecklist().size());
                }
            }
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        catch(ExecutionException e){
            throw new RuntimeException(e);
        }
        return Mono.just(checklistResultOutcome);
    }

    @Override
    public Flux<ChecklistResultOutcomeDTO> saveLCDDocumentCheckedList(ChecklistInputDTO checklistInput) throws ExecutionException, InterruptedException {
        log.info("=========================saveLCDDocumentCheckedList================================");
        ChecklistResultOutcomeDTO checklistResultOutcomeDTO = new ChecklistResultOutcomeDTO();
        List<SoLcdDocRefTransaction> soLcdDocRefTransactions = new ArrayList<SoLcdDocRefTransaction>();
        List<SoLcdCoverageCriteriaTransaction> soLcdCoverageCriteriaTransactions = new ArrayList<SoLcdCoverageCriteriaTransaction>();
        List<SoLcdDocRefTransactionDTO> soLcdDocRefTransactionDTOS = new ArrayList<SoLcdDocRefTransactionDTO>();
        List<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactionDTOS = new ArrayList<SoLcdCoverageCriteriaTransactionDTO>();
        if(checklistInput != null){
            log.info("checklistInput.getDocumentCheck().size()=====>"+checklistInput.getDocumentCheck().size());
            for(int i = 0; i < checklistInput.getDocumentCheck().size(); i++) {
                log.info("checklistInput.getDocumentCheck().get(i).booleanValue()=====>" + checklistInput.getDocumentCheck().get(i).booleanValue());
                SoLcdDocRefTransaction soLcdDocumentReference = soLcdDocRefTransactionRepository.getDocumentReferenceOnSalesOrderItemNDocRef(checklistInput.getSalesOrderId(), checklistInput.getItemId(), checklistInput.getDocumentReferenceId().get(i)).toFuture().get();
                if (soLcdDocumentReference != null){
                    soLcdDocumentReference.setSoId(checklistInput.getSalesOrderId());
                    soLcdDocumentReference.setItemId(checklistInput.getItemId());
                    soLcdDocumentReference.setItemName(checklistInput.getItemName());
                    soLcdDocumentReference.setHcpcsCode(checklistInput.getHcpcsCode());
                    soLcdDocumentReference.setChecklistId(checklistInput.getChecklistId());
                    soLcdDocumentReference.setChecklistName(checklistInput.getChecklistName());
                    soLcdDocumentReference.setDocRefId(checklistInput.getDocumentReferenceId().get(i));
                    soLcdDocumentReference.setDocRefName(checklistInput.getDocumentReferenceName().get(i));
                    if (checklistInput.getDocumentCheck().get(i).booleanValue())
                        soLcdDocumentReference.setValue("1");
                    else
                        soLcdDocumentReference.setValue("0");
                    soLcdDocumentReference.setStatus("A");
                    soLcdDocumentReference.setUpdatedById(1L);
                    soLcdDocumentReference.setUpdatedByName("Bimal");
                    soLcdDocumentReference.setUpdatedDate(LocalDate.now());
                    soLcdDocumentReference = soLcdDocRefTransactionRepository.save(soLcdDocumentReference).toFuture().get();
                    SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = modelMapper.map(soLcdDocumentReference, SoLcdDocRefTransactionDTO.class);
                    soLcdDocRefTransactionDTOS.add(soLcdDocRefTransactionDTO);
                    System.out.println("1.=====>After Converting====>"+soLcdDocRefTransactionDTO.getDocRefName());
                }
                else{
                    SoLcdDocRefTransaction soLcdDocRefTransaction = new SoLcdDocRefTransaction();
                    soLcdDocRefTransaction.setSoId(checklistInput.getSalesOrderId());
                    soLcdDocRefTransaction.setItemId(checklistInput.getItemId());
                    soLcdDocRefTransaction.setItemName(checklistInput.getItemName());
                    soLcdDocRefTransaction.setHcpcsCode(checklistInput.getHcpcsCode());
                    soLcdDocRefTransaction.setChecklistId(checklistInput.getChecklistId());
                    soLcdDocRefTransaction.setChecklistName(checklistInput.getChecklistName());
                    soLcdDocRefTransaction.setDocRefId(checklistInput.getDocumentReferenceId().get(i));
                    soLcdDocRefTransaction.setDocRefName(checklistInput.getDocumentReferenceName().get(i));
                    if (checklistInput.getDocumentCheck().get(i).booleanValue())
                        soLcdDocRefTransaction.setValue("1");
                    else
                        soLcdDocRefTransaction.setValue("0");
                    soLcdDocRefTransaction.setStatus("A");
                    soLcdDocRefTransaction.setCreatedById(1L);
                    soLcdDocRefTransaction.setCreatedByName("Bimal");
                    soLcdDocRefTransaction.setCreatedDate(LocalDate.now());
                    soLcdDocRefTransaction = soLcdDocRefTransactionRepository.save(soLcdDocRefTransaction).toFuture().get();
                    SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = modelMapper.map(soLcdDocRefTransaction, SoLcdDocRefTransactionDTO.class);
                    soLcdDocRefTransactionDTOS.add(soLcdDocRefTransactionDTO);
                    System.out.println("2.=====>After Converting====>"+soLcdDocRefTransactionDTO.getDocRefName());
                }
            }
            checklistResultOutcomeDTO.setSoLcdDocRefTransactionDTOS(soLcdDocRefTransactionDTOS);

            for(int i = 0; i < checklistInput.getCoverageCheck().size(); i++){
                SoLcdCoverageCriteriaTransaction soLcdCoverageCriteria = soLcdCoverageCriteriaTransactionRepository.getCoverageCriteriaOnSalesOrderItemNCoverageCriteria(checklistInput.getSalesOrderId(), checklistInput.getItemId(), checklistInput.getDocumentReferenceId().get(i)).toFuture().get();
                if(soLcdCoverageCriteria != null) {
                    soLcdCoverageCriteria.setSoId(checklistInput.getSalesOrderId());
                    soLcdCoverageCriteria.setItemId(checklistInput.getItemId());
                    soLcdCoverageCriteria.setItemName(checklistInput.getItemName());
                    soLcdCoverageCriteria.setHcpcsCode(checklistInput.getHcpcsCode());
                    soLcdCoverageCriteria.setChecklistId(checklistInput.getChecklistId());
                    soLcdCoverageCriteria.setChecklistName(checklistInput.getChecklistName());
                    soLcdCoverageCriteria.setCoverageCriteriaId(checklistInput.getCoverageCriteriaId().get(i));
                    if (checklistInput.getCoverageCheck().get(i).booleanValue())
                        soLcdCoverageCriteria.setValue("1");
                    else
                        soLcdCoverageCriteria.setValue("0");
                    soLcdCoverageCriteria.setStatus("A");
                    soLcdCoverageCriteria.setUpdatedById(1L);
                    soLcdCoverageCriteria.setUpdatedByName("Bimal");
                    soLcdCoverageCriteria.setUpdatedDate(LocalDate.now());
                    soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteria).toFuture().get();
                    SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = modelMapper.map(soLcdCoverageCriteria, SoLcdCoverageCriteriaTransactionDTO.class);
                    soLcdCoverageCriteriaTransactionDTOS.add(soLcdCoverageCriteriaTransactionDTO);
                }
                else{
                    SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransaction = new SoLcdCoverageCriteriaTransaction();
                    soLcdCoverageCriteriaTransaction.setSoId(checklistInput.getSalesOrderId());
                    soLcdCoverageCriteriaTransaction.setItemId(checklistInput.getItemId());
                    soLcdCoverageCriteriaTransaction.setItemName(checklistInput.getItemName());
                    soLcdCoverageCriteriaTransaction.setHcpcsCode(checklistInput.getHcpcsCode());
                    soLcdCoverageCriteriaTransaction.setChecklistId(checklistInput.getChecklistId());
                    soLcdCoverageCriteriaTransaction.setChecklistName(checklistInput.getChecklistName());
                    soLcdCoverageCriteriaTransaction.setCoverageCriteriaId(checklistInput.getCoverageCriteriaId().get(i));
                    if (checklistInput.getCoverageCheck().get(i).booleanValue())
                        soLcdCoverageCriteriaTransaction.setValue("1");
                    else
                        soLcdCoverageCriteriaTransaction.setValue("0");
                    soLcdCoverageCriteriaTransaction.setStatus("A");
                    soLcdCoverageCriteriaTransaction.setCreatedById(1L);
                    soLcdCoverageCriteriaTransaction.setCreatedByName("Bimal");
                    soLcdCoverageCriteriaTransaction.setCreatedDate(LocalDate.now());
                    soLcdCoverageCriteriaTransactionRepository.save(soLcdCoverageCriteriaTransaction).toFuture().get();
                    SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = modelMapper.map(soLcdCoverageCriteriaTransaction, SoLcdCoverageCriteriaTransactionDTO.class);
                    soLcdCoverageCriteriaTransactionDTOS.add(soLcdCoverageCriteriaTransactionDTO);
                }
            }
            checklistResultOutcomeDTO.setSoLcdCoverageCriteriaTransactionDTOS(soLcdCoverageCriteriaTransactionDTOS);
        }
        return Flux.just(checklistResultOutcomeDTO);
    }
}
