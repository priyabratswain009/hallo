package com.sunknowledge.dme.rcm.service.dto.delivery;

import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOutputResponse {
    private DeliveryTicketDTO deliveryTicketDTO;
    private List<DeliveryDocumentResponse> deliveryDocumentResponseList;
}
