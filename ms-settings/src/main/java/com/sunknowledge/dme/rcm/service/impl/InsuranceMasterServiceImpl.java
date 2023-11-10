package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterRepository;
import com.sunknowledge.dme.rcm.service.InsuranceMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceMaster}.
 */
@Service
@Transactional
public class InsuranceMasterServiceImpl implements InsuranceMasterService {

    private final Logger log = LoggerFactory.getLogger(InsuranceMasterServiceImpl.class);

    private final InsuranceMasterRepository insuranceMasterRepository;

    private final InsuranceMasterMapper insuranceMasterMapper;

    public InsuranceMasterServiceImpl(InsuranceMasterRepository insuranceMasterRepository, InsuranceMasterMapper insuranceMasterMapper) {
        this.insuranceMasterRepository = insuranceMasterRepository;
        this.insuranceMasterMapper = insuranceMasterMapper;
    }

    @Override
    public InsuranceMasterDTO save(InsuranceMasterDTO insuranceMasterDTO) {
        log.debug("Request to save InsuranceMaster : {}", insuranceMasterDTO);
        InsuranceMaster insuranceMaster = insuranceMasterMapper.toEntity(insuranceMasterDTO);
        insuranceMaster = insuranceMasterRepository.save(insuranceMaster);
        return insuranceMasterMapper.toDto(insuranceMaster);
    }

    @Override
    public InsuranceMasterDTO update(InsuranceMasterDTO insuranceMasterDTO) {
        log.debug("Request to save InsuranceMaster : {}", insuranceMasterDTO);
        InsuranceMaster insuranceMaster = insuranceMasterMapper.toEntity(insuranceMasterDTO);
        insuranceMaster = insuranceMasterRepository.save(insuranceMaster);
        return insuranceMasterMapper.toDto(insuranceMaster);
    }

    @Override
    public Optional<InsuranceMasterDTO> partialUpdate(InsuranceMasterDTO insuranceMasterDTO) {
        log.debug("Request to partially update InsuranceMaster : {}", insuranceMasterDTO);

        return insuranceMasterRepository
            .findById(insuranceMasterDTO.getInsuranceId())
            .map(existingInsuranceMaster -> {
                insuranceMasterMapper.partialUpdate(existingInsuranceMaster, insuranceMasterDTO);

                return existingInsuranceMaster;
            })
            .map(insuranceMasterRepository::save)
            .map(insuranceMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceMasters");
        return insuranceMasterRepository.findAll(pageable).map(insuranceMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceMasterDTO> findOne(Long id) {
        log.debug("Request to get InsuranceMaster : {}", id);
        return insuranceMasterRepository.findById(id).map(insuranceMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceMaster : {}", id);
        insuranceMasterRepository.deleteById(id);
    }
}
