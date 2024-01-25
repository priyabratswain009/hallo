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
public class PosMasterExtendedDTO {
    private UUID posMasterUuid;

    @NotNull(message = "posCode should not be null.")
    @NotBlank(message = "posCode should not be blank.")
    private String posCode;

    @NotNull(message = "posName should not be null.")
    @NotBlank(message = "posName should not be blank.")
    private String posName;

    /*@NotNull(message = "status should not be null.")
    @NotBlank(message = "status should not be blank.")
    private String status;*/

}
