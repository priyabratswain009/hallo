package com.sunknowledge.dme.rcm.dto.par;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EfaxResponseExtendedDTO extends EfaxResponse {
    private byte[] byteFormat;
    //private ResponseEntity<ByteArrayResource> byteArrayResponse;
}
