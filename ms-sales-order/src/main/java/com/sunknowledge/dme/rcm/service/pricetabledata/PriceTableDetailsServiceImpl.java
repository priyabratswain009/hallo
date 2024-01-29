package com.sunknowledge.dme.rcm.service.pricetabledata;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.sunknowledge.dme.rcm.dto.priceDetails.ModifiersOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.priceTable.PriceTableRequest;
import com.sunknowledge.dme.rcm.repository.pricetabledata.PriceDetailsMasterRepo;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PriceTableDetailsServiceImpl implements PriceTableDetailsService {
	@Autowired
	PriceDetailsMasterRepo priceDetailsMasterRepository;
	@Autowired
	SalesOrderMasterRepo salesOrderMasterReporitory;

	@Override
	public String getPriceTableDetails(String soId, String dos, String saleType, String itemId, int period) {

		PriceTableRequest objPriceTableRequest = new PriceTableRequest();
		SalesOrderMaster objSalesOrderMaster = new SalesOrderMaster();

		objPriceTableRequest.setDos(dos);
		objPriceTableRequest.setSaleType(saleType);
		objPriceTableRequest.setItemId(itemId);
		objPriceTableRequest.setPeriod(period);

		String output = "";

		List<PriceDetailsMaster> objList = new ArrayList<>();

		try {
			objSalesOrderMaster = salesOrderMasterReporitory.getPriceDetails(Long.valueOf(soId)).toFuture().get();
			if (objSalesOrderMaster == null) {
				output = "Data not found in Sales Order Master";
			} else {
				objPriceTableRequest.setPriceTableId(objSalesOrderMaster.getPrimaryInsurerPriceTableId());
				objPriceTableRequest.setPriceTableName(objSalesOrderMaster.getPrimaryInsurerPriceTableName());
				objList = priceDetailsMasterRepository
						.getPriceTableData(saleType, Long.valueOf(itemId),
								Long.valueOf(objSalesOrderMaster.getPrimaryInsurerPriceTableId()))
						.collectList().toFuture().get();
				if (objList.size() == 0) {
					output = "Data not found in Price Table Details";
				} else {
					PriceDetailsMaster objPriceDetailsMaster = getdata(objList, dos, period);
					if (objPriceDetailsMaster == null) {
						output = "Data not found in Price Table Details";
					} else {
						output = String.valueOf(objPriceDetailsMaster.getChargeAmt()) + "-"
								+ String.valueOf(objPriceDetailsMaster.getAllowedAmt());
					}
				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;

	}

	PriceDetailsMaster getdata(List<PriceDetailsMaster> objList, String dos, int period) {

		PriceDetailsMaster objPriceDetailsMaster = new PriceDetailsMaster();
		for (PriceDetailsMaster obj : objList) {
			LocalDate dateOfService = null;
			if (dos.contains("-")) {
				dateOfService = CommonUtilities.stringwithhyphentodateConverter(dos);
			} else {
				dateOfService = CommonUtilities.stringtodateConverter(dos);
			}
			if (!CommonUtilities.isStringNullOrBlank(String.valueOf(obj.getEffectiveEndDate()))
					&& (period >= obj.getPriceOptionBillingPeriodStart())
					&& obj.getPriceOptionBillingPeriodEnd() >= period
					&& (obj.getEffectiveStartDate().isBefore(dateOfService)
							|| obj.getEffectiveStartDate().isEqual(dateOfService))) {
				return obj;
			}
		}

		for (PriceDetailsMaster obj : objList) {
			LocalDate dateOfService = null;
			if (dos.contains("-")) {
				dateOfService = CommonUtilities.stringwithhyphentodateConverter(dos);
			} else {
				dateOfService = CommonUtilities.stringtodateConverter(dos);
			}
			if ((obj.getEffectiveStartDate().isBefore(dateOfService)
					|| obj.getEffectiveStartDate().isEqual(dateOfService))
					&& (obj.getEffectiveEndDate().isAfter(dateOfService)
							|| obj.getEffectiveEndDate().isEqual(dateOfService))
					&& (period >= obj.getPriceOptionBillingPeriodStart())
					&& obj.getPriceOptionBillingPeriodEnd() >= period) {
				return obj;
			}
		}

		return objPriceDetailsMaster;
	}

    @Override
    public Mono<ResponseDTO>getModifiersByHcpcsCodeAndItemNo(String hcpcsCode, String itemNo) throws ExecutionException, InterruptedException {
        ResponseDTO responseDTO = new ResponseDTO();

        if((hcpcsCode!=null && !hcpcsCode.equals("")) && (itemNo!=null && !itemNo.equals(""))){
            Flux<ModifiersOutputExtendedDTO> modifiersFlux =priceDetailsMasterRepository.getModifiersDataByHcpcsCodeAndItemNo(hcpcsCode, itemNo);

            return modifiersFlux.collectList().map(objList -> {
                if (!objList.isEmpty()) {
                    responseDTO.setOutcome(true);
                    responseDTO.setMessage("Successfully Fetched.");
                    responseDTO.setData(objList);
                } else {
                    responseDTO.setOutcome(false);
                    responseDTO.setMessage("Data Not Found.");
                    responseDTO.setData(null);
                }
                return responseDTO;
            });
        }else{
            responseDTO.setOutcome(false);
            responseDTO.setMessage("hcpcsCode and itemNo must be provided.");
            responseDTO.setData(null);
        }
        return Mono.just(responseDTO);
    }
}
