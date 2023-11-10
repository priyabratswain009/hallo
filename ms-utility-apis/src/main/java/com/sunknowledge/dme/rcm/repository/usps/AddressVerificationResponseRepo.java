package com.sunknowledge.dme.rcm.repository.usps;

import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import com.sunknowledge.dme.rcm.repository.AddressVerificationResponseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressVerificationResponseRepo extends AddressVerificationResponseRepository {
    @Query("FROM AddressVerificationResponse WHERE patientId = :patientId")
    AddressVerificationResponse getAddressVerificationResponseOnPatient(@Param("patientId") Long patientId);
}
