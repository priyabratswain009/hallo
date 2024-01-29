package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class DMEAuthorizationRequestFormHeaderFooterPageEvent extends PdfPageEventHelper {
    byte[] qrCodeBytes;
    String header;
    PdfTemplate total;
    public DMEAuthorizationRequestFormHeaderFooterPageEvent(byte[] qrCodeBytes){
        super();
        this.qrCodeBytes = qrCodeBytes;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        Image image;
        try{
            image = Image.getInstance(qrCodeBytes);
            image.setAlignment(Element.ALIGN_LEFT);
            image.setAbsolutePosition(30, 10);
            image.scalePercent(100f, 100f);
            //pdfWriter.getDirectContent().addImage(image, true);
            document.add(image);
        }
        catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        //new CommonPDFStubs().addPageNumber(pdfWriter);
    }
    public void onStartPage(PdfWriter writer, Document document) {
        Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        Rectangle rect = writer.getBoxSize("cmn");
        if(rect!= null)
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("WRITTEN ORDER FORM", font), rect.getRight(), rect.getTop(), 0);
    }
}
