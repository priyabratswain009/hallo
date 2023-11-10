package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import com.sunknowledge.dme.rcm.repository.DoctorMedicalInfoRepository;
import com.sunknowledge.dme.rcm.service.DoctorMedicalInfoService;
import com.sunknowledge.dme.rcm.service.dto.DoctorMedicalInfoDTO;
import com.sunknowledge.dme.rcm.service.mapper.DoctorMedicalInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DoctorMedicalInfo}.
 */
@Service
@Transactional
public class DoctorMedicalInfoServiceImpl implements DoctorMedicalInfoService {

    private final Logger log = LoggerFactory.getLogger(DoctorMedicalInfoServiceImpl.class);

    private final DoctorMedicalInfoRepository doctorMedicalInfoRepository;

    private final DoctorMedicalInfoMapper doctorMedicalInfoMapper;

    public DoctorMedicalInfoServiceImpl(
        DoctorMedicalInfoRepository doctorMedicalInfoRepository,
        DoctorMedicalInfoMapper doctorMedicalInfoMapper
    ) {
        this.doctorMedicalInfoRepository = doctorMedicalInfoRepository;
        this.doctorMedicalInfoMapper = doctorMedicalInfoMapper;
    }

    @Override
    public DoctorMedicalInfoDTO save(DoctorMedicalInfoDTO doctorMedicalInfoDTO) {
        log.debug("Request to save DoctorMedicalInfo : {}", doctorMedicalInfoDTO);
        DoctorMedicalInfo doctorMedicalInfo = doctorMedicalInfoMapper.toEntity(doctorMedicalInfoDTO);
        doctorMedicalInfo = doctorMedicalInfoRepository.save(doctorMedicalInfo);
        return doctorMedicalInfoMapper.toDto(doctorMedicalInfo);
    }

    @Override
    public DoctorMedicalInfoDTO update(DoctorMedicalInfoDTO doctorMedicalInfoDTO) {
        log.debug("Request to save DoctorMedicalInfo : {}", doctorMedicalInfoDTO);
        DoctorMedicalInfo doctorMedicalInfo = doctorMedicalInfoMapper.toEntity(doctorMedicalInfoDTO);
        doctorMedicalInfo = doctorMedicalInfoRepository.save(doctorMedicalInfo);
        return doctorMedicalInfoMapper.toDto(doctorMedicalInfo);
    }

    @Override
    public Optional<DoctorMedicalInfoDTO> partialUpdate(DoctorMedicalInfoDTO doctorMedicalInfoDTO) {
        log.debug("Request to partially update DoctorMedicalInfo : {}", doctorMedicalInfoDTO);

        return doctorMedicalInfoRepository
            .findById(doctorMedicalInfoDTO.getMedicalInfoId())
            .map(existingDoctorMedicalInfo -> {
                doctorMedicalInfoMapper.partialUpdate(existingDoctorMedicalInfo, doctorMedicalInfoDTO);

                return existingDoctorMedicalInfo;
            })
            .map(doctorMedicalInfoRepository::save)
            .map(doctorMedicalInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorMedicalInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DoctorMedicalInfos");
        return doctorMedicalInfoRepository.findAll(pageable).map(doctorMedicalInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorMedicalInfoDTO> findOne(Long id) {
        log.debug("Request to get DoctorMedicalInfo : {}", id);
        return doctorMedicalInfoRepository.findById(id).map(doctorMedicalInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DoctorMedicalInfo : {}", id);
        doctorMedicalInfoRepository.deleteById(id);
    }
}
