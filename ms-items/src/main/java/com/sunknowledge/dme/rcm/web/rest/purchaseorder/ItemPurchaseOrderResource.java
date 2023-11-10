package com.sunknowledge.dme.rcm.web.rest.purchaseorder;

import com.sunknowledge.dme.rcm.service.purchaseorder.ItemPurchaseOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Bimal K Sahoo
 * @since 17/03/2023
 */
@RestController
@RequestMapping("/api/purchaseorder")
@Slf4j
public class ItemPurchaseOrderResource {
    @Autowired
    private ItemPurchaseOrderService itemPurchaseOrderService;

    @ApiOperation(value = "Generate Purchase Order")
    @GetMapping(value="/generatePurchaseOrder", produces = MediaType.APPLICATION_PDF_VALUE)
    public String generatePurchaseOrder(@RequestParam("poNumber") String poNumber) {
        String result = "";
        try {
            System.out.println("=======================generatePurchaseOrder=========================");
            result = itemPurchaseOrderService.generatePurchaseOrderOnPoNumber(poNumber);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
