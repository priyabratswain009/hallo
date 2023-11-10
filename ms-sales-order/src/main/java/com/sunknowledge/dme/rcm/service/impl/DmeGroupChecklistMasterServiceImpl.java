package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import com.sunknowledge.dme.rcm.repository.DmeGroupChecklistMasterRepository;
import com.sunknowledge.dme.rcm.service.DmeGroupChecklistMasterService;
import com.sunknowledge.dme.rcm.service.dto.DmeGroupChecklistMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DmeGroupChecklistMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link DmeGroupChecklistMaster}.
 */
@Service
@Transactional
public class DmeGroupChecklistMasterServiceImpl implements DmeGroupChecklistMasterService {

    private final Logger log = LoggerFactory.getLogger(DmeGroupChecklistMasterServiceImpl.class);

    private final DmeGroupChecklistMasterRepository dmeGroupChecklistMasterRepository;

    private final DmeGroupChecklistMasterMapper dmeGroupChecklistMasterMapper;

    public DmeGroupChecklistMasterServiceImpl(
        DmeGroupChecklistMasterRepository dmeGroupChecklistMasterRepository,
        DmeGroupChecklistMasterMapper dmeGroupChecklistMasterMapper
    ) {
        this.dmeGroupChecklistMasterRepository = dmeGroupChecklistMasterRepository;
        this.dmeGroupChecklistMasterMapper = dmeGroupChecklistMasterMapper;
    }

    @Override
    public Mono<DmeGroupChecklistMasterDTO> save(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO) {
        log.debug("Request to save DmeGroupChecklistMaster : {}", dmeGroupChecklistMasterDTO);
        return dmeGroupChecklistMasterRepository
            .save(dmeGroupChecklistMasterMapper.toEntity(dmeGroupChecklistMasterDTO))
            .map(dmeGroupChecklistMasterMapper::toDto);
    }

    @Override
    public Mono<DmeGroupChecklistMasterDTO> update(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO) {
        log.debug("Request to save DmeGroupChecklistMaster : {}", dmeGroupChecklistMasterDTO);
        return dmeGroupChecklistMasterRepository
            .save(dmeGroupChecklistMasterMapper.toEntity(dmeGroupChecklistMasterDTO))
            .map(dmeGroupChecklistMasterMapper::toDto);
    }

    @Override
    public Mono<DmeGroupChecklistMasterDTO> partialUpdate(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO) {
        log.debug("Request to partially update DmeGroupChecklistMaster : {}", dmeGroupChecklistMasterDTO);

        return dmeGroupChecklistMasterRepository
            .findById(dmeGroupChecklistMasterDTO.getDmeGroupChecklistId())
            .map(existingDmeGroupChecklistMaster -> {
                dmeGroupChecklistMasterMapper.partialUpdate(existingDmeGroupChecklistMaster, dmeGroupChecklistMasterDTO);

                return existingDmeGroupChecklistMaster;
            })
            .flatMap(dmeGroupChecklistMasterRepository::save)
            .map(dmeGroupChecklistMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DmeGroupChecklistMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmeGroupChecklistMasters");
        return dmeGroupChecklistMasterRepository.findAllBy(pageable).map(dmeGroupChecklistMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return dmeGroupChecklistMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DmeGroupChecklistMasterDTO> findOne(Long id) {
        log.debug("Request to get DmeGroupChecklistMaster : {}", id);
        return dmeGroupChecklistMasterRepository.findById(id).map(dmeGroupChecklistMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DmeGroupChecklistMaster : {}", id);
        return dmeGroupChecklistMasterRepository.deleteById(id);
    }
}
