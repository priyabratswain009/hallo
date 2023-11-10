package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import java.time.LocalDate;
import java.time.LocalDateTime;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class SalesOrderMasterSearchOutputDTO {
    private Long sid;
    private String sno;
    private String pId;
    private String pFirstName;
    private String pMiddleName;
    private String pLastName;
    private String pSsn;
    private String pGender;
    private LocalDate pDob;
    private String pInsurerName;
    private String pAddresLine1;
    private String pAddressLine2;
    private String pCity;
    private String pState;
    private String pPhone1;
    private String pPhone2;
    private String status;
    private String oStatus;
    private LocalDateTime cDate;
    private Long cById;
    private String cByName;
    private Long confirmationById;
    private String confirmationByName;
    private String pBranchName;
    private String fid;
    private String fname;
    private String fNpi;
    private LocalDate confirmationDate;
    private LocalDate deliveryScheduleDatetime;
    private LocalDate deliveryActualDatetime;
    private String responseMessage;

    private static SalesOrderMasterSearchOutputDTO dto;

    public static SalesOrderMasterSearchOutputDTO initialize() {
        dto = new SalesOrderMasterSearchOutputDTO();
        return dto;
    }

    public SalesOrderMasterSearchOutputDTO() {
    }

    public SalesOrderMasterSearchOutputDTO(final Long sid, final String sno, final String pId, final String pFirstName, final String pMiddleName, final String pLastName, final String pSsn, final String pGender, final LocalDate pDob, final String pInsurerName, final String pAddresLine1, final String pAddressLine2, final String pCity, final String pState, final String pPhone1, final String pPhone2, final String status, final String oStatus, final LocalDateTime cDate, final Long cById, final String cByName, final Long confirmationById, final String confirmationByName, final String pBranchName, final String fid, final String fname, final String fNpi, final LocalDate confirmationDate, final LocalDate deliveryScheduleDatetime, final LocalDate deliveryActualDatetime, final String responseMessage) {
        this.sid = sid;
        this.sno = sno;
        this.pId = pId;
        this.pFirstName = pFirstName;
        this.pMiddleName = pMiddleName;
        this.pLastName = pLastName;
        this.pSsn = pSsn;
        this.pGender = pGender;
        this.pDob = pDob;
        this.pInsurerName = pInsurerName;
        this.pAddresLine1 = pAddresLine1;
        this.pAddressLine2 = pAddressLine2;
        this.pCity = pCity;
        this.pState = pState;
        this.pPhone1 = pPhone1;
        this.pPhone2 = pPhone2;
        this.status = status;
        this.oStatus = oStatus;
        this.cDate = cDate;
        this.cById = cById;
        this.cByName = cByName;
        this.confirmationById = confirmationById;
        this.confirmationByName = confirmationByName;
        this.pBranchName = pBranchName;
        this.fid = fid;
        this.fname = fname;
        this.fNpi = fNpi;
        this.confirmationDate = confirmationDate;
        this.deliveryScheduleDatetime = deliveryScheduleDatetime;
        this.deliveryActualDatetime = deliveryActualDatetime;
        this.responseMessage = responseMessage;
    }

    public Long getSid() {
        return this.sid;
    }

    public void setSid(final Long sid) {
        this.sid = sid;
    }

    public String getSno() {
        return this.sno;
    }

    public void setSno(final String sno) {
        this.sno = sno;
    }

    public String getpId() {
        return this.pId;
    }

    public void setpId(final String pId) {
        this.pId = pId;
    }

    public String getpFirstName() {
        return this.pFirstName;
    }

    public void setpFirstName(final String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpMiddleName() {
        return this.pMiddleName;
    }

    public void setpMiddleName(final String pMiddleName) {
        this.pMiddleName = pMiddleName;
    }

    public String getpLastName() {
        return this.pLastName;
    }

    public void setpLastName(final String pLastName) {
        this.pLastName = pLastName;
    }

    public String getpSsn() {
        return this.pSsn;
    }

    public void setpSsn(final String pSsn) {
        this.pSsn = pSsn;
    }

    public String getpGender() {
        return this.pGender;
    }

    public void setpGender(final String pGender) {
        this.pGender = pGender;
    }

    public LocalDate getpDob() {
        return this.pDob;
    }

    public void setpDob(final LocalDate pDob) {
        this.pDob = pDob;
    }

    public String getpInsurerName() {
        return this.pInsurerName;
    }

    public void setpInsurerName(final String pInsurerName) {
        this.pInsurerName = pInsurerName;
    }

    public String getpAddresLine1() {
        return this.pAddresLine1;
    }

    public void setpAddresLine1(final String pAddresLine1) {
        this.pAddresLine1 = pAddresLine1;
    }

    public String getpAddressLine2() {
        return this.pAddressLine2;
    }

    public void setpAddressLine2(final String pAddressLine2) {
        this.pAddressLine2 = pAddressLine2;
    }

    public String getpCity() {
        return this.pCity;
    }

    public void setpCity(final String pCity) {
        this.pCity = pCity;
    }

    public String getpState() {
        return this.pState;
    }

    public void setpState(final String pState) {
        this.pState = pState;
    }

    public String getpPhone1() {
        return this.pPhone1;
    }

    public void setpPhone1(final String pPhone1) {
        this.pPhone1 = pPhone1;
    }

    public String getpPhone2() {
        return this.pPhone2;
    }

    public void setpPhone2(final String pPhone2) {
        this.pPhone2 = pPhone2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getoStatus() {
        return this.oStatus;
    }

    public void setoStatus(final String oStatus) {
        this.oStatus = oStatus;
    }

    public LocalDateTime getcDate() {
        return this.cDate;
    }

    public void setcDate(final LocalDateTime cDate) {
        this.cDate = cDate;
    }

    public Long getcById() {
        return this.cById;
    }

    public void setcById(final Long cById) {
        this.cById = cById;
    }

    public String getcByName() {
        return this.cByName;
    }

    public void setcByName(final String cByName) {
        this.cByName = cByName;
    }

    public Long getConfirmationById() {
        return this.confirmationById;
    }

    public void setConfirmationById(final Long confirmationById) {
        this.confirmationById = confirmationById;
    }

    public String getConfirmationByName() {
        return this.confirmationByName;
    }

    public void setConfirmationByName(final String confirmationByName) {
        this.confirmationByName = confirmationByName;
    }

    public String getpBranchName() {
        return this.pBranchName;
    }

    public void setpBranchName(final String pBranchName) {
        this.pBranchName = pBranchName;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(final String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(final String fname) {
        this.fname = fname;
    }

    public String getfNpi() {
        return this.fNpi;
    }

    public void setfNpi(final String fNpi) {
        this.fNpi = fNpi;
    }

    public LocalDate getConfirmationDate() {
        return this.confirmationDate;
    }

    public void setConfirmationDate(final LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDate getDeliveryScheduleDatetime() {
        return this.deliveryScheduleDatetime;
    }

    public void setDeliveryScheduleDatetime(final LocalDate deliveryScheduleDatetime) {
        this.deliveryScheduleDatetime = deliveryScheduleDatetime;
    }

    public LocalDate getDeliveryActualDatetime() {
        return this.deliveryActualDatetime;
    }

    public void setDeliveryActualDatetime(final LocalDate deliveryActualDatetime) {
        this.deliveryActualDatetime = deliveryActualDatetime;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public SalesOrderMasterSearchOutputDTO setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
        return dto;
    }

    @Override
    public String toString() {
        return "SalesOrderMasterSearchOutputDTO{" +
            "sid=" + sid +
            ", sno='" + sno + '\'' +
            ", pId='" + pId + '\'' +
            ", pFirstName='" + pFirstName + '\'' +
            ", pMiddleName='" + pMiddleName + '\'' +
            ", pLastName='" + pLastName + '\'' +
            ", pSsn='" + pSsn + '\'' +
            ", pGender='" + pGender + '\'' +
            ", pDob=" + pDob +
            ", pInsurerName='" + pInsurerName + '\'' +
            ", pAddresLine1='" + pAddresLine1 + '\'' +
            ", pAddressLine2='" + pAddressLine2 + '\'' +
            ", pCity='" + pCity + '\'' +
            ", pState='" + pState + '\'' +
            ", pPhone1='" + pPhone1 + '\'' +
            ", pPhone2='" + pPhone2 + '\'' +
            ", status='" + status + '\'' +
            ", oStatus='" + oStatus + '\'' +
            ", cDate=" + cDate +
            ", cById=" + cById +
            ", cByName='" + cByName + '\'' +
            ", confirmationById=" + confirmationById +
            ", confirmationByName='" + confirmationByName + '\'' +
            ", pBranchName='" + pBranchName + '\'' +
            ", fid='" + fid + '\'' +
            ", fname='" + fname + '\'' +
            ", fNpi='" + fNpi + '\'' +
            ", confirmationDate=" + confirmationDate +
            ", deliveryScheduleDatetime=" + deliveryScheduleDatetime +
            ", deliveryActualDatetime=" + deliveryActualDatetime +
            ", responseMessage='" + responseMessage + '\'' +
            '}';
    }

}
