package com.sunknowledge.dme.rcm.service.abn;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;

import com.sunknowledge.dme.rcm.service.dto.abn.CreatedAbnOutputExtendedDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface AbnDataDeliveryService {

	public Mono<DeliveryAbnData> abnCreation(Long soid, String item_proc, String abn_reason, String abn_modifier_1, String abn_modifier_2, Long uid,
			String uname);

	public ServiceOutcome<List<DeliveryAbnData>> getabnData(Long soid, String item_proc) throws InterruptedException, ExecutionException;

    public Mono<CreatedAbnOutputExtendedDTO> abnCreationUsingDbFunction(Long soid, String item_proc, String abn_reason, String abn_modifier_1, String abn_modifier_2, Long uid,
                                                                        String uname) throws ExecutionException, InterruptedException;
}
