package com.sunknowledge.dme.rcm.service.claimservice;

import java.util.List;

import com.sunknowledge.dme.rcm.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;

public interface InvoiceService {

	ServiceOutcome<List<InvoiceLineItemDetailsDTO>> updateInvoicePostingDetails(String secondaryInvoiceNo, Long secondaryInvoiceId, String primaryInvoiceNo);
}
