package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications;
import com.sunknowledge.dme.rcm.repository.DeliveryPatientCommunicationsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryPatientCommunicationsRepo extends DeliveryPatientCommunicationsRepository {
    @Query("FROM DeliveryPatientCommunications WHERE deliveryTicketId = :deliveryTicketId AND reasonForCommunication = :reasonForCommunication")
    DeliveryPatientCommunications getDeliveryPatientCommunicationOnDeliveryAndCommunicationReason(@Param("deliveryTicketId") Long deliveryTicketId, @Param("reasonForCommunication") String reasonForCommunication);
}
