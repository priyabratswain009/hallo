package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepreciationMethodExtendedDTO {
    private UUID depreciationMethodUuid;

    private String depreciationMethodName;

    private String status;
}
