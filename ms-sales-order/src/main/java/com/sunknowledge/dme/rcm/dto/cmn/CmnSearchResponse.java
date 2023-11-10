package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnSearchResponse {
    private String salesordernumber;
    private Long cmnid;
    private String cmnnumber;
    private String cmntype;
    private String cmnformname;
    private Long patientid;
    private Long salesorderid;
    private String salesorderno;
    private LocalDate cmncreatedate;
    private LocalDate cmninitialdate;
    private LocalDate cmnexpirationdate;
    private LocalDate cmnlogged_date;
    private String cmnapproved_by;
    private LocalDate cmnapproved_date;
    private String lengthofneed;
    private UUID cmniduuid;
    private String cmnstatus;
    private String initialdocumentname;
    private String returndocumentname;
    private String patientfirstname;
    private String patientmiddlename;
    private String patientlastname;
    private LocalDate deliveryscheduledatetime;
    private LocalDate deliveryactualdatetime;
    private String orderstatus;
    private String salesorderitems;
}
