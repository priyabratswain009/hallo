package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<KeycloakUserCredentialDTO> credentials;
}
