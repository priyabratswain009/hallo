package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaskDetailsRepository;
import com.sunknowledge.dme.rcm.service.TaskDetailsService;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaskDetails}.
 */
@RestController
@RequestMapping("/api")
public class TaskDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TaskDetailsResource.class);

    private static final String ENTITY_NAME = "utilityapisTaskDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskDetailsService taskDetailsService;

    private final TaskDetailsRepository taskDetailsRepository;

    public TaskDetailsResource(TaskDetailsService taskDetailsService, TaskDetailsRepository taskDetailsRepository) {
        this.taskDetailsService = taskDetailsService;
        this.taskDetailsRepository = taskDetailsRepository;
    }

    /**
     * {@code POST  /task-details} : Create a new taskDetails.
     *
     * @param taskDetailsDTO the taskDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskDetailsDTO, or with status {@code 400 (Bad Request)} if the taskDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-details")
    public ResponseEntity<TaskDetailsDTO> createTaskDetails(@Valid @RequestBody TaskDetailsDTO taskDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save TaskDetails : {}", taskDetailsDTO);
        if (taskDetailsDTO.getTaskDetailsId() != null) {
            throw new BadRequestAlertException("A new taskDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskDetailsDTO result = taskDetailsService.save(taskDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/task-details/" + result.getTaskDetailsId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTaskDetailsId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-details/:taskDetailsId} : Updates an existing taskDetails.
     *
     * @param taskDetailsId the id of the taskDetailsDTO to save.
     * @param taskDetailsDTO the taskDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the taskDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-details/{taskDetailsId}")
    public ResponseEntity<TaskDetailsDTO> updateTaskDetails(
        @PathVariable(value = "taskDetailsId", required = false) final Long taskDetailsId,
        @Valid @RequestBody TaskDetailsDTO taskDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaskDetails : {}, {}", taskDetailsId, taskDetailsDTO);
        if (taskDetailsDTO.getTaskDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskDetailsId, taskDetailsDTO.getTaskDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskDetailsRepository.existsById(taskDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskDetailsDTO result = taskDetailsService.update(taskDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskDetailsDTO.getTaskDetailsId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /task-details/:taskDetailsId} : Partial updates given fields of an existing taskDetails, field will ignore if it is null
     *
     * @param taskDetailsId the id of the taskDetailsDTO to save.
     * @param taskDetailsDTO the taskDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the taskDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-details/{taskDetailsId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskDetailsDTO> partialUpdateTaskDetails(
        @PathVariable(value = "taskDetailsId", required = false) final Long taskDetailsId,
        @NotNull @RequestBody TaskDetailsDTO taskDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskDetails partially : {}, {}", taskDetailsId, taskDetailsDTO);
        if (taskDetailsDTO.getTaskDetailsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskDetailsId, taskDetailsDTO.getTaskDetailsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskDetailsRepository.existsById(taskDetailsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskDetailsDTO> result = taskDetailsService.partialUpdate(taskDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskDetailsDTO.getTaskDetailsId().toString())
        );
    }

    /**
     * {@code GET  /task-details} : get all the taskDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskDetails in body.
     */
    @GetMapping("/task-details")
    public ResponseEntity<List<TaskDetailsDTO>> getAllTaskDetails(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TaskDetails");
        Page<TaskDetailsDTO> page = taskDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /task-details/:id} : get the "id" taskDetails.
     *
     * @param id the id of the taskDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-details/{id}")
    public ResponseEntity<TaskDetailsDTO> getTaskDetails(@PathVariable Long id) {
        log.debug("REST request to get TaskDetails : {}", id);
        Optional<TaskDetailsDTO> taskDetailsDTO = taskDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskDetailsDTO);
    }

    /**
     * {@code DELETE  /task-details/:id} : delete the "id" taskDetails.
     *
     * @param id the id of the taskDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-details/{id}")
    public ResponseEntity<Void> deleteTaskDetails(@PathVariable Long id) {
        log.debug("REST request to delete TaskDetails : {}", id);
        taskDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
