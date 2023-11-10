package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import com.sunknowledge.dme.rcm.repository.FunctionalityEndpointMappingRepository;
import com.sunknowledge.dme.rcm.service.FunctionalityEndpointMappingService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityEndpointMappingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FunctionalityEndpointMapping}.
 */
@Service
@Transactional
public class FunctionalityEndpointMappingServiceImpl implements FunctionalityEndpointMappingService {

    private final Logger log = LoggerFactory.getLogger(FunctionalityEndpointMappingServiceImpl.class);

    private final FunctionalityEndpointMappingRepository functionalityEndpointMappingRepository;

    private final FunctionalityEndpointMappingMapper functionalityEndpointMappingMapper;

    public FunctionalityEndpointMappingServiceImpl(
        FunctionalityEndpointMappingRepository functionalityEndpointMappingRepository,
        FunctionalityEndpointMappingMapper functionalityEndpointMappingMapper
    ) {
        this.functionalityEndpointMappingRepository = functionalityEndpointMappingRepository;
        this.functionalityEndpointMappingMapper = functionalityEndpointMappingMapper;
    }

    @Override
    public FunctionalityEndpointMappingDTO save(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        log.debug("Request to save FunctionalityEndpointMapping : {}", functionalityEndpointMappingDTO);
        FunctionalityEndpointMapping functionalityEndpointMapping = functionalityEndpointMappingMapper.toEntity(
            functionalityEndpointMappingDTO
        );
        functionalityEndpointMapping = functionalityEndpointMappingRepository.save(functionalityEndpointMapping);
        return functionalityEndpointMappingMapper.toDto(functionalityEndpointMapping);
    }

    @Override
    public FunctionalityEndpointMappingDTO update(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        log.debug("Request to save FunctionalityEndpointMapping : {}", functionalityEndpointMappingDTO);
        FunctionalityEndpointMapping functionalityEndpointMapping = functionalityEndpointMappingMapper.toEntity(
            functionalityEndpointMappingDTO
        );
        functionalityEndpointMapping = functionalityEndpointMappingRepository.save(functionalityEndpointMapping);
        return functionalityEndpointMappingMapper.toDto(functionalityEndpointMapping);
    }

    @Override
    public Optional<FunctionalityEndpointMappingDTO> partialUpdate(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        log.debug("Request to partially update FunctionalityEndpointMapping : {}", functionalityEndpointMappingDTO);

        return functionalityEndpointMappingRepository
            .findById(functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())
            .map(existingFunctionalityEndpointMapping -> {
                functionalityEndpointMappingMapper.partialUpdate(existingFunctionalityEndpointMapping, functionalityEndpointMappingDTO);

                return existingFunctionalityEndpointMapping;
            })
            .map(functionalityEndpointMappingRepository::save)
            .map(functionalityEndpointMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FunctionalityEndpointMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FunctionalityEndpointMappings");
        return functionalityEndpointMappingRepository.findAll(pageable).map(functionalityEndpointMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FunctionalityEndpointMappingDTO> findOne(Long id) {
        log.debug("Request to get FunctionalityEndpointMapping : {}", id);
        return functionalityEndpointMappingRepository.findById(id).map(functionalityEndpointMappingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FunctionalityEndpointMapping : {}", id);
        functionalityEndpointMappingRepository.deleteById(id);
    }
}
