package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.repository.BranchGroupRepository;
import com.sunknowledge.dme.rcm.service.BranchGroupService;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchGroup}.
 */
@Service
@Transactional
public class BranchGroupServiceImpl implements BranchGroupService {

    private final Logger log = LoggerFactory.getLogger(BranchGroupServiceImpl.class);

    private final BranchGroupRepository branchGroupRepository;

    private final BranchGroupMapper branchGroupMapper;

    public BranchGroupServiceImpl(BranchGroupRepository branchGroupRepository, BranchGroupMapper branchGroupMapper) {
        this.branchGroupRepository = branchGroupRepository;
        this.branchGroupMapper = branchGroupMapper;
    }

    @Override
    public BranchGroupDTO save(BranchGroupDTO branchGroupDTO) {
        log.debug("Request to save BranchGroup : {}", branchGroupDTO);
        BranchGroup branchGroup = branchGroupMapper.toEntity(branchGroupDTO);
        branchGroup = branchGroupRepository.save(branchGroup);
        return branchGroupMapper.toDto(branchGroup);
    }

    @Override
    public BranchGroupDTO update(BranchGroupDTO branchGroupDTO) {
        log.debug("Request to update BranchGroup : {}", branchGroupDTO);
        BranchGroup branchGroup = branchGroupMapper.toEntity(branchGroupDTO);
        branchGroup = branchGroupRepository.save(branchGroup);
        return branchGroupMapper.toDto(branchGroup);
    }

    @Override
    public Optional<BranchGroupDTO> partialUpdate(BranchGroupDTO branchGroupDTO) {
        log.debug("Request to partially update BranchGroup : {}", branchGroupDTO);

        return branchGroupRepository
            .findById(branchGroupDTO.getBranchGroupId())
            .map(existingBranchGroup -> {
                branchGroupMapper.partialUpdate(existingBranchGroup, branchGroupDTO);

                return existingBranchGroup;
            })
            .map(branchGroupRepository::save)
            .map(branchGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchGroups");
        return branchGroupRepository.findAll(pageable).map(branchGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchGroupDTO> findOne(Long id) {
        log.debug("Request to get BranchGroup : {}", id);
        return branchGroupRepository.findById(id).map(branchGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchGroup : {}", id);
        branchGroupRepository.deleteById(id);
    }
}
