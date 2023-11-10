package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalesOrderMasterSearchDetailsDTO {
    private Long sid;
    private String sno;
    private String pFirstName;
    private String pMiddleName;
    private String pLastName;
    private LocalDate pDob;
    private String pInsurerName;
    private String pAddresLine1;
    private String pAddressLine2;
    private String status;
    private String oStatus;
    private LocalDateTime cDate;
    private Long cById;
    private String cByName;
    private Long primaryInsurerId;
    private String pBranchName;
    private String responseMessage;

    public SalesOrderMasterSearchDetailsDTO()  //-- Default constructor
    {

    }

    public SalesOrderMasterSearchDetailsDTO(Long sid, String sno, String pFirstName, String pMiddleName, String pLastName, LocalDate pDob, String pInsurerName, String pAddresLine1, String pAddressLine2, String status, String oStatus, LocalDateTime cDate, Long cById, String cByName, Long primaryInsurerId, String pBranchName, String responseMessage) {
        this.sid = sid;
        this.sno = sno;
        this.pFirstName = pFirstName;
        this.pMiddleName = pMiddleName;
        this.pLastName = pLastName;
        this.pDob = pDob;
        this.pInsurerName = pInsurerName;
        this.pAddresLine1 = pAddresLine1;
        this.pAddressLine2 = pAddressLine2;
        this.status = status;
        this.oStatus = oStatus;
        this.cDate = cDate;
        this.cById = cById;
        this.cByName = cByName;
        this.primaryInsurerId = primaryInsurerId;
        this.pBranchName = pBranchName;
        this.responseMessage = responseMessage;
    }

    private static SalesOrderMasterSearchDetailsDTO dto;

    public static SalesOrderMasterSearchDetailsDTO initialize() {
        dto = new SalesOrderMasterSearchDetailsDTO();
        return dto;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpMiddleName() {
        return pMiddleName;
    }

    public void setpMiddleName(String pMiddleName) {
        this.pMiddleName = pMiddleName;
    }

    public String getpLastName() {
        return pLastName;
    }

    public void setpLastName(String pLastName) {
        this.pLastName = pLastName;
    }

    public LocalDate getpDob() {
        return pDob;
    }

    public void setpDob(LocalDate pDob) {
        this.pDob = pDob;
    }

    public String getpInsurerName() {
        return pInsurerName;
    }

    public void setpInsurerName(String pInsurerName) {
        this.pInsurerName = pInsurerName;
    }

    public String getpAddresLine1() {
        return pAddresLine1;
    }

    public void setpAddresLine1(String pAddresLine1) {
        this.pAddresLine1 = pAddresLine1;
    }

    public String getpAddressLine2() {
        return pAddressLine2;
    }

    public void setpAddressLine2(String pAddressLine2) {
        this.pAddressLine2 = pAddressLine2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public LocalDateTime getcDate() {
        return cDate;
    }

    public void setcDate(LocalDateTime cDate) {
        this.cDate = cDate;
    }

    public Long getcById() {
        return cById;
    }

    public void setcById(Long cById) {
        this.cById = cById;
    }

    public String getcByName() {
        return cByName;
    }

    public void setcByName(String cByName) {
        this.cByName = cByName;
    }

    public Long getPrimaryInsurerId() {
        return primaryInsurerId;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getpBranchName() {
        return pBranchName;
    }

    public void setpBranchName(String pBranchName) {
        this.pBranchName = pBranchName;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public SalesOrderMasterSearchDetailsDTO setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
        return dto;
    }

    @Override
    public String toString() {
        return "SalesOrderSearchDetailsDTO{" +
            "sid=" + sid +
            ", sno='" + sno + '\'' +
            ", pFirstName='" + pFirstName + '\'' +
            ", pMiddleName='" + pMiddleName + '\'' +
            ", pLastName='" + pLastName + '\'' +
            ", pDob=" + pDob +
            ", pInsurerName='" + pInsurerName + '\'' +
            ", pAddresLine1='" + pAddresLine1 + '\'' +
            ", pAddressLine2='" + pAddressLine2 + '\'' +
            ", status='" + status + '\'' +
            ", oStatus='" + oStatus + '\'' +
            ", cDate=" + cDate +
            ", cById=" + cById +
            ", cByName='" + cByName + '\'' +
            ", primaryInsurerId='" + primaryInsurerId + '\'' +
            ", pBranchName='" + pBranchName + '\'' +
            ", responseMessage='" + pBranchName + '\'' +
            '}';
    }
}
