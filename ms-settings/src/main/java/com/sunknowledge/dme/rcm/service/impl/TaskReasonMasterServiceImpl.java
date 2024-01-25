package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaskReasonMaster;
import com.sunknowledge.dme.rcm.repository.TaskReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.TaskReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskReasonMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskReasonMaster}.
 */
@Service
@Transactional
public class TaskReasonMasterServiceImpl implements TaskReasonMasterService {

    private final Logger log = LoggerFactory.getLogger(TaskReasonMasterServiceImpl.class);

    private final TaskReasonMasterRepository taskReasonMasterRepository;

    private final TaskReasonMasterMapper taskReasonMasterMapper;

    public TaskReasonMasterServiceImpl(
        TaskReasonMasterRepository taskReasonMasterRepository,
        TaskReasonMasterMapper taskReasonMasterMapper
    ) {
        this.taskReasonMasterRepository = taskReasonMasterRepository;
        this.taskReasonMasterMapper = taskReasonMasterMapper;
    }

    @Override
    public TaskReasonMasterDTO save(TaskReasonMasterDTO taskReasonMasterDTO) {
        log.debug("Request to save TaskReasonMaster : {}", taskReasonMasterDTO);
        TaskReasonMaster taskReasonMaster = taskReasonMasterMapper.toEntity(taskReasonMasterDTO);
        taskReasonMaster = taskReasonMasterRepository.save(taskReasonMaster);
        return taskReasonMasterMapper.toDto(taskReasonMaster);
    }

    @Override
    public TaskReasonMasterDTO update(TaskReasonMasterDTO taskReasonMasterDTO) {
        log.debug("Request to update TaskReasonMaster : {}", taskReasonMasterDTO);
        TaskReasonMaster taskReasonMaster = taskReasonMasterMapper.toEntity(taskReasonMasterDTO);
        taskReasonMaster = taskReasonMasterRepository.save(taskReasonMaster);
        return taskReasonMasterMapper.toDto(taskReasonMaster);
    }

    @Override
    public Optional<TaskReasonMasterDTO> partialUpdate(TaskReasonMasterDTO taskReasonMasterDTO) {
        log.debug("Request to partially update TaskReasonMaster : {}", taskReasonMasterDTO);

        return taskReasonMasterRepository
            .findById(taskReasonMasterDTO.getTaskReasonId())
            .map(existingTaskReasonMaster -> {
                taskReasonMasterMapper.partialUpdate(existingTaskReasonMaster, taskReasonMasterDTO);

                return existingTaskReasonMaster;
            })
            .map(taskReasonMasterRepository::save)
            .map(taskReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskReasonMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskReasonMasters");
        return taskReasonMasterRepository.findAll(pageable).map(taskReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskReasonMasterDTO> findOne(Long id) {
        log.debug("Request to get TaskReasonMaster : {}", id);
        return taskReasonMasterRepository.findById(id).map(taskReasonMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskReasonMaster : {}", id);
        taskReasonMasterRepository.deleteById(id);
    }
}
