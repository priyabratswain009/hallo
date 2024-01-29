package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;

public class PriorAuthDocsHeaderFooter {
    DeliveryTicket deliveryTicket;
    String qrcodeFilePath;
    String header;
    PdfTemplate total;
    public PriorAuthDocsHeaderFooter(DeliveryTicket deliveryTicket, String qrcodeFilePath){
        super();
        this.deliveryTicket = deliveryTicket;
        this.qrcodeFilePath = qrcodeFilePath;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        //new CommonPDFStubs().addQrCodeOnFooter(qrcodeFilePath, pdfWriter);
        //new CommonPDFStubs().addPageNumber(pdfWriter);
        //Write Common Code for footer
    }
}
