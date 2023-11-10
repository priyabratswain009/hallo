package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.ServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.ServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.ServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ServiceLinesMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceLinesMaster}.
 */
@Service
@Transactional
public class ServiceLinesMasterServiceImpl implements ServiceLinesMasterService {

    private final Logger log = LoggerFactory.getLogger(ServiceLinesMasterServiceImpl.class);

    private final ServiceLinesMasterRepository serviceLinesMasterRepository;

    private final ServiceLinesMasterMapper serviceLinesMasterMapper;

    public ServiceLinesMasterServiceImpl(
        ServiceLinesMasterRepository serviceLinesMasterRepository,
        ServiceLinesMasterMapper serviceLinesMasterMapper
    ) {
        this.serviceLinesMasterRepository = serviceLinesMasterRepository;
        this.serviceLinesMasterMapper = serviceLinesMasterMapper;
    }

    @Override
    public ServiceLinesMasterDTO save(ServiceLinesMasterDTO serviceLinesMasterDTO) {
        log.debug("Request to save ServiceLinesMaster : {}", serviceLinesMasterDTO);
        ServiceLinesMaster serviceLinesMaster = serviceLinesMasterMapper.toEntity(serviceLinesMasterDTO);
        serviceLinesMaster = serviceLinesMasterRepository.save(serviceLinesMaster);
        return serviceLinesMasterMapper.toDto(serviceLinesMaster);
    }

    @Override
    public ServiceLinesMasterDTO update(ServiceLinesMasterDTO serviceLinesMasterDTO) {
        log.debug("Request to save ServiceLinesMaster : {}", serviceLinesMasterDTO);
        ServiceLinesMaster serviceLinesMaster = serviceLinesMasterMapper.toEntity(serviceLinesMasterDTO);
        serviceLinesMaster = serviceLinesMasterRepository.save(serviceLinesMaster);
        return serviceLinesMasterMapper.toDto(serviceLinesMaster);
    }

    @Override
    public Optional<ServiceLinesMasterDTO> partialUpdate(ServiceLinesMasterDTO serviceLinesMasterDTO) {
        log.debug("Request to partially update ServiceLinesMaster : {}", serviceLinesMasterDTO);

        return serviceLinesMasterRepository
            .findById(serviceLinesMasterDTO.getChangeHealthPrimarySubmisionServicelinesId())
            .map(existingServiceLinesMaster -> {
                serviceLinesMasterMapper.partialUpdate(existingServiceLinesMaster, serviceLinesMasterDTO);

                return existingServiceLinesMaster;
            })
            .map(serviceLinesMasterRepository::save)
            .map(serviceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceLinesMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceLinesMasters");
        return serviceLinesMasterRepository.findAll(pageable).map(serviceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceLinesMasterDTO> findOne(Long id) {
        log.debug("Request to get ServiceLinesMaster : {}", id);
        return serviceLinesMasterRepository.findById(id).map(serviceLinesMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceLinesMaster : {}", id);
        serviceLinesMasterRepository.deleteById(id);
    }
}
