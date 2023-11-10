package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.IcdMaster;
import com.sunknowledge.dme.rcm.repository.IcdMasterRepository;
import com.sunknowledge.dme.rcm.service.IcdMasterService;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.IcdMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link IcdMaster}.
 */
@Service
@Transactional
public class IcdMasterServiceImpl implements IcdMasterService {

    private final Logger log = LoggerFactory.getLogger(IcdMasterServiceImpl.class);

    private final IcdMasterRepository icdMasterRepository;

    private final IcdMasterMapper icdMasterMapper;

    public IcdMasterServiceImpl(IcdMasterRepository icdMasterRepository, IcdMasterMapper icdMasterMapper) {
        this.icdMasterRepository = icdMasterRepository;
        this.icdMasterMapper = icdMasterMapper;
    }

    @Override
    public IcdMasterDTO save(IcdMasterDTO icdMasterDTO) {
        log.debug("Request to save IcdMaster : {}", icdMasterDTO);
        IcdMaster icdMaster = icdMasterMapper.toEntity(icdMasterDTO);
        icdMaster = icdMasterRepository.save(icdMaster);
        return icdMasterMapper.toDto(icdMaster);
    }

    @Override
    public IcdMasterDTO update(IcdMasterDTO icdMasterDTO) {
        log.debug("Request to save IcdMaster : {}", icdMasterDTO);
        IcdMaster icdMaster = icdMasterMapper.toEntity(icdMasterDTO);
        icdMaster = icdMasterRepository.save(icdMaster);
        return icdMasterMapper.toDto(icdMaster);
    }

    @Override
    public Optional<IcdMasterDTO> partialUpdate(IcdMasterDTO icdMasterDTO) {
        log.debug("Request to partially update IcdMaster : {}", icdMasterDTO);

        return icdMasterRepository
            .findById(icdMasterDTO.getIcdMasterId())
            .map(existingIcdMaster -> {
                icdMasterMapper.partialUpdate(existingIcdMaster, icdMasterDTO);

                return existingIcdMaster;
            })
            .map(icdMasterRepository::save)
            .map(icdMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IcdMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IcdMasters");
        return icdMasterRepository.findAll(pageable).map(icdMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IcdMasterDTO> findOne(Long id) {
        log.debug("Request to get IcdMaster : {}", id);
        return icdMasterRepository.findById(id).map(icdMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete IcdMaster : {}", id);
        icdMasterRepository.deleteById(id);
    }
}
