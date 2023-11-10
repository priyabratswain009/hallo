package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemMasterRepository;
import com.sunknowledge.dme.rcm.service.ItemMasterService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemMaster}.
 */
@RestController
@RequestMapping("/api")
public class ItemMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemMasterResource.class);

    private static final String ENTITY_NAME = "itemsItemMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemMasterService itemMasterService;

    private final ItemMasterRepository itemMasterRepository;

    public ItemMasterResource(ItemMasterService itemMasterService, ItemMasterRepository itemMasterRepository) {
        this.itemMasterService = itemMasterService;
        this.itemMasterRepository = itemMasterRepository;
    }

    /**
     * {@code POST  /item-masters} : Create a new itemMaster.
     *
     * @param itemMasterDTO the itemMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemMasterDTO, or with status {@code 400 (Bad Request)} if the itemMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-masters")
    public ResponseEntity<ItemMasterDTO> createItemMaster(@Valid @RequestBody ItemMasterDTO itemMasterDTO) throws URISyntaxException {
        log.debug("REST request to save ItemMaster : {}", itemMasterDTO);
        if (itemMasterDTO.getItemId() != null) {
            throw new BadRequestAlertException("A new itemMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemMasterDTO result = itemMasterService.save(itemMasterDTO);
        return ResponseEntity
            .created(new URI("/api/item-masters/" + result.getItemId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-masters/:itemId} : Updates an existing itemMaster.
     *
     * @param itemId the id of the itemMasterDTO to save.
     * @param itemMasterDTO the itemMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemMasterDTO,
     * or with status {@code 400 (Bad Request)} if the itemMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-masters/{itemId}")
    public ResponseEntity<ItemMasterDTO> updateItemMaster(
        @PathVariable(value = "itemId", required = false) final Long itemId,
        @Valid @RequestBody ItemMasterDTO itemMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemMaster : {}, {}", itemId, itemMasterDTO);
        if (itemMasterDTO.getItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemId, itemMasterDTO.getItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemMasterRepository.existsById(itemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemMasterDTO result = itemMasterService.update(itemMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemMasterDTO.getItemId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-masters/:itemId} : Partial updates given fields of an existing itemMaster, field will ignore if it is null
     *
     * @param itemId the id of the itemMasterDTO to save.
     * @param itemMasterDTO the itemMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemMasterDTO,
     * or with status {@code 400 (Bad Request)} if the itemMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-masters/{itemId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemMasterDTO> partialUpdateItemMaster(
        @PathVariable(value = "itemId", required = false) final Long itemId,
        @NotNull @RequestBody ItemMasterDTO itemMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemMaster partially : {}, {}", itemId, itemMasterDTO);
        if (itemMasterDTO.getItemId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemId, itemMasterDTO.getItemId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemMasterRepository.existsById(itemId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemMasterDTO> result = itemMasterService.partialUpdate(itemMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemMasterDTO.getItemId().toString())
        );
    }

    /**
     * {@code GET  /item-masters} : get all the itemMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemMasters in body.
     */
    @GetMapping("/item-masters")
    public ResponseEntity<List<ItemMasterDTO>> getAllItemMasters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ItemMasters");
        Page<ItemMasterDTO> page = itemMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-masters/:id} : get the "id" itemMaster.
     *
     * @param id the id of the itemMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-masters/{id}")
    public ResponseEntity<ItemMasterDTO> getItemMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemMaster : {}", id);
        Optional<ItemMasterDTO> itemMasterDTO = itemMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemMasterDTO);
    }

    /**
     * {@code DELETE  /item-masters/:id} : delete the "id" itemMaster.
     *
     * @param id the id of the itemMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-masters/{id}")
    public ResponseEntity<Void> deleteItemMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemMaster : {}", id);
        itemMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
