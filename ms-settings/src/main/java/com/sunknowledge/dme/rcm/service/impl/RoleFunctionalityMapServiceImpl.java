package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import com.sunknowledge.dme.rcm.repository.RoleFunctionalityMapRepository;
import com.sunknowledge.dme.rcm.service.RoleFunctionalityMapService;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleFunctionalityMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RoleFunctionalityMap}.
 */
@Service
@Transactional
public class RoleFunctionalityMapServiceImpl implements RoleFunctionalityMapService {

    private final Logger log = LoggerFactory.getLogger(RoleFunctionalityMapServiceImpl.class);

    private final RoleFunctionalityMapRepository roleFunctionalityMapRepository;

    private final RoleFunctionalityMapMapper roleFunctionalityMapMapper;

    public RoleFunctionalityMapServiceImpl(
        RoleFunctionalityMapRepository roleFunctionalityMapRepository,
        RoleFunctionalityMapMapper roleFunctionalityMapMapper
    ) {
        this.roleFunctionalityMapRepository = roleFunctionalityMapRepository;
        this.roleFunctionalityMapMapper = roleFunctionalityMapMapper;
    }

    @Override
    public RoleFunctionalityMapDTO save(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        log.debug("Request to save RoleFunctionalityMap : {}", roleFunctionalityMapDTO);
        RoleFunctionalityMap roleFunctionalityMap = roleFunctionalityMapMapper.toEntity(roleFunctionalityMapDTO);
        roleFunctionalityMap = roleFunctionalityMapRepository.save(roleFunctionalityMap);
        return roleFunctionalityMapMapper.toDto(roleFunctionalityMap);
    }

    @Override
    public RoleFunctionalityMapDTO update(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        log.debug("Request to save RoleFunctionalityMap : {}", roleFunctionalityMapDTO);
        RoleFunctionalityMap roleFunctionalityMap = roleFunctionalityMapMapper.toEntity(roleFunctionalityMapDTO);
        roleFunctionalityMap = roleFunctionalityMapRepository.save(roleFunctionalityMap);
        return roleFunctionalityMapMapper.toDto(roleFunctionalityMap);
    }

    @Override
    public Optional<RoleFunctionalityMapDTO> partialUpdate(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        log.debug("Request to partially update RoleFunctionalityMap : {}", roleFunctionalityMapDTO);

        return roleFunctionalityMapRepository
            .findById(roleFunctionalityMapDTO.getRoleFunctionalityMapId())
            .map(existingRoleFunctionalityMap -> {
                roleFunctionalityMapMapper.partialUpdate(existingRoleFunctionalityMap, roleFunctionalityMapDTO);

                return existingRoleFunctionalityMap;
            })
            .map(roleFunctionalityMapRepository::save)
            .map(roleFunctionalityMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleFunctionalityMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RoleFunctionalityMaps");
        return roleFunctionalityMapRepository.findAll(pageable).map(roleFunctionalityMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleFunctionalityMapDTO> findOne(Long id) {
        log.debug("Request to get RoleFunctionalityMap : {}", id);
        return roleFunctionalityMapRepository.findById(id).map(roleFunctionalityMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleFunctionalityMap : {}", id);
        roleFunctionalityMapRepository.deleteById(id);
    }
}
