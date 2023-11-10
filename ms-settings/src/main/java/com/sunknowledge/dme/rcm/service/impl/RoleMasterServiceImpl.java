package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import com.sunknowledge.dme.rcm.service.RoleMasterService;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RoleMaster}.
 */
@Service
@Transactional
public class RoleMasterServiceImpl implements RoleMasterService {

    private final Logger log = LoggerFactory.getLogger(RoleMasterServiceImpl.class);

    private final RoleMasterRepository roleMasterRepository;

    private final RoleMasterMapper roleMasterMapper;

    public RoleMasterServiceImpl(RoleMasterRepository roleMasterRepository, RoleMasterMapper roleMasterMapper) {
        this.roleMasterRepository = roleMasterRepository;
        this.roleMasterMapper = roleMasterMapper;
    }

    @Override
    public RoleMasterDTO save(RoleMasterDTO roleMasterDTO) {
        log.debug("Request to save RoleMaster : {}", roleMasterDTO);
        RoleMaster roleMaster = roleMasterMapper.toEntity(roleMasterDTO);
        roleMaster = roleMasterRepository.save(roleMaster);
        return roleMasterMapper.toDto(roleMaster);
    }

    @Override
    public RoleMasterDTO update(RoleMasterDTO roleMasterDTO) {
        log.debug("Request to save RoleMaster : {}", roleMasterDTO);
        RoleMaster roleMaster = roleMasterMapper.toEntity(roleMasterDTO);
        roleMaster = roleMasterRepository.save(roleMaster);
        return roleMasterMapper.toDto(roleMaster);
    }

    @Override
    public Optional<RoleMasterDTO> partialUpdate(RoleMasterDTO roleMasterDTO) {
        log.debug("Request to partially update RoleMaster : {}", roleMasterDTO);

        return roleMasterRepository
            .findById(roleMasterDTO.getRoleId())
            .map(existingRoleMaster -> {
                roleMasterMapper.partialUpdate(existingRoleMaster, roleMasterDTO);

                return existingRoleMaster;
            })
            .map(roleMasterRepository::save)
            .map(roleMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RoleMasters");
        return roleMasterRepository.findAll(pageable).map(roleMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleMasterDTO> findOne(Long id) {
        log.debug("Request to get RoleMaster : {}", id);
        return roleMasterRepository.findById(id).map(roleMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleMaster : {}", id);
        roleMasterRepository.deleteById(id);
    }
}
