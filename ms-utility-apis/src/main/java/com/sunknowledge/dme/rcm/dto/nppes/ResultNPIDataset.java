package com.sunknowledge.dme.rcm.dto.nppes;

import java.util.List;
import lombok.Data;

@Data
public class ResultNPIDataset {
	private Integer resultCount;
	private String npiName;
	private String address;
	private String phone;
    private String npiNumber;
    private String enumerationType;
    private String createdEpoch;
    private String lastUpdatedEpoch;

	private Boolean status;
    private List<ErrorMessage> error;

    private Taxonomy primaryTaxonomy;
    private List<Taxonomy> taxonomies;
	private List<Address> addressList;
	private Basic basicDetails;
	private List<OtherName> otherNamesList;
	private List<Endpoint> endpoints;
	private List<PracticeLocation> practiceLocations;
}
