package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import com.sunknowledge.dme.rcm.repository.DepreciationMethodRepository;
import com.sunknowledge.dme.rcm.service.DepreciationMethodService;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import com.sunknowledge.dme.rcm.service.mapper.DepreciationMethodMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DepreciationMethod}.
 */
@Service
@Transactional
public class DepreciationMethodServiceImpl implements DepreciationMethodService {

    private final Logger log = LoggerFactory.getLogger(DepreciationMethodServiceImpl.class);

    private final DepreciationMethodRepository depreciationMethodRepository;

    private final DepreciationMethodMapper depreciationMethodMapper;

    public DepreciationMethodServiceImpl(
        DepreciationMethodRepository depreciationMethodRepository,
        DepreciationMethodMapper depreciationMethodMapper
    ) {
        this.depreciationMethodRepository = depreciationMethodRepository;
        this.depreciationMethodMapper = depreciationMethodMapper;
    }

    @Override
    public DepreciationMethodDTO save(DepreciationMethodDTO depreciationMethodDTO) {
        log.debug("Request to save DepreciationMethod : {}", depreciationMethodDTO);
        DepreciationMethod depreciationMethod = depreciationMethodMapper.toEntity(depreciationMethodDTO);
        depreciationMethod = depreciationMethodRepository.save(depreciationMethod);
        return depreciationMethodMapper.toDto(depreciationMethod);
    }

    @Override
    public DepreciationMethodDTO update(DepreciationMethodDTO depreciationMethodDTO) {
        log.debug("Request to update DepreciationMethod : {}", depreciationMethodDTO);
        DepreciationMethod depreciationMethod = depreciationMethodMapper.toEntity(depreciationMethodDTO);
        depreciationMethod = depreciationMethodRepository.save(depreciationMethod);
        return depreciationMethodMapper.toDto(depreciationMethod);
    }

    @Override
    public Optional<DepreciationMethodDTO> partialUpdate(DepreciationMethodDTO depreciationMethodDTO) {
        log.debug("Request to partially update DepreciationMethod : {}", depreciationMethodDTO);

        return depreciationMethodRepository
            .findById(depreciationMethodDTO.getDepreciationMethodId())
            .map(existingDepreciationMethod -> {
                depreciationMethodMapper.partialUpdate(existingDepreciationMethod, depreciationMethodDTO);

                return existingDepreciationMethod;
            })
            .map(depreciationMethodRepository::save)
            .map(depreciationMethodMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DepreciationMethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DepreciationMethods");
        return depreciationMethodRepository.findAll(pageable).map(depreciationMethodMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepreciationMethodDTO> findOne(Long id) {
        log.debug("Request to get DepreciationMethod : {}", id);
        return depreciationMethodRepository.findById(id).map(depreciationMethodMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DepreciationMethod : {}", id);
        depreciationMethodRepository.deleteById(id);
    }
}
