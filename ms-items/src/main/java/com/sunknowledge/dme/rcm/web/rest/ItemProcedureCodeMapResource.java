package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapRepository;
import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap}.
 */
@RestController
@RequestMapping("/api")
public class ItemProcedureCodeMapResource {

    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapResource.class);

    private static final String ENTITY_NAME = "itemsItemProcedureCodeMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemProcedureCodeMapService itemProcedureCodeMapService;

    private final ItemProcedureCodeMapRepository itemProcedureCodeMapRepository;

    public ItemProcedureCodeMapResource(
        ItemProcedureCodeMapService itemProcedureCodeMapService,
        ItemProcedureCodeMapRepository itemProcedureCodeMapRepository
    ) {
        this.itemProcedureCodeMapService = itemProcedureCodeMapService;
        this.itemProcedureCodeMapRepository = itemProcedureCodeMapRepository;
    }

    /**
     * {@code POST  /item-procedure-code-maps} : Create a new itemProcedureCodeMap.
     *
     * @param itemProcedureCodeMapDTO the itemProcedureCodeMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemProcedureCodeMapDTO, or with status {@code 400 (Bad Request)} if the itemProcedureCodeMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-procedure-code-maps")
    public ResponseEntity<ItemProcedureCodeMapDTO> createItemProcedureCodeMap(@RequestBody ItemProcedureCodeMapDTO itemProcedureCodeMapDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemProcedureCodeMap : {}", itemProcedureCodeMapDTO);
        if (itemProcedureCodeMapDTO.getItemProcedureCodeMapId() != null) {
            throw new BadRequestAlertException("A new itemProcedureCodeMap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemProcedureCodeMapDTO result = itemProcedureCodeMapService.save(itemProcedureCodeMapDTO);
        return ResponseEntity
            .created(new URI("/api/item-procedure-code-maps/" + result.getItemProcedureCodeMapId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemProcedureCodeMapId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /item-procedure-code-maps/:itemProcedureCodeMapId} : Updates an existing itemProcedureCodeMap.
     *
     * @param itemProcedureCodeMapId the id of the itemProcedureCodeMapDTO to save.
     * @param itemProcedureCodeMapDTO the itemProcedureCodeMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemProcedureCodeMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemProcedureCodeMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemProcedureCodeMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-procedure-code-maps/{itemProcedureCodeMapId}")
    public ResponseEntity<ItemProcedureCodeMapDTO> updateItemProcedureCodeMap(
        @PathVariable(value = "itemProcedureCodeMapId", required = false) final Long itemProcedureCodeMapId,
        @RequestBody ItemProcedureCodeMapDTO itemProcedureCodeMapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemProcedureCodeMap : {}, {}", itemProcedureCodeMapId, itemProcedureCodeMapDTO);
        if (itemProcedureCodeMapDTO.getItemProcedureCodeMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemProcedureCodeMapId, itemProcedureCodeMapDTO.getItemProcedureCodeMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemProcedureCodeMapRepository.existsById(itemProcedureCodeMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemProcedureCodeMapDTO result = itemProcedureCodeMapService.update(itemProcedureCodeMapDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    itemProcedureCodeMapDTO.getItemProcedureCodeMapId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-procedure-code-maps/:itemProcedureCodeMapId} : Partial updates given fields of an existing itemProcedureCodeMap, field will ignore if it is null
     *
     * @param itemProcedureCodeMapId the id of the itemProcedureCodeMapDTO to save.
     * @param itemProcedureCodeMapDTO the itemProcedureCodeMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemProcedureCodeMapDTO,
     * or with status {@code 400 (Bad Request)} if the itemProcedureCodeMapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemProcedureCodeMapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemProcedureCodeMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/item-procedure-code-maps/{itemProcedureCodeMapId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<ItemProcedureCodeMapDTO> partialUpdateItemProcedureCodeMap(
        @PathVariable(value = "itemProcedureCodeMapId", required = false) final Long itemProcedureCodeMapId,
        @RequestBody ItemProcedureCodeMapDTO itemProcedureCodeMapDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ItemProcedureCodeMap partially : {}, {}",
            itemProcedureCodeMapId,
            itemProcedureCodeMapDTO
        );
        if (itemProcedureCodeMapDTO.getItemProcedureCodeMapId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemProcedureCodeMapId, itemProcedureCodeMapDTO.getItemProcedureCodeMapId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemProcedureCodeMapRepository.existsById(itemProcedureCodeMapId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemProcedureCodeMapDTO> result = itemProcedureCodeMapService.partialUpdate(itemProcedureCodeMapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                itemProcedureCodeMapDTO.getItemProcedureCodeMapId().toString()
            )
        );
    }

    /**
     * {@code GET  /item-procedure-code-maps} : get all the itemProcedureCodeMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemProcedureCodeMaps in body.
     */
    @GetMapping("/item-procedure-code-maps")
    public ResponseEntity<List<ItemProcedureCodeMapDTO>> getAllItemProcedureCodeMaps(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemProcedureCodeMaps");
        Page<ItemProcedureCodeMapDTO> page = itemProcedureCodeMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-procedure-code-maps/:id} : get the "id" itemProcedureCodeMap.
     *
     * @param id the id of the itemProcedureCodeMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemProcedureCodeMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-procedure-code-maps/{id}")
    public ResponseEntity<ItemProcedureCodeMapDTO> getItemProcedureCodeMap(@PathVariable Long id) {
        log.debug("REST request to get ItemProcedureCodeMap : {}", id);
        Optional<ItemProcedureCodeMapDTO> itemProcedureCodeMapDTO = itemProcedureCodeMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemProcedureCodeMapDTO);
    }

    /**
     * {@code DELETE  /item-procedure-code-maps/:id} : delete the "id" itemProcedureCodeMap.
     *
     * @param id the id of the itemProcedureCodeMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-procedure-code-maps/{id}")
    public ResponseEntity<Void> deleteItemProcedureCodeMap(@PathVariable Long id) {
        log.debug("REST request to delete ItemProcedureCodeMap : {}", id);
        itemProcedureCodeMapService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
