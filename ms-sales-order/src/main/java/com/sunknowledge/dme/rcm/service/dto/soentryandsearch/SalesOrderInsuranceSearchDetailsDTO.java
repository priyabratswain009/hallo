package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

public class SalesOrderInsuranceSearchDetailsDTO {
    private Long primaryInsurerId;
    private String primaryInsurerName;
    private String primaryInsuranceGroupName;
    private String primaryInsuranceStateName;
    private String responseMessage;

    private static SalesOrderInsuranceSearchDetailsDTO dto;

    public static SalesOrderInsuranceSearchDetailsDTO initialize() {
        dto = new SalesOrderInsuranceSearchDetailsDTO();
        return dto;
    }

    public SalesOrderInsuranceSearchDetailsDTO()
    {
        //-- Default constructor
    }

    public SalesOrderInsuranceSearchDetailsDTO(Long primaryInsurerId, String primaryInsurerName, String primaryInsuranceGroupName, String primaryInsuranceStateName, String responseMessage) {
        this.primaryInsurerId = primaryInsurerId;
        this.primaryInsurerName = primaryInsurerName;
        this.primaryInsuranceGroupName = primaryInsuranceGroupName;
        this.primaryInsuranceStateName = primaryInsuranceStateName;
        this.responseMessage = responseMessage;
    }

    public Long getPrimaryInsurerId() {
        return primaryInsurerId;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return primaryInsurerName;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsuranceGroupName() {
        return primaryInsuranceGroupName;
    }

    public void setPrimaryInsuranceGroupName(String primaryInsuranceGroupName) {
        this.primaryInsuranceGroupName = primaryInsuranceGroupName;
    }

    public String getPrimaryInsuranceStateName() {
        return primaryInsuranceStateName;
    }

    public void setPrimaryInsuranceStateName(String primaryInsuranceStateName) {
        this.primaryInsuranceStateName = primaryInsuranceStateName;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public SalesOrderInsuranceSearchDetailsDTO setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
        return dto;
    }

    @Override
    public String toString() {
        return "SalesOrderInsuranceSearchDetailsDTO{" +
            "primaryInsurerId=" + primaryInsurerId +
            ", primaryInsurerName='" + primaryInsurerName + '\'' +
            ", primaryInsuranceGroupName='" + primaryInsuranceGroupName + '\'' +
            ", primaryInsuranceStateName='" + primaryInsuranceStateName + '\'' +
            ", responseMessage='" + responseMessage + '\'' +
            '}';
    }
}
