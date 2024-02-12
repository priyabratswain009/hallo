package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaxCoverPDFInputDTO {
    private String attn;
    private String attnTo;
    private String attnPhone;
    private String attnfax;
    private String attnRe;
    private String from;
    private String to;
    private String phone;
    private String fax;
    private String date;
    private String noOfPages;
    private String npi;
    private String name;
    private String companyName;

}
