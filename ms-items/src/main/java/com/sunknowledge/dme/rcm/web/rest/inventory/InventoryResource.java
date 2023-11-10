package com.sunknowledge.dme.rcm.web.rest.inventory;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusInputRequest;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusResponse;
import com.sunknowledge.dme.rcm.service.dto.inventory.StockAdjustmentRequestParams;
import com.sunknowledge.dme.rcm.service.inventory.InventoryService;
import com.sunknowledge.dme.rcm.validation.ValidationUtility;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bimal K Sahoo
 * @since 25/04/2023
 */
@RestController
@RequestMapping("/api/inventory")
@Validated
@Slf4j
public class InventoryResource {
    @Autowired
    private InventoryService inventoryService;

    @ApiOperation("Inventory Stock Adjustment")
    @PostMapping(value="/inventoryStockAdjustment")
    public ServiceOutcome<StockAdjustmentDTO> inventoryStockAdjustment(@RequestBody StockAdjustmentRequestParams stockAdjustmentRequestParams) {
        ServiceOutcome<StockAdjustmentDTO> outcome = null;
        try {
            outcome = ValidationUtility.validateStockAdjustmentRequestParams(stockAdjustmentRequestParams);
            if (outcome.getOutcome()){
                return inventoryService.inventoryStockAdjustment(stockAdjustmentRequestParams);
            }
            else{
                return outcome;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation("Get Item Inventory Status by Item and Location for Delivery Item")
    @PostMapping(value="/updateItemInventoryStatusByItemAndLocation")
    public ServiceOutcome<ItemInventoryStatusResponse> updateItemInventoryStatusByItemAndLocation(@RequestBody ItemInventoryStatusInputRequest itemInventoryStatusInputRequest) {
        log.info("=====================updateItemInventoryStatusByItemAndLocation Resource============================>");
        ServiceOutcome<ItemInventoryStatusResponse> outcome = new ServiceOutcome<>();
        try {
            if(itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().size() > 0) {
                outcome = inventoryService.updateItemInventoryStatusByItemAndLocation(itemInventoryStatusInputRequest);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }
}
