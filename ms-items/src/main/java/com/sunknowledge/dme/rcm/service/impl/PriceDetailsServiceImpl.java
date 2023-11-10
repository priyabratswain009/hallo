package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PriceDetails}.
 */
@Service
@Transactional
public class PriceDetailsServiceImpl implements PriceDetailsService {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsServiceImpl.class);

    private final PriceDetailsRepository priceDetailsRepository;

    private final PriceDetailsMapper priceDetailsMapper;

    public PriceDetailsServiceImpl(PriceDetailsRepository priceDetailsRepository, PriceDetailsMapper priceDetailsMapper) {
        this.priceDetailsRepository = priceDetailsRepository;
        this.priceDetailsMapper = priceDetailsMapper;
    }

    @Override
    public PriceDetailsDTO save(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to save PriceDetails : {}", priceDetailsDTO);
        PriceDetails priceDetails = priceDetailsMapper.toEntity(priceDetailsDTO);
        priceDetails = priceDetailsRepository.save(priceDetails);
        return priceDetailsMapper.toDto(priceDetails);
    }

    @Override
    public PriceDetailsDTO update(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to update PriceDetails : {}", priceDetailsDTO);
        PriceDetails priceDetails = priceDetailsMapper.toEntity(priceDetailsDTO);
        priceDetails = priceDetailsRepository.save(priceDetails);
        return priceDetailsMapper.toDto(priceDetails);
    }

    @Override
    public Optional<PriceDetailsDTO> partialUpdate(PriceDetailsDTO priceDetailsDTO) {
        log.debug("Request to partially update PriceDetails : {}", priceDetailsDTO);

        return priceDetailsRepository
            .findById(priceDetailsDTO.getPriceDetailsId())
            .map(existingPriceDetails -> {
                priceDetailsMapper.partialUpdate(existingPriceDetails, priceDetailsDTO);

                return existingPriceDetails;
            })
            .map(priceDetailsRepository::save)
            .map(priceDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PriceDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceDetails");
        return priceDetailsRepository.findAll(pageable).map(priceDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceDetailsDTO> findOne(Long id) {
        log.debug("Request to get PriceDetails : {}", id);
        return priceDetailsRepository.findById(id).map(priceDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriceDetails : {}", id);
        priceDetailsRepository.deleteById(id);
    }
}
