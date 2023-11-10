package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.repository.TaskDetailsRepository;
import com.sunknowledge.dme.rcm.service.TaskDetailsService;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskDetails}.
 */
@Service
@Transactional
public class TaskDetailsServiceImpl implements TaskDetailsService {

    private final Logger log = LoggerFactory.getLogger(TaskDetailsServiceImpl.class);

    private final TaskDetailsRepository taskDetailsRepository;

    private final TaskDetailsMapper taskDetailsMapper;

    public TaskDetailsServiceImpl(TaskDetailsRepository taskDetailsRepository, TaskDetailsMapper taskDetailsMapper) {
        this.taskDetailsRepository = taskDetailsRepository;
        this.taskDetailsMapper = taskDetailsMapper;
    }

    @Override
    public TaskDetailsDTO save(TaskDetailsDTO taskDetailsDTO) {
        log.debug("Request to save TaskDetails : {}", taskDetailsDTO);
        TaskDetails taskDetails = taskDetailsMapper.toEntity(taskDetailsDTO);
        taskDetails = taskDetailsRepository.save(taskDetails);
        return taskDetailsMapper.toDto(taskDetails);
    }

    @Override
    public TaskDetailsDTO update(TaskDetailsDTO taskDetailsDTO) {
        log.debug("Request to save TaskDetails : {}", taskDetailsDTO);
        TaskDetails taskDetails = taskDetailsMapper.toEntity(taskDetailsDTO);
        taskDetails = taskDetailsRepository.save(taskDetails);
        return taskDetailsMapper.toDto(taskDetails);
    }

    @Override
    public Optional<TaskDetailsDTO> partialUpdate(TaskDetailsDTO taskDetailsDTO) {
        log.debug("Request to partially update TaskDetails : {}", taskDetailsDTO);

        return taskDetailsRepository
            .findById(taskDetailsDTO.getTaskDetailsId())
            .map(existingTaskDetails -> {
                taskDetailsMapper.partialUpdate(existingTaskDetails, taskDetailsDTO);

                return existingTaskDetails;
            })
            .map(taskDetailsRepository::save)
            .map(taskDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskDetails");
        return taskDetailsRepository.findAll(pageable).map(taskDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskDetailsDTO> findOne(Long id) {
        log.debug("Request to get TaskDetails : {}", id);
        return taskDetailsRepository.findById(id).map(taskDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskDetails : {}", id);
        taskDetailsRepository.deleteById(id);
    }
}
