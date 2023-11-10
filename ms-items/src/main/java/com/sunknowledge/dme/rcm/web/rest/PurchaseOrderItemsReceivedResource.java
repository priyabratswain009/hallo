package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
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
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderItemsReceivedResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsReceivedResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrderItemsReceived";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderItemsReceivedService purchaseOrderItemsReceivedService;

    private final PurchaseOrderItemsReceivedRepository purchaseOrderItemsReceivedRepository;

    public PurchaseOrderItemsReceivedResource(
        PurchaseOrderItemsReceivedService purchaseOrderItemsReceivedService,
        PurchaseOrderItemsReceivedRepository purchaseOrderItemsReceivedRepository
    ) {
        this.purchaseOrderItemsReceivedService = purchaseOrderItemsReceivedService;
        this.purchaseOrderItemsReceivedRepository = purchaseOrderItemsReceivedRepository;
    }

    /**
     * {@code POST  /purchase-order-items-receiveds} : Create a new purchaseOrderItemsReceived.
     *
     * @param purchaseOrderItemsReceivedDTO the purchaseOrderItemsReceivedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderItemsReceivedDTO, or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceived has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-items-receiveds")
    public ResponseEntity<PurchaseOrderItemsReceivedDTO> createPurchaseOrderItemsReceived(
        @RequestBody PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderItemsReceived : {}", purchaseOrderItemsReceivedDTO);
        if (purchaseOrderItemsReceivedDTO.getPoItemReceivedId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderItemsReceived cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderItemsReceivedDTO result = purchaseOrderItemsReceivedService.save(purchaseOrderItemsReceivedDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-order-items-receiveds/" + result.getPoItemReceivedId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPoItemReceivedId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-items-receiveds/:poItemReceivedId} : Updates an existing purchaseOrderItemsReceived.
     *
     * @param poItemReceivedId the id of the purchaseOrderItemsReceivedDTO to save.
     * @param purchaseOrderItemsReceivedDTO the purchaseOrderItemsReceivedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsReceivedDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceivedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsReceivedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-items-receiveds/{poItemReceivedId}")
    public ResponseEntity<PurchaseOrderItemsReceivedDTO> updatePurchaseOrderItemsReceived(
        @PathVariable(value = "poItemReceivedId", required = false) final Long poItemReceivedId,
        @RequestBody PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderItemsReceived : {}, {}", poItemReceivedId, purchaseOrderItemsReceivedDTO);
        if (purchaseOrderItemsReceivedDTO.getPoItemReceivedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poItemReceivedId, purchaseOrderItemsReceivedDTO.getPoItemReceivedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsReceivedRepository.existsById(poItemReceivedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderItemsReceivedDTO result = purchaseOrderItemsReceivedService.update(purchaseOrderItemsReceivedDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    purchaseOrderItemsReceivedDTO.getPoItemReceivedId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-order-items-receiveds/:poItemReceivedId} : Partial updates given fields of an existing purchaseOrderItemsReceived, field will ignore if it is null
     *
     * @param poItemReceivedId the id of the purchaseOrderItemsReceivedDTO to save.
     * @param purchaseOrderItemsReceivedDTO the purchaseOrderItemsReceivedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderItemsReceivedDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderItemsReceivedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderItemsReceivedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderItemsReceivedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/purchase-order-items-receiveds/{poItemReceivedId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<PurchaseOrderItemsReceivedDTO> partialUpdatePurchaseOrderItemsReceived(
        @PathVariable(value = "poItemReceivedId", required = false) final Long poItemReceivedId,
        @RequestBody PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PurchaseOrderItemsReceived partially : {}, {}",
            poItemReceivedId,
            purchaseOrderItemsReceivedDTO
        );
        if (purchaseOrderItemsReceivedDTO.getPoItemReceivedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poItemReceivedId, purchaseOrderItemsReceivedDTO.getPoItemReceivedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderItemsReceivedRepository.existsById(poItemReceivedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderItemsReceivedDTO> result = purchaseOrderItemsReceivedService.partialUpdate(purchaseOrderItemsReceivedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                purchaseOrderItemsReceivedDTO.getPoItemReceivedId().toString()
            )
        );
    }

    /**
     * {@code GET  /purchase-order-items-receiveds} : get all the purchaseOrderItemsReceiveds.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderItemsReceiveds in body.
     */
    @GetMapping("/purchase-order-items-receiveds")
    public ResponseEntity<List<PurchaseOrderItemsReceivedDTO>> getAllPurchaseOrderItemsReceiveds(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PurchaseOrderItemsReceiveds");
        Page<PurchaseOrderItemsReceivedDTO> page = purchaseOrderItemsReceivedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-order-items-receiveds/:id} : get the "id" purchaseOrderItemsReceived.
     *
     * @param id the id of the purchaseOrderItemsReceivedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderItemsReceivedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-items-receiveds/{id}")
    public ResponseEntity<PurchaseOrderItemsReceivedDTO> getPurchaseOrderItemsReceived(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderItemsReceived : {}", id);
        Optional<PurchaseOrderItemsReceivedDTO> purchaseOrderItemsReceivedDTO = purchaseOrderItemsReceivedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderItemsReceivedDTO);
    }

    /**
     * {@code DELETE  /purchase-order-items-receiveds/:id} : delete the "id" purchaseOrderItemsReceived.
     *
     * @param id the id of the purchaseOrderItemsReceivedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-items-receiveds/{id}")
    public ResponseEntity<Void> deletePurchaseOrderItemsReceived(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderItemsReceived : {}", id);
        purchaseOrderItemsReceivedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
