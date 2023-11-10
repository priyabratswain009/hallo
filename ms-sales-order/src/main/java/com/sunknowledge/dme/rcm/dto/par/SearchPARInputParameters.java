package com.sunknowledge.dme.rcm.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPARInputParameters {
    private String sono;
    private Long soid;
    private Long insuranceid;
    private String insurancename;
    private String payeridno;
    private String payerlevel;
    private String policynumber;
    private LocalDate policystartdate;
    private LocalDate policyenddate;
    private String payercontactnumber;
    private String payercontactname;
    private LocalDate effectivestartdate;
    private String itemno;
    private Long itemid;
    private String itemuom;
    private Long itemquantity;
    private String description;
    private String itemname;
    private String hcpcsno;
    private String patientidno;
    private Long patientid;
    private String patientfirstname;
    private String patientlastname;
    private LocalDate patientdob;
    private String patientgender;
    private LocalDate dos;
    private Long createdbyid;
    private String createdbyname;
    private String useexistingpar;
    private Long pricetableid;
}
