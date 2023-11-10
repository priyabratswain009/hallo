package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.TaskCommentsRepository;
import com.sunknowledge.dme.rcm.service.TaskCommentsService;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.TaskComments}.
 */
@RestController
@RequestMapping("/api")
public class TaskCommentsResource {

    private final Logger log = LoggerFactory.getLogger(TaskCommentsResource.class);

    private static final String ENTITY_NAME = "utilityapisTaskComments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskCommentsService taskCommentsService;

    private final TaskCommentsRepository taskCommentsRepository;

    public TaskCommentsResource(TaskCommentsService taskCommentsService, TaskCommentsRepository taskCommentsRepository) {
        this.taskCommentsService = taskCommentsService;
        this.taskCommentsRepository = taskCommentsRepository;
    }

    /**
     * {@code POST  /task-comments} : Create a new taskComments.
     *
     * @param taskCommentsDTO the taskCommentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskCommentsDTO, or with status {@code 400 (Bad Request)} if the taskComments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/task-comments")
    public ResponseEntity<TaskCommentsDTO> createTaskComments(@Valid @RequestBody TaskCommentsDTO taskCommentsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TaskComments : {}", taskCommentsDTO);
        if (taskCommentsDTO.getTaskCommentId() != null) {
            throw new BadRequestAlertException("A new taskComments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskCommentsDTO result = taskCommentsService.save(taskCommentsDTO);
        return ResponseEntity
            .created(new URI("/api/task-comments/" + result.getTaskCommentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getTaskCommentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /task-comments/:taskCommentId} : Updates an existing taskComments.
     *
     * @param taskCommentId the id of the taskCommentsDTO to save.
     * @param taskCommentsDTO the taskCommentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskCommentsDTO,
     * or with status {@code 400 (Bad Request)} if the taskCommentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskCommentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/task-comments/{taskCommentId}")
    public ResponseEntity<TaskCommentsDTO> updateTaskComments(
        @PathVariable(value = "taskCommentId", required = false) final Long taskCommentId,
        @Valid @RequestBody TaskCommentsDTO taskCommentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaskComments : {}, {}", taskCommentId, taskCommentsDTO);
        if (taskCommentsDTO.getTaskCommentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskCommentId, taskCommentsDTO.getTaskCommentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskCommentsRepository.existsById(taskCommentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskCommentsDTO result = taskCommentsService.update(taskCommentsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskCommentsDTO.getTaskCommentId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /task-comments/:taskCommentId} : Partial updates given fields of an existing taskComments, field will ignore if it is null
     *
     * @param taskCommentId the id of the taskCommentsDTO to save.
     * @param taskCommentsDTO the taskCommentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskCommentsDTO,
     * or with status {@code 400 (Bad Request)} if the taskCommentsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskCommentsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskCommentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/task-comments/{taskCommentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskCommentsDTO> partialUpdateTaskComments(
        @PathVariable(value = "taskCommentId", required = false) final Long taskCommentId,
        @NotNull @RequestBody TaskCommentsDTO taskCommentsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskComments partially : {}, {}", taskCommentId, taskCommentsDTO);
        if (taskCommentsDTO.getTaskCommentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(taskCommentId, taskCommentsDTO.getTaskCommentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskCommentsRepository.existsById(taskCommentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskCommentsDTO> result = taskCommentsService.partialUpdate(taskCommentsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskCommentsDTO.getTaskCommentId().toString())
        );
    }

    /**
     * {@code GET  /task-comments} : get all the taskComments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskComments in body.
     */
    @GetMapping("/task-comments")
    public ResponseEntity<List<TaskCommentsDTO>> getAllTaskComments(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TaskComments");
        Page<TaskCommentsDTO> page = taskCommentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /task-comments/:id} : get the "id" taskComments.
     *
     * @param id the id of the taskCommentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskCommentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/task-comments/{id}")
    public ResponseEntity<TaskCommentsDTO> getTaskComments(@PathVariable Long id) {
        log.debug("REST request to get TaskComments : {}", id);
        Optional<TaskCommentsDTO> taskCommentsDTO = taskCommentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskCommentsDTO);
    }

    /**
     * {@code DELETE  /task-comments/:id} : delete the "id" taskComments.
     *
     * @param id the id of the taskCommentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-comments/{id}")
    public ResponseEntity<Void> deleteTaskComments(@PathVariable Long id) {
        log.debug("REST request to delete TaskComments : {}", id);
        taskCommentsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
