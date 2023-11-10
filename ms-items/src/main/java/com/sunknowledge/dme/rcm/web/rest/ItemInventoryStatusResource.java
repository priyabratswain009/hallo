package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusService;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemInventoryStatus}.
 */
@RestController
@RequestMapping("/api")
public class ItemInventoryStatusResource {

    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusResource.class);

    private static final String ENTITY_NAME = "itemsItemInventoryStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemInventoryStatusService itemInventoryStatusService;

    private final ItemInventoryStatusRepository itemInventoryStatusRepository;

    public ItemInventoryStatusResource(
        ItemInventoryStatusService itemInventoryStatusService,
        ItemInventoryStatusRepository itemInventoryStatusRepository
    ) {
        this.itemInventoryStatusService = itemInventoryStatusService;
        this.itemInventoryStatusRepository = itemInventoryStatusRepository;
    }

    /**
     * {@code POST  /item-inventory-statuses} : Create a new itemInventoryStatus.
     *
     * @param itemInventoryStatusDTO the itemInventoryStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemInventoryStatusDTO, or with status {@code 400 (Bad Request)} if the itemInventoryStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-inventory-statuses")
    public ResponseEntity<ItemInventoryStatusDTO> createItemInventoryStatus(@RequestBody ItemInventoryStatusDTO itemInventoryStatusDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemInventoryStatus : {}", itemInventoryStatusDTO);
        if (itemInventoryStatusDTO.getItemInventoryStatusId() != null) {
            throw new BadRequestAlertException("A new itemInventoryStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemInventoryStatusDTO result = itemInventoryStatusService.save(itemInventoryStatusDTO);
        return ResponseEntity
            .created(new URI("/api/item-inventory-statuses/" + result.getItemInventoryStatusId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemInventoryStatusId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /item-inventory-statuses/:itemInventoryStatusId} : Updates an existing itemInventoryStatus.
     *
     * @param itemInventoryStatusId the id of the itemInventoryStatusDTO to save.
     * @param itemInventoryStatusDTO the itemInventoryStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemInventoryStatusDTO,
     * or with status {@code 400 (Bad Request)} if the itemInventoryStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemInventoryStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-inventory-statuses/{itemInventoryStatusId}")
    public ResponseEntity<ItemInventoryStatusDTO> updateItemInventoryStatus(
        @PathVariable(value = "itemInventoryStatusId", required = false) final Long itemInventoryStatusId,
        @RequestBody ItemInventoryStatusDTO itemInventoryStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemInventoryStatus : {}, {}", itemInventoryStatusId, itemInventoryStatusDTO);
        if (itemInventoryStatusDTO.getItemInventoryStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemInventoryStatusId, itemInventoryStatusDTO.getItemInventoryStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemInventoryStatusRepository.existsById(itemInventoryStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemInventoryStatusDTO result = itemInventoryStatusService.update(itemInventoryStatusDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    itemInventoryStatusDTO.getItemInventoryStatusId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-inventory-statuses/:itemInventoryStatusId} : Partial updates given fields of an existing itemInventoryStatus, field will ignore if it is null
     *
     * @param itemInventoryStatusId the id of the itemInventoryStatusDTO to save.
     * @param itemInventoryStatusDTO the itemInventoryStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemInventoryStatusDTO,
     * or with status {@code 400 (Bad Request)} if the itemInventoryStatusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemInventoryStatusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemInventoryStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/item-inventory-statuses/{itemInventoryStatusId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ItemInventoryStatusDTO> partialUpdateItemInventoryStatus(
        @PathVariable(value = "itemInventoryStatusId", required = false) final Long itemInventoryStatusId,
        @RequestBody ItemInventoryStatusDTO itemInventoryStatusDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemInventoryStatus partially : {}, {}", itemInventoryStatusId, itemInventoryStatusDTO);
        if (itemInventoryStatusDTO.getItemInventoryStatusId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemInventoryStatusId, itemInventoryStatusDTO.getItemInventoryStatusId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemInventoryStatusRepository.existsById(itemInventoryStatusId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemInventoryStatusDTO> result = itemInventoryStatusService.partialUpdate(itemInventoryStatusDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                itemInventoryStatusDTO.getItemInventoryStatusId().toString()
            )
        );
    }

    /**
     * {@code GET  /item-inventory-statuses} : get all the itemInventoryStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemInventoryStatuses in body.
     */
    @GetMapping("/item-inventory-statuses")
    public ResponseEntity<List<ItemInventoryStatusDTO>> getAllItemInventoryStatuses(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemInventoryStatuses");
        Page<ItemInventoryStatusDTO> page = itemInventoryStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-inventory-statuses/:id} : get the "id" itemInventoryStatus.
     *
     * @param id the id of the itemInventoryStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemInventoryStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-inventory-statuses/{id}")
    public ResponseEntity<ItemInventoryStatusDTO> getItemInventoryStatus(@PathVariable Long id) {
        log.debug("REST request to get ItemInventoryStatus : {}", id);
        Optional<ItemInventoryStatusDTO> itemInventoryStatusDTO = itemInventoryStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemInventoryStatusDTO);
    }

    /**
     * {@code DELETE  /item-inventory-statuses/:id} : delete the "id" itemInventoryStatus.
     *
     * @param id the id of the itemInventoryStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-inventory-statuses/{id}")
    public ResponseEntity<Void> deleteItemInventoryStatus(@PathVariable Long id) {
        log.debug("REST request to delete ItemInventoryStatus : {}", id);
        itemInventoryStatusService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
