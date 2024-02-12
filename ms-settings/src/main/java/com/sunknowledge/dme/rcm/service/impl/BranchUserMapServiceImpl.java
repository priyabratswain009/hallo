package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.repository.BranchUserMapRepository;
import com.sunknowledge.dme.rcm.service.BranchUserMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchUserMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchUserMap}.
 */
@Service
@Transactional
public class BranchUserMapServiceImpl implements BranchUserMapService {

    private final Logger log = LoggerFactory.getLogger(BranchUserMapServiceImpl.class);

    private final BranchUserMapRepository branchUserMapRepository;

    private final BranchUserMapMapper branchUserMapMapper;

    public BranchUserMapServiceImpl(BranchUserMapRepository branchUserMapRepository, BranchUserMapMapper branchUserMapMapper) {
        this.branchUserMapRepository = branchUserMapRepository;
        this.branchUserMapMapper = branchUserMapMapper;
    }

    @Override
    public BranchUserMapDTO save(BranchUserMapDTO branchUserMapDTO) {
        log.debug("Request to save BranchUserMap : {}", branchUserMapDTO);
        BranchUserMap branchUserMap = branchUserMapMapper.toEntity(branchUserMapDTO);
        branchUserMap = branchUserMapRepository.save(branchUserMap);
        return branchUserMapMapper.toDto(branchUserMap);
    }

    @Override
    public BranchUserMapDTO update(BranchUserMapDTO branchUserMapDTO) {
        log.debug("Request to update BranchUserMap : {}", branchUserMapDTO);
        BranchUserMap branchUserMap = branchUserMapMapper.toEntity(branchUserMapDTO);
        branchUserMap = branchUserMapRepository.save(branchUserMap);
        return branchUserMapMapper.toDto(branchUserMap);
    }

    @Override
    public Optional<BranchUserMapDTO> partialUpdate(BranchUserMapDTO branchUserMapDTO) {
        log.debug("Request to partially update BranchUserMap : {}", branchUserMapDTO);

        return branchUserMapRepository
            .findById(branchUserMapDTO.getMapId())
            .map(existingBranchUserMap -> {
                branchUserMapMapper.partialUpdate(existingBranchUserMap, branchUserMapDTO);

                return existingBranchUserMap;
            })
            .map(branchUserMapRepository::save)
            .map(branchUserMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchUserMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchUserMaps");
        return branchUserMapRepository.findAll(pageable).map(branchUserMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchUserMapDTO> findOne(Long id) {
        log.debug("Request to get BranchUserMap : {}", id);
        return branchUserMapRepository.findById(id).map(branchUserMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchUserMap : {}", id);
        branchUserMapRepository.deleteById(id);
    }
}
