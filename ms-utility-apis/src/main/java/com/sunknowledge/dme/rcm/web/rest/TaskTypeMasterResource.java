package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaskTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.TaskTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.TaskTypeMasterDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaskTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class TaskTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(TaskTypeMasterResource.class);

    private static final String ENTITY_NAME = "utilityapisTaskTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskTypeMasterService taskTypeMasterService;

    private final TaskTypeMasterRepository taskTypeMasterRepository;

    public TaskTypeMasterResource(TaskTypeMasterService taskTypeMasterService, TaskTypeMasterRepository taskTypeMasterRepository) {
        this.taskTypeMasterService = taskTypeMasterService;
        this.taskTypeMasterRepository = taskTypeMasterRepository;
    }

    /**
     * {@code POST  /task-type-masters} : Create a new taskTypeMaster.
     *
     * @param taskTypeMasterDTO the taskTypeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskTypeMasterDTO, or with status {@code 400 (Bad Request)} if the taskTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-type-masters")
    public ResponseEntity<TaskTypeMasterDTO> createTaskTypeMaster(@Valid @RequestBody TaskTypeMasterDTO taskTypeMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save TaskTypeMaster : {}", taskTypeMasterDTO);
        if (taskTypeMasterDTO.getTaskId() != null) {
            throw new BadRequestAlertException("A new taskTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskTypeMasterDTO result = taskTypeMasterService.save(taskTypeMasterDTO);
        return ResponseEntity
            .created(new URI("/api/task-type-masters/" + result.getTaskId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTaskId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-type-masters/:taskId} : Updates an existing taskTypeMaster.
     *
     * @param taskId the id of the taskTypeMasterDTO to save.
     * @param taskTypeMasterDTO the taskTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the taskTypeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-type-masters/{taskId}")
    public ResponseEntity<TaskTypeMasterDTO> updateTaskTypeMaster(
        @PathVariable(value = "taskId", required = false) final Long taskId,
        @Valid @RequestBody TaskTypeMasterDTO taskTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaskTypeMaster : {}, {}", taskId, taskTypeMasterDTO);
        if (taskTypeMasterDTO.getTaskId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskId, taskTypeMasterDTO.getTaskId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskTypeMasterRepository.existsById(taskId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskTypeMasterDTO result = taskTypeMasterService.update(taskTypeMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskTypeMasterDTO.getTaskId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /task-type-masters/:taskId} : Partial updates given fields of an existing taskTypeMaster, field will ignore if it is null
     *
     * @param taskId the id of the taskTypeMasterDTO to save.
     * @param taskTypeMasterDTO the taskTypeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskTypeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the taskTypeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskTypeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskTypeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-type-masters/{taskId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskTypeMasterDTO> partialUpdateTaskTypeMaster(
        @PathVariable(value = "taskId", required = false) final Long taskId,
        @NotNull @RequestBody TaskTypeMasterDTO taskTypeMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskTypeMaster partially : {}, {}", taskId, taskTypeMasterDTO);
        if (taskTypeMasterDTO.getTaskId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskId, taskTypeMasterDTO.getTaskId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskTypeMasterRepository.existsById(taskId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskTypeMasterDTO> result = taskTypeMasterService.partialUpdate(taskTypeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskTypeMasterDTO.getTaskId().toString())
        );
    }

    /**
     * {@code GET  /task-type-masters} : get all the taskTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskTypeMasters in body.
     */
    @GetMapping("/task-type-masters")
    public ResponseEntity<List<TaskTypeMasterDTO>> getAllTaskTypeMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TaskTypeMasters");
        Page<TaskTypeMasterDTO> page = taskTypeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /task-type-masters/:id} : get the "id" taskTypeMaster.
     *
     * @param id the id of the taskTypeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskTypeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-type-masters/{id}")
    public ResponseEntity<TaskTypeMasterDTO> getTaskTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get TaskTypeMaster : {}", id);
        Optional<TaskTypeMasterDTO> taskTypeMasterDTO = taskTypeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskTypeMasterDTO);
    }

    /**
     * {@code DELETE  /task-type-masters/:id} : delete the "id" taskTypeMaster.
     *
     * @param id the id of the taskTypeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-type-masters/{id}")
    public ResponseEntity<Void> deleteTaskTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete TaskTypeMaster : {}", id);
        taskTypeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
