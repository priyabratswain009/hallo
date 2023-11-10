package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.repository.ObjectTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.ObjectTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.ObjectTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ObjectTypeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ObjectTypeMaster}.
 */
@Service
@Transactional
public class ObjectTypeMasterServiceImpl implements ObjectTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(ObjectTypeMasterServiceImpl.class);

    private final ObjectTypeMasterRepository objectTypeMasterRepository;

    private final ObjectTypeMasterMapper objectTypeMasterMapper;

    public ObjectTypeMasterServiceImpl(
        ObjectTypeMasterRepository objectTypeMasterRepository,
        ObjectTypeMasterMapper objectTypeMasterMapper
    ) {
        this.objectTypeMasterRepository = objectTypeMasterRepository;
        this.objectTypeMasterMapper = objectTypeMasterMapper;
    }

    @Override
    public ObjectTypeMasterDTO save(ObjectTypeMasterDTO objectTypeMasterDTO) {
        log.debug("Request to save ObjectTypeMaster : {}", objectTypeMasterDTO);
        ObjectTypeMaster objectTypeMaster = objectTypeMasterMapper.toEntity(objectTypeMasterDTO);
        objectTypeMaster = objectTypeMasterRepository.save(objectTypeMaster);
        return objectTypeMasterMapper.toDto(objectTypeMaster);
    }

    @Override
    public ObjectTypeMasterDTO update(ObjectTypeMasterDTO objectTypeMasterDTO) {
        log.debug("Request to save ObjectTypeMaster : {}", objectTypeMasterDTO);
        ObjectTypeMaster objectTypeMaster = objectTypeMasterMapper.toEntity(objectTypeMasterDTO);
        objectTypeMaster = objectTypeMasterRepository.save(objectTypeMaster);
        return objectTypeMasterMapper.toDto(objectTypeMaster);
    }

    @Override
    public Optional<ObjectTypeMasterDTO> partialUpdate(ObjectTypeMasterDTO objectTypeMasterDTO) {
        log.debug("Request to partially update ObjectTypeMaster : {}", objectTypeMasterDTO);

        return objectTypeMasterRepository
            .findById(objectTypeMasterDTO.getObjectId())
            .map(existingObjectTypeMaster -> {
                objectTypeMasterMapper.partialUpdate(existingObjectTypeMaster, objectTypeMasterDTO);

                return existingObjectTypeMaster;
            })
            .map(objectTypeMasterRepository::save)
            .map(objectTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ObjectTypeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ObjectTypeMasters");
        return objectTypeMasterRepository.findAll(pageable).map(objectTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjectTypeMasterDTO> findOne(Long id) {
        log.debug("Request to get ObjectTypeMaster : {}", id);
        return objectTypeMasterRepository.findById(id).map(objectTypeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjectTypeMaster : {}", id);
        objectTypeMasterRepository.deleteById(id);
    }
}
