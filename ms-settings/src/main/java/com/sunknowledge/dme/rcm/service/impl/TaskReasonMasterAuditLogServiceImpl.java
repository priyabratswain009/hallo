package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.TaskReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.TaskReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskReasonMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskReasonMasterAuditLog}.
 */
@Service
@Transactional
public class TaskReasonMasterAuditLogServiceImpl implements TaskReasonMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(TaskReasonMasterAuditLogServiceImpl.class);

    private final TaskReasonMasterAuditLogRepository taskReasonMasterAuditLogRepository;

    private final TaskReasonMasterAuditLogMapper taskReasonMasterAuditLogMapper;

    public TaskReasonMasterAuditLogServiceImpl(
        TaskReasonMasterAuditLogRepository taskReasonMasterAuditLogRepository,
        TaskReasonMasterAuditLogMapper taskReasonMasterAuditLogMapper
    ) {
        this.taskReasonMasterAuditLogRepository = taskReasonMasterAuditLogRepository;
        this.taskReasonMasterAuditLogMapper = taskReasonMasterAuditLogMapper;
    }

    @Override
    public TaskReasonMasterAuditLogDTO save(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO) {
        log.debug("Request to save TaskReasonMasterAuditLog : {}", taskReasonMasterAuditLogDTO);
        TaskReasonMasterAuditLog taskReasonMasterAuditLog = taskReasonMasterAuditLogMapper.toEntity(taskReasonMasterAuditLogDTO);
        taskReasonMasterAuditLog = taskReasonMasterAuditLogRepository.save(taskReasonMasterAuditLog);
        return taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);
    }

    @Override
    public TaskReasonMasterAuditLogDTO update(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO) {
        log.debug("Request to save TaskReasonMasterAuditLog : {}", taskReasonMasterAuditLogDTO);
        TaskReasonMasterAuditLog taskReasonMasterAuditLog = taskReasonMasterAuditLogMapper.toEntity(taskReasonMasterAuditLogDTO);
        taskReasonMasterAuditLog = taskReasonMasterAuditLogRepository.save(taskReasonMasterAuditLog);
        return taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);
    }

    @Override
    public Optional<TaskReasonMasterAuditLogDTO> partialUpdate(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO) {
        log.debug("Request to partially update TaskReasonMasterAuditLog : {}", taskReasonMasterAuditLogDTO);

        return taskReasonMasterAuditLogRepository
            .findById(taskReasonMasterAuditLogDTO.getId())
            .map(existingTaskReasonMasterAuditLog -> {
                taskReasonMasterAuditLogMapper.partialUpdate(existingTaskReasonMasterAuditLog, taskReasonMasterAuditLogDTO);

                return existingTaskReasonMasterAuditLog;
            })
            .map(taskReasonMasterAuditLogRepository::save)
            .map(taskReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskReasonMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskReasonMasterAuditLogs");
        return taskReasonMasterAuditLogRepository.findAll(pageable).map(taskReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskReasonMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get TaskReasonMasterAuditLog : {}", id);
        return taskReasonMasterAuditLogRepository.findById(id).map(taskReasonMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskReasonMasterAuditLog : {}", id);
        taskReasonMasterAuditLogRepository.deleteById(id);
    }
}
