package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryTicket}.
 */
public interface DeliveryTicketService {
    /**
     * Save a deliveryTicket.
     *
     * @param deliveryTicketDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DeliveryTicketDTO> save(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Updates a deliveryTicket.
     *
     * @param deliveryTicketDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DeliveryTicketDTO> update(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Partially updates a deliveryTicket.
     *
     * @param deliveryTicketDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DeliveryTicketDTO> partialUpdate(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Get all the deliveryTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DeliveryTicketDTO> findAll(Pageable pageable);

    /**
     * Returns the number of deliveryTickets available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" deliveryTicket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DeliveryTicketDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryTicket.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
