package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SoRecurringPurchase;
import com.sunknowledge.dme.rcm.repository.SoRecurringPurchaseRepository;
import com.sunknowledge.dme.rcm.service.SoRecurringPurchaseService;
import com.sunknowledge.dme.rcm.service.dto.SoRecurringPurchaseDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoRecurringPurchaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SoRecurringPurchase}.
 */
@Service
@Transactional
public class SoRecurringPurchaseServiceImpl implements SoRecurringPurchaseService {

    private final Logger log = LoggerFactory.getLogger(SoRecurringPurchaseServiceImpl.class);

    private final SoRecurringPurchaseRepository soRecurringPurchaseRepository;

    private final SoRecurringPurchaseMapper soRecurringPurchaseMapper;

    public SoRecurringPurchaseServiceImpl(
        SoRecurringPurchaseRepository soRecurringPurchaseRepository,
        SoRecurringPurchaseMapper soRecurringPurchaseMapper
    ) {
        this.soRecurringPurchaseRepository = soRecurringPurchaseRepository;
        this.soRecurringPurchaseMapper = soRecurringPurchaseMapper;
    }

    @Override
    public Mono<SoRecurringPurchaseDTO> save(SoRecurringPurchaseDTO soRecurringPurchaseDTO) {
        log.debug("Request to save SoRecurringPurchase : {}", soRecurringPurchaseDTO);
        return soRecurringPurchaseRepository
            .save(soRecurringPurchaseMapper.toEntity(soRecurringPurchaseDTO))
            .map(soRecurringPurchaseMapper::toDto);
    }

    @Override
    public Mono<SoRecurringPurchaseDTO> update(SoRecurringPurchaseDTO soRecurringPurchaseDTO) {
        log.debug("Request to update SoRecurringPurchase : {}", soRecurringPurchaseDTO);
        return soRecurringPurchaseRepository
            .save(soRecurringPurchaseMapper.toEntity(soRecurringPurchaseDTO))
            .map(soRecurringPurchaseMapper::toDto);
    }

    @Override
    public Mono<SoRecurringPurchaseDTO> partialUpdate(SoRecurringPurchaseDTO soRecurringPurchaseDTO) {
        log.debug("Request to partially update SoRecurringPurchase : {}", soRecurringPurchaseDTO);

        return soRecurringPurchaseRepository
            .findById(soRecurringPurchaseDTO.getRpId())
            .map(existingSoRecurringPurchase -> {
                soRecurringPurchaseMapper.partialUpdate(existingSoRecurringPurchase, soRecurringPurchaseDTO);

                return existingSoRecurringPurchase;
            })
            .flatMap(soRecurringPurchaseRepository::save)
            .map(soRecurringPurchaseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SoRecurringPurchaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoRecurringPurchases");
        return soRecurringPurchaseRepository.findAllBy(pageable).map(soRecurringPurchaseMapper::toDto);
    }

    public Mono<Long> countAll() {
        return soRecurringPurchaseRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SoRecurringPurchaseDTO> findOne(Long id) {
        log.debug("Request to get SoRecurringPurchase : {}", id);
        return soRecurringPurchaseRepository.findById(id).map(soRecurringPurchaseMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SoRecurringPurchase : {}", id);
        return soRecurringPurchaseRepository.deleteById(id);
    }
}
