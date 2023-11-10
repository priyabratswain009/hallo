package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryServiceLinesMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SecondaryServiceLinesMaster}.
 */
@Service
@Transactional
public class SecondaryServiceLinesMasterServiceImpl implements SecondaryServiceLinesMasterService {

    private final Logger log = LoggerFactory.getLogger(SecondaryServiceLinesMasterServiceImpl.class);

    private final SecondaryServiceLinesMasterRepository secondaryServiceLinesMasterRepository;

    private final SecondaryServiceLinesMasterMapper secondaryServiceLinesMasterMapper;

    public SecondaryServiceLinesMasterServiceImpl(
        SecondaryServiceLinesMasterRepository secondaryServiceLinesMasterRepository,
        SecondaryServiceLinesMasterMapper secondaryServiceLinesMasterMapper
    ) {
        this.secondaryServiceLinesMasterRepository = secondaryServiceLinesMasterRepository;
        this.secondaryServiceLinesMasterMapper = secondaryServiceLinesMasterMapper;
    }

    @Override
    public SecondaryServiceLinesMasterDTO save(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO) {
        log.debug("Request to save SecondaryServiceLinesMaster : {}", secondaryServiceLinesMasterDTO);
        SecondaryServiceLinesMaster secondaryServiceLinesMaster = secondaryServiceLinesMasterMapper.toEntity(
            secondaryServiceLinesMasterDTO
        );
        secondaryServiceLinesMaster = secondaryServiceLinesMasterRepository.save(secondaryServiceLinesMaster);
        return secondaryServiceLinesMasterMapper.toDto(secondaryServiceLinesMaster);
    }

    @Override
    public SecondaryServiceLinesMasterDTO update(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO) {
        log.debug("Request to save SecondaryServiceLinesMaster : {}", secondaryServiceLinesMasterDTO);
        SecondaryServiceLinesMaster secondaryServiceLinesMaster = secondaryServiceLinesMasterMapper.toEntity(
            secondaryServiceLinesMasterDTO
        );
        secondaryServiceLinesMaster = secondaryServiceLinesMasterRepository.save(secondaryServiceLinesMaster);
        return secondaryServiceLinesMasterMapper.toDto(secondaryServiceLinesMaster);
    }

    @Override
    public Optional<SecondaryServiceLinesMasterDTO> partialUpdate(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO) {
        log.debug("Request to partially update SecondaryServiceLinesMaster : {}", secondaryServiceLinesMasterDTO);

        return secondaryServiceLinesMasterRepository
            .findById(secondaryServiceLinesMasterDTO.getChangeHealthSecondarySubmisionServicelinesId())
            .map(existingSecondaryServiceLinesMaster -> {
                secondaryServiceLinesMasterMapper.partialUpdate(existingSecondaryServiceLinesMaster, secondaryServiceLinesMasterDTO);

                return existingSecondaryServiceLinesMaster;
            })
            .map(secondaryServiceLinesMasterRepository::save)
            .map(secondaryServiceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SecondaryServiceLinesMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecondaryServiceLinesMasters");
        return secondaryServiceLinesMasterRepository.findAll(pageable).map(secondaryServiceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecondaryServiceLinesMasterDTO> findOne(Long id) {
        log.debug("Request to get SecondaryServiceLinesMaster : {}", id);
        return secondaryServiceLinesMasterRepository.findById(id).map(secondaryServiceLinesMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SecondaryServiceLinesMaster : {}", id);
        secondaryServiceLinesMasterRepository.deleteById(id);
    }
}
