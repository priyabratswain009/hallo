package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PosMaster;
import com.sunknowledge.dme.rcm.repository.PosMasterRepository;
import com.sunknowledge.dme.rcm.service.PosMasterService;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PosMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PosMaster}.
 */
@Service
@Transactional
public class PosMasterServiceImpl implements PosMasterService {

    private final Logger log = LoggerFactory.getLogger(PosMasterServiceImpl.class);

    private final PosMasterRepository posMasterRepository;

    private final PosMasterMapper posMasterMapper;

    public PosMasterServiceImpl(PosMasterRepository posMasterRepository, PosMasterMapper posMasterMapper) {
        this.posMasterRepository = posMasterRepository;
        this.posMasterMapper = posMasterMapper;
    }

    @Override
    public PosMasterDTO save(PosMasterDTO posMasterDTO) {
        log.debug("Request to save PosMaster : {}", posMasterDTO);
        PosMaster posMaster = posMasterMapper.toEntity(posMasterDTO);
        posMaster = posMasterRepository.save(posMaster);
        return posMasterMapper.toDto(posMaster);
    }

    @Override
    public PosMasterDTO update(PosMasterDTO posMasterDTO) {
        log.debug("Request to save PosMaster : {}", posMasterDTO);
        PosMaster posMaster = posMasterMapper.toEntity(posMasterDTO);
        posMaster = posMasterRepository.save(posMaster);
        return posMasterMapper.toDto(posMaster);
    }

    @Override
    public Optional<PosMasterDTO> partialUpdate(PosMasterDTO posMasterDTO) {
        log.debug("Request to partially update PosMaster : {}", posMasterDTO);

        return posMasterRepository
            .findById(posMasterDTO.getPosId())
            .map(existingPosMaster -> {
                posMasterMapper.partialUpdate(existingPosMaster, posMasterDTO);

                return existingPosMaster;
            })
            .map(posMasterRepository::save)
            .map(posMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PosMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PosMasters");
        return posMasterRepository.findAll(pageable).map(posMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PosMasterDTO> findOne(Long id) {
        log.debug("Request to get PosMaster : {}", id);
        return posMasterRepository.findById(id).map(posMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PosMaster : {}", id);
        posMasterRepository.deleteById(id);
    }
}
