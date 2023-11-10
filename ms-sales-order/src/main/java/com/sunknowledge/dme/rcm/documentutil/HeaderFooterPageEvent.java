package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
    String qrcodeFilePath;
    String header;
    PdfTemplate total;
    public HeaderFooterPageEvent(String qrcodeFilePath){
        super();
        this.qrcodeFilePath = qrcodeFilePath;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        new CommonPDFStubs().addQrCodeOnFooter(qrcodeFilePath, pdfWriter);
        new CommonPDFStubs().addPageNumber(pdfWriter);
    }
    public void onStartPage(PdfWriter writer, Document document) {
      Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
      Rectangle rect = writer.getBoxSize("cmn");
      ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("WRITTEN ORDER FORM", font), rect.getRight(), rect.getTop(), 0);
  }
}
