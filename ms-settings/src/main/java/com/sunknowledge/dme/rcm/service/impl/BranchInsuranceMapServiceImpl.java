package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapRepository;
import com.sunknowledge.dme.rcm.service.BranchInsuranceMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchInsuranceMap}.
 */
@Service
@Transactional
public class BranchInsuranceMapServiceImpl implements BranchInsuranceMapService {

    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapServiceImpl.class);

    private final BranchInsuranceMapRepository branchInsuranceMapRepository;

    private final BranchInsuranceMapMapper branchInsuranceMapMapper;

    public BranchInsuranceMapServiceImpl(
        BranchInsuranceMapRepository branchInsuranceMapRepository,
        BranchInsuranceMapMapper branchInsuranceMapMapper
    ) {
        this.branchInsuranceMapRepository = branchInsuranceMapRepository;
        this.branchInsuranceMapMapper = branchInsuranceMapMapper;
    }

    @Override
    public BranchInsuranceMapDTO save(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        log.debug("Request to save BranchInsuranceMap : {}", branchInsuranceMapDTO);
        BranchInsuranceMap branchInsuranceMap = branchInsuranceMapMapper.toEntity(branchInsuranceMapDTO);
        branchInsuranceMap = branchInsuranceMapRepository.save(branchInsuranceMap);
        return branchInsuranceMapMapper.toDto(branchInsuranceMap);
    }

    @Override
    public BranchInsuranceMapDTO update(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        log.debug("Request to update BranchInsuranceMap : {}", branchInsuranceMapDTO);
        BranchInsuranceMap branchInsuranceMap = branchInsuranceMapMapper.toEntity(branchInsuranceMapDTO);
        branchInsuranceMap = branchInsuranceMapRepository.save(branchInsuranceMap);
        return branchInsuranceMapMapper.toDto(branchInsuranceMap);
    }

    @Override
    public Optional<BranchInsuranceMapDTO> partialUpdate(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        log.debug("Request to partially update BranchInsuranceMap : {}", branchInsuranceMapDTO);

        return branchInsuranceMapRepository
            .findById(branchInsuranceMapDTO.getBranchInsuranceMapId())
            .map(existingBranchInsuranceMap -> {
                branchInsuranceMapMapper.partialUpdate(existingBranchInsuranceMap, branchInsuranceMapDTO);

                return existingBranchInsuranceMap;
            })
            .map(branchInsuranceMapRepository::save)
            .map(branchInsuranceMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchInsuranceMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchInsuranceMaps");
        return branchInsuranceMapRepository.findAll(pageable).map(branchInsuranceMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchInsuranceMapDTO> findOne(Long id) {
        log.debug("Request to get BranchInsuranceMap : {}", id);
        return branchInsuranceMapRepository.findById(id).map(branchInsuranceMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchInsuranceMap : {}", id);
        branchInsuranceMapRepository.deleteById(id);
    }
}
