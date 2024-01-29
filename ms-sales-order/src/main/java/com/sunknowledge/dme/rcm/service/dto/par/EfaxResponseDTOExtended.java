package com.sunknowledge.dme.rcm.service.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EfaxResponseDTOExtended {

    private Long efaxResponseId;

    private LocalDate efaxReceivedDate;

    private String senderEmail;

    private String emailSubjectLine;

    private String attachmentName;

    private Boolean isQrDecoded;

    private String qrValue;

    private Boolean isCmn;

    private Boolean isPar;

    private String patientIdNo;

    private String patientFirstName;

    private String patientLastName;

    private String cmnIdNo;

    private String parIdNo;

    private Long soId;

    private String soNo;

    private String isManuallyTagged;

    private UUID efaxResponseUuid;
}
