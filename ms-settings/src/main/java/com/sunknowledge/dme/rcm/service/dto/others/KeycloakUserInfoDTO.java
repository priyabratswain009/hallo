package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserInfoDTO {
    private String id;
    private Long createdTimestamp;
    private String username;
    private Boolean enabled;
    private Boolean totp;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private Long notBefore;
    private AccessDTO access;
}
