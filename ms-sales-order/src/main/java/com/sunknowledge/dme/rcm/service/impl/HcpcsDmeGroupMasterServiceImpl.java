package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster;
import com.sunknowledge.dme.rcm.repository.HcpcsDmeGroupMasterRepository;
import com.sunknowledge.dme.rcm.service.HcpcsDmeGroupMasterService;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDmeGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.HcpcsDmeGroupMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link HcpcsDmeGroupMaster}.
 */
@Service
@Transactional
public class HcpcsDmeGroupMasterServiceImpl implements HcpcsDmeGroupMasterService {

    private final Logger log = LoggerFactory.getLogger(HcpcsDmeGroupMasterServiceImpl.class);

    private final HcpcsDmeGroupMasterRepository hcpcsDmeGroupMasterRepository;

    private final HcpcsDmeGroupMasterMapper hcpcsDmeGroupMasterMapper;

    public HcpcsDmeGroupMasterServiceImpl(
        HcpcsDmeGroupMasterRepository hcpcsDmeGroupMasterRepository,
        HcpcsDmeGroupMasterMapper hcpcsDmeGroupMasterMapper
    ) {
        this.hcpcsDmeGroupMasterRepository = hcpcsDmeGroupMasterRepository;
        this.hcpcsDmeGroupMasterMapper = hcpcsDmeGroupMasterMapper;
    }

    @Override
    public Mono<HcpcsDmeGroupMasterDTO> save(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO) {
        log.debug("Request to save HcpcsDmeGroupMaster : {}", hcpcsDmeGroupMasterDTO);
        return hcpcsDmeGroupMasterRepository
            .save(hcpcsDmeGroupMasterMapper.toEntity(hcpcsDmeGroupMasterDTO))
            .map(hcpcsDmeGroupMasterMapper::toDto);
    }

    @Override
    public Mono<HcpcsDmeGroupMasterDTO> update(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO) {
        log.debug("Request to save HcpcsDmeGroupMaster : {}", hcpcsDmeGroupMasterDTO);
        return hcpcsDmeGroupMasterRepository
            .save(hcpcsDmeGroupMasterMapper.toEntity(hcpcsDmeGroupMasterDTO))
            .map(hcpcsDmeGroupMasterMapper::toDto);
    }

    @Override
    public Mono<HcpcsDmeGroupMasterDTO> partialUpdate(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO) {
        log.debug("Request to partially update HcpcsDmeGroupMaster : {}", hcpcsDmeGroupMasterDTO);

        return hcpcsDmeGroupMasterRepository
            .findById(hcpcsDmeGroupMasterDTO.getHcpcsDmeId())
            .map(existingHcpcsDmeGroupMaster -> {
                hcpcsDmeGroupMasterMapper.partialUpdate(existingHcpcsDmeGroupMaster, hcpcsDmeGroupMasterDTO);

                return existingHcpcsDmeGroupMaster;
            })
            .flatMap(hcpcsDmeGroupMasterRepository::save)
            .map(hcpcsDmeGroupMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<HcpcsDmeGroupMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HcpcsDmeGroupMasters");
        return hcpcsDmeGroupMasterRepository.findAllBy(pageable).map(hcpcsDmeGroupMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return hcpcsDmeGroupMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<HcpcsDmeGroupMasterDTO> findOne(Long id) {
        log.debug("Request to get HcpcsDmeGroupMaster : {}", id);
        return hcpcsDmeGroupMasterRepository.findById(id).map(hcpcsDmeGroupMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete HcpcsDmeGroupMaster : {}", id);
        return hcpcsDmeGroupMasterRepository.deleteById(id);
    }
}
