package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionalityEndpointMapUpdateParameterDTO {
    @NotNull(message = "Functionality_UUID must be provided.")
    UUID functionalityUUID;
    @NotNull(message = "Endpoint_UUID must be provided.")
    UUID endpointUUID;
    @NotNull(message = "Status must be 'Active'/'inActive'.")
    @NotBlank(message = "Status must be 'Active'/'inActive'.")
    String status;

}
