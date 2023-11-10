package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DMECertificationFormInput;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DeliveryItemDetails;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class DmeCertificationDocumentTableBuilder {

    public static void populateDataInDMEDocument(Document document, DMECertificationFormInput responseObject) throws DocumentException, IOException {
        document.add(createDeliveryDocumentMainBodyTitleDME(responseObject.getCompanyName()));
        document.add(createDeliveryDocumentMainFormTitleDME("DME Certification and Receipt Form"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitleDME("Certificación y Recibo de Equipo Medico Duradero (DME)"));
        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("This certification is required by section 32.024 of the Human Resources Code and must be completed before the DME provider can be paid for durable medical equipment provided to a Medicaid client.", 3, 8));
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("Esta certificación es necesaria bajo la Sección 32.024 del Código de Recursos Humanos y se debe Ilenar antes de poder rembolsar al proveedoe del equipo médico duradero por cualquier equipo médico proporcionado al cliente de Medicaid.", 0, 8));
        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitleDME("Section A: Client Information"));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextSpacedDME("Name: ", "RIGHT",1, responseObject.getName()));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextSpacedDME("Patient ID: ", "RIGHT", 1, responseObject.getPatientId()));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextSpacedDME("Street Address: ", "RIGHT", 1, responseObject.getStreetAddress()));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextSpacedDME("City: ", "RIGHT", 1, responseObject.getCity()));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextSpacedDME("Telephone Number: ", "RIGHT", 1, responseObject.getTelephoneNo()));

        /*document.add(HomePatientAssessmentTableBuilder.createTableForTextAndInputBoxSpacedDME("Name: ", "RIGHT",0, responseObject.getName()));
        document.add(HomePatientAssessmentTableBuilder.createTableForTextAndInputBoxSpacedDME("Patient ID: ", "RIGHT", 0, responseObject.getPatientId()));
        document.add(HomePatientAssessmentTableBuilder.createTableForTextAndInputBoxSpacedDME("Street Address: ", "RIGHT", 0, responseObject.getStreetAddress()));
        document.add(HomePatientAssessmentTableBuilder.createTableForTextAndInputBoxSpacedDME("City: ", "RIGHT", 0, responseObject.getCity()));
        document.add(HomePatientAssessmentTableBuilder.createTableForTextAndInputBoxSpacedDME("Telephone Number: ", "RIGHT", 1, responseObject.getTelephoneNo()));*/
        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitleDME("Section B: Provider Information"));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextAndInputBoxMultipleSpacedDME(responseObject));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextAndInputBoxMultipleSpacedDME1(responseObject));
        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitleDME("Section C: Product Information"));
        document.add(DmeCertificationDocumentTableBuilder.createTableForTextAndInputBoxSpacedDME("Date of Service: ", "LEFT", 0, responseObject.getDateOfService()));
        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentDynamicRowTableHeaderDME());
        List<DeliveryItemDetails> deliveryItemDetails = responseObject.getDeliveryItemDetailsList();  //Get Delivery Item Details
        deliveryItemDetails.sort(Comparator.comparing(DeliveryItemDetails::getProccode));
        for(int i=0;i<deliveryItemDetails.size();i++){
            document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentDynamicRowWithTableBodyDME(deliveryItemDetails.get(i)));
        }

        //document.add(new Paragraph("\n"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitleDME("Section D: Certification"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentParagraphWithInputBoxDME());
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("The equipment has been properly fitted to the client and/or meets the client's needs.", 2, 8));
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("The client, parent, or the guardian of the client, and/or the caregiver of the client has received training and instruction regarding the equipment's proper use and maintenance.", 3, 8));
        //document.add(new Paragraph("\n"));

        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentMainFormSubTitle1DME("Section D (Optional): Certification (Spanish)"));
        document.add(DmeCertificationDocumentTableBuilder.createDeliveryDocumentParagraphWithInputBoxDME1());
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("correctamente para el cliente o satisface las necesidades del client.", 2, 8));
        document.add(DmeCertificationDocumentTableBuilder.createParagraphTableBuilderInBodyDME("El cliente, padre, o tutor, o el cuidador principal del cliente ha recibido entrenamiento e instrucción con respecto al uso y mantenimiento apropiado del equipo.", 0, 8));
        // Move to the next page
        //document.newPage();
    }
    public static PdfPTable createDeliveryDocumentMainBodyTitleDME(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.addCell(createTitleRowLargeText(text));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainFormTitleDME(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.addCell(createCellWithBodyLebelText(text));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainFormSubTitleDME(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(1);
        table.addCell(createCellWithBodyLebelTextFontTen(text));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainFormSubTitle1DME(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(2);
        table.addCell(createCellWithBodyLebelTextFontTen(text));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextFontTen(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPTable createParagraphTableBuilderInBodyDME(String paragraph, int spacingAfter, int fontSize) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(97);
        table.setWidths(new float[]{ 1f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.setSpacingAfter(spacingAfter);
        table.addCell(createCellWithBodyLebelTextAsParagraphDME(paragraph, fontSize));
        return  table;
    }

    private static PdfPCell createCellWithBodyLebelTextAsParagraphDME(String paragraphContent, int fontSize)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(paragraphContent, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        return cell;
    }

    public static PdfPTable createTableForTextSpacedDME(String labelText, String textLabelAlignment , int spacingAfter, String inputBoxVal) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 1.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(spacingAfter);
        if(textLabelAlignment.equals("LEFT")){
            table.addCell(createCellWithBodyLebelTextFontResize(labelText, Element.ALIGN_LEFT, Font.NORMAL, 8));
        } else if (textLabelAlignment.equals("RIGHT")) {
            table.addCell(createCellWithBodyLebelTextFontResize(labelText, Element.ALIGN_RIGHT, Font.NORMAL, 8));
        }

        table.addCell(createCellWithBodyLebelTextFontResize(inputBoxVal, Element.ALIGN_LEFT, Font.NORMAL, 8));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextFontResize(String text, int elementAlignement, int fontType, int fontSize) {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallDMEShape(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
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

    public static PdfPTable createTableForTextAndInputBoxSpacedDME(String labelText, String textLabelAlignment , int spacingAfter, String inputBoxVal) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 1.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(spacingAfter);
        if(textLabelAlignment.equals("LEFT")){
            table.addCell(createCellWithBodyLebelTextFontResize(labelText, Element.ALIGN_LEFT, Font.NORMAL, 8));
        } else if (textLabelAlignment.equals("RIGHT")) {
            table.addCell(createCellWithBodyLebelTextFontResize(labelText, Element.ALIGN_RIGHT, Font.NORMAL, 8));
        }

        table.addCell(createCellWithBodyInputBoxWithPaddingDME(inputBoxVal, "WHITE", 10));

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTableHeaderDME() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.25f, 0.05f, 1f, 0.05f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxWithPaddingDME("Proc Code", "WHITE", 9));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "WHITE", 9));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("Description", "WHITE", 9));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "WHITE", 9));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("Serial #", "WHITE", 9));

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowWithTableBodyDME(DeliveryItemDetails deliveryItemDetails) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.25f, 0.05f, 1f, 0.05f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //for(int i=0;i<deliveryItemDetails.size();i++){
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(deliveryItemDetails.getProccode(), "BLACK", 9));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(deliveryItemDetails.getDescription(), "BLACK", 9));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(deliveryItemDetails.getSerial(), "BLACK", 9));
        //}

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowWithTableBodyDME1() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.25f, 0.05f, 1f, 0.05f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxWithPaddingDME("E0203", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("Walker, rigid, wheeled, adjustable or fixed height", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("22222", "BLACK", 10));

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowWithTableBodyDME2() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.25f, 0.05f, 1f, 0.05f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxWithPaddingDME("E0204", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("Walker, rigid, wheeled, adjustable or fixed height", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("33333", "BLACK", 10));

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowWithTableBodyDME3() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.25f, 0.05f, 1f, 0.05f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxWithPaddingDME("E0205", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 10));
        table.addCell(createCellWithBodyInputBoxSetTopBottomBorderDME(""));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 10));

        return table;
    }

    public static PdfPTable createDeliveryDocumentParagraphWithInputBoxDME1() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.34f, 0.11f, 0.19f, 0.2f, 0.54f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.setSpacingAfter(1);

        table.addCell(createCellWithBodyLebelTextFontResize("Esto certifica que el (mes/día/año):", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize(" el cliente recibió el", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize(" (equipo) qu el doctor recet. El equipo ha side adaptado", Element.ALIGN_JUSTIFIED, Font.NORMAL, 8));

        return table;
    }

    public static PdfPTable createDeliveryDocumentParagraphWithInputBoxDME() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.40f, 0.11f, 0.23f, 0.2f, 0.44f});
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        table.setSpacingAfter(1);

        table.addCell(createCellWithBodyLebelTextFontResize("This is to certify that on (month/day/year):", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize(" the client received the", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize(" (equipment) as prescribed by the physician.", Element.ALIGN_JUSTIFIED, Font.NORMAL, 8));

        return table;
    }

    private static PdfPCell createCellWithBodyInputBoxSetTopBottomBorderDME(String inputBoxVal) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));

        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.WHITE);

        cell.setBorderWidthTop(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidth(0f);

        return cell;
    }

    private static PdfPCell createCellWithBodyInputBoxWithPaddingDME(String inputBoxVal, String inputboxBorderColor, int fontSize) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setBorderWidth(0.1f);
        return cell;
    }

    public static PdfPTable createTableForTextAndInputBoxMultipleSpacedDME(DMECertificationFormInput responseObject) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{ 0.3f, 0.4f, 0.15f, 0.4f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyLebelTextFontResize("Provider Name: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(responseObject.getProviderName(), "BLACK", 8));

        table.addCell(createCellWithBodyLebelTextFontResize("  PAN: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(responseObject.getPan(), "BLACK", 8));

        return table;
    }

    public static PdfPTable createTableForTextAndInputBoxMultipleSpacedDME1(DMECertificationFormInput responseObject) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{ 0.3f, 0.4f, 0.15f, 0.4f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(2);

        table.addCell(createCellWithBodyLebelTextFontResize("NPI/API: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(responseObject.getNPIAPI(), "BLACK", 8));

        table.addCell(createCellWithBodyLebelTextFontResize("  TPI: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createCellWithBodyInputBoxWithPaddingDME(responseObject.getTpi(), "BLACK", 8));

        return table;
    }

    private static PdfPCell createTitleRowLargeText(String text) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLeftLargeStyle(cell);
        return cell;
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

    private static PdfPCell createCellWithBodyLebelText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
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
}
