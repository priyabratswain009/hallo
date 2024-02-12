package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.FaxCoverPDFInputDTO;

import java.io.IOException;

public class FaxCoverTableBuilder {
    public static Element createDeliveryDocumentMainBodyTitle(FaxCoverPDFInputDTO inputDTO) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("FAX", Font.BOLD, 0.05f, 0.05f,  0.05f, 0f, 0, 24));
        table.addCell(createCellWithBodyLebelTextDynamic("Attn: "+inputDTO.getAttn()+"\nTo: "+inputDTO.getAttnTo()+"\nPhone: "+inputDTO.getAttnPhone()+"\nFax: "+inputDTO.getAttnfax()+"\nRe: "+inputDTO.getAttnRe(), Font.NORMAL, 0.05f, 0.05f,  0.05f,
            0.05f, 0, 12));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextDynamic(String text, int fontType, float leftBorderWidth, float rightBorderWidth, float topBorderWidth,
                                                               float bottomBorderWidth, int minCellHeight, int fontSize) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, fontSize, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell, leftBorderWidth, rightBorderWidth, topBorderWidth, bottomBorderWidth);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(minCellHeight);
        return cell;
    }

    private static void tableCellStyle(PdfPCell cell, float leftBorderWidth, float rightBorderWidth, float topBorderWidth, float bottomBorderWidth) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(5f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidthLeft(leftBorderWidth);
        cell.setBorderWidthRight(rightBorderWidth);
        cell.setBorderWidthTop(topBorderWidth);
        cell.setBorderWidthBottom(bottomBorderWidth);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.BLACK);
    }

    public static Element createDeliveryDocumentMainBodyTitleSecondRow(FaxCoverPDFInputDTO inputDTO) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.BOLD, 0.05f, 0.05f,  0f, 0f, 0, 12)); //Hampton Home Care Inc609 Hampton Rd STE 2Southampton NY 119683096
        table.addCell(createCellWithBodyLebelTextDynamic("From: "+inputDTO.getFrom()+"\nPhone: "+inputDTO.getPhone()+"\nFax: "+inputDTO.getFax(), Font.NORMAL, 0.05f, 0.05f,  0.05f,
            0.05f, 0, 12));

        return table;
    }

    public static Element createDeliveryDocumentMainBodyTitleThirdRow(String date) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0.05f, 0.05f,  0f, 0f, 0, 12));
        table.addCell(createCellWithBodyLebelTextDynamic("Date: "+date, Font.NORMAL, 0.05f, 0.05f,  0.05f,
            0.05f, 0, 12));

        return table;
    }

    public static Element createDeliveryDocumentMainBodyTitleFourthRow(String noOfPages) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(5);
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0.05f, 0.05f,  0f, 0.05f, 0, 12));
        table.addCell(createCellWithBodyLebelTextDynamic("Pages (including cover): "+noOfPages, Font.NORMAL, 0.05f, 0.05f,  0.05f,
            0.05f, 0, 12));

        return table;
    }

    public static Element createFaxDocumentCommentsTitle() throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelTextDynamic("Comments:", Font.BOLD, 0.05f, 0.05f,  0.05f,
            0f, 15, 14));
        return table;
    }

    public static PdfPTable createFaxDocumentCommentsContent(String text1, String text2, String text3) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.addCell(createCellWithCommentsTextDynamic(text1, text2, text3,480, 12));
        return table;
    }

    private static PdfPCell createCellWithCommentsTextDynamic(String text1, String text2, String text3, int minCellHeight, int fontSize) {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);
        Font fontUnderlined = new Font(Font.FontFamily.HELVETICA, fontSize, Font.UNDERLINE, BaseColor.BLACK);
        // Create a Phrase with the Normal font
        Phrase phrase = new Phrase(text1, font);
        // Create a Phrase with the bold font
        Phrase phrase1 = new Phrase(text2, fontUnderlined);
        Phrase phrase2 = new Phrase(text3, font);

        // Create a Paragraph and add the Phrases to it
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.add(phrase1);
        paragraph.add(phrase2);

        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(minCellHeight);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

        cell.setPaddingTop(5f);
        cell.setPaddingLeft(5f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidthLeft(0.05f);
        cell.setBorderWidthRight(0.05f);
        cell.setBorderWidthTop(0.05f);
        cell.setBorderWidthBottom(0.05f);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.BLACK);

        return cell;
    }

    public static Element createDeliveryDocumentMainBodyTitleTest() throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("FAX", Font.BOLD, 0.05f, 0.05f,  0.05f, 0f, 0, 24));
        table.addCell(createCellWithBodyLebelTextDynamic("Attn: \nTo: \nPhone: \nFax: \nRe: ", Font.NORMAL, 0.05f, 0.05f,  0.05f,
            0.05f, 0, 12));

        return table;
    }

    public static Image addQrCodeOnFooterInAwsBucket(Document document, byte[] qrCodeBytes, Image qrCodeImage) throws BadElementException, IOException {
        qrCodeImage.setAlignment(Element.ALIGN_LEFT);
        qrCodeImage.scalePercent(100f, 100f); // Adjust the size as needed
        qrCodeImage.setAbsolutePosition(document.leftMargin(), document.bottomMargin() - 30); // Adjust coordinates as needed
        return qrCodeImage;
    }
}
