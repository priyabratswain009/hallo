package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.UserInformationResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CreateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserPasswordDTO;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public class UserMasterResourceExtended {

    private final Logger log = LoggerFactory.getLogger(UserMasterResourceExtended.class);
    @Autowired
    UserMasterServiceExtended userMasterServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PatchMapping(value = "saveUserMaster", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveUserMaster(@RequestBody CreateUserMasterExtendedDTO userMasterParameterDTO) throws ParseException, JsonProcessingException {
        return userMasterServiceExtended.saveUserMaster(userMasterParameterDTO);
    }

    @PatchMapping(value = "updateUserMaster", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO updateUserMaster(@RequestBody UpdateUserMasterExtendedDTO userMasterParameterDTO) {
        return userMasterServiceExtended.updateUserMaster(userMasterParameterDTO);
    }

    @GetMapping(value = "getAllUserMasterData")
    public ResponseDTO getAllUserMasterData() {
        return userMasterServiceExtended.getAllUserMasterData();
    }

    @GetMapping("/createRSAKeys")
    public void createPrivatePublicKey() {
        userMasterServiceExtended.createPrivatePublicKey();
    }

    @PostMapping("/encryptPassword")
    public String encryptPassword(@RequestBody String plainString) {
        return userMasterServiceExtended.encryptPassword(plainString);
    }

    @PostMapping("/decryptPassword")
    public String decryptPassword(@RequestBody String encryptedString) {
        return userMasterServiceExtended.decryptPassword(encryptedString);
    }

    @PatchMapping(value = "updateUserPassword", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO updateUserPassword(@RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) throws IOException {
        return userMasterServiceExtended.updateUserPassword(updateUserPasswordDTO);
    }



    /**Temporary User Deactivation and deletion**/
    @PutMapping(value = "/activeOrDeactiveUserByUuid")
    public ResponseDTO activeOrDeactiveUserByUuid(@RequestParam("uuid") UUID uuid,
                                          @RequestParam(name = "isDeactive", defaultValue = "false") Boolean isDeactive){
        return userMasterServiceExtended.activeOrDeactiveUserByUuid(uuid,isDeactive);
    }

    /**Permanent User Delete**/
    @PutMapping(value = "/deleteUserMasterStatusByUuid")
    public ResponseDTO deleteUserMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                 @RequestParam("status") String status){
        return userMasterServiceExtended.deleteUserMasterStatusByUuid(uuid,status);
    }

    /**
     * This API will help to Validate a User with valid UserName and Password credential and Redirect to the Next Page.
     *
     * @param userInformationResponseDTO
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping(value = "validateLogin", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO validateLogin(@RequestBody UserInformationResponseDTO userInformationResponseDTO) throws IOException, ParseException {
        if (Objects.nonNull(userInformationResponseDTO) && userInformationResponseDTO.getUsername() != null && userInformationResponseDTO.getPassword() != null) {
            return userMasterServiceExtended.validateLogin(userInformationResponseDTO.getUsername(), userInformationResponseDTO.getPassword());
        } else {
            CommonUtilities commonUtilities = new CommonUtilities();
            Properties properties = commonUtilities.readPropertiesFile("/project-properties-files/url_config.properties");
            return new ResponseDTO(false, "Please Provide a Valid UserName with Password", null, Integer.parseInt(properties.getProperty("status_code_200")));
        }
    }
}
