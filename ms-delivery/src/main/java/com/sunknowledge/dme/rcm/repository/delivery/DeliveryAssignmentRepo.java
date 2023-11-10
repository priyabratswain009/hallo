package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryAssignment;
import com.sunknowledge.dme.rcm.repository.DeliveryAssignmentRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DeliveryAssignmentRepo extends DeliveryAssignmentRepository {
    @Query("FROM DeliveryAssignment WHERE deliveryTicketId = :deliveryTicketId")
    DeliveryAssignment getDeliveryAssignmentOnDeliveryTicket(@Param("deliveryTicketId") Long deliveryTicketId);

    @Query(value = "select * from t_delivery_assignment tda \n" +
        "where tda.assgnment_date between :startDate and :endDate and tda.assignment_status = 'initiated'\n" +
        "and tda.status = 'Active' and concat(tda.agent_first_name,' ', tda.agent_last_name) like %:searchedAgentName%", nativeQuery = true)
    List<DeliveryAssignment> getDeliveryAssignedListByAgentDate(@Param("searchedAgentName") String searchedAgentName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
