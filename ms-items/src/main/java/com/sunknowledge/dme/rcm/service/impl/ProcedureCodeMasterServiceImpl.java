package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import com.sunknowledge.dme.rcm.repository.ProcedureCodeMasterRepository;
import com.sunknowledge.dme.rcm.service.ProcedureCodeMasterService;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ProcedureCodeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProcedureCodeMaster}.
 */
@Service
@Transactional
public class ProcedureCodeMasterServiceImpl implements ProcedureCodeMasterService {

    private final Logger log = LoggerFactory.getLogger(ProcedureCodeMasterServiceImpl.class);

    private final ProcedureCodeMasterRepository procedureCodeMasterRepository;

    private final ProcedureCodeMasterMapper procedureCodeMasterMapper;

    public ProcedureCodeMasterServiceImpl(
        ProcedureCodeMasterRepository procedureCodeMasterRepository,
        ProcedureCodeMasterMapper procedureCodeMasterMapper
    ) {
        this.procedureCodeMasterRepository = procedureCodeMasterRepository;
        this.procedureCodeMasterMapper = procedureCodeMasterMapper;
    }

    @Override
    public ProcedureCodeMasterDTO save(ProcedureCodeMasterDTO procedureCodeMasterDTO) {
        log.debug("Request to save ProcedureCodeMaster : {}", procedureCodeMasterDTO);
        ProcedureCodeMaster procedureCodeMaster = procedureCodeMasterMapper.toEntity(procedureCodeMasterDTO);
        procedureCodeMaster = procedureCodeMasterRepository.save(procedureCodeMaster);
        return procedureCodeMasterMapper.toDto(procedureCodeMaster);
    }

    @Override
    public ProcedureCodeMasterDTO update(ProcedureCodeMasterDTO procedureCodeMasterDTO) {
        log.debug("Request to update ProcedureCodeMaster : {}", procedureCodeMasterDTO);
        ProcedureCodeMaster procedureCodeMaster = procedureCodeMasterMapper.toEntity(procedureCodeMasterDTO);
        procedureCodeMaster = procedureCodeMasterRepository.save(procedureCodeMaster);
        return procedureCodeMasterMapper.toDto(procedureCodeMaster);
    }

    @Override
    public Optional<ProcedureCodeMasterDTO> partialUpdate(ProcedureCodeMasterDTO procedureCodeMasterDTO) {
        log.debug("Request to partially update ProcedureCodeMaster : {}", procedureCodeMasterDTO);

        return procedureCodeMasterRepository
            .findById(procedureCodeMasterDTO.getProcedureCodeId())
            .map(existingProcedureCodeMaster -> {
                procedureCodeMasterMapper.partialUpdate(existingProcedureCodeMaster, procedureCodeMasterDTO);

                return existingProcedureCodeMaster;
            })
            .map(procedureCodeMasterRepository::save)
            .map(procedureCodeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProcedureCodeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProcedureCodeMasters");
        return procedureCodeMasterRepository.findAll(pageable).map(procedureCodeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProcedureCodeMasterDTO> findOne(Long id) {
        log.debug("Request to get ProcedureCodeMaster : {}", id);
        return procedureCodeMasterRepository.findById(id).map(procedureCodeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProcedureCodeMaster : {}", id);
        procedureCodeMasterRepository.deleteById(id);
    }
}
