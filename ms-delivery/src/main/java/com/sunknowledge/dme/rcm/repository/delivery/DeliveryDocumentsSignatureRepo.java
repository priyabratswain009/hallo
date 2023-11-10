package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature;
import com.sunknowledge.dme.rcm.repository.DeliveryDocumentsSignatureRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryDocumentsSignatureRepo extends DeliveryDocumentsSignatureRepository {
    @Query("FROM DeliveryDocumentsSignature WHERE deliveryTicketId = :deliveryTicketId")
    DeliveryDocumentsSignature getDeliveryDocumentSignatureOnDeliveryTicketId(@Param("deliveryTicketId") Long deliveryTicketId);
}
