package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import com.sunknowledge.dme.rcm.repository.SoLcdCoverageCriteriaTransactionRepository;
import com.sunknowledge.dme.rcm.service.SoLcdCoverageCriteriaTransactionService;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoLcdCoverageCriteriaTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SoLcdCoverageCriteriaTransaction}.
 */
@Service
@Transactional
public class SoLcdCoverageCriteriaTransactionServiceImpl implements SoLcdCoverageCriteriaTransactionService {

    private final Logger log = LoggerFactory.getLogger(SoLcdCoverageCriteriaTransactionServiceImpl.class);

    private final SoLcdCoverageCriteriaTransactionRepository soLcdCoverageCriteriaTransactionRepository;

    private final SoLcdCoverageCriteriaTransactionMapper soLcdCoverageCriteriaTransactionMapper;

    public SoLcdCoverageCriteriaTransactionServiceImpl(
        SoLcdCoverageCriteriaTransactionRepository soLcdCoverageCriteriaTransactionRepository,
        SoLcdCoverageCriteriaTransactionMapper soLcdCoverageCriteriaTransactionMapper
    ) {
        this.soLcdCoverageCriteriaTransactionRepository = soLcdCoverageCriteriaTransactionRepository;
        this.soLcdCoverageCriteriaTransactionMapper = soLcdCoverageCriteriaTransactionMapper;
    }

    @Override
    public Mono<SoLcdCoverageCriteriaTransactionDTO> save(SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO) {
        log.debug("Request to save SoLcdCoverageCriteriaTransaction : {}", soLcdCoverageCriteriaTransactionDTO);
        return soLcdCoverageCriteriaTransactionRepository
            .save(soLcdCoverageCriteriaTransactionMapper.toEntity(soLcdCoverageCriteriaTransactionDTO))
            .map(soLcdCoverageCriteriaTransactionMapper::toDto);
    }

    @Override
    public Mono<SoLcdCoverageCriteriaTransactionDTO> update(SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO) {
        log.debug("Request to save SoLcdCoverageCriteriaTransaction : {}", soLcdCoverageCriteriaTransactionDTO);
        return soLcdCoverageCriteriaTransactionRepository
            .save(soLcdCoverageCriteriaTransactionMapper.toEntity(soLcdCoverageCriteriaTransactionDTO))
            .map(soLcdCoverageCriteriaTransactionMapper::toDto);
    }

    @Override
    public Mono<SoLcdCoverageCriteriaTransactionDTO> partialUpdate(
        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO
    ) {
        log.debug("Request to partially update SoLcdCoverageCriteriaTransaction : {}", soLcdCoverageCriteriaTransactionDTO);

        return soLcdCoverageCriteriaTransactionRepository
            .findById(soLcdCoverageCriteriaTransactionDTO.getSoLcdDocRefId())
            .map(existingSoLcdCoverageCriteriaTransaction -> {
                soLcdCoverageCriteriaTransactionMapper.partialUpdate(
                    existingSoLcdCoverageCriteriaTransaction,
                    soLcdCoverageCriteriaTransactionDTO
                );

                return existingSoLcdCoverageCriteriaTransaction;
            })
            .flatMap(soLcdCoverageCriteriaTransactionRepository::save)
            .map(soLcdCoverageCriteriaTransactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SoLcdCoverageCriteriaTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoLcdCoverageCriteriaTransactions");
        return soLcdCoverageCriteriaTransactionRepository.findAllBy(pageable).map(soLcdCoverageCriteriaTransactionMapper::toDto);
    }

    public Mono<Long> countAll() {
        return soLcdCoverageCriteriaTransactionRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SoLcdCoverageCriteriaTransactionDTO> findOne(Long id) {
        log.debug("Request to get SoLcdCoverageCriteriaTransaction : {}", id);
        return soLcdCoverageCriteriaTransactionRepository.findById(id).map(soLcdCoverageCriteriaTransactionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SoLcdCoverageCriteriaTransaction : {}", id);
        return soLcdCoverageCriteriaTransactionRepository.deleteById(id);
    }
}
