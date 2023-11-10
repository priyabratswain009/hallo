package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesRep;
import com.sunknowledge.dme.rcm.repository.SalesRepRepository;
import com.sunknowledge.dme.rcm.service.SalesRepService;
import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesRepMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesRep}.
 */
@Service
@Transactional
public class SalesRepServiceImpl implements SalesRepService {

    private final Logger log = LoggerFactory.getLogger(SalesRepServiceImpl.class);

    private final SalesRepRepository salesRepRepository;

    private final SalesRepMapper salesRepMapper;

    public SalesRepServiceImpl(SalesRepRepository salesRepRepository, SalesRepMapper salesRepMapper) {
        this.salesRepRepository = salesRepRepository;
        this.salesRepMapper = salesRepMapper;
    }

    @Override
    public SalesRepDTO save(SalesRepDTO salesRepDTO) {
        log.debug("Request to save SalesRep : {}", salesRepDTO);
        SalesRep salesRep = salesRepMapper.toEntity(salesRepDTO);
        salesRep = salesRepRepository.save(salesRep);
        return salesRepMapper.toDto(salesRep);
    }

    @Override
    public SalesRepDTO update(SalesRepDTO salesRepDTO) {
        log.debug("Request to save SalesRep : {}", salesRepDTO);
        SalesRep salesRep = salesRepMapper.toEntity(salesRepDTO);
        salesRep = salesRepRepository.save(salesRep);
        return salesRepMapper.toDto(salesRep);
    }

    @Override
    public Optional<SalesRepDTO> partialUpdate(SalesRepDTO salesRepDTO) {
        log.debug("Request to partially update SalesRep : {}", salesRepDTO);

        return salesRepRepository
            .findById(salesRepDTO.getSalesRepId())
            .map(existingSalesRep -> {
                salesRepMapper.partialUpdate(existingSalesRep, salesRepDTO);

                return existingSalesRep;
            })
            .map(salesRepRepository::save)
            .map(salesRepMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesRepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesReps");
        return salesRepRepository.findAll(pageable).map(salesRepMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesRepDTO> findOne(Long id) {
        log.debug("Request to get SalesRep : {}", id);
        return salesRepRepository.findById(id).map(salesRepMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesRep : {}", id);
        salesRepRepository.deleteById(id);
    }
}
