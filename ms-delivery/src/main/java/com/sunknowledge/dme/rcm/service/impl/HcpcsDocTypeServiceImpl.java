package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.HcpcsDocType;
import com.sunknowledge.dme.rcm.repository.HcpcsDocTypeRepository;
import com.sunknowledge.dme.rcm.service.HcpcsDocTypeService;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDocTypeDTO;
import com.sunknowledge.dme.rcm.service.mapper.HcpcsDocTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HcpcsDocType}.
 */
@Service
@Transactional
public class HcpcsDocTypeServiceImpl implements HcpcsDocTypeService {

    private final Logger log = LoggerFactory.getLogger(HcpcsDocTypeServiceImpl.class);

    private final HcpcsDocTypeRepository hcpcsDocTypeRepository;

    private final HcpcsDocTypeMapper hcpcsDocTypeMapper;

    public HcpcsDocTypeServiceImpl(HcpcsDocTypeRepository hcpcsDocTypeRepository, HcpcsDocTypeMapper hcpcsDocTypeMapper) {
        this.hcpcsDocTypeRepository = hcpcsDocTypeRepository;
        this.hcpcsDocTypeMapper = hcpcsDocTypeMapper;
    }

    @Override
    public HcpcsDocTypeDTO save(HcpcsDocTypeDTO hcpcsDocTypeDTO) {
        log.debug("Request to save HcpcsDocType : {}", hcpcsDocTypeDTO);
        HcpcsDocType hcpcsDocType = hcpcsDocTypeMapper.toEntity(hcpcsDocTypeDTO);
        hcpcsDocType = hcpcsDocTypeRepository.save(hcpcsDocType);
        return hcpcsDocTypeMapper.toDto(hcpcsDocType);
    }

    @Override
    public HcpcsDocTypeDTO update(HcpcsDocTypeDTO hcpcsDocTypeDTO) {
        log.debug("Request to save HcpcsDocType : {}", hcpcsDocTypeDTO);
        HcpcsDocType hcpcsDocType = hcpcsDocTypeMapper.toEntity(hcpcsDocTypeDTO);
        hcpcsDocType = hcpcsDocTypeRepository.save(hcpcsDocType);
        return hcpcsDocTypeMapper.toDto(hcpcsDocType);
    }

    @Override
    public Optional<HcpcsDocTypeDTO> partialUpdate(HcpcsDocTypeDTO hcpcsDocTypeDTO) {
        log.debug("Request to partially update HcpcsDocType : {}", hcpcsDocTypeDTO);

        return hcpcsDocTypeRepository
            .findById(hcpcsDocTypeDTO.getHcpcsDoctypeId())
            .map(existingHcpcsDocType -> {
                hcpcsDocTypeMapper.partialUpdate(existingHcpcsDocType, hcpcsDocTypeDTO);

                return existingHcpcsDocType;
            })
            .map(hcpcsDocTypeRepository::save)
            .map(hcpcsDocTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HcpcsDocTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HcpcsDocTypes");
        return hcpcsDocTypeRepository.findAll(pageable).map(hcpcsDocTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HcpcsDocTypeDTO> findOne(Long id) {
        log.debug("Request to get HcpcsDocType : {}", id);
        return hcpcsDocTypeRepository.findById(id).map(hcpcsDocTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HcpcsDocType : {}", id);
        hcpcsDocTypeRepository.deleteById(id);
    }
}
