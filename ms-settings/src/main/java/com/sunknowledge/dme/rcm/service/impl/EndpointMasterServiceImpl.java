package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.repository.EndpointMasterRepository;
import com.sunknowledge.dme.rcm.service.EndpointMasterService;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.EndpointMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EndpointMaster}.
 */
@Service
@Transactional
public class EndpointMasterServiceImpl implements EndpointMasterService {

    private final Logger log = LoggerFactory.getLogger(EndpointMasterServiceImpl.class);

    private final EndpointMasterRepository endpointMasterRepository;

    private final EndpointMasterMapper endpointMasterMapper;

    public EndpointMasterServiceImpl(EndpointMasterRepository endpointMasterRepository, EndpointMasterMapper endpointMasterMapper) {
        this.endpointMasterRepository = endpointMasterRepository;
        this.endpointMasterMapper = endpointMasterMapper;
    }

    @Override
    public EndpointMasterDTO save(EndpointMasterDTO endpointMasterDTO) {
        log.debug("Request to save EndpointMaster : {}", endpointMasterDTO);
        EndpointMaster endpointMaster = endpointMasterMapper.toEntity(endpointMasterDTO);
        endpointMaster = endpointMasterRepository.save(endpointMaster);
        return endpointMasterMapper.toDto(endpointMaster);
    }

    @Override
    public EndpointMasterDTO update(EndpointMasterDTO endpointMasterDTO) {
        log.debug("Request to save EndpointMaster : {}", endpointMasterDTO);
        EndpointMaster endpointMaster = endpointMasterMapper.toEntity(endpointMasterDTO);
        endpointMaster = endpointMasterRepository.save(endpointMaster);
        return endpointMasterMapper.toDto(endpointMaster);
    }

    @Override
    public Optional<EndpointMasterDTO> partialUpdate(EndpointMasterDTO endpointMasterDTO) {
        log.debug("Request to partially update EndpointMaster : {}", endpointMasterDTO);

        return endpointMasterRepository
            .findById(endpointMasterDTO.getEndpointId())
            .map(existingEndpointMaster -> {
                endpointMasterMapper.partialUpdate(existingEndpointMaster, endpointMasterDTO);

                return existingEndpointMaster;
            })
            .map(endpointMasterRepository::save)
            .map(endpointMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EndpointMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EndpointMasters");
        return endpointMasterRepository.findAll(pageable).map(endpointMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EndpointMasterDTO> findOne(Long id) {
        log.debug("Request to get EndpointMaster : {}", id);
        return endpointMasterRepository.findById(id).map(endpointMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EndpointMaster : {}", id);
        endpointMasterRepository.deleteById(id);
    }
}
