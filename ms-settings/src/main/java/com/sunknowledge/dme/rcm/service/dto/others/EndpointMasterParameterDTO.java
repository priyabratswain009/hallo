package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndpointMasterParameterDTO {
    @NotBlank(message = "EndpointName must not be Blank.")
    @NotNull(message = "EndpointName must not be Null.")
    private String endpointName;
    private String endpointGroup;
    private String endpointDesc;
    @NotBlank(message = "EndpointUrl must not be Blank.")
    @NotNull(message = "EndpointUrl must not be Null.")
    private String endpointUrl;
    //private String status;
    private UUID endpointMasterUuid;
}
