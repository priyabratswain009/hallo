package com.sunknowledge.dme.rcm.dto.nppes;

import lombok.Data;

@Data
public class NPPESIndividualsInputCriteria {
	private String version;
	private String npiNumber;
	private String enumerationType;
	private String taxonomyDescription;
	private String firstName;
	private String firstNameAlias;
	private String lastName;
	private String organizationName;
	private String addressPurpose;
	private String city;
	private String state;
	private String postalCode;
	private String countryCode;
	private String limit;
	private String skip;
	private String pretty;
}
