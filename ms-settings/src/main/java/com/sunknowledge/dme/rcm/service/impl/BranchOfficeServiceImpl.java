package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.repository.BranchOfficeRepository;
import com.sunknowledge.dme.rcm.service.BranchOfficeService;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchOffice}.
 */
@Service
@Transactional
public class BranchOfficeServiceImpl implements BranchOfficeService {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeServiceImpl.class);

    private final BranchOfficeRepository branchOfficeRepository;

    private final BranchOfficeMapper branchOfficeMapper;

    public BranchOfficeServiceImpl(BranchOfficeRepository branchOfficeRepository, BranchOfficeMapper branchOfficeMapper) {
        this.branchOfficeRepository = branchOfficeRepository;
        this.branchOfficeMapper = branchOfficeMapper;
    }

    @Override
    public BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to save BranchOffice : {}", branchOfficeDTO);
        BranchOffice branchOffice = branchOfficeMapper.toEntity(branchOfficeDTO);
        branchOffice = branchOfficeRepository.save(branchOffice);
        return branchOfficeMapper.toDto(branchOffice);
    }

    @Override
    public BranchOfficeDTO update(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to save BranchOffice : {}", branchOfficeDTO);
        BranchOffice branchOffice = branchOfficeMapper.toEntity(branchOfficeDTO);
        branchOffice = branchOfficeRepository.save(branchOffice);
        return branchOfficeMapper.toDto(branchOffice);
    }

    @Override
    public Optional<BranchOfficeDTO> partialUpdate(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to partially update BranchOffice : {}", branchOfficeDTO);

        return branchOfficeRepository
            .findById(branchOfficeDTO.getBranchId())
            .map(existingBranchOffice -> {
                branchOfficeMapper.partialUpdate(existingBranchOffice, branchOfficeDTO);

                return existingBranchOffice;
            })
            .map(branchOfficeRepository::save)
            .map(branchOfficeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchOffices");
        return branchOfficeRepository.findAll(pageable).map(branchOfficeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchOfficeDTO> findOne(Long id) {
        log.debug("Request to get BranchOffice : {}", id);
        return branchOfficeRepository.findById(id).map(branchOfficeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchOffice : {}", id);
        branchOfficeRepository.deleteById(id);
    }
}
