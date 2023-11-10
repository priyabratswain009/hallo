package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.repository.UszipMasterRepository;
import com.sunknowledge.dme.rcm.service.UszipMasterService;
import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.UszipMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UszipMaster}.
 */
@Service
@Transactional
public class UszipMasterServiceImpl implements UszipMasterService {

    private final Logger log = LoggerFactory.getLogger(UszipMasterServiceImpl.class);

    private final UszipMasterRepository uszipMasterRepository;

    private final UszipMasterMapper uszipMasterMapper;

    public UszipMasterServiceImpl(UszipMasterRepository uszipMasterRepository, UszipMasterMapper uszipMasterMapper) {
        this.uszipMasterRepository = uszipMasterRepository;
        this.uszipMasterMapper = uszipMasterMapper;
    }

    @Override
    public UszipMasterDTO save(UszipMasterDTO uszipMasterDTO) {
        log.debug("Request to save UszipMaster : {}", uszipMasterDTO);
        UszipMaster uszipMaster = uszipMasterMapper.toEntity(uszipMasterDTO);
        uszipMaster = uszipMasterRepository.save(uszipMaster);
        return uszipMasterMapper.toDto(uszipMaster);
    }

    @Override
    public UszipMasterDTO update(UszipMasterDTO uszipMasterDTO) {
        log.debug("Request to save UszipMaster : {}", uszipMasterDTO);
        UszipMaster uszipMaster = uszipMasterMapper.toEntity(uszipMasterDTO);
        uszipMaster = uszipMasterRepository.save(uszipMaster);
        return uszipMasterMapper.toDto(uszipMaster);
    }

    @Override
    public Optional<UszipMasterDTO> partialUpdate(UszipMasterDTO uszipMasterDTO) {
        log.debug("Request to partially update UszipMaster : {}", uszipMasterDTO);

        return uszipMasterRepository
            .findById(uszipMasterDTO.getUszipMasterId())
            .map(existingUszipMaster -> {
                uszipMasterMapper.partialUpdate(existingUszipMaster, uszipMasterDTO);

                return existingUszipMaster;
            })
            .map(uszipMasterRepository::save)
            .map(uszipMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UszipMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UszipMasters");
        return uszipMasterRepository.findAll(pageable).map(uszipMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UszipMasterDTO> findOne(Long id) {
        log.debug("Request to get UszipMaster : {}", id);
        return uszipMasterRepository.findById(id).map(uszipMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UszipMaster : {}", id);
        uszipMasterRepository.deleteById(id);
    }
}
