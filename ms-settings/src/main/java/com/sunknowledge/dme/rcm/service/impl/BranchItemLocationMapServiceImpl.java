package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapRepository;
import com.sunknowledge.dme.rcm.service.BranchItemLocationMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchItemLocationMap}.
 */
@Service
@Transactional
public class BranchItemLocationMapServiceImpl implements BranchItemLocationMapService {

    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapServiceImpl.class);

    private final BranchItemLocationMapRepository branchItemLocationMapRepository;

    private final BranchItemLocationMapMapper branchItemLocationMapMapper;

    public BranchItemLocationMapServiceImpl(
        BranchItemLocationMapRepository branchItemLocationMapRepository,
        BranchItemLocationMapMapper branchItemLocationMapMapper
    ) {
        this.branchItemLocationMapRepository = branchItemLocationMapRepository;
        this.branchItemLocationMapMapper = branchItemLocationMapMapper;
    }

    @Override
    public BranchItemLocationMapDTO save(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        log.debug("Request to save BranchItemLocationMap : {}", branchItemLocationMapDTO);
        BranchItemLocationMap branchItemLocationMap = branchItemLocationMapMapper.toEntity(branchItemLocationMapDTO);
        branchItemLocationMap = branchItemLocationMapRepository.save(branchItemLocationMap);
        return branchItemLocationMapMapper.toDto(branchItemLocationMap);
    }

    @Override
    public BranchItemLocationMapDTO update(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        log.debug("Request to update BranchItemLocationMap : {}", branchItemLocationMapDTO);
        BranchItemLocationMap branchItemLocationMap = branchItemLocationMapMapper.toEntity(branchItemLocationMapDTO);
        branchItemLocationMap = branchItemLocationMapRepository.save(branchItemLocationMap);
        return branchItemLocationMapMapper.toDto(branchItemLocationMap);
    }

    @Override
    public Optional<BranchItemLocationMapDTO> partialUpdate(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        log.debug("Request to partially update BranchItemLocationMap : {}", branchItemLocationMapDTO);

        return branchItemLocationMapRepository
            .findById(branchItemLocationMapDTO.getBranchItemLocationMapId())
            .map(existingBranchItemLocationMap -> {
                branchItemLocationMapMapper.partialUpdate(existingBranchItemLocationMap, branchItemLocationMapDTO);

                return existingBranchItemLocationMap;
            })
            .map(branchItemLocationMapRepository::save)
            .map(branchItemLocationMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchItemLocationMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchItemLocationMaps");
        return branchItemLocationMapRepository.findAll(pageable).map(branchItemLocationMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchItemLocationMapDTO> findOne(Long id) {
        log.debug("Request to get BranchItemLocationMap : {}", id);
        return branchItemLocationMapRepository.findById(id).map(branchItemLocationMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchItemLocationMap : {}", id);
        branchItemLocationMapRepository.deleteById(id);
    }
}
