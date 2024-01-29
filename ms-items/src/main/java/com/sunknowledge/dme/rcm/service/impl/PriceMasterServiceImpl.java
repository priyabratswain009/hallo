package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceMaster;
import com.sunknowledge.dme.rcm.repository.PriceMasterRepository;
import com.sunknowledge.dme.rcm.service.PriceMasterService;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PriceMaster}.
 */
@Service
@Transactional
public class PriceMasterServiceImpl implements PriceMasterService {

    private final Logger log = LoggerFactory.getLogger(PriceMasterServiceImpl.class);

    private final PriceMasterRepository priceMasterRepository;

    private final PriceMasterMapper priceMasterMapper;

    public PriceMasterServiceImpl(PriceMasterRepository priceMasterRepository, PriceMasterMapper priceMasterMapper) {
        this.priceMasterRepository = priceMasterRepository;
        this.priceMasterMapper = priceMasterMapper;
    }

    @Override
    public PriceMasterDTO save(PriceMasterDTO priceMasterDTO) {
        log.debug("Request to save PriceMaster : {}", priceMasterDTO);
        PriceMaster priceMaster = priceMasterMapper.toEntity(priceMasterDTO);
        priceMaster = priceMasterRepository.save(priceMaster);
        return priceMasterMapper.toDto(priceMaster);
    }

    @Override
    public PriceMasterDTO update(PriceMasterDTO priceMasterDTO) {
        log.debug("Request to save PriceMaster : {}", priceMasterDTO);
        PriceMaster priceMaster = priceMasterMapper.toEntity(priceMasterDTO);
        priceMaster = priceMasterRepository.save(priceMaster);
        return priceMasterMapper.toDto(priceMaster);
    }

    @Override
    public Optional<PriceMasterDTO> partialUpdate(PriceMasterDTO priceMasterDTO) {
        log.debug("Request to partially update PriceMaster : {}", priceMasterDTO);

        return priceMasterRepository
            .findById(priceMasterDTO.getPriceTableId())
            .map(existingPriceMaster -> {
                priceMasterMapper.partialUpdate(existingPriceMaster, priceMasterDTO);

                return existingPriceMaster;
            })
            .map(priceMasterRepository::save)
            .map(priceMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PriceMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceMasters");
        return priceMasterRepository.findAll(pageable).map(priceMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceMasterDTO> findOne(Long id) {
        log.debug("Request to get PriceMaster : {}", id);
        return priceMasterRepository.findById(id).map(priceMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriceMaster : {}", id);
        priceMasterRepository.deleteById(id);
    }
}
