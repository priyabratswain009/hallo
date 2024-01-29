package com.sunknowledge.dme.rcm.service.claimservice;

import java.util.ArrayList;
import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.repository.invoice.InvoiceLineItemDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsMapper;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceLineItemDetailsRepo invoiceLineItemDetailsRepository;
	@Autowired
	InvoiceLineItemDetailsMapper invoiceLineItemDetailsMapper;

	@Override
	public ServiceOutcome<List<InvoiceLineItemDetailsDTO>> updateInvoicePostingDetails(String secondaryInvoiceNo,
                                                                                       Long secondaryInvoiceId, String primaryInvoiceNo) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<InvoiceLineItemDetailsDTO>> outCome = new ServiceOutcome<List<InvoiceLineItemDetailsDTO>>();
		List<InvoiceLineItemDetailsDTO> dtoList = new ArrayList<>();
		List<InvoiceLineItemDetails> invoiceList = invoiceLineItemDetailsRepository.getPrimaryInvoiceLineItemDetailsOnPrimaryInvoiceNo(primaryInvoiceNo);

		for(InvoiceLineItemDetails invoiceLineItemDetails : invoiceList) {
			InvoiceLineItemDetailsDTO InvoicePostingDetailsDTO = new InvoiceLineItemDetailsDTO();
			invoiceLineItemDetails.setSecondaryInvoiceId(secondaryInvoiceId);
			invoiceLineItemDetails.setSecondaryInvoiceNo(secondaryInvoiceNo);

			invoiceLineItemDetailsRepository.save(invoiceLineItemDetails);
			InvoicePostingDetailsDTO = invoiceLineItemDetailsMapper.toDto(invoiceLineItemDetails);
			dtoList.add(InvoicePostingDetailsDTO);
		}

		outCome.setData(dtoList);
		outCome.setMessage("Data Updated Successfully");
		outCome.setOutcome(true);

		return null;
	}

}
