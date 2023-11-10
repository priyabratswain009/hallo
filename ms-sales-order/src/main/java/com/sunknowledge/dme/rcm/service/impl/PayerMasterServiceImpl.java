package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.PayerMasterService;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PayerMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PayerMaster}.
 */
@Service
@Transactional
public class PayerMasterServiceImpl implements PayerMasterService {

    private final Logger log = LoggerFactory.getLogger(PayerMasterServiceImpl.class);

    private final PayerMasterRepository payerMasterRepository;

    private final PayerMasterMapper payerMasterMapper;

    public PayerMasterServiceImpl(PayerMasterRepository payerMasterRepository, PayerMasterMapper payerMasterMapper) {
        this.payerMasterRepository = payerMasterRepository;
        this.payerMasterMapper = payerMasterMapper;
    }

    @Override
    public Mono<PayerMasterDTO> save(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to save PayerMaster : {}", payerMasterDTO);
        return payerMasterRepository.save(payerMasterMapper.toEntity(payerMasterDTO)).map(payerMasterMapper::toDto);
    }

    @Override
    public Mono<PayerMasterDTO> update(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to save PayerMaster : {}", payerMasterDTO);
        return payerMasterRepository.save(payerMasterMapper.toEntity(payerMasterDTO)).map(payerMasterMapper::toDto);
    }

    @Override
    public Mono<PayerMasterDTO> partialUpdate(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to partially update PayerMaster : {}", payerMasterDTO);

        return payerMasterRepository
            .findById(payerMasterDTO.getPayerMasterId())
            .map(existingPayerMaster -> {
                payerMasterMapper.partialUpdate(existingPayerMaster, payerMasterDTO);

                return existingPayerMaster;
            })
            .flatMap(payerMasterRepository::save)
            .map(payerMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PayerMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PayerMasters");
        return payerMasterRepository.findAllBy(pageable).map(payerMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return payerMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PayerMasterDTO> findOne(Long id) {
        log.debug("Request to get PayerMaster : {}", id);
        return payerMasterRepository.findById(id).map(payerMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PayerMaster : {}", id);
        return payerMasterRepository.deleteById(id);
    }
}
