package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.ItemGroupRepository;
import com.sunknowledge.dme.rcm.service.ItemGroupService;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.ItemGroup}.
 */
@RestController
@RequestMapping("/api")
public class ItemGroupResource {

    private final Logger log = LoggerFactory.getLogger(ItemGroupResource.class);

    private static final String ENTITY_NAME = "itemsItemGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemGroupService itemGroupService;

    private final ItemGroupRepository itemGroupRepository;

    public ItemGroupResource(ItemGroupService itemGroupService, ItemGroupRepository itemGroupRepository) {
        this.itemGroupService = itemGroupService;
        this.itemGroupRepository = itemGroupRepository;
    }

    /**
     * {@code POST  /item-groups} : Create a new itemGroup.
     *
     * @param itemGroupDTO the itemGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemGroupDTO, or with status {@code 400 (Bad Request)} if the itemGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-groups")
    public ResponseEntity<ItemGroupDTO> createItemGroup(@RequestBody ItemGroupDTO itemGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ItemGroup : {}", itemGroupDTO);
        if (itemGroupDTO.getItemGroupId() != null) {
            throw new BadRequestAlertException("A new itemGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemGroupDTO result = itemGroupService.save(itemGroupDTO);
        return ResponseEntity
            .created(new URI("/api/item-groups/" + result.getItemGroupId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getItemGroupId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-groups/:itemGroupId} : Updates an existing itemGroup.
     *
     * @param itemGroupId the id of the itemGroupDTO to save.
     * @param itemGroupDTO the itemGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemGroupDTO,
     * or with status {@code 400 (Bad Request)} if the itemGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-groups/{itemGroupId}")
    public ResponseEntity<ItemGroupDTO> updateItemGroup(
        @PathVariable(value = "itemGroupId", required = false) final Long itemGroupId,
        @RequestBody ItemGroupDTO itemGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ItemGroup : {}, {}", itemGroupId, itemGroupDTO);
        if (itemGroupDTO.getItemGroupId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemGroupId, itemGroupDTO.getItemGroupId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemGroupRepository.existsById(itemGroupId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItemGroupDTO result = itemGroupService.update(itemGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemGroupDTO.getItemGroupId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /item-groups/:itemGroupId} : Partial updates given fields of an existing itemGroup, field will ignore if it is null
     *
     * @param itemGroupId the id of the itemGroupDTO to save.
     * @param itemGroupDTO the itemGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemGroupDTO,
     * or with status {@code 400 (Bad Request)} if the itemGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itemGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itemGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/item-groups/{itemGroupId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItemGroupDTO> partialUpdateItemGroup(
        @PathVariable(value = "itemGroupId", required = false) final Long itemGroupId,
        @RequestBody ItemGroupDTO itemGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ItemGroup partially : {}, {}", itemGroupId, itemGroupDTO);
        if (itemGroupDTO.getItemGroupId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(itemGroupId, itemGroupDTO.getItemGroupId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itemGroupRepository.existsById(itemGroupId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItemGroupDTO> result = itemGroupService.partialUpdate(itemGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, itemGroupDTO.getItemGroupId().toString())
        );
    }

    /**
     * {@code GET  /item-groups} : get all the itemGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemGroups in body.
     */
    @GetMapping("/item-groups")
    public ResponseEntity<List<ItemGroupDTO>> getAllItemGroups(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ItemGroups");
        Page<ItemGroupDTO> page = itemGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-groups/:id} : get the "id" itemGroup.
     *
     * @param id the id of the itemGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-groups/{id}")
    public ResponseEntity<ItemGroupDTO> getItemGroup(@PathVariable Long id) {
        log.debug("REST request to get ItemGroup : {}", id);
        Optional<ItemGroupDTO> itemGroupDTO = itemGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemGroupDTO);
    }

    /**
     * {@code DELETE  /item-groups/:id} : delete the "id" itemGroup.
     *
     * @param id the id of the itemGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-groups/{id}")
    public ResponseEntity<Void> deleteItemGroup(@PathVariable Long id) {
        log.debug("REST request to delete ItemGroup : {}", id);
        itemGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
