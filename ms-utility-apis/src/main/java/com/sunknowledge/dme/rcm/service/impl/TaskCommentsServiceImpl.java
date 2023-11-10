package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.repository.TaskCommentsRepository;
import com.sunknowledge.dme.rcm.service.TaskCommentsService;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskCommentsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskComments}.
 */
@Service
@Transactional
public class TaskCommentsServiceImpl implements TaskCommentsService {

    private final Logger log = LoggerFactory.getLogger(TaskCommentsServiceImpl.class);

    private final TaskCommentsRepository taskCommentsRepository;

    private final TaskCommentsMapper taskCommentsMapper;

    public TaskCommentsServiceImpl(TaskCommentsRepository taskCommentsRepository, TaskCommentsMapper taskCommentsMapper) {
        this.taskCommentsRepository = taskCommentsRepository;
        this.taskCommentsMapper = taskCommentsMapper;
    }

    @Override
    public TaskCommentsDTO save(TaskCommentsDTO taskCommentsDTO) {
        log.debug("Request to save TaskComments : {}", taskCommentsDTO);
        TaskComments taskComments = taskCommentsMapper.toEntity(taskCommentsDTO);
        taskComments = taskCommentsRepository.save(taskComments);
        return taskCommentsMapper.toDto(taskComments);
    }

    @Override
    public TaskCommentsDTO update(TaskCommentsDTO taskCommentsDTO) {
        log.debug("Request to save TaskComments : {}", taskCommentsDTO);
        TaskComments taskComments = taskCommentsMapper.toEntity(taskCommentsDTO);
        taskComments = taskCommentsRepository.save(taskComments);
        return taskCommentsMapper.toDto(taskComments);
    }

    @Override
    public Optional<TaskCommentsDTO> partialUpdate(TaskCommentsDTO taskCommentsDTO) {
        log.debug("Request to partially update TaskComments : {}", taskCommentsDTO);

        return taskCommentsRepository
            .findById(taskCommentsDTO.getTaskCommentId())
            .map(existingTaskComments -> {
                taskCommentsMapper.partialUpdate(existingTaskComments, taskCommentsDTO);

                return existingTaskComments;
            })
            .map(taskCommentsRepository::save)
            .map(taskCommentsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskCommentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskComments");
        return taskCommentsRepository.findAll(pageable).map(taskCommentsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskCommentsDTO> findOne(Long id) {
        log.debug("Request to get TaskComments : {}", id);
        return taskCommentsRepository.findById(id).map(taskCommentsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskComments : {}", id);
        taskCommentsRepository.deleteById(id);
    }
}
