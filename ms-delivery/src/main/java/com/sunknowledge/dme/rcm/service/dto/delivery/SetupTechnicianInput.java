package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetupTechnicianInput {
    private Long deliveryTicketId;
    private String setupMethod;
    private String setupTechnicianNo;
    private String setupTechnicianContactNo;
    private String setupTechnicianFirstName;
    private String setupTechnicianMiddleName;
    private String setupTechnicianLastName;
    private String setupDateTime;
    private String scheduleSetupDateTime;
    private String setupComments;
    private String setupStatus;
    private String courierPackageAcceptedBy;
    private String therapistFirstName;
    private String therapistMiddleName;
    private String therapistLastName;
    private String therapistLicenseNo;
    private String therapistNpi;
    private String therapistTaxonomyCode;
    private String scheduleTherapyDate;
    private String actualTherapyDate;
    private String therapyMode;
    private String therapyStatus;
    private String therapyNotes;
}
