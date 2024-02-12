package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.repository.UserMasterRepository;
import com.sunknowledge.dme.rcm.service.UserMasterService;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.UserMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserMaster}.
 */
@Service
@Transactional
public class UserMasterServiceImpl implements UserMasterService {

    private final Logger log = LoggerFactory.getLogger(UserMasterServiceImpl.class);

    private final UserMasterRepository userMasterRepository;

    private final UserMasterMapper userMasterMapper;

    public UserMasterServiceImpl(UserMasterRepository userMasterRepository, UserMasterMapper userMasterMapper) {
        this.userMasterRepository = userMasterRepository;
        this.userMasterMapper = userMasterMapper;
    }

    @Override
    public UserMasterDTO save(UserMasterDTO userMasterDTO) {
        log.debug("Request to save UserMaster : {}", userMasterDTO);
        UserMaster userMaster = userMasterMapper.toEntity(userMasterDTO);
        userMaster = userMasterRepository.save(userMaster);
        return userMasterMapper.toDto(userMaster);
    }

    @Override
    public UserMasterDTO update(UserMasterDTO userMasterDTO) {
        log.debug("Request to update UserMaster : {}", userMasterDTO);
        UserMaster userMaster = userMasterMapper.toEntity(userMasterDTO);
        userMaster = userMasterRepository.save(userMaster);
        return userMasterMapper.toDto(userMaster);
    }

    @Override
    public Optional<UserMasterDTO> partialUpdate(UserMasterDTO userMasterDTO) {
        log.debug("Request to partially update UserMaster : {}", userMasterDTO);

        return userMasterRepository
            .findById(userMasterDTO.getUserId())
            .map(existingUserMaster -> {
                userMasterMapper.partialUpdate(existingUserMaster, userMasterDTO);

                return existingUserMaster;
            })
            .map(userMasterRepository::save)
            .map(userMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserMasters");
        return userMasterRepository.findAll(pageable).map(userMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserMasterDTO> findOne(Long id) {
        log.debug("Request to get UserMaster : {}", id);
        return userMasterRepository.findById(id).map(userMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserMaster : {}", id);
        userMasterRepository.deleteById(id);
    }
}
