package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.repository.TaxonomyDetailsRepository;
import com.sunknowledge.dme.rcm.service.TaxonomyDetailsService;
import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxonomyDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaxonomyDetails}.
 */
@Service
@Transactional
public class TaxonomyDetailsServiceImpl implements TaxonomyDetailsService {

    private final Logger log = LoggerFactory.getLogger(TaxonomyDetailsServiceImpl.class);

    private final TaxonomyDetailsRepository taxonomyDetailsRepository;

    private final TaxonomyDetailsMapper taxonomyDetailsMapper;

    public TaxonomyDetailsServiceImpl(TaxonomyDetailsRepository taxonomyDetailsRepository, TaxonomyDetailsMapper taxonomyDetailsMapper) {
        this.taxonomyDetailsRepository = taxonomyDetailsRepository;
        this.taxonomyDetailsMapper = taxonomyDetailsMapper;
    }

    @Override
    public TaxonomyDetailsDTO save(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        log.debug("Request to save TaxonomyDetails : {}", taxonomyDetailsDTO);
        TaxonomyDetails taxonomyDetails = taxonomyDetailsMapper.toEntity(taxonomyDetailsDTO);
        taxonomyDetails = taxonomyDetailsRepository.save(taxonomyDetails);
        return taxonomyDetailsMapper.toDto(taxonomyDetails);
    }

    @Override
    public TaxonomyDetailsDTO update(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        log.debug("Request to update TaxonomyDetails : {}", taxonomyDetailsDTO);
        TaxonomyDetails taxonomyDetails = taxonomyDetailsMapper.toEntity(taxonomyDetailsDTO);
        taxonomyDetails = taxonomyDetailsRepository.save(taxonomyDetails);
        return taxonomyDetailsMapper.toDto(taxonomyDetails);
    }

    @Override
    public Optional<TaxonomyDetailsDTO> partialUpdate(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        log.debug("Request to partially update TaxonomyDetails : {}", taxonomyDetailsDTO);

        return taxonomyDetailsRepository
            .findById(taxonomyDetailsDTO.getTaxonomyDetailsId())
            .map(existingTaxonomyDetails -> {
                taxonomyDetailsMapper.partialUpdate(existingTaxonomyDetails, taxonomyDetailsDTO);

                return existingTaxonomyDetails;
            })
            .map(taxonomyDetailsRepository::save)
            .map(taxonomyDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxonomyDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaxonomyDetails");
        return taxonomyDetailsRepository.findAll(pageable).map(taxonomyDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaxonomyDetailsDTO> findOne(Long id) {
        log.debug("Request to get TaxonomyDetails : {}", id);
        return taxonomyDetailsRepository.findById(id).map(taxonomyDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxonomyDetails : {}", id);
        taxonomyDetailsRepository.deleteById(id);
    }
}
