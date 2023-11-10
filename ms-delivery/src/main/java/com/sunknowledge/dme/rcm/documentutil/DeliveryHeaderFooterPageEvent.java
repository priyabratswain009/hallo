package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import org.apache.commons.text.CaseUtils;

public class DeliveryHeaderFooterPageEvent extends PdfPageEventHelper {
    DeliveryTicket deliveryTicket;
    String qrcodeFilePath;
    String header;
    PdfTemplate total;
    public DeliveryHeaderFooterPageEvent(DeliveryTicket deliveryTicket, String qrcodeFilePath){
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
        new CommonPDFStubs().addQrCodeOnFooter(qrcodeFilePath, pdfWriter);
        new CommonPDFStubs().addPageNumber(pdfWriter);
    }

    public void addFooter(PdfWriter writer) {
        PdfPTable footer = new PdfPTable(2);
        try {
            footer.setWidthPercentage(100);
            footer.setWidths(new int[]{50, 50});
            footer.setTotalWidth(527);

            footer.addCell(createCellWithBodyLebelText("Name: "+ CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' ')));
            footer.addCell(createCellWithBodyPageText(writer));
            footer.addCell(createCellWithBodyLebelText("Order#: 783926"));
            footer.addCell(createCellWithBodyLebelText(""));

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);

            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    private static PdfPCell createCellWithAddImage(String imageFile) throws Exception
    {
        Image image = Image.getInstance(imageFile);
        image.setAlignment(Image.LEFT | Image.TEXTWRAP);
        PdfPCell cell = new PdfPCell(image, false);
        cell.setPadding(0);
        cell.setFixedHeight(80);
        cell.setBorder(0);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    private static void titleRowTextSmallSubTitle5Style(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyPageText(PdfWriter writer)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(String.format(writer.getPageNumber()+ " | Page"), font));
        titleRowTextSmallSubTitle5PageStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    private static void titleRowTextSmallSubTitle5PageStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }
}
