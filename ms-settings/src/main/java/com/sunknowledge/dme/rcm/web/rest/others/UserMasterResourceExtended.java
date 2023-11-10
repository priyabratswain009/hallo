package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxZoneParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UserMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api")
public class UserMasterResourceExtended {

    private final Logger log = LoggerFactory.getLogger(UserMasterResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    UserMasterServiceExtended userMasterServiceExtended;

    @PatchMapping(value = "saveUserMaster", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveUserMaster(@RequestBody UserMasterParameterDTO userMasterParameterDTO){
        return userMasterServiceExtended.saveUserMaster(userMasterParameterDTO);
    }
}
