package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDTO {
    private String emailId;
    private String userName;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
