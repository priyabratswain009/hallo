package com.sunknowledge.dme.rcm.validation;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.StockAdjustmentRequestParams;

public class ValidationUtility {
    public static ServiceOutcome<StockAdjustmentDTO> validateStockAdjustmentRequestParams(StockAdjustmentRequestParams stockAdjustmentRequestParams) {
        ServiceOutcome<StockAdjustmentDTO> serviceOutcome = new ServiceOutcome<StockAdjustmentDTO>();
        if(stockAdjustmentRequestParams.getLocationId() == null){
            serviceOutcome.setData(null);
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Location should not be null!");
            return serviceOutcome;
        }
        else if(stockAdjustmentRequestParams.getAdjustmentType() == null || stockAdjustmentRequestParams.getAdjustmentType() == ""){
            serviceOutcome.setData(null);
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Adjustment type should not be blank!");
            return serviceOutcome;
        }
        else if(stockAdjustmentRequestParams.getAdjustmentType() == ""){
            serviceOutcome.setData(null);
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Item quantity should not be blank!");
            return serviceOutcome;
        }
        else if(stockAdjustmentRequestParams.getItemQty() < 0){
            serviceOutcome.setData(null);
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Item quantity should not be negative!");
            return serviceOutcome;
        }
        return serviceOutcome;
    }
}
