package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DmeAuthReqForm1HighmarkHealthOptionsDTO;

import java.io.IOException;

public class DMEAuthorizationRequestFormTableBuilder {
    public static PdfPTable createDeliveryDocumentMainBodyTitle() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new float[]{1f});
        table.addCell(createTitleRowLargeText("Durable Medical Equipment (DME) Prior Authorization Request Form"));
        return table;
    }

    /*public static PdfPTable createDeliveryDocumentMainBodyTitle() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.addCell(createTitleRowLargeText("HIGHMARK\nHEALTH OPTIONS"));
        table.addCell(createTitleRowLargeText("Durable Medical Equipment (DME) Prior Authorization Request Form"));
        return table;
    }*/

    private static PdfPCell createTitleRowLargeText(String text) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLeftLargeStyle(cell);
        return cell;
    }

    private static PdfPCell createTitleRowLargeText1(String text) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLeftLargeStyle(cell);
        return cell;
    }

    private static void titleRowTextLeftLargeStyle1(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBorderColorBottom(BaseColor.WHITE);
    }

    private static void titleRowTextLeftLargeStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBorderColorBottom(BaseColor.WHITE);
    }

    public static PdfPTable createParagraphTitleTableBuilderInBody(String paragraphContent, String paragraphContent1) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.setSpacingAfter(4);
       // table.addCell(createCellWithBodyLebelText(title));
        table.addCell(createCellWithBodyLebelTextAsParagraph(paragraphContent, paragraphContent1));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextAsParagraph(String paragraphContent, String paragraphContent1) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font fontBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        // Create a Phrase with the Normal font
        Phrase phrase = new Phrase(paragraphContent, font);
        // Create a Phrase with the bold font
        Phrase phrase1 = new Phrase(paragraphContent1, fontBold);

        // Create a Paragraph and add the Phrases to it
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.add(phrase1);

        PdfPCell cell = new PdfPCell(paragraph);
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
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

    public static PdfPTable createParagraphTitleTableBuilderInBody1(String paragraphContent, String paragraphContent1) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.setSpacingAfter(5);
        // table.addCell(createCellWithBodyLebelText(title));
        table.addCell(createCellWithBodyLebelTextAsParagraph1(paragraphContent, paragraphContent1));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextAsParagraph1(String paragraphContent, String paragraphContent1) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fontBold = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        // Create a Phrase with the Normal font
        Phrase phrase = new Phrase(paragraphContent, font);
        // Create a Phrase with the bold font
        Phrase phrase1 = new Phrase(paragraphContent1, fontBold);

        // Create a Paragraph and add the Phrases to it
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.add(phrase1);

        PdfPCell cell = new PdfPCell(paragraph);
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        return cell;
    }

    public static PdfPTable createTableForDate(DmeAuthReqForm1HighmarkHealthOptionsDTO inputDTO, PdfWriter writer) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(16);
        table.setWidths(new float[]{ 0.23f, 0.37f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(10);
        table.addCell(createCellWithBodyLebelTextFontResize("Date: ", Element.ALIGN_LEFT, Font.NORMAL, 10));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(inputDTO.getDate(), 9));
        //table.addCell(createCellWithBodyInputBoxWithPaddingPriorAuth("", 12, writer));  // Interactive Input box code
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextFontResize(String text, int elementAlignement, int fontType, int fontSize) {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallDMEShape(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setMinimumHeight(20);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        return cell;
    }

    private static void titleRowTextSmallDMEShape(PdfPCell cell, int elementAlignement) {
        cell.setHorizontalAlignment(elementAlignement);
        cell.setPaddingTop(1f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(1f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyInputBoxWithPaddingDME(String inputBoxVal, int fontSize) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        //cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.WHITE);
        cell.setBorderColorLeft(BaseColor.WHITE);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        //cell.setBorderWidth(0.1f);
        return cell;
    }

    private static PdfPCell createCellWithBodyInputBoxWithPaddingPriorAuth(String inputBoxVal, int fontSize, PdfWriter writer) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);

        // Create a TextField
        TextField textField = new TextField(writer, new Rectangle(200, 750, 350, 850), "textFieldName");
        textField.setBorderColor(BaseColor.WHITE);
        textField.setBorderWidth(1);
        textField.setFontSize(14);
        textField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);

        PdfFormField field = textField.getTextField();

        PdfPCell cell = new PdfPCell();
        cell.setCellEvent(new FormFieldCellEvent(field));
        cell.setBorder(Rectangle.NO_BORDER);

        field.setFieldName("yourTextFieldName");
        writer.addAnnotation(field);
        //PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        //cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.WHITE);
        cell.setBorderColorLeft(BaseColor.WHITE);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        cell.setMinimumHeight(20);
        //cell.setBorderWidth(0.1f);
        return cell;
    }

    public static Element createParagraphTitleTableBuilder(String title, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelTextDynamic(title, fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextDynamic(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(20);
        return cell;
    }

    private static void tableCellStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidth(0.1f);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.WHITE);
        cell.setBorderWidthBottom(0);
    }

    public static Element createParagraphTitleTableBuilder1(int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.33f, 0.34f, 0.33f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic("Member Name", fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic("Member ID", fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic("Date of Birth", fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelSmallTextDynamic(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell);
        cell.setBorderColor(BaseColor.BLACK);
        return cell;
    }

    public static Element createParagraphTitleTableBuilderWithCol3(DmeAuthReqForm1HighmarkHealthOptionsDTO inputDTO, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.33f, 0.34f, 0.33f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic1(inputDTO.getMemberName(), fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic1(inputDTO.getMemberId(), fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic1(inputDTO.getDob(), fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelSmallTextDynamic1(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle1(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(18);
        return cell;
    }

    private static void tableCellStyle1(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidth(0.1f);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.WHITE);
        cell.setBorderWidthBottom(0);
    }

    public static Element createParagraphTitleTableBuilder3(String text1, String text2, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic(text1, fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic(text2, fontType));
        return table;
    }

    public static Element createParagraphTitleTableBuilderWithCol2(String value1, String value2, int fontType, int tableSpacing) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.5f, 0.5f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic2(value1, fontType));
        table.addCell(createCellWithBodyLebelSmallTextDynamic2(value2, fontType));
        table.setSpacingAfter(tableSpacing);
        return table;
    }

    private static PdfPCell createCellWithBodyLebelSmallTextDynamic2(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle2(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(18);
        return cell;
    }

    private static void tableCellStyle2(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidth(0.1f);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderWidthTop(0);
    }

    public static Element createParagraphTitleTableBuilder5(String text1, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic(text1, fontType));
        return table;
    }

    public static Element createParagraphTitleTableBuilderWithCol1(String value, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic6(value, fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelSmallTextDynamic6(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle2(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(18);
        return cell;
    }

    public static Element createParagraphTitleTableBuilderWithCol5(String title1, String title2, String title3, String title4, String title5, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.17f, 0.37f, 0.14f, 0.14f, 0.18f});
        table.addCell(createCellWithBodyLebelTextDynamic(title1, fontType));
        table.addCell(createCellWithBodyLebelTextDynamic(title2, fontType));
        table.addCell(createCellWithBodyLebelTextDynamic(title3, fontType));
        table.addCell(createCellWithBodyLebelTextDynamic(title4, fontType));
        table.addCell(createCellWithBodyLebelTextDynamic(title5, fontType));
        return table;
    }

    public static Element createParagraphTitleTableBuilderWithCol5Bottom(String title1, String title2, String title3, String title4, String title5, int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.17f, 0.37f, 0.14f, 0.14f, 0.18f});
        table.setSpacingAfter(15);
        table.addCell(createCellWithBodyLebelTextDynamicBottom(title1, fontType));
        table.addCell(createCellWithBodyLebelTextDynamicBottom(title2, fontType));
        table.addCell(createCellWithBodyLebelTextDynamicBottom(title3, fontType));
        table.addCell(createCellWithBodyLebelTextDynamicBottom(title4, fontType));
        table.addCell(createCellWithBodyLebelTextDynamicBottom(title5, fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextDynamicBottom(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyleBottom(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(17);
        return cell;
    }

    private static void tableCellStyleBottom(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidth(0.1f);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.BLACK);
        //cell.setBorderWidthBottom(0);
    }

    public static Element createCheckBoxInTableWithCol20(PdfFormField radioGroup, DmeAuthReqForm1HighmarkHealthOptionsDTO inputDTO, String elementType ,int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(20);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.073f, 0.02f, 0.03f, 0.02f, 0.02f, 0.063f, 0.02f, 0.03f, 0.02f, 0.02f, 0.083f, 0.02f, 0.03f, 0.02f, 0.02f, 0.073f, 0.02f, 0.03f, 0.02f, 0.02f});
        table.setSpacingAfter(10);
        //table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //Prepare Checkbox data
        boolean purchaseYes = false;
        boolean purchaseNo = false;
        boolean rentalYes = false;
        boolean rentalNo = false;
        boolean initialRequestYes = false;
        boolean initialRequestNo = false;
        boolean replacementYes = false;
        boolean replacementNo = false;
        if(inputDTO.getPurchaseYes())
            purchaseYes = true;

        if(inputDTO.getPurchaseNo())
            purchaseNo = true;

        if(inputDTO.getRentalYes())
            rentalYes = true;

        if(inputDTO.getRentalNo())
            rentalNo = true;

        if(inputDTO.getInitialRequestYes())
            initialRequestYes = true;

        if(inputDTO.getInitialRequestNo())
            initialRequestNo = true;

        if(inputDTO.getRentalYes())
            replacementYes = true;

        if(inputDTO.getRentalNo())
            replacementNo = true;

        table.addCell(createCellWithBodyLebelText(" Purchase?", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.BLACK, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0.1f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Yes", elementType, purchaseYes, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f)); //CheckBox

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f)); //Yes

        table.addCell(createCellWithBodyCheckbox(radioGroup, "No", elementType, purchaseNo, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f)); //CheckBox

        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0.1f, 0.1f, 0.1f)); //No

        table.addCell(createCellWithBodyLebelText(" Rental?", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.BLACK, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0.1f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Yes", elementType, rentalYes, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "No", elementType, rentalNo, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0.1f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText(" Initial request?", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Yes", elementType, initialRequestYes, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "No", elementType, initialRequestNo, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0.1f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText(" Replacement?", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.BLACK, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0.1f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Yes", elementType, replacementYes, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "No", elementType, replacementNo, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0.1f, 0.1f));

        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0.1f, 0.1f, 0.1f));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelText(String text, int elementAlignement, int fontType, BaseColor left, BaseColor right, BaseColor bottom, BaseColor top,
                                                        float leftWidth, float rightWidth, float topWidth, float bottomWidth) {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleForCheckBox(cell, elementAlignement, left, right, bottom, top, leftWidth, rightWidth, topWidth, bottomWidth);
        //cell.setBorder(PdfPCell.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5StyleForCheckBox(PdfPCell cell, int elementAlignement, BaseColor left, BaseColor right, BaseColor bottom, BaseColor top,
        float leftWidth, float rightWidth, float topWidth, float bottomWidth) {
        cell.setHorizontalAlignment(elementAlignement);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidthLeft(leftWidth);
        cell.setBorderWidthRight(rightWidth);
        cell.setBorderWidthTop(topWidth);
        cell.setBorderWidthBottom(bottomWidth);
        cell.setBorderColorLeft(left);
        cell.setBorderColorRight(right);
        cell.setBorderColorBottom(bottom);
        cell.setBorderColorTop(top);
    }

    private static PdfPCell createCellWithBodyCheckbox(PdfFormField radioGroup, String elementValue, String elementType, boolean isChecked, BaseColor left, BaseColor right,
                                                       BaseColor bottom, BaseColor top, float leftWidth, float rightWidth, float topWidth, float bottomWidth) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        String radioValue = elementValue;
        elementValue = "";
        PdfPCell cell = new PdfPCell(new Phrase(elementValue, font));
        tableRowCheckboxStyle(cell, radioGroup, radioValue, elementType, isChecked, left, right, bottom, top, leftWidth, rightWidth, topWidth, bottomWidth);
        //cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void tableRowCheckboxStyle(PdfPCell cell, PdfFormField radioGroup, String radioValue, String elementType, boolean isChecked, BaseColor left, BaseColor right,
                                              BaseColor bottom, BaseColor top, float leftWidth, float rightWidth, float topWidth, float bottomWidth) {
        cell.setCellEvent(new CellFieldEvent(radioGroup, radioValue, elementType, isChecked));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        //cell.setBorder(0);
        cell.setBorderWidthLeft(leftWidth);
        cell.setBorderWidthRight(rightWidth);
        cell.setBorderWidthTop(topWidth);
        cell.setBorderWidthBottom(bottomWidth);
        cell.setBorderColorLeft(left);
        cell.setBorderColorRight(right);
        cell.setBorderColorBottom(bottom);
        cell.setBorderColorTop(top);
        //cell.setMinimumHeight(50);
    }


    public static PdfPTable createParagraphDocumentFooter(String footerContent) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.setSpacingAfter(4);
        // table.addCell(createCellWithBodyLebelText(title));
        table.addCell(createCellWithBodyLebelTextAsFooterParagraph(footerContent));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextAsFooterParagraph(String paragraphContent) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        // Create a Phrase with the Normal font
        Phrase phrase = new Phrase(paragraphContent, font);

        // Create a Paragraph and add the Phrases to it
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);

        PdfPCell cell = new PdfPCell(paragraph);
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        return cell;
    }

    public static Element createParagraphTitleTableBuilder7(int fontType) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelSmallTextDynamic7("Demo Text", fontType));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelSmallTextDynamic7(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle2(cell);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(100);
        return cell;
    }

    public static PdfPTable createMultiLineTextBox(String text, int fontType, float fixedHeight, float minHeight, float topBorderWidth, float bottomBorderWidth) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.addCell(createCellWithBodyTextbox(text, fontType, fixedHeight, minHeight, topBorderWidth, bottomBorderWidth));
        return table;
    }

    private static PdfPCell createCellWithBodyTextbox(String text, int fontType, float fixedHeight, float minHeight, float topBorderWidth, float bottomBorderWidth) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        if(topBorderWidth !=0L){
            cell.setBorderWidthTop(0.1f);
        }else{
            cell.setBorderWidthTop(0f);
        }

        if(bottomBorderWidth !=0L){
            cell.setBorderWidthBottom(0.1f);
        }else{
            cell.setBorderWidthBottom(0f);
        }
        cell.setPaddingTop(0);
        if(fixedHeight!=0L){
            cell.setFixedHeight(fixedHeight);
        }
        cell.setMinimumHeight(minHeight);
        // Create a paragraph with a text box
        Paragraph textBoxParagraph = new Paragraph();
        textBoxParagraph.add(text); // Add your desired text here

        // Set the properties for the text box
        textBoxParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        textBoxParagraph.setLeading(0, 1);
        textBoxParagraph.setSpacingBefore(0f);

        // Change font size, font type, and font color
        Font font = new Font(Font.FontFamily.HELVETICA, 8, fontType); // Font type and size
        font.setColor(BaseColor.BLACK); // Font color
        textBoxParagraph.setFont(font);

        cell.addElement(textBoxParagraph);
        return cell;
    }
}
