package com.sunknowledge.dme.rcm.service.impl.abn;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.sunknowledge.dme.rcm.service.dto.abn.CreatedAbnOutputExtendedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.abn.DeliveryAbnDataRepo;
import com.sunknowledge.dme.rcm.service.abn.AbnDataDeliveryService;

import reactor.core.publisher.Mono;

@Service
public class AbnDataDeliveryServiceImpl implements AbnDataDeliveryService {

	@Autowired
	DeliveryAbnDataRepo deliveryAbnDataRepository;

	@Override
	public Mono<DeliveryAbnData> abnCreation(Long soid, String item_proc, String abn_reason, String abn_modifier_1,
			String abn_modifier_2, Long uid, String uname) {
		// TODO Auto-generated method stub
		Mono<DeliveryAbnData> abndata = deliveryAbnDataRepository.abnCreation(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid,
				uname);

		return abndata;
	}

	@Override
	public ServiceOutcome<List<DeliveryAbnData>> getabnData(Long soid, String item_proc) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		ServiceOutcome<List<DeliveryAbnData>> outCome = new ServiceOutcome<List<DeliveryAbnData>>();

		List<DeliveryAbnData> list = deliveryAbnDataRepository.getAbnData(soid, item_proc, "active").collectList().toFuture().get();

		if(list.size()!=0) {
			outCome.setData(list);
			outCome.setMessage("Data Fetched Successfully");
			outCome.setOutcome(true);
		}else {
			outCome.setData(null);
			outCome.setMessage("Data not Found");
			outCome.setOutcome(false);
		}

		return outCome;
	}

    @Override
    public Mono<CreatedAbnOutputExtendedDTO> abnCreationUsingDbFunction(Long soid, String item_proc, String abn_reason, String abn_modifier_1,
                                             String abn_modifier_2, Long uid, String uname) {
        Mono<CreatedAbnOutputExtendedDTO> abndata = deliveryAbnDataRepository.abnCreationUsingDbFunction(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid,
            uname);

        return abndata;
    }

}
