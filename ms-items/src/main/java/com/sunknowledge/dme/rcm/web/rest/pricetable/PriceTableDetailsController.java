package com.sunknowledge.dme.rcm.web.rest.pricetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sunknowledge.dme.rcm.service.pricetable.PriceTableDetailsService;

@RestController
@RequestMapping("/api")
public class PriceTableDetailsController {

	@Autowired
	private PriceTableDetailsService priceTableDetailsService;
	@Autowired
	RestTemplate restTemplate;

    @Autowired
    PriceTableDetailsController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

	@GetMapping("/getPricingDetails")
	public String getPricingDetails(@RequestParam("itemId") String item_id,
			@RequestParam("priceType") String price_Type, @RequestParam("period") String period) {

		String str = "";
		String url = "http://localhost:8080/services/settings/api/getPriceTableData?InsuranceId=1757";

		String response;
        response = restTemplate.exchange(url,HttpMethod.GET, null, String.class).getBody();
        System.out.println(response);

		str = priceTableDetailsService.getPricingDetails(item_id, price_Type, period);

		return str;
	}

}
