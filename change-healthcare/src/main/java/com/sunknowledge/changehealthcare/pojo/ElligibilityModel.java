package com.sunknowledge.changehealthcare.pojo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ElligibilityModel {

	private String relationToSubscriber;
	private String senderId;
	private String entityType;
	private String entityIdentifier;	
	private String name;	
	private String status;	
	private String payorIdentification;
	private String tradingPartnerServiceId;
	private String controlNumber;
	private String providerorganizationName;
	private String providernpi;
	private String subscribermemberId;
	private String subscriberfirstName;
	private String subscriberlastName;
	private String gender;
	private String subscriberdateOfBirth;
	private String subscriberssn;
	private String subscriberidCard;
	private String beginningDateOfService;
	private String endDateOfService;
	private ArrayList<String> serviceTypeCodes;

}
