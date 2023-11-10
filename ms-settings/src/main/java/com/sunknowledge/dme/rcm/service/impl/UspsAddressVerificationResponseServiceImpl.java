package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse;
import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.UspsAddressVerificationResponseService;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.UspsAddressVerificationResponseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UspsAddressVerificationResponse}.
 */
@Service
@Transactional
public class UspsAddressVerificationResponseServiceImpl implements UspsAddressVerificationResponseService {

    private final Logger log = LoggerFactory.getLogger(UspsAddressVerificationResponseServiceImpl.class);

    private final UspsAddressVerificationResponseRepository uspsAddressVerificationResponseRepository;

    private final UspsAddressVerificationResponseMapper uspsAddressVerificationResponseMapper;

    public UspsAddressVerificationResponseServiceImpl(
        UspsAddressVerificationResponseRepository uspsAddressVerificationResponseRepository,
        UspsAddressVerificationResponseMapper uspsAddressVerificationResponseMapper
    ) {
        this.uspsAddressVerificationResponseRepository = uspsAddressVerificationResponseRepository;
        this.uspsAddressVerificationResponseMapper = uspsAddressVerificationResponseMapper;
    }

    @Override
    public UspsAddressVerificationResponseDTO save(UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO) {
        log.debug("Request to save UspsAddressVerificationResponse : {}", uspsAddressVerificationResponseDTO);
        UspsAddressVerificationResponse uspsAddressVerificationResponse = uspsAddressVerificationResponseMapper.toEntity(
            uspsAddressVerificationResponseDTO
        );
        uspsAddressVerificationResponse = uspsAddressVerificationResponseRepository.save(uspsAddressVerificationResponse);
        return uspsAddressVerificationResponseMapper.toDto(uspsAddressVerificationResponse);
    }

    @Override
    public UspsAddressVerificationResponseDTO update(UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO) {
        log.debug("Request to save UspsAddressVerificationResponse : {}", uspsAddressVerificationResponseDTO);
        UspsAddressVerificationResponse uspsAddressVerificationResponse = uspsAddressVerificationResponseMapper.toEntity(
            uspsAddressVerificationResponseDTO
        );
        uspsAddressVerificationResponse = uspsAddressVerificationResponseRepository.save(uspsAddressVerificationResponse);
        return uspsAddressVerificationResponseMapper.toDto(uspsAddressVerificationResponse);
    }

    @Override
    public Optional<UspsAddressVerificationResponseDTO> partialUpdate(
        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO
    ) {
        log.debug("Request to partially update UspsAddressVerificationResponse : {}", uspsAddressVerificationResponseDTO);

        return uspsAddressVerificationResponseRepository
            .findById(uspsAddressVerificationResponseDTO.getUspsAddressVerificationId())
            .map(existingUspsAddressVerificationResponse -> {
                uspsAddressVerificationResponseMapper.partialUpdate(
                    existingUspsAddressVerificationResponse,
                    uspsAddressVerificationResponseDTO
                );

                return existingUspsAddressVerificationResponse;
            })
            .map(uspsAddressVerificationResponseRepository::save)
            .map(uspsAddressVerificationResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UspsAddressVerificationResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UspsAddressVerificationResponses");
        return uspsAddressVerificationResponseRepository.findAll(pageable).map(uspsAddressVerificationResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UspsAddressVerificationResponseDTO> findOne(Long id) {
        log.debug("Request to get UspsAddressVerificationResponse : {}", id);
        return uspsAddressVerificationResponseRepository.findById(id).map(uspsAddressVerificationResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UspsAddressVerificationResponse : {}", id);
        uspsAddressVerificationResponseRepository.deleteById(id);
    }
}
