package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import com.sunknowledge.dme.rcm.service.dto.AddressVerificationResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressVerificationResponse} and its DTO {@link AddressVerificationResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddressVerificationResponseMapper extends EntityMapper<AddressVerificationResponseDTO, AddressVerificationResponse> {}
