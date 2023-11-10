package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaskReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.TaskReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog}.
 */
@RestController
@RequestMapping("/api")
public class TaskReasonMasterAuditLogResource {

    private final Logger log = LoggerFactory.getLogger(TaskReasonMasterAuditLogResource.class);

    private static final String ENTITY_NAME = "settingsTaskReasonMasterAuditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskReasonMasterAuditLogService taskReasonMasterAuditLogService;

    private final TaskReasonMasterAuditLogRepository taskReasonMasterAuditLogRepository;

    public TaskReasonMasterAuditLogResource(
        TaskReasonMasterAuditLogService taskReasonMasterAuditLogService,
        TaskReasonMasterAuditLogRepository taskReasonMasterAuditLogRepository
    ) {
        this.taskReasonMasterAuditLogService = taskReasonMasterAuditLogService;
        this.taskReasonMasterAuditLogRepository = taskReasonMasterAuditLogRepository;
    }

    /**
     * {@code POST  /task-reason-master-audit-logs} : Create a new taskReasonMasterAuditLog.
     *
     * @param taskReasonMasterAuditLogDTO the taskReasonMasterAuditLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskReasonMasterAuditLogDTO, or with status {@code 400 (Bad Request)} if the taskReasonMasterAuditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-reason-master-audit-logs")
    public ResponseEntity<TaskReasonMasterAuditLogDTO> createTaskReasonMasterAuditLog(
        @RequestBody TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TaskReasonMasterAuditLog : {}", taskReasonMasterAuditLogDTO);
        if (taskReasonMasterAuditLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskReasonMasterAuditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskReasonMasterAuditLogDTO result = taskReasonMasterAuditLogService.save(taskReasonMasterAuditLogDTO);
        return ResponseEntity
            .created(new URI("/api/task-reason-master-audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-reason-master-audit-logs/:id} : Updates an existing taskReasonMasterAuditLog.
     *
     * @param id the id of the taskReasonMasterAuditLogDTO to save.
     * @param taskReasonMasterAuditLogDTO the taskReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the taskReasonMasterAuditLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-reason-master-audit-logs/{id}")
    public ResponseEntity<TaskReasonMasterAuditLogDTO> updateTaskReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaskReasonMasterAuditLog : {}, {}", id, taskReasonMasterAuditLogDTO);
        if (taskReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskReasonMasterAuditLogDTO result = taskReasonMasterAuditLogService.update(taskReasonMasterAuditLogDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskReasonMasterAuditLogDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /task-reason-master-audit-logs/:id} : Partial updates given fields of an existing taskReasonMasterAuditLog, field will ignore if it is null
     *
     * @param id the id of the taskReasonMasterAuditLogDTO to save.
     * @param taskReasonMasterAuditLogDTO the taskReasonMasterAuditLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskReasonMasterAuditLogDTO,
     * or with status {@code 400 (Bad Request)} if the taskReasonMasterAuditLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskReasonMasterAuditLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskReasonMasterAuditLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-reason-master-audit-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskReasonMasterAuditLogDTO> partialUpdateTaskReasonMasterAuditLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskReasonMasterAuditLog partially : {}, {}", id, taskReasonMasterAuditLogDTO);
        if (taskReasonMasterAuditLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskReasonMasterAuditLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskReasonMasterAuditLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskReasonMasterAuditLogDTO> result = taskReasonMasterAuditLogService.partialUpdate(taskReasonMasterAuditLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskReasonMasterAuditLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /task-reason-master-audit-logs} : get all the taskReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskReasonMasterAuditLogs in body.
     */
    @GetMapping("/task-reason-master-audit-logs")
    public ResponseEntity<List<TaskReasonMasterAuditLogDTO>> getAllTaskReasonMasterAuditLogs(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TaskReasonMasterAuditLogs");
        Page<TaskReasonMasterAuditLogDTO> page = taskReasonMasterAuditLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /task-reason-master-audit-logs/:id} : get the "id" taskReasonMasterAuditLog.
     *
     * @param id the id of the taskReasonMasterAuditLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskReasonMasterAuditLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-reason-master-audit-logs/{id}")
    public ResponseEntity<TaskReasonMasterAuditLogDTO> getTaskReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to get TaskReasonMasterAuditLog : {}", id);
        Optional<TaskReasonMasterAuditLogDTO> taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskReasonMasterAuditLogDTO);
    }

    /**
     * {@code DELETE  /task-reason-master-audit-logs/:id} : delete the "id" taskReasonMasterAuditLog.
     *
     * @param id the id of the taskReasonMasterAuditLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-reason-master-audit-logs/{id}")
    public ResponseEntity<Void> deleteTaskReasonMasterAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete TaskReasonMasterAuditLog : {}", id);
        taskReasonMasterAuditLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
