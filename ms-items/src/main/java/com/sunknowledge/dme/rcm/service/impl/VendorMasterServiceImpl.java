package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.VendorMasterRepository;
import com.sunknowledge.dme.rcm.service.VendorMasterService;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.VendorMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VendorMaster}.
 */
@Service
@Transactional
public class VendorMasterServiceImpl implements VendorMasterService {

    private final Logger log = LoggerFactory.getLogger(VendorMasterServiceImpl.class);

    private final VendorMasterRepository vendorMasterRepository;

    private final VendorMasterMapper vendorMasterMapper;

    public VendorMasterServiceImpl(VendorMasterRepository vendorMasterRepository, VendorMasterMapper vendorMasterMapper) {
        this.vendorMasterRepository = vendorMasterRepository;
        this.vendorMasterMapper = vendorMasterMapper;
    }

    @Override
    public VendorMasterDTO save(VendorMasterDTO vendorMasterDTO) {
        log.debug("Request to save VendorMaster : {}", vendorMasterDTO);
        VendorMaster vendorMaster = vendorMasterMapper.toEntity(vendorMasterDTO);
        vendorMaster = vendorMasterRepository.save(vendorMaster);
        return vendorMasterMapper.toDto(vendorMaster);
    }

    @Override
    public VendorMasterDTO update(VendorMasterDTO vendorMasterDTO) {
        log.debug("Request to save VendorMaster : {}", vendorMasterDTO);
        VendorMaster vendorMaster = vendorMasterMapper.toEntity(vendorMasterDTO);
        vendorMaster = vendorMasterRepository.save(vendorMaster);
        return vendorMasterMapper.toDto(vendorMaster);
    }

    @Override
    public Optional<VendorMasterDTO> partialUpdate(VendorMasterDTO vendorMasterDTO) {
        log.debug("Request to partially update VendorMaster : {}", vendorMasterDTO);

        return vendorMasterRepository
            .findById(vendorMasterDTO.getVendorId())
            .map(existingVendorMaster -> {
                vendorMasterMapper.partialUpdate(existingVendorMaster, vendorMasterDTO);

                return existingVendorMaster;
            })
            .map(vendorMasterRepository::save)
            .map(vendorMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VendorMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendorMasters");
        return vendorMasterRepository.findAll(pageable).map(vendorMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VendorMasterDTO> findOne(Long id) {
        log.debug("Request to get VendorMaster : {}", id);
        return vendorMasterRepository.findById(id).map(vendorMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendorMaster : {}", id);
        vendorMasterRepository.deleteById(id);
    }
}
