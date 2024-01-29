package com.sunknowledge.dme.rcm.service.lcd;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListCoverageCriteriaMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListDocumentReferenceMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CoverageCriteriaFileMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdCoverageCriteriaTransactionExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdDocRefTransactionExtendedDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LocalCoverageDeterminationsService {

    Flux<CheckListCoverageCriteriaMapExtendedDTO> getCheckListCoverageCriteria(Long itemGroupId);

    Flux<CheckListDocumentReferenceMapExtendedDTO> getCheckListDocumentReference(Long itemGroupId);

    Flux<LcdCoverageCriteriaTransactionExtendedDTO> getLcdCoverageCriteria(Long soId, Long itemGroupId);

    Mono<LcdDocRefTransactionExtendedDTO> getLcdDocumentReference(Long soId, Long itemGroupId, Long coverageCriteriaId);

    Flux<LcdDocRefTransactionExtendedDTO> getAllLcdDocumentReference(Long soId, Long itemGroupId);

    Mono<ServiceOutcome> saveLcdCoverageCriteria(List<LcdCoverageCriteriaTransactionExtendedDTO> lcdCoverageCriteriaTransactionExtendedDTO);

    Mono<ServiceOutcome> saveLcdDocumentReferenceTransaction(List<LcdDocRefTransactionExtendedDTO> lcdDocRefTransactionExtendedDTO);

    Flux<CoverageCriteriaFileMapExtendedDTO> getCoverageCriteriaFileMap(Long soId, Long itemGroupId, Long coverageCriteriaId);

    Flux<CoverageCriteriaFileMapExtendedDTO> getAllCoverageCriteriaFileMap(Long soId, Long itemGroupId);

    Mono<ServiceOutcome> saveCoverageCriteriaFileMapList(List<CoverageCriteriaFileMapExtendedDTO> coverageCriteriaFileMapExtendedDTO);
}
