package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.InsuranceGroupMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceGroupMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceGroupMaster}.
 */
@Service
@Transactional
public class InsuranceGroupMasterServiceImpl implements InsuranceGroupMasterService {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterServiceImpl.class);

    private final InsuranceGroupMasterRepository insuranceGroupMasterRepository;

    private final InsuranceGroupMasterMapper insuranceGroupMasterMapper;

    public InsuranceGroupMasterServiceImpl(
        InsuranceGroupMasterRepository insuranceGroupMasterRepository,
        InsuranceGroupMasterMapper insuranceGroupMasterMapper
    ) {
        this.insuranceGroupMasterRepository = insuranceGroupMasterRepository;
        this.insuranceGroupMasterMapper = insuranceGroupMasterMapper;
    }

    @Override
    public InsuranceGroupMasterDTO save(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        log.debug("Request to save InsuranceGroupMaster : {}", insuranceGroupMasterDTO);
        InsuranceGroupMaster insuranceGroupMaster = insuranceGroupMasterMapper.toEntity(insuranceGroupMasterDTO);
        insuranceGroupMaster = insuranceGroupMasterRepository.save(insuranceGroupMaster);
        return insuranceGroupMasterMapper.toDto(insuranceGroupMaster);
    }

    @Override
    public InsuranceGroupMasterDTO update(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        log.debug("Request to save InsuranceGroupMaster : {}", insuranceGroupMasterDTO);
        InsuranceGroupMaster insuranceGroupMaster = insuranceGroupMasterMapper.toEntity(insuranceGroupMasterDTO);
        insuranceGroupMaster = insuranceGroupMasterRepository.save(insuranceGroupMaster);
        return insuranceGroupMasterMapper.toDto(insuranceGroupMaster);
    }

    @Override
    public Optional<InsuranceGroupMasterDTO> partialUpdate(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        log.debug("Request to partially update InsuranceGroupMaster : {}", insuranceGroupMasterDTO);

        return insuranceGroupMasterRepository
            .findById(insuranceGroupMasterDTO.getInsuranceGroupMasterId())
            .map(existingInsuranceGroupMaster -> {
                insuranceGroupMasterMapper.partialUpdate(existingInsuranceGroupMaster, insuranceGroupMasterDTO);

                return existingInsuranceGroupMaster;
            })
            .map(insuranceGroupMasterRepository::save)
            .map(insuranceGroupMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceGroupMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceGroupMasters");
        return insuranceGroupMasterRepository.findAll(pageable).map(insuranceGroupMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceGroupMasterDTO> findOne(Long id) {
        log.debug("Request to get InsuranceGroupMaster : {}", id);
        return insuranceGroupMasterRepository.findById(id).map(insuranceGroupMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceGroupMaster : {}", id);
        insuranceGroupMasterRepository.deleteById(id);
    }
}
