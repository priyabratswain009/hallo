package com.sunknowledge.dme.rcm.service.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParSODeatilsforEfaxDTO {
    String parIdNo;
    String patientFirstName;
    String patientLastName;
    String patientIdNumber;
    String soNo;
    Long soId;
}
