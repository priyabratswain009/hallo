package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemSerialNumberRepository;
import com.sunknowledge.dme.rcm.service.ItemSerialNumberService;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemSerialNumber}.
 */
@RestController
@RequestMapping("/api")
public class ItemSerialNumberResource {

    private final Logger log = LoggerFactory.getLogger(ItemSerialNumberResource.class);

    private static final String ENTITY_NAME = "itemsItemSerialNumber";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemSerialNumberService itemSerialNumberService;

    private final ItemSerialNumberRepository itemSerialNumberRepository;

    public ItemSerialNumberResource(
        ItemSerialNumberService itemSerialNumberService,
        ItemSerialNumberRepository itemSerialNumberRepository
    ) {
        this.itemSerialNumberService = itemSerialNumberService;
        this.itemSerialNumberRepository = itemSerialNumberRepository;
    }

    /**
     * {@code POST  /item-serial-numbers} : Create a new itemSerialNumber.
     *
     * @param itemSerialNumberDTO the itemSerialNumberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemSerialNumberDTO, or with status {@code 400 (Bad Request)} if the itemSerialNumber has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-serial-numbers")
    public ResponseEntity<ItemSerialNumberDTO> createItemSerialNumber(@RequestBody ItemSerialNumberDTO itemSerialNumberDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemSerialNumber : {}", itemSerialNumberDTO);
        if (itemSerialNumberDTO.getItemSerialNumberId() != null) {
            throw new BadRequestAlertException("A new itemSerialNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemSerialNumberDTO result = itemSerialNumberService.save(itemSerialNumberDTO);
        return ResponseEntity
            .created(new URI("/api/item-serial-numbers/" + result.getItemSerialNumberId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemSerialNumberId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-serial-numbers/:itemSerialNumberId} : Updates an existing itemSerialNumber.
     *
     * @param itemSerialNumberId the id of the itemSerialNumberDTO to save.
     * @param itemSerialNumberDTO the itemSerialNumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemSerialNumberDTO,
     * or with status {@code 400 (Bad Request)} if the itemSerialNumberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemSerialNumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-serial-numbers/{itemSerialNumberId}")
    public ResponseEntity<ItemSerialNumberDTO> updateItemSerialNumber(
        @PathVariable(value = "itemSerialNumberId", required = false) final Long itemSerialNumberId,
        @RequestBody ItemSerialNumberDTO itemSerialNumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemSerialNumber : {}, {}", itemSerialNumberId, itemSerialNumberDTO);
        if (itemSerialNumberDTO.getItemSerialNumberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemSerialNumberId, itemSerialNumberDTO.getItemSerialNumberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemSerialNumberRepository.existsById(itemSerialNumberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemSerialNumberDTO result = itemSerialNumberService.update(itemSerialNumberDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    itemSerialNumberDTO.getItemSerialNumberId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-serial-numbers/:itemSerialNumberId} : Partial updates given fields of an existing itemSerialNumber, field will ignore if it is null
     *
     * @param itemSerialNumberId the id of the itemSerialNumberDTO to save.
     * @param itemSerialNumberDTO the itemSerialNumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemSerialNumberDTO,
     * or with status {@code 400 (Bad Request)} if the itemSerialNumberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemSerialNumberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemSerialNumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-serial-numbers/{itemSerialNumberId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemSerialNumberDTO> partialUpdateItemSerialNumber(
        @PathVariable(value = "itemSerialNumberId", required = false) final Long itemSerialNumberId,
        @RequestBody ItemSerialNumberDTO itemSerialNumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemSerialNumber partially : {}, {}", itemSerialNumberId, itemSerialNumberDTO);
        if (itemSerialNumberDTO.getItemSerialNumberId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemSerialNumberId, itemSerialNumberDTO.getItemSerialNumberId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemSerialNumberRepository.existsById(itemSerialNumberId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemSerialNumberDTO> result = itemSerialNumberService.partialUpdate(itemSerialNumberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemSerialNumberDTO.getItemSerialNumberId().toString())
        );
    }

    /**
     * {@code GET  /item-serial-numbers} : get all the itemSerialNumbers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemSerialNumbers in body.
     */
    @GetMapping("/item-serial-numbers")
    public ResponseEntity<List<ItemSerialNumberDTO>> getAllItemSerialNumbers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemSerialNumbers");
        Page<ItemSerialNumberDTO> page = itemSerialNumberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-serial-numbers/:id} : get the "id" itemSerialNumber.
     *
     * @param id the id of the itemSerialNumberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemSerialNumberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-serial-numbers/{id}")
    public ResponseEntity<ItemSerialNumberDTO> getItemSerialNumber(@PathVariable Long id) {
        log.debug("REST request to get ItemSerialNumber : {}", id);
        Optional<ItemSerialNumberDTO> itemSerialNumberDTO = itemSerialNumberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemSerialNumberDTO);
    }

    /**
     * {@code DELETE  /item-serial-numbers/:id} : delete the "id" itemSerialNumber.
     *
     * @param id the id of the itemSerialNumberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-serial-numbers/{id}")
    public ResponseEntity<Void> deleteItemSerialNumber(@PathVariable Long id) {
        log.debug("REST request to delete ItemSerialNumber : {}", id);
        itemSerialNumberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
