package com.sunknowledge.dme.rcm.web.rest.localcoveragedeterminations;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListCoverageCriteriaMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListDocumentReferenceMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CoverageCriteriaFileMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdCoverageCriteriaTransactionExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdCriteriaOutcomeDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdDocRefTransactionExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdDocumentRefInputDto;
import com.sunknowledge.dme.rcm.dto.lcd.LcdDocumentRefOutcomeDto;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DocumentsBySoIdOutputDTO;
import com.sunknowledge.dme.rcm.service.lcd.LocalCoverageDeterminationsService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.PatientDocumentSoMapServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Bimal K Sahoo
 * @since 06/03/2023
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class LocalCoverageDeterminationsResource {
    @Autowired
    PatientDocumentSoMapServiceExtended patientDocumentSoMapService;
    @Autowired
    private LocalCoverageDeterminationsService localCoverageDeterminationsService;

    /**
     * This API will help to find LCD Coverage Criteria Information against So ID and Item Group ID
     * When User Click LCD Criteria Button from UI.
     *
     * @param itemGroupId
     * @return list of LCD Coverage Criteria.
     */
    @GetMapping("/getLcdCoverageCriteria")
    public Mono<ServiceOutcome> getLcdCoverageCriteria(Long soId, Long itemGroupId) {
        try {
            // t_so_lcd_coverage_criteria_transaction
            List<LcdCoverageCriteriaTransactionExtendedDTO> transactionList = localCoverageDeterminationsService.getLcdCoverageCriteria(soId, itemGroupId).collectList().toFuture().get();
            // t_checklist_coverage_criteria_map
            List<CheckListCoverageCriteriaMapExtendedDTO> criteriaMapList = localCoverageDeterminationsService.getCheckListCoverageCriteria(itemGroupId).collectList().toFuture().get();

            if (criteriaMapList != null && criteriaMapList.size() > 0) {
                List<LcdCriteriaOutcomeDTO> lcdCoverageCriterialist = new ArrayList<>();
                Long slNo = 0l;
                String value = "0";
                for (CheckListCoverageCriteriaMapExtendedDTO list : criteriaMapList) {
                    LcdCriteriaOutcomeDTO lcdCriteriaOutcomeDTO = new LcdCriteriaOutcomeDTO();
                    lcdCriteriaOutcomeDTO.setSoId(soId);
                    lcdCriteriaOutcomeDTO.setChecklistId(list.getChecklistId());
                    lcdCriteriaOutcomeDTO.setChecklistName(list.getChecklistName());
                    lcdCriteriaOutcomeDTO.setCoverageCriteriaId(list.getCoverageCriteriaId());
                    if (transactionList != null && transactionList.size() > 0 && transactionList.get(slNo.intValue()).getValue() != null)
                        lcdCriteriaOutcomeDTO.setValue(transactionList.get(slNo.intValue()).getValue());
                    else lcdCriteriaOutcomeDTO.setValue("0");
                    lcdCriteriaOutcomeDTO.setItemGroupId(itemGroupId);
                    lcdCriteriaOutcomeDTO.setItemGroupName(list.getItemGroupName());
                    lcdCriteriaOutcomeDTO.setSlNo(++slNo);
                    lcdCriteriaOutcomeDTO.setCoverageCriteriaName(list.getCoverageCriteriaName());
                    lcdCriteriaOutcomeDTO.setCoverageCriteriaDetails(list.getCoverageCriteriaDetails());
                    lcdCoverageCriterialist.add(lcdCriteriaOutcomeDTO);
                }
                return Mono.just(new ServiceOutcome<Object>(lcdCoverageCriterialist.size() > 0 ? lcdCoverageCriterialist : null, true, ""));
            } else
                return Mono.just(new ServiceOutcome<LcdCriteriaOutcomeDTO>(null, false, "LCD Coverage Criteria Not Found."));
        } catch (InterruptedException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This API will help to Save LCD Coverage Criteria when User Click CheckBox Button from LCD Criteria Pop Up.
     *
     * @param lcdCoverageCriteriaTransactionExtendedDTO
     * @return
     */
    @PatchMapping(value = "saveLcdCoverageCriteria", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ServiceOutcome> saveLcdCoverageCriteria(
        @RequestBody @Valid List<LcdCoverageCriteriaTransactionExtendedDTO> lcdCoverageCriteriaTransactionExtendedDTO) {
        try {
            if (lcdCoverageCriteriaTransactionExtendedDTO != null && lcdCoverageCriteriaTransactionExtendedDTO.size() > 0) {
                // t_so_lcd_coverage_criteria_transaction
                List<LcdCoverageCriteriaTransactionExtendedDTO> transactionList =
                    localCoverageDeterminationsService.getLcdCoverageCriteria(lcdCoverageCriteriaTransactionExtendedDTO.get(0).getSoId(),
                        lcdCoverageCriteriaTransactionExtendedDTO.get(0).getItemGroupId()).collectList().toFuture().get();

                // t_so_lcd_doc_ref_transaction
                List<LcdDocRefTransactionExtendedDTO> lcdDocRefTransactionExtendedDTOList =
                    localCoverageDeterminationsService.getAllLcdDocumentReference(lcdCoverageCriteriaTransactionExtendedDTO.get(0).getSoId(),
                        lcdCoverageCriteriaTransactionExtendedDTO.get(0).getItemGroupId()).collectList().toFuture().get();

                if (lcdDocRefTransactionExtendedDTOList != null && lcdDocRefTransactionExtendedDTOList.size() > 0) {
                    List<LcdDocRefTransactionExtendedDTO> dtoList = new ArrayList<>();
                    for (int j = 0; j < lcdCoverageCriteriaTransactionExtendedDTO.size(); j++) {
                        if (lcdCoverageCriteriaTransactionExtendedDTO.get(j).getValue() != null && lcdCoverageCriteriaTransactionExtendedDTO.get(j).getValue().equals("0")) {
                            for (LcdDocRefTransactionExtendedDTO dto : lcdDocRefTransactionExtendedDTOList) {
                                if (dto.getCoverageCriteriaId().equals(lcdCoverageCriteriaTransactionExtendedDTO.get(j).getCoverageCriteriaId())) {
                                    LcdDocRefTransactionExtendedDTO lcdDocRefTransactionExtendedDTO = new LcdDocRefTransactionExtendedDTO();
                                    BeanUtils.copyProperties(dto, lcdDocRefTransactionExtendedDTO);
                                    lcdDocRefTransactionExtendedDTO.setStatus("InActive");
                                    dtoList.add(lcdDocRefTransactionExtendedDTO);
                                }
                            }
                        }
                    }
                    // Update t_so_lcd_doc_ref_transaction
                    if (dtoList != null && dtoList.size() > 0)
                        localCoverageDeterminationsService.saveLcdDocumentReferenceTransaction(dtoList).toFuture().get();
                }

                // t_coverage_criteria_file_map table.
                List<CoverageCriteriaFileMapExtendedDTO> coverageCriteriaFileMapExtendedDTOList =
                    localCoverageDeterminationsService.getAllCoverageCriteriaFileMap(lcdCoverageCriteriaTransactionExtendedDTO.get(0).getSoId(),
                        lcdCoverageCriteriaTransactionExtendedDTO.get(0).getItemGroupId()).collectList().toFuture().get();
                if (coverageCriteriaFileMapExtendedDTOList != null && coverageCriteriaFileMapExtendedDTOList.size() > 0) {
                    List<CoverageCriteriaFileMapExtendedDTO> dtoList = new ArrayList<>();
                    for (int j = 0; j < lcdCoverageCriteriaTransactionExtendedDTO.size(); j++) {
                        if (lcdCoverageCriteriaTransactionExtendedDTO.get(j).getValue() != null && lcdCoverageCriteriaTransactionExtendedDTO.get(j).getValue().equals("0")) {
                            for (CoverageCriteriaFileMapExtendedDTO dto : coverageCriteriaFileMapExtendedDTOList) {
                                if (dto.getCoverageCriteriaId().equals(lcdCoverageCriteriaTransactionExtendedDTO.get(j).getCoverageCriteriaId())) {
                                    CoverageCriteriaFileMapExtendedDTO coverageCriteriaFileMapExtendedDTO = new CoverageCriteriaFileMapExtendedDTO();
                                    BeanUtils.copyProperties(dto, coverageCriteriaFileMapExtendedDTO);
                                    coverageCriteriaFileMapExtendedDTO.setStatus("InActive");
                                    dtoList.add(coverageCriteriaFileMapExtendedDTO);
                                }
                            }
                        }
                    }
                    // Update t_coverage_criteria_file_map
                    if (dtoList != null && dtoList.size() > 0)
                        localCoverageDeterminationsService.saveCoverageCriteriaFileMapList(dtoList).toFuture().get();
                }

                if (transactionList != null && transactionList.size() > 0) {
                    for (int i = 0; i < transactionList.size(); i++) {
                        BeanUtils.copyProperties(transactionList.get(i), lcdCoverageCriteriaTransactionExtendedDTO.get(i), "value");
                    }
                }

                // Update or Insert into t_so_lcd_coverage_criteria_transaction
                return localCoverageDeterminationsService.saveLcdCoverageCriteria(lcdCoverageCriteriaTransactionExtendedDTO);
            } else return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
        } catch (InterruptedException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This API will help to Show the Document Reference Name List from the Hyperlink.
     *
     * @param itemGroupId
     * @return
     */
    @GetMapping("/getDocumentReferenceNameList")
    public Mono<ServiceOutcome> getDocumentReferenceNameList(Long itemGroupId) {
        try {
            // t_checklist_document_reference_map
            List<CheckListDocumentReferenceMapExtendedDTO> checkListDocumentReferenceMapExtendedDTOList =
                localCoverageDeterminationsService.getCheckListDocumentReference(itemGroupId).collectList().toFuture().get();
            if (checkListDocumentReferenceMapExtendedDTOList != null && checkListDocumentReferenceMapExtendedDTOList.size() > 0)
                return Mono.just(new ServiceOutcome(checkListDocumentReferenceMapExtendedDTOList, true, ""));
            else return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
        } catch (InterruptedException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This API will help to find LCD Document Reference and Shown the Data when User Click on 'LCD Document Ref.' Button.
     *
     * @param soId
     * @param itemGroupId
     * @param coverageCriteriaId
     * @return
     */
    @GetMapping("/getLcdDocumentRef")
    public Mono<ServiceOutcome> getLcdDocumentRef(Long soId, Long itemGroupId, Long coverageCriteriaId) {
        try {
            // t_coverage_criteria_file_map
            List<CoverageCriteriaFileMapExtendedDTO> coverageCriteriaFileMapExtendedDTOList =
                localCoverageDeterminationsService.getCoverageCriteriaFileMap(soId, itemGroupId, coverageCriteriaId).collectList().toFuture().get();
            // t_checklist_document_reference_map
            List<CheckListDocumentReferenceMapExtendedDTO> checkListDocumentReferenceMapExtendedDTOList =
                localCoverageDeterminationsService.getCheckListDocumentReference(itemGroupId).collectList().toFuture().get();
            // All File List with Map format
            List<Map<String, Object>> totalFileListMap =
                patientDocumentSoMapService.getAllDocumentsBySoID(soId)
                    .collectList().toFuture().get()
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(i -> i.getDocumentName() != null && !i.getDocumentName().isEmpty())
                    .map(DocumentsBySoIdOutputDTO::getDocumentName)
                    .distinct()
                    .map(obj -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", obj);
                        map.put("title", obj);
                        return map;
                    }).collect(Collectors.toList());
            LcdDocumentRefOutcomeDto lcdDocumentRefOutcomeDto = new LcdDocumentRefOutcomeDto();

            List<Map<String, Object>> documentReferenceListMap = checkListDocumentReferenceMapExtendedDTOList.stream().filter(Objects::nonNull).map(obj -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", obj.getDocumentReferenceId());
                map.put("title", obj.getDocumentReferenceName());
                return map;
            }).collect(Collectors.toList());
            lcdDocumentRefOutcomeDto.setDocumentReferenceListMap(documentReferenceListMap);

            LcdDocRefTransactionExtendedDTO lcdDocRefTransactionExtendedDTO =
                localCoverageDeterminationsService.getLcdDocumentReference(soId, itemGroupId, coverageCriteriaId).toFuture().get();
            lcdDocumentRefOutcomeDto.setDocumentReference(lcdDocRefTransactionExtendedDTO != null ? lcdDocRefTransactionExtendedDTO.getDocRefName() : "");
            lcdDocumentRefOutcomeDto.setDocumentReferenceId(lcdDocRefTransactionExtendedDTO != null ? lcdDocRefTransactionExtendedDTO.getDocRefId() : 0L);

            // Set All File List With Map Format.
            lcdDocumentRefOutcomeDto.setTotalFileListMap(totalFileListMap);

            List<String> fileList = new ArrayList<>();
            fileList.addAll(coverageCriteriaFileMapExtendedDTOList.stream()
                .filter(i -> i.getCoverageCriteriaId().equals(coverageCriteriaId))
                .map(j -> j.getFileName())
                .collect(Collectors.toList()));
            lcdDocumentRefOutcomeDto.setExistingFileList(fileList);

            Optional<String> description = coverageCriteriaFileMapExtendedDTOList.stream()
                .filter(Objects::nonNull)
                .flatMap(dto -> dto.getCoverageCriteriaNotes() != null ? Stream.of(dto.getCoverageCriteriaNotes()) : Stream.empty())
                .findFirst();
            lcdDocumentRefOutcomeDto.setCoverageCriteriaNotes(description.isPresent() ? description.get() : "");
            lcdDocumentRefOutcomeDto.setSoId(soId);
            lcdDocumentRefOutcomeDto.setItemGroupId(itemGroupId);
            lcdDocumentRefOutcomeDto.setCoverageCriteriaId(coverageCriteriaId);

            return Mono.just(new ServiceOutcome(lcdDocumentRefOutcomeDto, true, ""));

        } catch (InterruptedException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveLcdDocumentWithCriteria", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ServiceOutcome> saveLcdDocumentWithCriteria(
        @RequestBody @Valid LcdDocumentRefInputDto lcdDocumentRefInputDto) {
        try {
            if (Objects.nonNull(lcdDocumentRefInputDto)) {
                // t_so_lcd_doc_ref_transaction
                LcdDocRefTransactionExtendedDTO lcdDocRefTransactionExtendedDTO =
                    localCoverageDeterminationsService.getLcdDocumentReference(lcdDocumentRefInputDto.getSoId(),
                        lcdDocumentRefInputDto.getItemGroupId(),
                        lcdDocumentRefInputDto.getCoverageCriteriaId()).toFuture().get();
                List<LcdDocRefTransactionExtendedDTO> dtoList = new ArrayList<>();
                // t_checklist_document_reference_map
                List<CheckListDocumentReferenceMapExtendedDTO> checkListDocumentReferenceMapExtendedDTOList =
                    localCoverageDeterminationsService.getCheckListDocumentReference(lcdDocumentRefInputDto.getItemGroupId()).collectList().toFuture().get();
                AtomicReference<CheckListDocumentReferenceMapExtendedDTO> checkListDocumentReferenceMapExtendedDTO = new AtomicReference<>(new CheckListDocumentReferenceMapExtendedDTO());
                if (checkListDocumentReferenceMapExtendedDTOList != null && !checkListDocumentReferenceMapExtendedDTOList.isEmpty()) {
                    Optional<CheckListDocumentReferenceMapExtendedDTO> existingDTO = checkListDocumentReferenceMapExtendedDTOList.stream()
                        .filter(Objects::nonNull)
                        .filter(dto -> lcdDocumentRefInputDto.getDocumentReferenceId().equals(dto.getDocumentReferenceId()))
                        .findFirst();
                    existingDTO.ifPresent(dto -> checkListDocumentReferenceMapExtendedDTO.set(dto));
                }
                if (Objects.nonNull(lcdDocRefTransactionExtendedDTO)) {
                    lcdDocRefTransactionExtendedDTO.setDocRefId(lcdDocumentRefInputDto.getDocumentReferenceId());
                    lcdDocRefTransactionExtendedDTO.setDocRefName(lcdDocumentRefInputDto.getDocumentReference());
                    lcdDocRefTransactionExtendedDTO.setValue("1");
                    dtoList.add(lcdDocRefTransactionExtendedDTO);
                } else {
                    LcdDocRefTransactionExtendedDTO dto = new LcdDocRefTransactionExtendedDTO();
                    dto.setSoId(lcdDocumentRefInputDto.getSoId());
                    dto.setChecklistId(checkListDocumentReferenceMapExtendedDTO.get().getChecklistId());
                    dto.setChecklistName(checkListDocumentReferenceMapExtendedDTO.get().getChecklistName());
                    dto.setDocRefId(lcdDocumentRefInputDto.getDocumentReferenceId());
                    dto.setDocRefName(lcdDocumentRefInputDto.getDocumentReference());
                    dto.setValue("1");
                    dto.setStatus("Active");
                    dto.setItemGroupId(lcdDocumentRefInputDto.getItemGroupId());
                    dto.setItemGroupName(checkListDocumentReferenceMapExtendedDTO.get().getItemGroupName());
                    dto.setCoverageCriteriaId(lcdDocumentRefInputDto.getCoverageCriteriaId());
                    dtoList.add(dto);
                }
                // Update or Insert t_so_lcd_doc_ref_transaction.
                localCoverageDeterminationsService.saveLcdDocumentReferenceTransaction(dtoList).toFuture().get();

                // t_coverage_criteria_file_map
                List<CoverageCriteriaFileMapExtendedDTO> existingList =
                    localCoverageDeterminationsService.getCoverageCriteriaFileMap(lcdDocumentRefInputDto.getSoId(),
                        lcdDocumentRefInputDto.getItemGroupId(),
                        lcdDocumentRefInputDto.getCoverageCriteriaId()).collectList().toFuture().get();
                existingList.stream()
                    .forEach(list -> list.setStatus("InActive"));

                // Update t_coverage_criteria_file_map table with Status InActive.
                localCoverageDeterminationsService.saveCoverageCriteriaFileMapList(existingList).toFuture().get();

                existingList.clear();
                if (Objects.nonNull(checkListDocumentReferenceMapExtendedDTO)) {
                    existingList = lcdDocumentRefInputDto.getExistingFileList()
                        .stream()
                        .map(fileName -> {
                            CoverageCriteriaFileMapExtendedDTO coverageCriteriaFileMapExtendedDTO = new CoverageCriteriaFileMapExtendedDTO();
                            coverageCriteriaFileMapExtendedDTO.setChecklistId(checkListDocumentReferenceMapExtendedDTO.get().getChecklistId());
                            coverageCriteriaFileMapExtendedDTO.setChecklistName(checkListDocumentReferenceMapExtendedDTO.get().getChecklistName());
                            coverageCriteriaFileMapExtendedDTO.setDocumentReferenceId(lcdDocumentRefInputDto.getDocumentReferenceId());
                            coverageCriteriaFileMapExtendedDTO.setDocumentReferenceName(lcdDocumentRefInputDto.getDocumentReference());
                            coverageCriteriaFileMapExtendedDTO.setFileName(fileName);
                            coverageCriteriaFileMapExtendedDTO.setStatus("Active");
                            coverageCriteriaFileMapExtendedDTO.setFileReference(fileName);
                            coverageCriteriaFileMapExtendedDTO.setCoverageCriteriaNotes(lcdDocumentRefInputDto.getCoverageCriteriaNotes());
                            coverageCriteriaFileMapExtendedDTO.setSoId(lcdDocumentRefInputDto.getSoId());
                            coverageCriteriaFileMapExtendedDTO.setItemGroupId(lcdDocumentRefInputDto.getItemGroupId());
                            coverageCriteriaFileMapExtendedDTO.setItemGroupName(checkListDocumentReferenceMapExtendedDTO.get().getItemGroupName());
                            coverageCriteriaFileMapExtendedDTO.setCoverageCriteriaId(lcdDocumentRefInputDto.getCoverageCriteriaId());
                            return coverageCriteriaFileMapExtendedDTO;
                        }).collect(Collectors.toList());
                }

                // Insert t_coverage_criteria_file_map table.
                ServiceOutcome serviceOutcome = localCoverageDeterminationsService.saveCoverageCriteriaFileMapList(existingList).toFuture().get();
                JSONObject jsonObj = new JSONObject();
                if (serviceOutcome.getOutcome()) {
                    jsonObj.put("data", serviceOutcome.getData());
                    jsonObj.put("message", "Operation Completed Successfully.");
                    jsonObj.put("deletionReason", null);
                    jsonObj.put("isValid", true);
                }
                return Mono.just(new ServiceOutcome(jsonObj, true, " "));

            } else return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
        } catch (InterruptedException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error(" Exception : " + e);
            throw new RuntimeException(e);
        }
    }

}
