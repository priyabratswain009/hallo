package com.sunknowledge.dme.rcm.web.rest.pricetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.pricetable.PriceTableService;

@RestController
@RequestMapping("/api")
public class PriceTableController {

	@Autowired
	private PriceTableService priceTableService;

	@GetMapping("/getPriceTableData")
	public String getPriceTableData(@RequestParam("insuranceid") String insuranceid) {

		String output = priceTableService.getpriceTableData(insuranceid);

		return output;
	}

}
