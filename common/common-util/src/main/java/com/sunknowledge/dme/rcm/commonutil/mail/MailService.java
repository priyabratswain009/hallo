package com.sunknowledge.dme.rcm.commonutil.mail;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;

public interface MailService {
    ServiceOutcome<String> sendPurchaseOrderDocumentToVendor(String email, String attachmentFilePath);
}
