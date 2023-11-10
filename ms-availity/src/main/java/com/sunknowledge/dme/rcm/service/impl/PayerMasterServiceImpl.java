package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.PayerMasterService;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PayerMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PayerMasterDTO save(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to save PayerMaster : {}", payerMasterDTO);
        PayerMaster payerMaster = payerMasterMapper.toEntity(payerMasterDTO);
        payerMaster = payerMasterRepository.save(payerMaster);
        return payerMasterMapper.toDto(payerMaster);
    }

    @Override
    public PayerMasterDTO update(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to save PayerMaster : {}", payerMasterDTO);
        PayerMaster payerMaster = payerMasterMapper.toEntity(payerMasterDTO);
        payerMaster = payerMasterRepository.save(payerMaster);
        return payerMasterMapper.toDto(payerMaster);
    }

    @Override
    public Optional<PayerMasterDTO> partialUpdate(PayerMasterDTO payerMasterDTO) {
        log.debug("Request to partially update PayerMaster : {}", payerMasterDTO);

        return payerMasterRepository
            .findById(payerMasterDTO.getPayerMasterId())
            .map(existingPayerMaster -> {
                payerMasterMapper.partialUpdate(existingPayerMaster, payerMasterDTO);

                return existingPayerMaster;
            })
            .map(payerMasterRepository::save)
            .map(payerMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PayerMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PayerMasters");
        return payerMasterRepository.findAll(pageable).map(payerMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayerMasterDTO> findOne(Long id) {
        log.debug("Request to get PayerMaster : {}", id);
        return payerMasterRepository.findById(id).map(payerMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PayerMaster : {}", id);
        payerMasterRepository.deleteById(id);
    }
}
