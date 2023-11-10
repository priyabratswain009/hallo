package com.sunknowledge.dme.rcm.commonutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;

public class CommonPdfTableStubs {
    public static PdfPTable createParagraphTitleTableBuilder(String title, int fontType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelTextDynamic(title, fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextDynamic(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5Style(PdfPCell cell) {
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

    public static PdfPTable createTextboxInTable(String text) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{1f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.addCell(createCellWithBodyTextbox(text));
        return table;
    }

    private static PdfPCell createCellWithBodyTextbox(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(1f); // Set border width for the cell
        cell.setPaddingTop(0);
        cell.setFixedHeight(40f);

        // Create a paragraph with a text box
        Paragraph textBoxParagraph = new Paragraph();
        textBoxParagraph.add(text); // Add your desired text here

        // Set the properties for the text box
        textBoxParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        textBoxParagraph.setLeading(0, 1);
        textBoxParagraph.setSpacingBefore(0f);

        // Change font size, font type, and font color
        Font font = new Font(Font.FontFamily.HELVETICA, 8); // Font type and size
        font.setColor(BaseColor.BLACK); // Font color
        textBoxParagraph.setFont(font);

        cell.addElement(textBoxParagraph);
        return cell;
    }
}
