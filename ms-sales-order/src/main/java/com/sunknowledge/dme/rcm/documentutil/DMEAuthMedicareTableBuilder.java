package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DMEAuthorizationMedicareInputDTO;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DMEAuthMedicareTableBuilder {
    public static PdfPTable createDeliveryDocumentMainBodyTitle1(DMEAuthorizationMedicareInputDTO inputDTO) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(createTitleRowLargeText(inputDTO.getFormName()));
        return table;
    }

    private static PdfPCell createTitleRowLargeText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextRightLargeStyle(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static void titleRowTextRightLargeStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.55f);
    }

    public static PdfPTable createDeliveryDocumentMainBodyTitle(DMEAuthorizationMedicareInputDTO inputDTO) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{ 1f });
        table.addCell(createTitleRowSmallText(inputDTO.getCompanyName().toUpperCase()+" | "+inputDTO.getFormType()));
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        return table;
    }

    private static PdfPCell createTitleRowSmallText(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextRightSmallStyle(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static void titleRowTextRightSmallStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0f);
    }

    public static Element createFormDataTableBuilderWithCol13(PdfFormField radioGroup, DMEAuthorizationMedicareInputDTO inputDTO, String elementType) throws DocumentException {
        Boolean ltCheckBox = false;
        Boolean rtCheckBox = false;
        /*if(inputDTO.getLtValue())
            ltCheckBox = true;

        if(inputDTO.getRtValue())
            rtCheckBox = true;*/

        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.11f, 0.12f, 0.02f, 0.31f, 0.06f, 0.02f, 0.11f, 0.15f, 0.02f, 0.03f, 0.02f, 0.03f, 0.02f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("Request Date", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(inputDTO.getRequestDate(), Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("Number of Pages (including coversheet)",Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(inputDTO.getNumberOfPages(), Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("HCPCS Code", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(inputDTO.getHcpcsCode(), Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("LT", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        //table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "lt", elementType, ltCheckBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0f, 0.05f)));
        table.addCell(createCellWithBodyLebelTextDynamic("RT", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "rt", elementType, rtCheckBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0f, 0.05f)));
        //table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f));

        return table;
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
        cell.setPaddingBottom(7f);
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

    private static PdfPCell createCellWithBodyLebelTextDynamic(String text, int fontType, float leftBorderWidth, float rightBorderWidth, float topBorderWidth,
                                                               float bottomBorderWidth, int minCellHeight) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 8, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell, leftBorderWidth, rightBorderWidth, topBorderWidth, bottomBorderWidth);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(minCellHeight);
        return cell;
    }

    private static void tableCellStyle(PdfPCell cell, float leftBorderWidth, float rightBorderWidth, float topBorderWidth, float bottomBorderWidth) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(7f);
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

    public static Element createFormDataTableBuilderWithCol2(PdfFormField radioGroup, DMEAuthorizationMedicareInputDTO inputDTO, String elementType) throws DocumentException {

        Boolean reviewVoluntaryAccessoryCode = true;
        /*if(inputDTO.getReviewVoluntaryAccessoryCode())
            reviewVoluntaryAccessoryCode = true;*/

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(30);
        table.setWidths(new float[]{0.98f, 0.02f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("Review Voluntary Accessory Code(s)", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "reviewVoluntaryAccessoryCode", elementType, reviewVoluntaryAccessoryCode, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.WHITE,
            0f, 0f, 0f, 0.05f)));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol8(HashMap<String, String> mapAccessoryHcpcsCodes, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(15);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.18f, 0.02f, 0.1f, 0.02f, 0.1f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("Accessory HCPCS Code(s)", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode1"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode2"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode3"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode4"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode5"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode6"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode7"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol9(HashMap<String, String> mapAccessoryHcpcsCodes, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(15);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.18f, 0.02f, 0.1f, 0.02f, 0.1f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("", Font.BOLD, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode8"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode9"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode10"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode11"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode12"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode13"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode14"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol9Next(HashMap<String, String> mapAccessoryHcpcsCodes, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(15);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.18f, 0.02f, 0.1f, 0.02f, 0.1f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f, 0.02f, 0.09f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("", Font.BOLD, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode15"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode16"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode17"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode18"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode19"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode20"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(mapAccessoryHcpcsCodes.get("accessoryHcpcsCode21"), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodySubTitle(String text, int fontSize, BaseColor bottomColor, int FontType) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(createSubTitleText(text, fontSize, bottomColor, FontType));
        table.setSpacingAfter(5f);
        return table;
    }

    private static PdfPCell createSubTitleText(String text, int fontSize, BaseColor bottomColor, int FontType)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, FontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextRightLargeStyle(cell);
        cell.setBorderWidthLeft(0f);
        cell.setBorderWidthRight(0f);
        cell.setBorderWidthTop(0f);
        cell.setBorderWidthBottom(0.05f);
        cell.setBorderColorBottom(bottomColor);
        cell.setMinimumHeight(15);
        return cell;
    }

    public static Element createFormDataTableBuilderWithCol8Check(PdfFormField radioGroup, DMEAuthorizationMedicareInputDTO inputDTO, String elementType) throws DocumentException {

        Boolean initialCheckBox = false;
        Boolean resubmissionBox = false;
       // Boolean expeditedReviewBox = false;
        if(inputDTO.getInitialValue())
            initialCheckBox = true;

        if(inputDTO.getResubmissionValue())
            resubmissionBox = true;

        /*if(inputDTO.getExpeditedReview())
            expeditedReviewBox = true;*/

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(40);
        table.setWidths(new float[]{0.2f, 0.09f, 0.02f, 0.25f, 0.06f, 0.02f}); //, 0.29f, 0.04f, 0.02f
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(4);
        table.addCell(createCellWithBodyLebelTextDynamic("Initial", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "initial", elementType, initialCheckBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.WHITE,
            0f, 0f, 0f, 0.05f)));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 15));

        table.addCell(createCellWithBodyLebelTextDynamic("Resubmission", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "resubmission", elementType, resubmissionBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.WHITE,
            0f, 0f, 0f, 0.05f)));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 15));

        /*table.addCell(createCellWithBodyLebelTextDynamic("Expedited Review", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "expeditedReview", elementType, expeditedReviewBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.WHITE,
            0f, 0f, 0f, 0.05f)));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 15));*/
        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol5(DMEAuthorizationMedicareInputDTO inputDTO, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.15f, 0.38f, 0.02f, 0.15f, 0.3f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic1("Name", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1(inputDTO.getBeneficiaryInfoName(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1("Medicare ID", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1(inputDTO.getBeneficiaryInfoMedicareID(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextDynamic1(String text, int fontType, float leftBorderWidth, float rightBorderWidth, float topBorderWidth,
                                                               float bottomBorderWidth, int minCellHeight) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 9, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell, leftBorderWidth, rightBorderWidth, topBorderWidth, bottomBorderWidth);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(minCellHeight);
        return cell;
    }

    public static Element createInputBoxDataSmallerTableBuilderWithCol5(DMEAuthorizationMedicareInputDTO inputDTO, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(53);
        table.setWidths(new float[]{0.2f, 0.18f, 0.02f, 0.27f, 0.33f});
        table.setSpacingAfter(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelTextDynamic1("Date of Birth", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1(" "+inputDTO.getBeneficiaryInfoDOB(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1("State of Residence", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic1(" "+inputDTO.getBeneficiaryInfoStateOfResidence(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol8ForSI(DMEAuthorizationMedicareInputDTO inputDTO, int minCellHeight) throws DocumentException {
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 0.42f, 0.02f, 0.04f, 0.19f, 0.02f, 0.05f, 0.2f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("Name", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+inputDTO.getSupplierInfoName(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("NPI", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+inputDTO.getSupplierInfoNpi(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("PTAN", Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+inputDTO.getSupplierInfoPtan(), Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol5ForSI(String text1, String text2, int minCellHeight, float col1Width, float col2Width, float col3Width,
                                                                      float col4Width, String value1, String value2) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{col1Width, col2Width, 0.02f, col3Width, col4Width});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic(text1, Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+value1, Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(text2, Font.BOLD, 0f, 0f,  0f, 0.05f, minCellHeight));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+value2, Font.NORMAL, 0f, 0f,  0f, 0.05f, minCellHeight));

        return table;
    }

    public static Element createInputBoxDataTableBuilderWithCol2ForSI(String text1, String value) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(25);
        table.setWidths(new float[]{0.25f, 0.75f});
        table.setSpacingAfter(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelTextDynamic(text1, Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(value, Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));

        return table;
    }

    public static Element createFooterTitleTableBuilderWithCol1(String text) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(55f);
        table.setWidths(new float[]{1f});
        table.setSpacingAfter(3);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithFooterLebelTextDynamic(text, Font.BOLD, 0f, 0f,  0f, 0f, 0));

        return table;
    }

    private static PdfPCell createCellWithFooterLebelTextDynamic(String text, int fontType, float leftBorderWidth, float rightBorderWidth, float topBorderWidth,
                                                               float bottomBorderWidth, int minCellHeight) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 8, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableCellStyle(cell, leftBorderWidth, rightBorderWidth, topBorderWidth, bottomBorderWidth);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setMinimumHeight(minCellHeight);
        return cell;
    }

    public static Element createFooterTableBuilderWithCol2Fax(String text1, DMEAuthorizationMedicareInputDTO inputDTO) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(25f);
        table.setWidths(new float[]{0.23f, 0.77f});
        //table.setSpacingAfter(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelTextDynamic(text1, Font.BOLD, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+inputDTO.getFooterFax(), Font.NORMAL, 0f, 0f,  0f, 0f, 0));

        return table;
    }

    public static Element createFooterTableBuilderWithCol2Mail(String text1, DMEAuthorizationMedicareInputDTO inputDTO) throws DocumentException {
        LocalDate currentDate = LocalDate.now();
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        // Format the current date using the defined format
        String formattedDate = currentDate.format(formatter);
        int currentYear = Year.now().getValue();

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(55f);
        table.setWidths(new float[]{0.1f, 0.9f});
        table.setSpacingAfter(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelTextDynamic(text1, Font.BOLD, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic(" "+inputDTO.getFooterMail()+"\n "+inputDTO.getFooterMailAddr()+"\n "+inputDTO.getFooterMailCity()+" "+inputDTO.getFooterMailState()+" "+inputDTO.getFooterMailZip()+"\n\n Revised "+formattedDate+". © "+currentYear+" Copyright, "+inputDTO.getCompanyName()+".", Font.NORMAL, 0f, 0f,  0f, 0f, 0));

        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyTitleDemo() throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createTitleRowSmallText("Text | Text"));
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        return table;
    }

    public static Element createFormDataTableBuilderWithCol13Demo(PdfFormField radioGroup, String elementType) throws DocumentException {
        Boolean ltCheckBox = true;
        Boolean rtCheckBox = false;

        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.11f, 0.12f, 0.02f, 0.31f, 0.06f, 0.02f, 0.11f, 0.15f, 0.02f, 0.03f, 0.02f, 0.03f, 0.02f});
        table.setSpacingAfter(4);

        table.addCell(createCellWithBodyLebelTextDynamic("Request Date", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("Number of Pages (including coversheet)",Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("HCPCS Code", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0f, 0));
        table.addCell(createCellWithBodyLebelTextDynamic("LT", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        //table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "lt", elementType, ltCheckBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0f, 0.05f)));
        table.addCell(createCellWithBodyLebelTextDynamic("RT", Font.BOLD, 0f, 0f,  0f, 0.05f, 0));
        table.addCell((createCellWithBodyCheckbox(radioGroup, "rt", elementType, rtCheckBox, BaseColor.WHITE, BaseColor.WHITE, BaseColor.BLACK, BaseColor.BLACK,
            0f, 0f, 0f, 0.05f)));
        //table.addCell(createCellWithBodyLebelTextDynamic("", Font.NORMAL, 0f, 0f,  0f, 0.05f));

        return table;
    }
}
