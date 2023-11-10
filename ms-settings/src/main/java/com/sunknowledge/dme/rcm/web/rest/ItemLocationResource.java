package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemLocationRepository;
import com.sunknowledge.dme.rcm.service.ItemLocationService;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemLocation}.
 */
@RestController
@RequestMapping("/api")
public class ItemLocationResource {

    private final Logger log = LoggerFactory.getLogger(ItemLocationResource.class);

    private static final String ENTITY_NAME = "settingsItemLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemLocationService itemLocationService;

    private final ItemLocationRepository itemLocationRepository;

    public ItemLocationResource(ItemLocationService itemLocationService, ItemLocationRepository itemLocationRepository) {
        this.itemLocationService = itemLocationService;
        this.itemLocationRepository = itemLocationRepository;
    }

    /**
     * {@code POST  /item-locations} : Create a new itemLocation.
     *
     * @param itemLocationDTO the itemLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemLocationDTO, or with status {@code 400 (Bad Request)} if the itemLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-locations")
    public ResponseEntity<ItemLocationDTO> createItemLocation(@RequestBody ItemLocationDTO itemLocationDTO) throws URISyntaxException {
        log.debug("REST request to save ItemLocation : {}", itemLocationDTO);
        if (itemLocationDTO.getItemLocationId() != null) {
            throw new BadRequestAlertException("A new itemLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemLocationDTO result = itemLocationService.save(itemLocationDTO);
        return ResponseEntity
            .created(new URI("/api/item-locations/" + result.getItemLocationId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemLocationId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-locations/:itemLocationId} : Updates an existing itemLocation.
     *
     * @param itemLocationId the id of the itemLocationDTO to save.
     * @param itemLocationDTO the itemLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemLocationDTO,
     * or with status {@code 400 (Bad Request)} if the itemLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-locations/{itemLocationId}")
    public ResponseEntity<ItemLocationDTO> updateItemLocation(
        @PathVariable(value = "itemLocationId", required = false) final Long itemLocationId,
        @RequestBody ItemLocationDTO itemLocationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemLocation : {}, {}", itemLocationId, itemLocationDTO);
        if (itemLocationDTO.getItemLocationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemLocationId, itemLocationDTO.getItemLocationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemLocationRepository.existsById(itemLocationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemLocationDTO result = itemLocationService.update(itemLocationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemLocationDTO.getItemLocationId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-locations/:itemLocationId} : Partial updates given fields of an existing itemLocation, field will ignore if it is null
     *
     * @param itemLocationId the id of the itemLocationDTO to save.
     * @param itemLocationDTO the itemLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemLocationDTO,
     * or with status {@code 400 (Bad Request)} if the itemLocationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemLocationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-locations/{itemLocationId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemLocationDTO> partialUpdateItemLocation(
        @PathVariable(value = "itemLocationId", required = false) final Long itemLocationId,
        @RequestBody ItemLocationDTO itemLocationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemLocation partially : {}, {}", itemLocationId, itemLocationDTO);
        if (itemLocationDTO.getItemLocationId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemLocationId, itemLocationDTO.getItemLocationId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemLocationRepository.existsById(itemLocationId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemLocationDTO> result = itemLocationService.partialUpdate(itemLocationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemLocationDTO.getItemLocationId().toString())
        );
    }

    /**
     * {@code GET  /item-locations} : get all the itemLocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemLocations in body.
     */
    @GetMapping("/item-locations")
    public ResponseEntity<List<ItemLocationDTO>> getAllItemLocations(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ItemLocations");
        Page<ItemLocationDTO> page = itemLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-locations/:id} : get the "id" itemLocation.
     *
     * @param id the id of the itemLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-locations/{id}")
    public ResponseEntity<ItemLocationDTO> getItemLocation(@PathVariable Long id) {
        log.debug("REST request to get ItemLocation : {}", id);
        Optional<ItemLocationDTO> itemLocationDTO = itemLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemLocationDTO);
    }

    /**
     * {@code DELETE  /item-locations/:id} : delete the "id" itemLocation.
     *
     * @param id the id of the itemLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-locations/{id}")
    public ResponseEntity<Void> deleteItemLocation(@PathVariable Long id) {
        log.debug("REST request to delete ItemLocation : {}", id);
        itemLocationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
