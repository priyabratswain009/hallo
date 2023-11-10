package com.sunknowledge.dme.rcm.validation;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.delivery.CreateDeliveryTicketParams;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusResponse;

public class ValidationUtility {
    public static ServiceOutcome<ItemInventoryStatusResponse> validateCreateDeliveryTicketRequestParams(CreateDeliveryTicketParams createDeliveryTicketParams) {
        if(createDeliveryTicketParams.getSalesOrderId() == null){
            return new ServiceOutcome<>(null, false, "Salesorderid should not be empty!");
        }
        if(createDeliveryTicketParams.getDeliveryType() == null){
            return new ServiceOutcome<>(null, false, "Delivery type should not be empty!");
        }
        if(createDeliveryTicketParams.getSetupMethod() == null){
            return new ServiceOutcome<>(null, false, "Setup method should not be empty!");
        }
        if(createDeliveryTicketParams.getUserId() == null){
            return new ServiceOutcome<>(null, false, "User id should not be empty!");
        }
        if(createDeliveryTicketParams.getUserName() == null){
            return new ServiceOutcome<>(null, false, "User name should not be empty!");
        }
        return new ServiceOutcome<>(new ItemInventoryStatusResponse(), true, "Salesorderid is exists!");
    }
}
