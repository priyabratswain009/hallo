package com.sunknowledge.dme.rcm.service.impl.lcd;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListCoverageCriteriaMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CheckListDocumentReferenceMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.CoverageCriteriaFileMapExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdCoverageCriteriaTransactionExtendedDTO;
import com.sunknowledge.dme.rcm.dto.lcd.LcdDocRefTransactionExtendedDTO;
import com.sunknowledge.dme.rcm.repository.lcd.ChecklistCoverageCriteriaMapRepo;
import com.sunknowledge.dme.rcm.repository.lcd.ChecklistDocumentReferenceMapRepo;
import com.sunknowledge.dme.rcm.repository.lcd.CoverageCriteriaFileMapRepo;
import com.sunknowledge.dme.rcm.repository.lcd.SoLcdCoverageCriteriaTransactionRepo;
import com.sunknowledge.dme.rcm.repository.lcd.SoLcdDocRefTransactionRepo;
import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import com.sunknowledge.dme.rcm.service.lcd.LocalCoverageDeterminationsService;
import com.sunknowledge.dme.rcm.service.mapper.CoverageCriteriaFileMapMapper;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdCoverageCriteriaTransactionMapper;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdDocRefTransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class LocalCoverageDeterminationsServiceImpl implements LocalCoverageDeterminationsService {
    @Autowired
    SoLcdCoverageCriteriaTransactionMapper soLcdCoverageCriteriaTransactionMapper;
    @Autowired
    SoLcdDocRefTransactionMapper soLcdDocRefTransactionMapper;
    @Autowired
    ChecklistCoverageCriteriaMapRepo checklistCoverageCriteriaMapRepository;
    @Autowired
    ChecklistDocumentReferenceMapRepo checklistDocumentReferenceMapRepository;
    @Autowired
    private SoLcdDocRefTransactionRepo soLcdDocRefTransactionRepository;
    @Autowired
    private SoLcdCoverageCriteriaTransactionRepo soLcdCoverageCriteriaTransactionRepository;
    @Autowired
    private CoverageCriteriaFileMapRepo coverageCriteriaFileMapRepository;
    @Autowired
    private CoverageCriteriaFileMapMapper coverageCriteriaFileMapMapper;

    @Override
    public Flux<CheckListCoverageCriteriaMapExtendedDTO> getCheckListCoverageCriteria(Long itemGroupId) {
        return checklistCoverageCriteriaMapRepository.getCheckListCoverageCriteria(itemGroupId);
    }

    @Override
    public Flux<CheckListDocumentReferenceMapExtendedDTO> getCheckListDocumentReference(Long itemGroupId) {
        return checklistDocumentReferenceMapRepository.getCheckListDocumentReference(itemGroupId);
    }

    @Override
    public Flux<LcdCoverageCriteriaTransactionExtendedDTO> getLcdCoverageCriteria(Long soId, Long itemGroupId) {
        return soLcdCoverageCriteriaTransactionRepository.getLcdCoverageCriteria(soId, itemGroupId);
    }

    @Override
    public Mono<LcdDocRefTransactionExtendedDTO> getLcdDocumentReference(Long soId, Long itemGroupId, Long coverageCriteriaId) {
        return soLcdDocRefTransactionRepository.getLcdDocumentReference(soId, itemGroupId, coverageCriteriaId);
    }

    @Override
    public Flux<LcdDocRefTransactionExtendedDTO> getAllLcdDocumentReference(Long soId, Long itemGroupId) {
        return soLcdDocRefTransactionRepository.getAllLcdDocumentReference(soId, itemGroupId);
    }

    @Override
    public Mono<ServiceOutcome> saveLcdCoverageCriteria(List<LcdCoverageCriteriaTransactionExtendedDTO> lcdCoverageCriteriaTransactionExtendedDTO) {

        if (lcdCoverageCriteriaTransactionExtendedDTO != null && !lcdCoverageCriteriaTransactionExtendedDTO.isEmpty()) {
            List<SoLcdCoverageCriteriaTransactionDTO> soLcdCoverageCriteriaTransactionDTOList = new ArrayList<SoLcdCoverageCriteriaTransactionDTO>();
            for (LcdCoverageCriteriaTransactionExtendedDTO transactionDto : lcdCoverageCriteriaTransactionExtendedDTO) {
                SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = new SoLcdCoverageCriteriaTransactionDTO();
                if (!Objects.nonNull(transactionDto.getSoLcdCoverageCriteriaTransactionUuid())
                    && (!Objects.nonNull(transactionDto.getSoLcdCoverageCriteriaTransactionId()) || transactionDto.getSoLcdCoverageCriteriaTransactionId() <= 0)) {
                    CommonUtilities.dtoTrimmer(transactionDto);
                    BeanUtils.copyProperties(transactionDto, soLcdCoverageCriteriaTransactionDTO);
                    soLcdCoverageCriteriaTransactionDTO.setStatus("Active");
                    soLcdCoverageCriteriaTransactionDTO.setCreatedDate(LocalDate.now());
                    soLcdCoverageCriteriaTransactionDTO.setCreatedById(79L);
                    soLcdCoverageCriteriaTransactionDTO.setCreatedByName("Pritam");
                    soLcdCoverageCriteriaTransactionDTO.setSoLcdCoverageCriteriaTransactionUuid(UUID.randomUUID());
                    soLcdCoverageCriteriaTransactionDTOList.add(soLcdCoverageCriteriaTransactionDTO);
                } else {
                    CommonUtilities.dtoTrimmer(transactionDto);
                    BeanUtils.copyProperties(transactionDto, soLcdCoverageCriteriaTransactionDTO);
                    soLcdCoverageCriteriaTransactionDTO.setUpdatedDate(LocalDate.now());
                    soLcdCoverageCriteriaTransactionDTO.setUpdatedById(79L);
                    soLcdCoverageCriteriaTransactionDTO.setUpdatedByName("Pritam");
                    soLcdCoverageCriteriaTransactionDTOList.add(soLcdCoverageCriteriaTransactionDTO);
                }

            }
            return soLcdCoverageCriteriaTransactionRepository.saveAll(soLcdCoverageCriteriaTransactionMapper.toEntity(soLcdCoverageCriteriaTransactionDTOList))
                .map(soLcdCoverageCriteriaTransactionMapper::toDto)
                .collectList()
                .map(i -> new ServiceOutcome(i, true, ""));
            //soLcdCoverageCriteriaTransactionRepository.saveAll(soLcdCoverageCriteriaTransactionMapper.toEntity(soLcdCoverageCriteriaTransactionDTOList));
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
        }

    }

    @Override
    public Mono<ServiceOutcome> saveLcdDocumentReferenceTransaction(List<LcdDocRefTransactionExtendedDTO> lcdDocRefTransactionExtendedDTO) {
        if (lcdDocRefTransactionExtendedDTO != null && lcdDocRefTransactionExtendedDTO.size() > 0) {
            List<SoLcdDocRefTransactionDTO> soLcdDocRefTransactionDTOList = new ArrayList<>();
            for (LcdDocRefTransactionExtendedDTO transactionDto : lcdDocRefTransactionExtendedDTO) {
                SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = new SoLcdDocRefTransactionDTO();
                CommonUtilities.dtoTrimmer(transactionDto);
                BeanUtils.copyProperties(transactionDto, soLcdDocRefTransactionDTO);
                if (Objects.nonNull(soLcdDocRefTransactionDTO.getSoLcdDocRefTransactionUuid()) && Objects.nonNull(soLcdDocRefTransactionDTO.getSoLcdDocRefId())) {
                    soLcdDocRefTransactionDTO.setUpdatedById(79L);
                    soLcdDocRefTransactionDTO.setUpdatedByName("Pritam");
                    soLcdDocRefTransactionDTO.setUpdatedDate(LocalDate.now());
                    soLcdDocRefTransactionDTOList.add(soLcdDocRefTransactionDTO);
                } else {
                    soLcdDocRefTransactionDTO.setCreatedDate(LocalDate.now());
                    soLcdDocRefTransactionDTO.setCreatedById(79L);
                    soLcdDocRefTransactionDTO.setCreatedByName("Pritam");
                    soLcdDocRefTransactionDTO.setSoLcdDocRefTransactionUuid(UUID.randomUUID());
                    soLcdDocRefTransactionDTOList.add(soLcdDocRefTransactionDTO);
                }
            }
            return soLcdDocRefTransactionRepository.saveAll(soLcdDocRefTransactionMapper.toEntity(soLcdDocRefTransactionDTOList))
                .collectList()
                .map(soLcdDocRefTransactionMapper::toDto)
                .map(i -> new ServiceOutcome(i, true, ""));
        } else return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
    }

    @Override
    public Flux<CoverageCriteriaFileMapExtendedDTO> getCoverageCriteriaFileMap(Long soId, Long itemGroupId, Long coverageCriteriaId) {
        return coverageCriteriaFileMapRepository.getCoverageCriteriaFileMap(soId, itemGroupId, coverageCriteriaId);
    }

    @Override
    public Flux<CoverageCriteriaFileMapExtendedDTO> getAllCoverageCriteriaFileMap(Long soId, Long itemGroupId) {
        return coverageCriteriaFileMapRepository.getAllCoverageCriteriaFileMap(soId, itemGroupId);
    }

    @Override
    public Mono<ServiceOutcome> saveCoverageCriteriaFileMapList(List<CoverageCriteriaFileMapExtendedDTO> coverageCriteriaFileMapExtendedDTO) {
        if (coverageCriteriaFileMapExtendedDTO != null && coverageCriteriaFileMapExtendedDTO.size() > 0) {
            List<CoverageCriteriaFileMapDTO> coverageCriteriaFileMapDTOList = new ArrayList<>();
            for (CoverageCriteriaFileMapExtendedDTO transactionDto : coverageCriteriaFileMapExtendedDTO) {
                CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = new CoverageCriteriaFileMapDTO();
                CommonUtilities.dtoTrimmer(transactionDto);
                BeanUtils.copyProperties(transactionDto, coverageCriteriaFileMapDTO);
                if (Objects.nonNull(transactionDto.getCoverageCriteriaFileMapUuid())
                    && (Objects.nonNull(transactionDto.getCoverageCriteriaFileMapId()))) {
                    coverageCriteriaFileMapDTO.setUpdatedDate(LocalDate.now());
                    coverageCriteriaFileMapDTO.setUpdatedById(79L);
                    coverageCriteriaFileMapDTO.setUpdatedByName("Pritam");
                    coverageCriteriaFileMapDTOList.add(coverageCriteriaFileMapDTO);
                } else {
                    coverageCriteriaFileMapDTO.setCreatedDate(LocalDate.now());
                    coverageCriteriaFileMapDTO.setCreatedById(79L);
                    coverageCriteriaFileMapDTO.setCreatedByName("Pritam");
                    coverageCriteriaFileMapDTO.setCoverageCriteriaFileMapUuid(UUID.randomUUID());
                    coverageCriteriaFileMapDTOList.add(coverageCriteriaFileMapDTO);
                }
            }
            return coverageCriteriaFileMapRepository.saveAll(coverageCriteriaFileMapMapper.toEntity(coverageCriteriaFileMapDTOList)).map(coverageCriteriaFileMapMapper
                ::toDto).collectList().map(i -> new ServiceOutcome(i, true, ""));
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Unsuccessful "));
        }
    }

}
