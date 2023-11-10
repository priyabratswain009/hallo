package com.sunknowledge.dme.rcm.service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketingReferalTypeMasterExtendedDTO {

    private UUID marketingReferalTypeMasterUUID;

    @NotBlank(message = "Marketing Referral Type Code must not be Blank.")
    @NotNull(message = "Marketing Referral Type Code must not be Blank.")
    private String marketingReferralTypeCode;

    private String marketingReferalTypeDescription;

    private String status;
}
