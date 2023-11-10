package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryDocuments;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryDocumentsRepo extends DeliveryDocumentsRepository {
    @Query("FROM DeliveryDocuments WHERE deliveryTicketId = :deliveryTicketId AND hcpcsDoctypeId = :hcpcsDoctypeId")
    DeliveryDocuments getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(@Param("deliveryTicketId") Long deliveryTicketId, @Param("hcpcsDoctypeId") Long hcpcsDoctypeId);

    @Query("FROM DeliveryDocuments WHERE deliveryTicketId = :deliveryTicketId")
    List<DeliveryDocuments> getDeliveryDocumentOnDeliveryTicket(@Param("deliveryTicketId") Long deliveryTicketId);

    @Query(value = "select tdd.* from t_delivery_ticket tdt, t_delivery_documents tdd, t_hcpcs_doc_type thdt \n" +
        "where tdt.delivery_ticket_id = tdd.delivery_ticket_id and tdd.hcpcs_doctype_id = thdt.hcpcs_doctype_id \n" +
        "and thdt.hcpcs_code = '0' and tdt.delivery_status = 'Approved'\n" +
        "and tdt.delivery_ticket_id = :deliveryTicketId", nativeQuery = true)
    List<DeliveryDocuments> getDeliveryPODDocumentsOnDeliveryTicket(@Param("deliveryTicketId") Long deliveryTicketId);
}
