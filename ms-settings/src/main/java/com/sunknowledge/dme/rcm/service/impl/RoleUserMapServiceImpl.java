package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.RoleUserMapRepository;
import com.sunknowledge.dme.rcm.service.RoleUserMapService;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleUserMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RoleUserMap}.
 */
@Service
@Transactional
public class RoleUserMapServiceImpl implements RoleUserMapService {

    private final Logger log = LoggerFactory.getLogger(RoleUserMapServiceImpl.class);

    private final RoleUserMapRepository roleUserMapRepository;

    private final RoleUserMapMapper roleUserMapMapper;

    public RoleUserMapServiceImpl(RoleUserMapRepository roleUserMapRepository, RoleUserMapMapper roleUserMapMapper) {
        this.roleUserMapRepository = roleUserMapRepository;
        this.roleUserMapMapper = roleUserMapMapper;
    }

    @Override
    public RoleUserMapDTO save(RoleUserMapDTO roleUserMapDTO) {
        log.debug("Request to save RoleUserMap : {}", roleUserMapDTO);
        RoleUserMap roleUserMap = roleUserMapMapper.toEntity(roleUserMapDTO);
        roleUserMap = roleUserMapRepository.save(roleUserMap);
        return roleUserMapMapper.toDto(roleUserMap);
    }

    @Override
    public RoleUserMapDTO update(RoleUserMapDTO roleUserMapDTO) {
        log.debug("Request to save RoleUserMap : {}", roleUserMapDTO);
        RoleUserMap roleUserMap = roleUserMapMapper.toEntity(roleUserMapDTO);
        roleUserMap = roleUserMapRepository.save(roleUserMap);
        return roleUserMapMapper.toDto(roleUserMap);
    }

    @Override
    public Optional<RoleUserMapDTO> partialUpdate(RoleUserMapDTO roleUserMapDTO) {
        log.debug("Request to partially update RoleUserMap : {}", roleUserMapDTO);

        return roleUserMapRepository
            .findById(roleUserMapDTO.getRoleUserMapId())
            .map(existingRoleUserMap -> {
                roleUserMapMapper.partialUpdate(existingRoleUserMap, roleUserMapDTO);

                return existingRoleUserMap;
            })
            .map(roleUserMapRepository::save)
            .map(roleUserMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleUserMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RoleUserMaps");
        return roleUserMapRepository.findAll(pageable).map(roleUserMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleUserMapDTO> findOne(Long id) {
        log.debug("Request to get RoleUserMap : {}", id);
        return roleUserMapRepository.findById(id).map(roleUserMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleUserMap : {}", id);
        roleUserMapRepository.deleteById(id);
    }
}
