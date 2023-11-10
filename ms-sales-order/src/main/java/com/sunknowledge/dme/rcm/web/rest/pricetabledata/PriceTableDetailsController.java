package com.sunknowledge.dme.rcm.web.rest.pricetabledata;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.pricetabledata.PriceTableDetailsService;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PriceTableDetailsController {

	@Autowired
	PriceTableDetailsService priceTableDetailsService;

	@GetMapping("/getPriceTableData")
	public String getPriceTableDetails(@RequestParam("salesOrderId") String soId,
			@RequestParam("saleType") String saleType, @RequestParam("dateOfService") String dos,
			@RequestParam("itemId") String itemId, @RequestParam("period") int period) {

		return priceTableDetailsService.getPriceTableDetails(soId, dos, saleType, itemId, period);
	}

    @ApiOperation(value = "Get Modifiers By Hcpcs Code And Item No")
    @GetMapping("/getModifiersByHcpcsCodeAndItemNo")
    public Mono<ResponseDTO> getModifiersByHcpcsCodeAndItemNo(
        @NotNull(message = "Hcpcs Code must be required.")
        @NotBlank(message = "Hcpcs Code must be required.")
        @RequestParam("hcpcsCode") String hcpcsCode,
        @NotNull(message = "item No must be required.")
        @NotBlank(message = "item No must be required.")
        @RequestParam("itemNo") String itemNo) throws Exception {

        return priceTableDetailsService.getModifiersByHcpcsCodeAndItemNo(hcpcsCode, itemNo);
    }
}
