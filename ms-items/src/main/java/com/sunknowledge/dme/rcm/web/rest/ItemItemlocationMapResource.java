package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemItemlocationMapRepository;
import com.sunknowledge.dme.rcm.service.ItemItemlocationMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemItemlocationMap}.
 */
@RestController
@RequestMapping("/api")
public class ItemItemlocationMapResource {

    private final Logger log = LoggerFactory.getLogger(ItemItemlocationMapResource.class);

    private static final String ENTITY_NAME = "itemsItemItemlocationMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemItemlocationMapService itemItemlocationMapService;

    private final ItemItemlocationMapRepository itemItemlocationMapRepository;

    public ItemItemlocationMapResource(
        ItemItemlocationMapService itemItemlocationMapService,
        ItemItemlocationMapRepository itemItemlocationMapRepository
    ) {
        this.itemItemlocationMapService = itemItemlocationMapService;
        this.itemItemlocationMapRepository = itemItemlocationMapRepository;
    }

    /**
     * {@code POST  /item-itemlocation-maps} : Create a new itemItemlocationMap.
     *
     * @param itemItemlocationMapDTO the itemItemlocationMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemItemlocationMapDTO, or with status {@code 400 (Bad Request)} if the itemItemlocationMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-itemlocation-maps")
    public ResponseEntity<ItemItemlocationMapDTO> createItemItemlocationMap(@RequestBody ItemItemlocationMapDTO itemItemlocationMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemItemlocationMap : {}", itemItemlocationMapDTO);
        if (itemItemlocationMapDTO.getItemItemlocationMapId() != null) {
            throw new BadRequestAlertException("A new itemItemlocationMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemItemlocationMapDTO result = itemItemlocationMapService.save(itemItemlocationMapDTO);
        return ResponseEntity
            .created(new URI("/api/item-itemlocation-maps/" + result.getItemItemlocationMapId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemItemlocationMapId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /item-itemlocation-maps/:itemItemlocationMapId} : Updates an existing itemItemlocationMap.
     *
     * @param itemItemlocationMapId the id of the itemItemlocationMapDTO to save.
     * @param itemItemlocationMapDTO the itemItemlocationMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemItemlocationMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemItemlocationMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemItemlocationMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-itemlocation-maps/{itemItemlocationMapId}")
    public ResponseEntity<ItemItemlocationMapDTO> updateItemItemlocationMap(
        @PathVariable(value = "itemItemlocationMapId", required = false) final Long itemItemlocationMapId,
        @RequestBody ItemItemlocationMapDTO itemItemlocationMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemItemlocationMap : {}, {}", itemItemlocationMapId, itemItemlocationMapDTO);
        if (itemItemlocationMapDTO.getItemItemlocationMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemItemlocationMapId, itemItemlocationMapDTO.getItemItemlocationMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemItemlocationMapRepository.existsById(itemItemlocationMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemItemlocationMapDTO result = itemItemlocationMapService.update(itemItemlocationMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    itemItemlocationMapDTO.getItemItemlocationMapId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-itemlocation-maps/:itemItemlocationMapId} : Partial updates given fields of an existing itemItemlocationMap, field will ignore if it is null
     *
     * @param itemItemlocationMapId the id of the itemItemlocationMapDTO to save.
     * @param itemItemlocationMapDTO the itemItemlocationMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemItemlocationMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemItemlocationMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemItemlocationMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemItemlocationMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/item-itemlocation-maps/{itemItemlocationMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ItemItemlocationMapDTO> partialUpdateItemItemlocationMap(
        @PathVariable(value = "itemItemlocationMapId", required = false) final Long itemItemlocationMapId,
        @RequestBody ItemItemlocationMapDTO itemItemlocationMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemItemlocationMap partially : {}, {}", itemItemlocationMapId, itemItemlocationMapDTO);
        if (itemItemlocationMapDTO.getItemItemlocationMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemItemlocationMapId, itemItemlocationMapDTO.getItemItemlocationMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemItemlocationMapRepository.existsById(itemItemlocationMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemItemlocationMapDTO> result = itemItemlocationMapService.partialUpdate(itemItemlocationMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                itemItemlocationMapDTO.getItemItemlocationMapId().toString()
            )
        );
    }

    /**
     * {@code GET  /item-itemlocation-maps} : get all the itemItemlocationMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemItemlocationMaps in body.
     */
    @GetMapping("/item-itemlocation-maps")
    public ResponseEntity<List<ItemItemlocationMapDTO>> getAllItemItemlocationMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemItemlocationMaps");
        Page<ItemItemlocationMapDTO> page = itemItemlocationMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-itemlocation-maps/:id} : get the "id" itemItemlocationMap.
     *
     * @param id the id of the itemItemlocationMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemItemlocationMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-itemlocation-maps/{id}")
    public ResponseEntity<ItemItemlocationMapDTO> getItemItemlocationMap(@PathVariable Long id) {
        log.debug("REST request to get ItemItemlocationMap : {}", id);
        Optional<ItemItemlocationMapDTO> itemItemlocationMapDTO = itemItemlocationMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemItemlocationMapDTO);
    }

    /**
     * {@code DELETE  /item-itemlocation-maps/:id} : delete the "id" itemItemlocationMap.
     *
     * @param id the id of the itemItemlocationMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-itemlocation-maps/{id}")
    public ResponseEntity<Void> deleteItemItemlocationMap(@PathVariable Long id) {
        log.debug("REST request to delete ItemItemlocationMap : {}", id);
        itemItemlocationMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
