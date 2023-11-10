package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.repository.TaskTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.TaskTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.TaskTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskTypeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskTypeMaster}.
 */
@Service
@Transactional
public class TaskTypeMasterServiceImpl implements TaskTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(TaskTypeMasterServiceImpl.class);

    private final TaskTypeMasterRepository taskTypeMasterRepository;

    private final TaskTypeMasterMapper taskTypeMasterMapper;

    public TaskTypeMasterServiceImpl(TaskTypeMasterRepository taskTypeMasterRepository, TaskTypeMasterMapper taskTypeMasterMapper) {
        this.taskTypeMasterRepository = taskTypeMasterRepository;
        this.taskTypeMasterMapper = taskTypeMasterMapper;
    }

    @Override
    public TaskTypeMasterDTO save(TaskTypeMasterDTO taskTypeMasterDTO) {
        log.debug("Request to save TaskTypeMaster : {}", taskTypeMasterDTO);
        TaskTypeMaster taskTypeMaster = taskTypeMasterMapper.toEntity(taskTypeMasterDTO);
        taskTypeMaster = taskTypeMasterRepository.save(taskTypeMaster);
        return taskTypeMasterMapper.toDto(taskTypeMaster);
    }

    @Override
    public TaskTypeMasterDTO update(TaskTypeMasterDTO taskTypeMasterDTO) {
        log.debug("Request to save TaskTypeMaster : {}", taskTypeMasterDTO);
        TaskTypeMaster taskTypeMaster = taskTypeMasterMapper.toEntity(taskTypeMasterDTO);
        taskTypeMaster = taskTypeMasterRepository.save(taskTypeMaster);
        return taskTypeMasterMapper.toDto(taskTypeMaster);
    }

    @Override
    public Optional<TaskTypeMasterDTO> partialUpdate(TaskTypeMasterDTO taskTypeMasterDTO) {
        log.debug("Request to partially update TaskTypeMaster : {}", taskTypeMasterDTO);

        return taskTypeMasterRepository
            .findById(taskTypeMasterDTO.getTaskId())
            .map(existingTaskTypeMaster -> {
                taskTypeMasterMapper.partialUpdate(existingTaskTypeMaster, taskTypeMasterDTO);

                return existingTaskTypeMaster;
            })
            .map(taskTypeMasterRepository::save)
            .map(taskTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskTypeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskTypeMasters");
        return taskTypeMasterRepository.findAll(pageable).map(taskTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskTypeMasterDTO> findOne(Long id) {
        log.debug("Request to get TaskTypeMaster : {}", id);
        return taskTypeMasterRepository.findById(id).map(taskTypeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskTypeMaster : {}", id);
        taskTypeMasterRepository.deleteById(id);
    }
}
