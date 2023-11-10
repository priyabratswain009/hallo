package com.sunknowledge.dme.rcm.web.rest.claimssubmissiondata;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.elligibility.ResultElligibilityOutcome;
import com.sunknowledge.dme.rcm.domain.elligibility.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ElligibilityServiceExtended;

import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/api")
public class ClaimsElligibilityResourceExtended {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	@Autowired
	private ElligibilityServiceExtended elligibilityServiceExtended;

	@PostMapping("/checkMemberElligibility")
	public ServiceOutcome<ResultElligibilityOutcome> getClaimsSubmissionData(
			@RequestParam("salesOrderId") String salesOrderId) {
		return elligibilityServiceExtended.medicalEligibiltycheck(salesOrderId);
	}

	@PostMapping("/InsertMemberPrimaryElligibilityData")
	public ServiceOutcome<MemberElligibilityMaster> insertMemberPrimaryElligibilityData(
			@RequestParam("salesOrderId") Long salesOrderId) throws InterruptedException, ExecutionException {
		return elligibilityServiceExtended.insertMemberPrimaryElligibilityData(salesOrderId);
	}

	@PostMapping("/InsertMemberSecondaryElligibilityData")
	public ServiceOutcome<MemberElligibilityMaster> insertMemberSecondaryElligibilityData(
			@RequestParam("salesOrderId") Long salesOrderId) throws InterruptedException, ExecutionException {
		return elligibilityServiceExtended.insertMemberSecondaryElligibilityData(salesOrderId);
	}

	@PostMapping("/validateClaimSubmissionData")
	public Mono<Boolean> validateClaimSubmissionData(@RequestParam("salesOrderId") Long salesOrderId)
			throws InterruptedException, ExecutionException {

		Boolean value = elligibilityServiceExtended.validateClaimSubmissionData(salesOrderId).toFuture().get();

		System.out.println("OUTSIDE IF");

		if (value) {

			System.out.println("INSIDE IF");

			SalesOrderMaster salesOrderMaster = elligibilityServiceExtended.updateOrderStatus(salesOrderId).toFuture()
					.get();

			System.out.println(salesOrderMaster.getSalesOrderId());

			salesOrderMaster.setOrderStatus("Confirmed");

			return elligibilityServiceExtended.saveSalesOrder(salesOrderMaster).map(x -> {
				return value;
			});
		} else {
			return Mono.just(value);
		}
	}

	@PostMapping("/createDuplicateSalesOrder")
	public Mono<SalesOrderMaster> createDuplicateSalesOrder(@RequestParam("salesOrderId") Long salesOrderId)
			throws InterruptedException, ExecutionException {
		return elligibilityServiceExtended.createDuplicateSalesOrder(salesOrderId);
	}

	@PostMapping("/rentalHelperPeriodGreaterThanOne")
	public Mono<Boolean> rentalHelperPeriodGreaterThanOne(@RequestParam("salesOrderId") Long salesOrderId,
			@RequestParam("periodNo") String periodNo, @RequestParam("previousSoId") Long previousSoId)
			throws InterruptedException, ExecutionException {
    	
    	Boolean value = elligibilityServiceExtended.rentalHelperPeriodGreaterThanOne(salesOrderId,periodNo,previousSoId).toFuture().get();
    	
    	System.out.println("OUTSIDE IF");
    	
    	if(value) {
        	
        	System.out.println("INSIDE IF");
        	
    		SalesOrderMaster salesOrderMaster = elligibilityServiceExtended.updateOrderStatus(salesOrderId).toFuture().get();
        	
        	System.out.println(salesOrderMaster.getSalesOrderId());
        	
    		salesOrderMaster.setOrderStatus("Confirmed");
    		
    		return elligibilityServiceExtended.saveSalesOrder(salesOrderMaster).map(x->{
    			return value;
    		});
    	}else {
            return Mono.just(value);
    	}
    }

}
