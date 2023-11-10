package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    DeliveryTicketDTO save(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Updates a deliveryTicket.
     *
     * @param deliveryTicketDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryTicketDTO update(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Partially updates a deliveryTicket.
     *
     * @param deliveryTicketDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryTicketDTO> partialUpdate(DeliveryTicketDTO deliveryTicketDTO);

    /**
     * Get all the deliveryTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryTicketDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryTicket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryTicketDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryTicket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
