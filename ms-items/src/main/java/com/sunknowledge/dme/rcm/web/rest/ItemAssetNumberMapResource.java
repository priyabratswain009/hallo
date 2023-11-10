package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemAssetNumberMapRepository;
import com.sunknowledge.dme.rcm.service.ItemAssetNumberMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap}.
 */
@RestController
@RequestMapping("/api")
public class ItemAssetNumberMapResource {

    private final Logger log = LoggerFactory.getLogger(ItemAssetNumberMapResource.class);

    private static final String ENTITY_NAME = "itemsItemAssetNumberMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemAssetNumberMapService itemAssetNumberMapService;

    private final ItemAssetNumberMapRepository itemAssetNumberMapRepository;

    public ItemAssetNumberMapResource(
        ItemAssetNumberMapService itemAssetNumberMapService,
        ItemAssetNumberMapRepository itemAssetNumberMapRepository
    ) {
        this.itemAssetNumberMapService = itemAssetNumberMapService;
        this.itemAssetNumberMapRepository = itemAssetNumberMapRepository;
    }

    /**
     * {@code POST  /item-asset-number-maps} : Create a new itemAssetNumberMap.
     *
     * @param itemAssetNumberMapDTO the itemAssetNumberMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemAssetNumberMapDTO, or with status {@code 400 (Bad Request)} if the itemAssetNumberMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-asset-number-maps")
    public ResponseEntity<ItemAssetNumberMapDTO> createItemAssetNumberMap(@RequestBody ItemAssetNumberMapDTO itemAssetNumberMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemAssetNumberMap : {}", itemAssetNumberMapDTO);
        if (itemAssetNumberMapDTO.getItemAssetNumberId() != null) {
            throw new BadRequestAlertException("A new itemAssetNumberMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAssetNumberMapDTO result = itemAssetNumberMapService.save(itemAssetNumberMapDTO);
        return ResponseEntity
            .created(new URI("/api/item-asset-number-maps/" + result.getItemAssetNumberId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemAssetNumberId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-asset-number-maps/:itemAssetNumberId} : Updates an existing itemAssetNumberMap.
     *
     * @param itemAssetNumberId the id of the itemAssetNumberMapDTO to save.
     * @param itemAssetNumberMapDTO the itemAssetNumberMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemAssetNumberMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemAssetNumberMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemAssetNumberMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-asset-number-maps/{itemAssetNumberId}")
    public ResponseEntity<ItemAssetNumberMapDTO> updateItemAssetNumberMap(
        @PathVariable(value = "itemAssetNumberId", required = false) final Long itemAssetNumberId,
        @RequestBody ItemAssetNumberMapDTO itemAssetNumberMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemAssetNumberMap : {}, {}", itemAssetNumberId, itemAssetNumberMapDTO);
        if (itemAssetNumberMapDTO.getItemAssetNumberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemAssetNumberId, itemAssetNumberMapDTO.getItemAssetNumberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemAssetNumberMapRepository.existsById(itemAssetNumberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemAssetNumberMapDTO result = itemAssetNumberMapService.update(itemAssetNumberMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    itemAssetNumberMapDTO.getItemAssetNumberId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-asset-number-maps/:itemAssetNumberId} : Partial updates given fields of an existing itemAssetNumberMap, field will ignore if it is null
     *
     * @param itemAssetNumberId the id of the itemAssetNumberMapDTO to save.
     * @param itemAssetNumberMapDTO the itemAssetNumberMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemAssetNumberMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemAssetNumberMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemAssetNumberMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemAssetNumberMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-asset-number-maps/{itemAssetNumberId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemAssetNumberMapDTO> partialUpdateItemAssetNumberMap(
        @PathVariable(value = "itemAssetNumberId", required = false) final Long itemAssetNumberId,
        @RequestBody ItemAssetNumberMapDTO itemAssetNumberMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemAssetNumberMap partially : {}, {}", itemAssetNumberId, itemAssetNumberMapDTO);
        if (itemAssetNumberMapDTO.getItemAssetNumberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemAssetNumberId, itemAssetNumberMapDTO.getItemAssetNumberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemAssetNumberMapRepository.existsById(itemAssetNumberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemAssetNumberMapDTO> result = itemAssetNumberMapService.partialUpdate(itemAssetNumberMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemAssetNumberMapDTO.getItemAssetNumberId().toString())
        );
    }

    /**
     * {@code GET  /item-asset-number-maps} : get all the itemAssetNumberMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemAssetNumberMaps in body.
     */
    @GetMapping("/item-asset-number-maps")
    public ResponseEntity<List<ItemAssetNumberMapDTO>> getAllItemAssetNumberMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemAssetNumberMaps");
        Page<ItemAssetNumberMapDTO> page = itemAssetNumberMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-asset-number-maps/:id} : get the "id" itemAssetNumberMap.
     *
     * @param id the id of the itemAssetNumberMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemAssetNumberMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-asset-number-maps/{id}")
    public ResponseEntity<ItemAssetNumberMapDTO> getItemAssetNumberMap(@PathVariable Long id) {
        log.debug("REST request to get ItemAssetNumberMap : {}", id);
        Optional<ItemAssetNumberMapDTO> itemAssetNumberMapDTO = itemAssetNumberMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAssetNumberMapDTO);
    }

    /**
     * {@code DELETE  /item-asset-number-maps/:id} : delete the "id" itemAssetNumberMap.
     *
     * @param id the id of the itemAssetNumberMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-asset-number-maps/{id}")
    public ResponseEntity<Void> deleteItemAssetNumberMap(@PathVariable Long id) {
        log.debug("REST request to delete ItemAssetNumberMap : {}", id);
        itemAssetNumberMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
