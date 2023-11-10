package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import com.sunknowledge.dme.rcm.repository.SoLcdDocRefTransactionRepository;
import com.sunknowledge.dme.rcm.service.SoLcdDocRefTransactionService;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdDocRefTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SoLcdDocRefTransaction}.
 */
@Service
@Transactional
public class SoLcdDocRefTransactionServiceImpl implements SoLcdDocRefTransactionService {

    private final Logger log = LoggerFactory.getLogger(SoLcdDocRefTransactionServiceImpl.class);

    private final SoLcdDocRefTransactionRepository soLcdDocRefTransactionRepository;

    private final SoLcdDocRefTransactionMapper soLcdDocRefTransactionMapper;

    public SoLcdDocRefTransactionServiceImpl(
        SoLcdDocRefTransactionRepository soLcdDocRefTransactionRepository,
        SoLcdDocRefTransactionMapper soLcdDocRefTransactionMapper
    ) {
        this.soLcdDocRefTransactionRepository = soLcdDocRefTransactionRepository;
        this.soLcdDocRefTransactionMapper = soLcdDocRefTransactionMapper;
    }

    @Override
    public Mono<SoLcdDocRefTransactionDTO> save(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO) {
        log.debug("Request to save SoLcdDocRefTransaction : {}", soLcdDocRefTransactionDTO);
        return soLcdDocRefTransactionRepository
            .save(soLcdDocRefTransactionMapper.toEntity(soLcdDocRefTransactionDTO))
            .map(soLcdDocRefTransactionMapper::toDto);
    }

    @Override
    public Mono<SoLcdDocRefTransactionDTO> update(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO) {
        log.debug("Request to save SoLcdDocRefTransaction : {}", soLcdDocRefTransactionDTO);
        return soLcdDocRefTransactionRepository
            .save(soLcdDocRefTransactionMapper.toEntity(soLcdDocRefTransactionDTO))
            .map(soLcdDocRefTransactionMapper::toDto);
    }

    @Override
    public Mono<SoLcdDocRefTransactionDTO> partialUpdate(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO) {
        log.debug("Request to partially update SoLcdDocRefTransaction : {}", soLcdDocRefTransactionDTO);

        return soLcdDocRefTransactionRepository
            .findById(soLcdDocRefTransactionDTO.getSoLcdDocRefId())
            .map(existingSoLcdDocRefTransaction -> {
                soLcdDocRefTransactionMapper.partialUpdate(existingSoLcdDocRefTransaction, soLcdDocRefTransactionDTO);

                return existingSoLcdDocRefTransaction;
            })
            .flatMap(soLcdDocRefTransactionRepository::save)
            .map(soLcdDocRefTransactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SoLcdDocRefTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoLcdDocRefTransactions");
        return soLcdDocRefTransactionRepository.findAllBy(pageable).map(soLcdDocRefTransactionMapper::toDto);
    }

    public Mono<Long> countAll() {
        return soLcdDocRefTransactionRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SoLcdDocRefTransactionDTO> findOne(Long id) {
        log.debug("Request to get SoLcdDocRefTransaction : {}", id);
        return soLcdDocRefTransactionRepository.findById(id).map(soLcdDocRefTransactionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SoLcdDocRefTransaction : {}", id);
        return soLcdDocRefTransactionRepository.deleteById(id);
    }
}
