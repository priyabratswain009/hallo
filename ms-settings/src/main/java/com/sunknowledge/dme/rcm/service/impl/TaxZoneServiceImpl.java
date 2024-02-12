package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaxZone;
import com.sunknowledge.dme.rcm.repository.TaxZoneRepository;
import com.sunknowledge.dme.rcm.service.TaxZoneService;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxZoneMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaxZone}.
 */
@Service
@Transactional
public class TaxZoneServiceImpl implements TaxZoneService {

    private final Logger log = LoggerFactory.getLogger(TaxZoneServiceImpl.class);

    private final TaxZoneRepository taxZoneRepository;

    private final TaxZoneMapper taxZoneMapper;

    public TaxZoneServiceImpl(TaxZoneRepository taxZoneRepository, TaxZoneMapper taxZoneMapper) {
        this.taxZoneRepository = taxZoneRepository;
        this.taxZoneMapper = taxZoneMapper;
    }

    @Override
    public TaxZoneDTO save(TaxZoneDTO taxZoneDTO) {
        log.debug("Request to save TaxZone : {}", taxZoneDTO);
        TaxZone taxZone = taxZoneMapper.toEntity(taxZoneDTO);
        taxZone = taxZoneRepository.save(taxZone);
        return taxZoneMapper.toDto(taxZone);
    }

    @Override
    public TaxZoneDTO update(TaxZoneDTO taxZoneDTO) {
        log.debug("Request to update TaxZone : {}", taxZoneDTO);
        TaxZone taxZone = taxZoneMapper.toEntity(taxZoneDTO);
        taxZone = taxZoneRepository.save(taxZone);
        return taxZoneMapper.toDto(taxZone);
    }

    @Override
    public Optional<TaxZoneDTO> partialUpdate(TaxZoneDTO taxZoneDTO) {
        log.debug("Request to partially update TaxZone : {}", taxZoneDTO);

        return taxZoneRepository
            .findById(taxZoneDTO.getTaxZoneId())
            .map(existingTaxZone -> {
                taxZoneMapper.partialUpdate(existingTaxZone, taxZoneDTO);

                return existingTaxZone;
            })
            .map(taxZoneRepository::save)
            .map(taxZoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaxZones");
        return taxZoneRepository.findAll(pageable).map(taxZoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaxZoneDTO> findOne(Long id) {
        log.debug("Request to get TaxZone : {}", id);
        return taxZoneRepository.findById(id).map(taxZoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxZone : {}", id);
        taxZoneRepository.deleteById(id);
    }
}
