package com.sunknowledge.dme.rcm.service.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PARsearchInitiatedOrActiveDTO {
    private Long par_id;
    private String par_id_no;
    private String par_status;
    private UUID par_uuid;
    private String log_in_status;
    private String item_no;
    private String hcpcs_code;
    private Long so_id;
}
