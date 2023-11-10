package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceDocument;
import com.sunknowledge.dme.rcm.repository.InsuranceDocumentRepository;
import com.sunknowledge.dme.rcm.service.InsuranceDocumentService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceDocumentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceDocument}.
 */
@Service
@Transactional
public class InsuranceDocumentServiceImpl implements InsuranceDocumentService {

    private final Logger log = LoggerFactory.getLogger(InsuranceDocumentServiceImpl.class);

    private final InsuranceDocumentRepository insuranceDocumentRepository;

    private final InsuranceDocumentMapper insuranceDocumentMapper;

    public InsuranceDocumentServiceImpl(
        InsuranceDocumentRepository insuranceDocumentRepository,
        InsuranceDocumentMapper insuranceDocumentMapper
    ) {
        this.insuranceDocumentRepository = insuranceDocumentRepository;
        this.insuranceDocumentMapper = insuranceDocumentMapper;
    }

    @Override
    public InsuranceDocumentDTO save(InsuranceDocumentDTO insuranceDocumentDTO) {
        log.debug("Request to save InsuranceDocument : {}", insuranceDocumentDTO);
        InsuranceDocument insuranceDocument = insuranceDocumentMapper.toEntity(insuranceDocumentDTO);
        insuranceDocument = insuranceDocumentRepository.save(insuranceDocument);
        return insuranceDocumentMapper.toDto(insuranceDocument);
    }

    @Override
    public InsuranceDocumentDTO update(InsuranceDocumentDTO insuranceDocumentDTO) {
        log.debug("Request to save InsuranceDocument : {}", insuranceDocumentDTO);
        InsuranceDocument insuranceDocument = insuranceDocumentMapper.toEntity(insuranceDocumentDTO);
        insuranceDocument = insuranceDocumentRepository.save(insuranceDocument);
        return insuranceDocumentMapper.toDto(insuranceDocument);
    }

    @Override
    public Optional<InsuranceDocumentDTO> partialUpdate(InsuranceDocumentDTO insuranceDocumentDTO) {
        log.debug("Request to partially update InsuranceDocument : {}", insuranceDocumentDTO);

        return insuranceDocumentRepository
            .findById(insuranceDocumentDTO.getInsuranceDocumentId())
            .map(existingInsuranceDocument -> {
                insuranceDocumentMapper.partialUpdate(existingInsuranceDocument, insuranceDocumentDTO);

                return existingInsuranceDocument;
            })
            .map(insuranceDocumentRepository::save)
            .map(insuranceDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceDocuments");
        return insuranceDocumentRepository.findAll(pageable).map(insuranceDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceDocumentDTO> findOne(Long id) {
        log.debug("Request to get InsuranceDocument : {}", id);
        return insuranceDocumentRepository.findById(id).map(insuranceDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceDocument : {}", id);
        insuranceDocumentRepository.deleteById(id);
    }
}
