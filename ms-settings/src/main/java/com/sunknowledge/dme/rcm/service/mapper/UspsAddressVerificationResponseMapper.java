package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UspsAddressVerificationResponse} and its DTO {@link UspsAddressVerificationResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface UspsAddressVerificationResponseMapper
    extends EntityMapper<UspsAddressVerificationResponseDTO, UspsAddressVerificationResponse> {}
