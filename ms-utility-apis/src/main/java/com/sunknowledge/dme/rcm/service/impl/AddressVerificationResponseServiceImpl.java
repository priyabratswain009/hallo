package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import com.sunknowledge.dme.rcm.repository.AddressVerificationResponseRepository;
import com.sunknowledge.dme.rcm.service.AddressVerificationResponseService;
import com.sunknowledge.dme.rcm.service.dto.AddressVerificationResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.AddressVerificationResponseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AddressVerificationResponse}.
 */
@Service
@Transactional
public class AddressVerificationResponseServiceImpl implements AddressVerificationResponseService {

    private final Logger log = LoggerFactory.getLogger(AddressVerificationResponseServiceImpl.class);

    private final AddressVerificationResponseRepository addressVerificationResponseRepository;

    private final AddressVerificationResponseMapper addressVerificationResponseMapper;

    public AddressVerificationResponseServiceImpl(
        AddressVerificationResponseRepository addressVerificationResponseRepository,
        AddressVerificationResponseMapper addressVerificationResponseMapper
    ) {
        this.addressVerificationResponseRepository = addressVerificationResponseRepository;
        this.addressVerificationResponseMapper = addressVerificationResponseMapper;
    }

    @Override
    public AddressVerificationResponseDTO save(AddressVerificationResponseDTO addressVerificationResponseDTO) {
        log.debug("Request to save AddressVerificationResponse : {}", addressVerificationResponseDTO);
        AddressVerificationResponse addressVerificationResponse = addressVerificationResponseMapper.toEntity(
            addressVerificationResponseDTO
        );
        addressVerificationResponse = addressVerificationResponseRepository.save(addressVerificationResponse);
        return addressVerificationResponseMapper.toDto(addressVerificationResponse);
    }

    @Override
    public AddressVerificationResponseDTO update(AddressVerificationResponseDTO addressVerificationResponseDTO) {
        log.debug("Request to save AddressVerificationResponse : {}", addressVerificationResponseDTO);
        AddressVerificationResponse addressVerificationResponse = addressVerificationResponseMapper.toEntity(
            addressVerificationResponseDTO
        );
        addressVerificationResponse = addressVerificationResponseRepository.save(addressVerificationResponse);
        return addressVerificationResponseMapper.toDto(addressVerificationResponse);
    }

    @Override
    public Optional<AddressVerificationResponseDTO> partialUpdate(AddressVerificationResponseDTO addressVerificationResponseDTO) {
        log.debug("Request to partially update AddressVerificationResponse : {}", addressVerificationResponseDTO);

        return addressVerificationResponseRepository
            .findById(addressVerificationResponseDTO.getUspsAddressVerificationId())
            .map(existingAddressVerificationResponse -> {
                addressVerificationResponseMapper.partialUpdate(existingAddressVerificationResponse, addressVerificationResponseDTO);

                return existingAddressVerificationResponse;
            })
            .map(addressVerificationResponseRepository::save)
            .map(addressVerificationResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AddressVerificationResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AddressVerificationResponses");
        return addressVerificationResponseRepository.findAll(pageable).map(addressVerificationResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AddressVerificationResponseDTO> findOne(Long id) {
        log.debug("Request to get AddressVerificationResponse : {}", id);
        return addressVerificationResponseRepository.findById(id).map(addressVerificationResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddressVerificationResponse : {}", id);
        addressVerificationResponseRepository.deleteById(id);
    }
}
