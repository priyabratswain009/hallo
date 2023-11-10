package com.sunknowledge.dme.rcm.web.rest.abn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.service.abn.AbnDataDeliveryService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/abn")
public class AbnCreationResource {

	@Autowired
	private AbnDataDeliveryService abnDataDeliveryService;

	@ApiOperation(value = "Create ABN Data")
	@PostMapping("/abnCreation")
	public Mono<DeliveryAbnData> abnCreation(@RequestParam("soid") Long soid,
			@RequestParam("item_proc") String item_proc, @RequestParam("abn_reason") String abn_reason,
			@RequestParam("abn_modifier_1") String abn_modifier_1,
			@RequestParam("abn_modifier_2") String abn_modifier_2, @RequestParam("uid") Long uid,
			@RequestParam("uname") String uname) throws Exception {
		return abnDataDeliveryService.abnCreation(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid,
				uname);
	}

	@ApiOperation(value = "Get ABN Data")
	@PostMapping("/getabnData")
	public ServiceOutcome<List<DeliveryAbnData>> getabnData(@RequestParam("soid") Long soid,
			@RequestParam("item_proc") String item_proc) throws Exception {
		return abnDataDeliveryService.getabnData(soid, item_proc);
	}

}
