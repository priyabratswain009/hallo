package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItems}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderItemsResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrderItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderItemsService purchaseOrderItemsService;

    private final PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    public PurchaseOrderItemsResource(
        PurchaseOrderItemsService purchaseOrderItemsService,
        PurchaseOrderItemsRepository purchaseOrderItemsRepository
    ) {
        this.purchaseOrderItemsService = purchaseOrderItemsService;
        this.purchaseOrderItemsRepository = purchaseOrderItemsRepository;
    }

    /**
     * {@code POST  /purchase-order-items} : Create a new purchaseOrderItems.
     *
     * @param purchaseOrderItemsDTO the purchaseOrderItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderItemsDTO, or with status {@code 400 (Bad Request)} if the purchaseOrderItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-items")
    public ResponseEntity<PurchaseOrderItemsDTO> createPurchaseOrderItems(@RequestBody PurchaseOrderItemsDTO purchaseOrderItemsDTO)
        throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderItems : {}", purchaseOrderItemsDTO);
        if (purchaseOrderItemsDTO.getPoItemsId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderItemsDTO result = purchaseOrderItemsService.save(purchaseOrderItemsDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-order-items/" + result.getPoItemsId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPoItemsId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-items/:poItemsId} : Updates an existing purchaseOrderItems.
     *
     * @param poItemsId the id of the purchaseOrderItemsDTO to save.
     * @param purchaseOrderItemsDTO the purchaseOrderItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-items/{poItemsId}")
    public ResponseEntity<PurchaseOrderItemsDTO> updatePurchaseOrderItems(
        @PathVariable(value = "poItemsId", required = false) final Long poItemsId,
        @RequestBody PurchaseOrderItemsDTO purchaseOrderItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderItems : {}, {}", poItemsId, purchaseOrderItemsDTO);
        if (purchaseOrderItemsDTO.getPoItemsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poItemsId, purchaseOrderItemsDTO.getPoItemsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsRepository.existsById(poItemsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderItemsDTO result = purchaseOrderItemsService.update(purchaseOrderItemsDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderItemsDTO.getPoItemsId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-order-items/:poItemsId} : Partial updates given fields of an existing purchaseOrderItems, field will ignore if it is null
     *
     * @param poItemsId the id of the purchaseOrderItemsDTO to save.
     * @param purchaseOrderItemsDTO the purchaseOrderItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderItemsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/purchase-order-items/{poItemsId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseOrderItemsDTO> partialUpdatePurchaseOrderItems(
        @PathVariable(value = "poItemsId", required = false) final Long poItemsId,
        @RequestBody PurchaseOrderItemsDTO purchaseOrderItemsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseOrderItems partially : {}, {}", poItemsId, purchaseOrderItemsDTO);
        if (purchaseOrderItemsDTO.getPoItemsId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poItemsId, purchaseOrderItemsDTO.getPoItemsId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsRepository.existsById(poItemsId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderItemsDTO> result = purchaseOrderItemsService.partialUpdate(purchaseOrderItemsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderItemsDTO.getPoItemsId().toString())
        );
    }

    /**
     * {@code GET  /purchase-order-items} : get all the purchaseOrderItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderItems in body.
     */
    @GetMapping("/purchase-order-items")
    public ResponseEntity<List<PurchaseOrderItemsDTO>> getAllPurchaseOrderItems(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PurchaseOrderItems");
        Page<PurchaseOrderItemsDTO> page = purchaseOrderItemsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-order-items/:id} : get the "id" purchaseOrderItems.
     *
     * @param id the id of the purchaseOrderItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-items/{id}")
    public ResponseEntity<PurchaseOrderItemsDTO> getPurchaseOrderItems(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderItems : {}", id);
        Optional<PurchaseOrderItemsDTO> purchaseOrderItemsDTO = purchaseOrderItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderItemsDTO);
    }

    /**
     * {@code DELETE  /purchase-order-items/:id} : delete the "id" purchaseOrderItems.
     *
     * @param id the id of the purchaseOrderItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-items/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItems(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderItems : {}", id);
        purchaseOrderItemsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
