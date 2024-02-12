package com.sunknowledge.dme.rcm.service.dto.others;

import com.sunknowledge.dme.rcm.domain.UserMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMasterExtendedDTO extends UserMaster {
    private Long id;
    private String title;
}
