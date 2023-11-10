package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemVendorMappingRepository;
import com.sunknowledge.dme.rcm.service.ItemVendorMappingService;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemVendorMapping}.
 */
@RestController
@RequestMapping("/api")
public class ItemVendorMappingResource {

    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingResource.class);

    private static final String ENTITY_NAME = "itemsItemVendorMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemVendorMappingService itemVendorMappingService;

    private final ItemVendorMappingRepository itemVendorMappingRepository;

    public ItemVendorMappingResource(
        ItemVendorMappingService itemVendorMappingService,
        ItemVendorMappingRepository itemVendorMappingRepository
    ) {
        this.itemVendorMappingService = itemVendorMappingService;
        this.itemVendorMappingRepository = itemVendorMappingRepository;
    }

    /**
     * {@code POST  /item-vendor-mappings} : Create a new itemVendorMapping.
     *
     * @param itemVendorMappingDTO the itemVendorMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemVendorMappingDTO, or with status {@code 400 (Bad Request)} if the itemVendorMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-vendor-mappings")
    public ResponseEntity<ItemVendorMappingDTO> createItemVendorMapping(@Valid @RequestBody ItemVendorMappingDTO itemVendorMappingDTO)
        throws URISyntaxException {
        log.debug("REST request to save ItemVendorMapping : {}", itemVendorMappingDTO);
        if (itemVendorMappingDTO.getItemVendorId() != null) {
            throw new BadRequestAlertException("A new itemVendorMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemVendorMappingDTO result = itemVendorMappingService.save(itemVendorMappingDTO);
        return ResponseEntity
            .created(new URI("/api/item-vendor-mappings/" + result.getItemVendorId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemVendorId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-vendor-mappings/:itemVendorId} : Updates an existing itemVendorMapping.
     *
     * @param itemVendorId the id of the itemVendorMappingDTO to save.
     * @param itemVendorMappingDTO the itemVendorMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemVendorMappingDTO,
     * or with status {@code 400 (Bad Request)} if the itemVendorMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemVendorMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-vendor-mappings/{itemVendorId}")
    public ResponseEntity<ItemVendorMappingDTO> updateItemVendorMapping(
        @PathVariable(value = "itemVendorId", required = false) final Long itemVendorId,
        @Valid @RequestBody ItemVendorMappingDTO itemVendorMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemVendorMapping : {}, {}", itemVendorId, itemVendorMappingDTO);
        if (itemVendorMappingDTO.getItemVendorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemVendorId, itemVendorMappingDTO.getItemVendorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemVendorMappingRepository.existsById(itemVendorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemVendorMappingDTO result = itemVendorMappingService.update(itemVendorMappingDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemVendorMappingDTO.getItemVendorId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /item-vendor-mappings/:itemVendorId} : Partial updates given fields of an existing itemVendorMapping, field will ignore if it is null
     *
     * @param itemVendorId the id of the itemVendorMappingDTO to save.
     * @param itemVendorMappingDTO the itemVendorMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemVendorMappingDTO,
     * or with status {@code 400 (Bad Request)} if the itemVendorMappingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemVendorMappingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemVendorMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-vendor-mappings/{itemVendorId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemVendorMappingDTO> partialUpdateItemVendorMapping(
        @PathVariable(value = "itemVendorId", required = false) final Long itemVendorId,
        @NotNull @RequestBody ItemVendorMappingDTO itemVendorMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemVendorMapping partially : {}, {}", itemVendorId, itemVendorMappingDTO);
        if (itemVendorMappingDTO.getItemVendorId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemVendorId, itemVendorMappingDTO.getItemVendorId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemVendorMappingRepository.existsById(itemVendorId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemVendorMappingDTO> result = itemVendorMappingService.partialUpdate(itemVendorMappingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemVendorMappingDTO.getItemVendorId().toString())
        );
    }

    /**
     * {@code GET  /item-vendor-mappings} : get all the itemVendorMappings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemVendorMappings in body.
     */
    @GetMapping("/item-vendor-mappings")
    public ResponseEntity<List<ItemVendorMappingDTO>> getAllItemVendorMappings(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ItemVendorMappings");
        Page<ItemVendorMappingDTO> page = itemVendorMappingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-vendor-mappings/:id} : get the "id" itemVendorMapping.
     *
     * @param id the id of the itemVendorMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemVendorMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-vendor-mappings/{id}")
    public ResponseEntity<ItemVendorMappingDTO> getItemVendorMapping(@PathVariable Long id) {
        log.debug("REST request to get ItemVendorMapping : {}", id);
        Optional<ItemVendorMappingDTO> itemVendorMappingDTO = itemVendorMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemVendorMappingDTO);
    }

    /**
     * {@code DELETE  /item-vendor-mappings/:id} : delete the "id" itemVendorMapping.
     *
     * @param id the id of the itemVendorMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-vendor-mappings/{id}")
    public ResponseEntity<Void> deleteItemVendorMapping(@PathVariable Long id) {
        log.debug("REST request to delete ItemVendorMapping : {}", id);
        itemVendorMappingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
