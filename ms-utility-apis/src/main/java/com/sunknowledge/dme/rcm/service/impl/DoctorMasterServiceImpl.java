package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.repository.DoctorMasterRepository;
import com.sunknowledge.dme.rcm.service.DoctorMasterService;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DoctorMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DoctorMaster}.
 */
@Service
@Transactional
public class DoctorMasterServiceImpl implements DoctorMasterService {

    private final Logger log = LoggerFactory.getLogger(DoctorMasterServiceImpl.class);

    private final DoctorMasterRepository doctorMasterRepository;

    private final DoctorMasterMapper doctorMasterMapper;

    public DoctorMasterServiceImpl(DoctorMasterRepository doctorMasterRepository, DoctorMasterMapper doctorMasterMapper) {
        this.doctorMasterRepository = doctorMasterRepository;
        this.doctorMasterMapper = doctorMasterMapper;
    }

    @Override
    public DoctorMasterDTO save(DoctorMasterDTO doctorMasterDTO) {
        log.debug("Request to save DoctorMaster : {}", doctorMasterDTO);
        DoctorMaster doctorMaster = doctorMasterMapper.toEntity(doctorMasterDTO);
        doctorMaster = doctorMasterRepository.save(doctorMaster);
        return doctorMasterMapper.toDto(doctorMaster);
    }

    @Override
    public DoctorMasterDTO update(DoctorMasterDTO doctorMasterDTO) {
        log.debug("Request to save DoctorMaster : {}", doctorMasterDTO);
        DoctorMaster doctorMaster = doctorMasterMapper.toEntity(doctorMasterDTO);
        doctorMaster = doctorMasterRepository.save(doctorMaster);
        return doctorMasterMapper.toDto(doctorMaster);
    }

    @Override
    public Optional<DoctorMasterDTO> partialUpdate(DoctorMasterDTO doctorMasterDTO) {
        log.debug("Request to partially update DoctorMaster : {}", doctorMasterDTO);

        return doctorMasterRepository
            .findById(doctorMasterDTO.getDoctorId())
            .map(existingDoctorMaster -> {
                doctorMasterMapper.partialUpdate(existingDoctorMaster, doctorMasterDTO);

                return existingDoctorMaster;
            })
            .map(doctorMasterRepository::save)
            .map(doctorMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DoctorMasters");
        return doctorMasterRepository.findAll(pageable).map(doctorMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorMasterDTO> findOne(Long id) {
        log.debug("Request to get DoctorMaster : {}", id);
        return doctorMasterRepository.findById(id).map(doctorMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DoctorMaster : {}", id);
        doctorMasterRepository.deleteById(id);
    }
}
