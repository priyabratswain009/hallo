package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.pojo.PatientHomeAssessmentFormInputs;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DMECertificationFormInput;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DeliveryItemDetails;
import com.sunknowledge.dme.rcm.pojo.patientassessment.AlternateContacts;
import com.sunknowledge.dme.rcm.pojo.patientassessment.PatientAssessmentFormInput;

import javax.swing.plaf.TreeUI;
import java.io.IOException;

public class HomePatientAssessmentTableBuilder {

    public static PdfPTable createDeliveryDocumentPatientMainBodyTitle(PatientAssessmentFormInput responseObject) throws DocumentException, IOException{
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.addCell(createTitleRowLargeText(responseObject.getCompanyName()));
        table.addCell(createCellWithBodyLebelText(responseObject.getStreetAddress()));
        table.addCell(createCellWithBodyLebelText(responseObject.getCityStateZip()));
        return table;
    }

    public static PdfPTable createDeliveryDocumentPatientMainBodyContactTitle(PatientAssessmentFormInput responseObject) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.25f, 1.75f});
        table.addCell(createCellWithBodyLebelText("Phone: "));
        table.addCell(createCellWithBodyLebelText(responseObject.getPhoneNo()));
        table.addCell(createCellWithBodyLebelText("Fax: "));
        table.addCell(createCellWithBodyLebelText(responseObject.getFax()));
        return table;
    }

    public static PdfPTable createDeliveryDocumentPatientBodyTableHeader(PatientAssessmentFormInput responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 0.8f, 0.3f, 0.6f });

        table.addCell(createCellWithBodyLabelNormalText("Patient:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPatientName()));
        table.addCell(createCellWithBodyLabelNormalText("Patient ID:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPatientId()));

        table.addCell(createCellWithBodyLabelNormalText("Address:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getAddress()));
        table.addCell(createCellWithBodyLabelNormalText("Account #:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getAccount()));

        table.addCell(createCellWithBodyLabelNormalText("Phone:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPhone()));
        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyLabelNormalText(""));
        return table;
    }
    public static PdfPTable createDeliveryDocumentMainBodyTitle(PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createTitleRowLargeText(responseObject.getCompanyName()));
        table.addCell(createCellWithBodyLebelText(responseObject.getStreetAddress()));
        table.addCell(createCellWithBodyLebelText(responseObject.getCityStateZip()));
        return table;
    }

    public static PdfPTable createDeliveryDocumentBodyHeaderTitle(String text) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createTitleRowMediumText(text));
        //table.addCell(createCellWithBodyLebelText("Company Address"))
        return table;
    }

    private static PdfPCell createTitleRowMediumText(String text) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLeftLargeStyle(cell);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentMainBodyContactTitle(PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.25f, 1.75f});
        table.addCell(createCellWithBodyLebelText("Phone: "));
        table.addCell(createCellWithBodyLebelText(responseObject.getPhoneNo()));
        table.addCell(createCellWithBodyLebelText("Fax: "));
        table.addCell(createCellWithBodyLebelText(responseObject.getFax()));
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

    private static void titleRowTextRightLargeStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(205f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.55f);
    }

    private static PdfPCell createCellWithBodyLebelText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPTable createParagraphTitleTableBuilderNormal(String text) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelTextNormal(text));
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextNormal(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
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

    public static PdfPTable createParagraphTitleTableBuilderInBody(String title) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelText(title));
        String paragraphContent = "Are there any factors such as temperature, physical layout, surfaces, or obstacles that will render the MAE unusable in the patient's home? Reminder: The physical layout of the home is required to be documented if least costly alternative is ruled out.";
        table.addCell(createCellWithBodyLebelTextAsParagraph(paragraphContent));
        return table;
    }

    public static PdfPTable createParagraphTitleTableBuilderInBody2(String title) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelText(title));
        table.addCell(createCellWithBodyLebelTextAsParagraph2());
        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextAsParagraph2() {
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase("Home Environment: ", boldFont));
        paragraph.add(new Phrase("Does the patient's home provide adequate access between rooms, adequate maneuvering space, surfaces for the placement of MAE?", normalFont));

        PdfPCell cell = new PdfPCell(new Phrase(paragraph));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelTextAsParagraph(String paragraphContent) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(paragraphContent, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentBodyTableHeader(PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.3f, 0.8f, 0.3f, 0.6f});

        table.addCell(createCellWithBodyLabelNormalText("Patient:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPatientName()));
        table.addCell(createCellWithBodyLabelNormalText("Patient ID:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPatientId()));

        table.addCell(createCellWithBodyLabelNormalText("Address:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getAddress()));
        table.addCell(createCellWithBodyLabelNormalText("Account #:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getAccount()));

        table.addCell(createCellWithBodyLabelNormalText("Phone:"));
        table.addCell(createCellWithBodyLabelNormalText(responseObject.getPhone()));
        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyLabelNormalText(""));
        return table;
    }

    private static PdfPCell createCellWithBodyLabelNormalText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentTableContactDetails(PatientAssessmentFormInput responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.25f, 0.4f, 0.25f, 0.4f, 0.3f, 0.4f});

        AlternateContacts alternateContacts = responseObject.getAlternateContacts();
        System.out.println("======Alternate Contacts======="+alternateContacts);

        table.addCell(createCellWithBodyLabelNormalText("Name:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getName1()));
        table.addCell(createCellWithBodyLabelNormalText("Phone:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getPhone1()));
        table.addCell(createCellWithBodyLabelNormalText("Relationship:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getRelationship1()));

        table.addCell(createCellWithBodyLabelNormalText("Name:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getName2()));
        table.addCell(createCellWithBodyLabelNormalText("Phone:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getPhone2()));
        table.addCell(createCellWithBodyLabelNormalText("Relationship:"));
        table.addCell(createCellWithBodyLabelNormalText(alternateContacts.getRelationship2()));

        return table;
    }

    public static PdfPTable createTitleTableHeaderBoldText(String text) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createTitleSingleRowText(text));
        return table;
    }

    private static PdfPCell createTitleSingleRowText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleSingleRowTextSmallStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleSingleRowTextSmallStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBorderColorBottom(BaseColor.WHITE);
    }

    public static PdfPTable createTableForRadioButton(PdfFormField radioGroup, PatientAssessmentFormInput responseObject) throws DocumentException, IOException {
        String elementType = "radioButton";
        //boolean isRadioChecked = true; //later send as parameter in the function body

        boolean patientVisionValueYes = false;
        boolean patientVisionValueNo = false;
        boolean patientVisionValueNa = false;
        if(responseObject.getPatientVision().equals("Yes")){
            patientVisionValueYes = true;
        } else if (responseObject.getPatientVision().equals("No")) {
            patientVisionValueNo = true;
        }else if (responseObject.getPatientVision().equals("Na")) {
            patientVisionValueNa = true;
        }

        boolean patientHearingValueYes = false;
        boolean patientHearingNo = false;
        boolean patientHearingNa = false;
        if(responseObject.getPatientHearing().equals("Yes")){
            patientHearingValueYes = true;
        } else if (responseObject.getPatientHearing().equals("No")) {
            patientHearingNo = true;
        }else if (responseObject.getPatientHearing().equals("Na")) {
            patientHearingNa = true;
        }

        boolean patientSpeechValueYes = false;
        boolean patientSpeechNo = false;
        boolean patientSpeechNa = false;
        if(responseObject.getPatientSpeech().equals("Yes")){
            patientSpeechValueYes = true;
        } else if (responseObject.getPatientSpeech().equals("No")) {
            patientSpeechNo = true;
        }else if (responseObject.getPatientSpeech().equals("Na")) {
            patientSpeechNa = true;
        }

        boolean patientAmbulatoryValueYes = false;
        boolean patientAmbulatoryNo = false;
        boolean patientAmbulatoryNa = false;
        if(responseObject.getPatientAmbulatory().equals("Yes")){
            patientAmbulatoryValueYes = true;
        } else if (responseObject.getPatientAmbulatory().equals("No")) {
            patientAmbulatoryNo = true;
        }else if (responseObject.getPatientAmbulatory().equals("Na")) {
            patientAmbulatoryNa = true;
        }

        boolean alertUnderstandValueYes = false;
        boolean alertUnderstandValueNo = false;
        boolean alertUnderstandValueNa = false;
        if(responseObject.getAlertUnderstand().equals("Yes")){
            alertUnderstandValueYes = true;
        } else if (responseObject.getAlertUnderstand().equals("No")) {
            alertUnderstandValueNo = true;
        }else if (responseObject.getAlertUnderstand().equals("Na")) {
            alertUnderstandValueNa = true;
        }

        boolean confusedValueYes = false;
        boolean confusedValueNo = false;
        boolean confusedValueNa = false;
        if(responseObject.getConfused().equals("Yes")){
            confusedValueYes = true;
        } else if (responseObject.getConfused().equals("No")) {
            confusedValueNo = true;
        }else if (responseObject.getConfused().equals("Na")) {
            confusedValueNa = true;
        }

        boolean dementiaValueYes = false;
        boolean dementiaValueNo = false;
        boolean dementiaValueNa = false;
        if(responseObject.getDementia().equals("Yes")){
            dementiaValueYes = true;
        } else if (responseObject.getDementia().equals("No")) {
            dementiaValueNo = true;
        }else if (responseObject.getDementia().equals("Na")) {
            dementiaValueNa = true;
        }

        boolean patientMobileValueYes = false;
        boolean patientMobileValueNo = false;
        boolean patientMobileValueNa = false;
        if(responseObject.getPatientMobile().equals("Yes")){
            patientMobileValueYes = true;
        } else if (responseObject.getPatientMobile().equals("No")) {
            patientMobileValueNo = true;
        }else if (responseObject.getPatientMobile().equals("Na")) {
            patientMobileValueNa = true;
        }

        boolean patientBedRiddenValueYes = false;
        boolean patientBedRiddenValueNo = false;
        boolean patientBedRiddenValueNa = false;
        if(responseObject.getPatientBedRidden().equals("Yes")){
            patientBedRiddenValueYes = true;
        } else if (responseObject.getPatientBedRidden().equals("No")) {
            patientBedRiddenValueNo = true;
        }else if (responseObject.getPatientBedRidden().equals("Na")) {
            patientBedRiddenValueNa = true;
        }

        boolean patientFallRiskValueYes = false;
        boolean patientFallRiskValueNo = false;
        boolean patientFallRiskValueNa = false;
        if(responseObject.getPatientFallRisk().equals("Yes")){
            patientFallRiskValueYes = true;
        } else if (responseObject.getPatientFallRisk().equals("No")) {
            patientFallRiskValueNo = true;
        }else if (responseObject.getPatientFallRisk().equals("Na")) {
            patientFallRiskValueNa = true;
        }

        boolean patientNutritionalValueYes = false;
        boolean patientNutritionalValueNo = false;
        boolean patientNutritionalValueNa = false;
        if(responseObject.getPatientNutritional().equals("Yes")){
            patientNutritionalValueYes = true;
        } else if (responseObject.getPatientNutritional().equals("No")) {
            patientNutritionalValueNo = true;
        }else if (responseObject.getPatientNutritional().equals("Na")) {
            patientNutritionalValueNa = true;
        }

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(50);
        table.setWidths(new float[]{0.05f, 0.05f, 0.05f, 0.35f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("NA", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("Functional Limits", Element.ALIGN_LEFT, Font.BOLD));

        String[] rowWiseRadioBtnArr = new String[]{"Yes", "No", "NA"};
        String[] rowWiseTextArr = new String[]{"Patient has vision impairment", "Patient has hearing impairment", "Patient has speech impairment", "Patient has ambulatory impairment"};

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_A", elementType, patientVisionValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_A", elementType, patientVisionValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_A", elementType, patientVisionValueNa));
        table.addCell(createCellWithBodyLebelText("Patient has vision impairment", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_B", elementType, patientHearingValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_B", elementType, patientHearingNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_B", elementType, patientHearingNa));
        table.addCell(createCellWithBodyLebelText("Patient has hearing impairment", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_C", elementType, patientSpeechValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_C", elementType, patientSpeechNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_C", elementType, patientSpeechNa));
        table.addCell(createCellWithBodyLebelText("Patient has speech impairment", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_D", elementType, patientAmbulatoryValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_D", elementType, patientAmbulatoryNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_D", elementType, patientAmbulatoryNa));
        table.addCell(createCellWithBodyLebelText("Patient has ambulatory impairment", Element.ALIGN_LEFT, Font.NORMAL));

        //Second Section of Table Starts
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("Patient Mental State", Element.ALIGN_LEFT, Font.BOLD));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_E", elementType, alertUnderstandValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_E", elementType, alertUnderstandValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_E", elementType, alertUnderstandValueNa));
        table.addCell(createCellWithBodyLebelText("Alert and understands situation", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_F", elementType, confusedValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_F", elementType, confusedValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_F", elementType, confusedValueNa));
        table.addCell(createCellWithBodyLebelText("Confused\\Incoherent", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_G", elementType, dementiaValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_G", elementType, dementiaValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_G", elementType, dementiaValueNa));
        table.addCell(createCellWithBodyLebelText("Dementia", Element.ALIGN_LEFT, Font.NORMAL));

        //Third Section of Table Starts
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("Activity Level", Element.ALIGN_LEFT, Font.BOLD));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_H", elementType, patientMobileValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_H", elementType, patientMobileValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_H", elementType, patientMobileValueNa));
        table.addCell(createCellWithBodyLebelText("Patient is mobile\\active", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_I", elementType, patientBedRiddenValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_I", elementType, patientBedRiddenValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_I", elementType, patientBedRiddenValueNa));
        table.addCell(createCellWithBodyLebelText("Patient is bed-ridden", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_J", elementType, patientFallRiskValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_J", elementType, patientFallRiskValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_J", elementType, patientFallRiskValueNa));
        table.addCell(createCellWithBodyLebelText("Patient is a fall risk", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_K", elementType, patientNutritionalValueYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_K", elementType, patientNutritionalValueNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_K", elementType, patientNutritionalValueNa));
        table.addCell(createCellWithBodyLebelText("Patient is nutritional high-risk", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createTableForRadioButton3(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.035f, 0.035f, 0.035f, 0.45f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data
        String carpetThrowRugsVal = responseObject.getCarpetThrowRugs();
        boolean carpetThrowRugsYesChecked = false;
        boolean carpetThrowRugsNoChecked = false;
        ;
        boolean carpetThrowRugsNAChecked = false;
        ;
        if (carpetThrowRugsVal.equals("Yes")) {
            carpetThrowRugsYesChecked = true;
        } else if (carpetThrowRugsVal.equals("No")) {
            carpetThrowRugsNoChecked = true;
        } else if (carpetThrowRugsVal.equals("Na")) {
            carpetThrowRugsNAChecked = true;
        }
        String looseUnevenFloorsVal = responseObject.getLooseUnevenFloors();
        boolean looseUnevenFloorsYesChecked = false;
        boolean looseUnevenFloorsNoChecked = false;
        ;
        boolean looseUnevenFloorsNAChecked = false;
        ;
        if (looseUnevenFloorsVal.equals("Yes")) {
            looseUnevenFloorsYesChecked = true;
        } else if (looseUnevenFloorsVal.equals("No")) {
            looseUnevenFloorsNoChecked = true;
        } else if (looseUnevenFloorsVal.equals("Na")) {
            looseUnevenFloorsNAChecked = true;
        }

        String stairsStepsVal = responseObject.getStairsSteps();
        boolean stairsStepsYesChecked = false;
        boolean stairsStepsNoChecked = false;
        ;
        boolean stairsStepsNAChecked = false;
        ;
        if (stairsStepsVal.equals("Yes")) {
            stairsStepsYesChecked = true;
        } else if (stairsStepsVal.equals("No")) {
            stairsStepsNoChecked = true;
        } else if (stairsStepsVal.equals("Na")) {
            stairsStepsNAChecked = true;
        }

        String wcRampsInsideOrOutsideTheHomeVal = responseObject.getWcRampsInsideOrOutsideTheHome();
        boolean wcRampsInsideOrOutsideTheHomeYesChecked = false;
        boolean wcRampsInsideOrOutsideTheHomeNoChecked = false;
        ;
        boolean wcRampsInsideOrOutsideTheHomeNAChecked = false;
        ;
        if (wcRampsInsideOrOutsideTheHomeVal.equals("Yes")) {
            wcRampsInsideOrOutsideTheHomeYesChecked = true;
        } else if (wcRampsInsideOrOutsideTheHomeVal.equals("No")) {
            wcRampsInsideOrOutsideTheHomeNoChecked = true;
        } else if (wcRampsInsideOrOutsideTheHomeVal.equals("Na")) {
            wcRampsInsideOrOutsideTheHomeNAChecked = true;
        }

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("NA", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_LEFT, Font.BOLD));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, carpetThrowRugsYesChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, carpetThrowRugsNoChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, carpetThrowRugsNAChecked));
        table.addCell(createCellWithBodyLebelText("Carpet or Throw Rugs", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, looseUnevenFloorsYesChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, looseUnevenFloorsNoChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, looseUnevenFloorsNAChecked));
        table.addCell(createCellWithBodyLebelText("Loose/Uneven floors", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, stairsStepsYesChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, stairsStepsNoChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, stairsStepsNAChecked));
        table.addCell(createCellWithBodyLebelText("Stairs or Steps", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, wcRampsInsideOrOutsideTheHomeYesChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, wcRampsInsideOrOutsideTheHomeNoChecked));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_15", elementType, wcRampsInsideOrOutsideTheHomeNAChecked));
        table.addCell(createCellWithBodyLebelText("W/C ramps inside or outside the home", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createTableForRadioButton4(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table2 = new PdfPTable(4);
        table2.setWidthPercentage(100);
        table2.setWidths(new float[]{0.05f, 0.05f, 0.05f, 1f});
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        table2.addCell(createCellWithBodyLebelTextNextPage("Yes", Element.ALIGN_LEFT, Font.BOLD));
        table2.addCell(createCellWithBodyLebelTextNextPage("No", Element.ALIGN_LEFT, Font.BOLD));
        table2.addCell(createCellWithBodyLebelTextNextPage("N/A", Element.ALIGN_LEFT, Font.BOLD));
        table2.addCell(createCellWithBodyLebelTextNextPage("", Element.ALIGN_LEFT, Font.BOLD));

        //Prepare Dynamic data to populate
        String isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchair = responseObject.getCaneCrutchesWalkerManualWheelchair();
        boolean isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairYes = false;
        boolean isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNo = false;
        boolean isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNA = false;
        if (isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchair.equals("Yes")) {
            isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairYes = true;
        } else if (isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchair.equals("No")) {
            isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNo = true;
        } else if (isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchair.equals("Na")) {
            isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNA = true;
        }

        String isAdequateAccessCheckedForPovScooter = responseObject.getPovScooter();
        boolean isAdequateAccessCheckedForPovScooterYes = false;
        boolean isAdequateAccessCheckedForPovScooterNo = false;
        boolean isAdequateAccessCheckedForPovScooterNA = false;
        if (isAdequateAccessCheckedForPovScooter.equals("Yes")) {
            isAdequateAccessCheckedForPovScooterYes = true;
        } else if (isAdequateAccessCheckedForPovScooter.equals("No")) {
            isAdequateAccessCheckedForPovScooterNo = true;
        } else if (isAdequateAccessCheckedForPovScooter.equals("Na")) {
            isAdequateAccessCheckedForPovScooterNA = true;
        }

        String isAdequateAccessCheckedForPowerWheelchair = responseObject.getPowerWheelchair();
        boolean isAdequateAccessCheckedForPowerWheelchairYes = false;
        boolean isAdequateAccessCheckedForPowerWheelchairNo = false;
        boolean isAdequateAccessCheckedForPowerWheelchairNA = false;
        if (isAdequateAccessCheckedForPowerWheelchair.equals("Yes")) {
            isAdequateAccessCheckedForPowerWheelchairYes = true;
        } else if (isAdequateAccessCheckedForPowerWheelchair.equals("No")) {
            isAdequateAccessCheckedForPowerWheelchairNo = true;
        } else if (isAdequateAccessCheckedForPowerWheelchair.equals("Na")) {
            isAdequateAccessCheckedForPowerWheelchairNA = true;
        }

        String isLimitedAccessCheckedForBathroomFacilities = responseObject.getBathroomFacilities();
        boolean isLimitedAccessCheckedForBathroomFacilitiesYes = false;
        boolean isLimitedAccessCheckedForBathroomFacilitiesNo = false;
        boolean isLimitedAccessCheckedForBathroomFacilitiesNA = false;
        if (isLimitedAccessCheckedForBathroomFacilities.equals("Yes")) {
            isLimitedAccessCheckedForBathroomFacilitiesYes = true;
        } else if (isLimitedAccessCheckedForBathroomFacilities.equals("No")) {
            isLimitedAccessCheckedForBathroomFacilitiesNo = true;
        } else if (isLimitedAccessCheckedForBathroomFacilities.equals("Na")) {
            isLimitedAccessCheckedForBathroomFacilitiesNA = true;
        }

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, isAdequateAccessCheckedForCaneCrutchesWalkerManualWheelchairNA));
        table2.addCell(createCellWithBodyLebelText("Adequate access & maneuvering space for a cane, crutches, walker, Manual Wheelchair", Element.ALIGN_LEFT, Font.NORMAL));

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, isAdequateAccessCheckedForPovScooterYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, isAdequateAccessCheckedForPovScooterNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, isAdequateAccessCheckedForPovScooterNA));
        table2.addCell(createCellWithBodyLebelText("Adequate access & maneuvering space for POV/Scooter?", Element.ALIGN_LEFT, Font.NORMAL));

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, isAdequateAccessCheckedForPowerWheelchairYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, isAdequateAccessCheckedForPowerWheelchairNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, isAdequateAccessCheckedForPowerWheelchairNA));
        table2.addCell(createCellWithBodyLebelText("Adequate access & maneuvering space for Power Wheelchair?", Element.ALIGN_LEFT, Font.NORMAL));

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, isLimitedAccessCheckedForBathroomFacilitiesYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, isLimitedAccessCheckedForBathroomFacilitiesNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, isLimitedAccessCheckedForBathroomFacilitiesNA));
        table2.addCell(createCellWithBodyLebelText("Limited access to bathroom facilities; (i.e. cannot get into bathroom, but there a bedside commode in bedroom)", Element.ALIGN_LEFT, Font.NORMAL));

        return table2;
    }

    public static PdfPTable createTableForRadioButtonAndInputBox4(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(80);
        table2.setWidths(new float[]{0.05f, 0.05f, 0.05f, 0.5f, 0.27f});
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data to populate
        String isRoomAccess = responseObject.getRoomAccess();
        boolean isRoomAccessYes = false;
        boolean isRoomAccessNo = false;
        boolean isRoomAccessNA = false;
        if (isRoomAccess.equals("Yes")) {
            isRoomAccessYes = true;
        } else if (isRoomAccess.equals("No")) {
            isRoomAccessNo = true;
        } else if (isRoomAccess.equals("Na")) {
            isRoomAccessNA = true;
        }

        String roomAccessValue = responseObject.getRoomAccessValue();

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, isRoomAccessYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, isRoomAccessNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, isRoomAccessNA));
        table2.addCell(createCellWithBodyLebelText("Explain in detail room access issue/options:", Element.ALIGN_LEFT, Font.NORMAL));
        table2.addCell(createCellWithBodyInputBoxWithPadding(roomAccessValue));

        return table2;
    }

    public static PdfPTable createTableForRadioButtonAndInputBox6(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(55);
        table2.setWidths(new float[]{0.035f, 0.035f, 0.035f, 0.2f, 0.16f});
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.setSpacingAfter(3);

        //Prepare Checkbox data
        String explainAccessOptionsRadio = responseObject.getExplainAccessOptions();
        boolean explainAccessOptionsRadioYes = false;
        boolean explainAccessOptionsRadioNo = false;
        boolean explainAccessOptionsRadioNA = false;
        if (explainAccessOptionsRadio.equals("Yes")) {
            explainAccessOptionsRadioYes = true;
        } else if (explainAccessOptionsRadio.equals("No")) {
            explainAccessOptionsRadioNo = true;
        } else if (explainAccessOptionsRadio.equals("Na")) {
            explainAccessOptionsRadioNA = true;
        }
        String explainAccessOptionsVal = responseObject.getExplainAccessOptionsValue();

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, explainAccessOptionsRadioYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, explainAccessOptionsRadioNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, explainAccessOptionsRadioNA));
        table2.addCell(createCellWithBodyLebelText("Explain Access Options", Element.ALIGN_LEFT, Font.NORMAL));
        table2.addCell(createCellWithBodyInputBoxWithPadding(explainAccessOptionsVal));

        return table2;
    }

    public static PdfPTable createTableForRadioButtonAndInputBox7(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(47);
        table2.setWidths(new float[]{0.035f, 0.035f, 0.035f, 0.1f, 0.2f});
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data to populate
        String othersSectionRadio = responseObject.getOthersSection();
        boolean othersSectionRadioYes = false;
        boolean othersSectionRadioNo = false;
        boolean othersSectionRadioNA = false;
        if (othersSectionRadio.equals("Yes")) {
            othersSectionRadioYes = true;
        } else if (othersSectionRadio.equals("No")) {
            othersSectionRadioNo = true;
        } else if (othersSectionRadio.equals("Na")) {
            othersSectionRadioNA = true;
        }
        String othersSectionVal = responseObject.getOthersSectionValue();

        table2.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, othersSectionRadioYes));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, othersSectionRadioNo));
        table2.addCell(createCellWithBodyRadioButton(radioGroup, "NA", elementType, othersSectionRadioNA));
        table2.addCell(createCellWithBodyLebelText("Others:", Element.ALIGN_LEFT, Font.NORMAL));
        table2.addCell(createCellWithBodyInputBoxWithPadding(othersSectionVal));

        return table2;
    }

    public static PdfPTable createTableForRadioButtonAndInputBox5(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelTextNextPage("Yes", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelTextNextPage("No", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelTextNextPage("#", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelTextNextPage("", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelTextNextPage("", Element.ALIGN_LEFT, Font.BOLD));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced1(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //Prepare Dynamic data
        String entryDoorsRadioVal = responseObject.getEntryDoors();
        boolean entryDoorsRadioYes = false;
        boolean entryDoorsRadioNo = false;
        if (entryDoorsRadioVal.equals("Yes")) {
            entryDoorsRadioYes = true;
        } else if (entryDoorsRadioVal.equals("No")) {
            entryDoorsRadioNo = true;
        }

        String entryDoorsSlVal = responseObject.getEntryDoorsSl();
        String entryDoorsValue = responseObject.getEntryDoorsValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, entryDoorsRadioYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, entryDoorsRadioNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(entryDoorsSlVal));
        table.addCell(createCellWithBodyLebelText("Entry Doors:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(entryDoorsValue));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced2(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //Prepare Dynamic data
        String bedRoomRadioVal = responseObject.getBedRoom();
        boolean bedRoomYes = false;
        boolean bedRoomNo = false;
        if (bedRoomRadioVal.equals("Yes")) {
            bedRoomYes = true;
        } else if (bedRoomRadioVal.equals("No")) {
            bedRoomNo = true;
        }

        String bedRoomSlVal = responseObject.getBedRoomSl();
        String bedRoomValue = responseObject.getBedRoomValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, bedRoomYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, bedRoomNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(bedRoomSlVal));
        table.addCell(createCellWithBodyLebelText("Bed Room:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(bedRoomValue));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced3(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //Prepare Dynamic data
        String kitchenRadioVal = responseObject.getKitchen();
        boolean kitchenYes = false;
        boolean kitchenNo = false;
        if (kitchenRadioVal.equals("Yes")) {
            kitchenYes = true;
        } else if (kitchenRadioVal.equals("No")) {
            kitchenNo = true;
        }

        String kitchenSlVal = responseObject.getKitchenSl();
        String kitchenValue = responseObject.getKitchenValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, kitchenYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, kitchenNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(kitchenSlVal));
        table.addCell(createCellWithBodyLebelText("Kitchen:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(kitchenValue));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced4(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //Prepare Dynamic data
        String bathroomRadioVal = responseObject.getBathroom();
        boolean bathroomRadioYes = false;
        boolean bathroomRadioNo = false;
        if (bathroomRadioVal.equals("Yes")) {
            bathroomRadioYes = true;
        } else if (bathroomRadioVal.equals("No")) {
            bathroomRadioNo = true;
        }

        String bathroomSlVal = responseObject.getBathroomSl();
        String bathroomValue = responseObject.getBathroomValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, bathroomRadioYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, bathroomRadioNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(bathroomSlVal));
        table.addCell(createCellWithBodyLebelText("Bathroom:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(bathroomValue));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced5(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        //Prepare Dynamic data
        String hallwaysRadioVal = responseObject.getHallways();
        boolean hallwaysRadioYes = false;
        boolean hallwaysRadioNo = false;
        if (hallwaysRadioVal.equals("Yes")) {
            hallwaysRadioYes = true;
        } else if (hallwaysRadioVal.equals("No")) {
            hallwaysRadioNo = true;
        }

        String hallwaysSlVal = responseObject.getHallwaysSl();
        String hallwaysValue = responseObject.getHallwaysValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, hallwaysRadioYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, hallwaysRadioNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(hallwaysSlVal));
        table.addCell(createCellWithBodyLebelText("Hallways:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(hallwaysValue));

        return table;
    }

    public static PdfPTable createTableForRadioButtonAndInputBoxSpaced6(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(65);
        table.setWidths(new float[]{0.05f, 0.05f, 0.15f, 0.15f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data
        String otherRoomsRadioVal = responseObject.getOtherRooms();
        boolean otherRoomsRadioYes = false;
        boolean otherRoomsRadioNo = false;
        if (otherRoomsRadioVal.equals("Yes")) {
            otherRoomsRadioYes = true;
        } else if (otherRoomsRadioVal.equals("No")) {
            otherRoomsRadioNo = true;
        }

        String otherRoomsSl = responseObject.getOtherRoomsSl();
        String otherRoomsValue = responseObject.getOtherRoomsValue();

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", elementType, otherRoomsRadioYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", elementType, otherRoomsRadioNo));
        table.addCell(createCellWithBodyInputBoxWithPadding(otherRoomsSl));
        table.addCell(createCellWithBodyLebelText("Other Rooms:", Element.ALIGN_RIGHT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBoxWithPadding(otherRoomsValue));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextNextPage(String text, int elementAlignement, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        tableHeaderTitleInBodyStyle(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void tableHeaderTitleInBodyStyle(PdfPCell cell, int elementAlignement) {
        cell.setHorizontalAlignment(elementAlignement);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyInputBoxWithPadding(String text) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.1f);
        return cell;
    }

    public static PdfPTable createTableForRadioButton2(PdfFormField radioGroup, PatientAssessmentFormInput responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(69);
        table.setWidths(new float[]{0.05f, 0.05f, 0.05f, 0.59f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        boolean caregiverWillingYes = false;
        boolean caregiverWillingNo = false;
        boolean caregiverWillingNa = false;
        if(responseObject.getCaregiverWilling().equals("Yes")){
            caregiverWillingYes = true;
        } else if (responseObject.getCaregiverWilling().equals("No")) {
            caregiverWillingNo = true;
        }else if (responseObject.getCaregiverWilling().equals("Na")) {
            caregiverWillingNo = true;
        }

        boolean caregiverAbleYes = false;
        boolean caregiverAbleNo = false;
        boolean caregiverAbleNa = false;
        if(responseObject.getCaregiverAble().equals("Yes")){
            caregiverAbleYes = true;
        } else if (responseObject.getCaregiverAble().equals("No")) {
            caregiverAbleNo = true;
        }else if (responseObject.getCaregiverAble().equals("Na")) {
            caregiverAbleNa = true;
        }

        boolean caregiverUnderstandsYes = false;
        boolean caregiverUnderstandsNo = false;
        boolean caregiverUnderstandsNa = false;
        if(responseObject.getCaregiverUnderstands().equals("Yes")){
            caregiverUnderstandsYes = true;
        } else if (responseObject.getCaregiverUnderstands().equals("No")) {
            caregiverUnderstandsNo = true;
        }else if (responseObject.getCaregiverUnderstands().equals("Na")) {
            caregiverUnderstandsNa = true;
        }

        boolean caregiverMaintainYes = false;
        boolean caregiverMaintainNo = false;
        boolean caregiverMaintainNa = false;
        if(responseObject.getCaregiverMaintain().equals("Yes")){
            caregiverMaintainYes = true;
        } else if (responseObject.getCaregiverMaintain().equals("No")) {
            caregiverMaintainNo = true;
        }else if (responseObject.getCaregiverMaintain().equals("Na")) {
            caregiverMaintainNa = true;
        }

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("N/A", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("Assessment", Element.ALIGN_LEFT, Font.BOLD));

        String elementType = "radioButton";

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_L", elementType, caregiverWillingYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_L", elementType, caregiverWillingNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_L", elementType, caregiverWillingNa));
        table.addCell(createCellWithBodyLebelText("Caregiver willing to provide care", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_M", elementType, caregiverAbleYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_M", elementType, caregiverAbleNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_M", elementType, caregiverAbleNa));
        table.addCell(createCellWithBodyLebelText("Caregiver able to provide care", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_N", elementType, caregiverUnderstandsYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_N", elementType, caregiverUnderstandsNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_N", elementType, caregiverUnderstandsNa));
        table.addCell(createCellWithBodyLebelText("Caregiver understands instructions and equipment operation", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes_O", elementType, caregiverMaintainYes));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No_O", elementType, caregiverMaintainNo));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "NA_O", elementType, caregiverMaintainNa));
        table.addCell(createCellWithBodyLebelText("Caregiver able to maintain and operate equipment safely", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelText(String text, int elementAlignement, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5Style(PdfPCell cell, int elementAlignement) {
        cell.setHorizontalAlignment(elementAlignement);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyRadioButton(PdfFormField radioGroup, String elementValue, String elementType, boolean isRadioChecked) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        String radioValue = elementValue;
        elementValue = "";
        PdfPCell cell = new PdfPCell(new Phrase(elementValue, font));
        tableRowRadioButtonStyle(cell, radioGroup, radioValue, elementType, isRadioChecked);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void tableRowRadioButtonStyle(PdfPCell cell, PdfFormField radioGroup, String radioValue, String elementType, boolean isRadioChecked) {
        cell.setCellEvent(new CellFieldEvent(radioGroup, radioValue, elementType, isRadioChecked));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    /***
     static class MyCellField implements PdfPCellEvent {
     protected PdfFormField radioGroup;
     protected String value;
     HashMap<String, String> map = new HashMap<>();
     public MyCellField(PdfFormField radioGroup, String value) {
     this.radioGroup = radioGroup;
     this.value = value;
     }
     public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
     //Dummy HashMap to make few radio button checked
     map.put("Yes_1", "Yes_1");
     map.put("No_2", "No_2");
     map.put("NA_3", "NA_3");
     map.put("No_4", "No_4");
     map.put("Yes_5", "Yes_5");
     map.put("Yes_6", "Yes_6");
     map.put("Yes_7", "Yes_7");
     map.put("NA_8", "NA_8");
     map.put("NA_9", "NA_9");
     map.put("Yes_10", "Yes_10");
     map.put("Yes_11", "Yes_11");
     final PdfWriter writer = canvases[0].getPdfWriter();

     // define the coordinates of the middle
     float x = (rectangle.getLeft() + rectangle.getRight()) / 2;
     float y = (rectangle.getTop() + rectangle.getBottom()) / 2;
     // define the position of a check box that measures 10 by 10
     //Resize Radio Button
     Rectangle rect = new Rectangle(x - 5, y - 5, x + 5, y + 5);

     RadioCheckField radio = new RadioCheckField(writer, rect, value, value);
     System.out.println("=======Radio value======"+value);
     System.out.println("=======Radio Rectangle======"+radio.getBox());

     try {
     if(map.containsKey(value)){
     radio.setChecked(true);
     }
     radio.setBorderColor(BaseColor.BLACK);
     radioGroup.addKid(radio.getRadioField());
     } catch (final IOException ioe) {
     throw new ExceptionConverter(ioe);
     } catch (final DocumentException de) {
     throw new ExceptionConverter(de);
     }
     }
     }
     ***/
    public static PdfPTable createTitleTableForCommentText(String tableHeader) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithNormalHeaderText(tableHeader));
        //table.addCell(createCellWithBodyLebelText("9083373106"));
        return table;
    }

    private static PdfPCell createCellWithNormalHeaderText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallHeaderStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallHeaderStyle(PdfPCell cell) {
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

    /*** =====================Code for Radio Button and Check Box============================***/
    public static PdfPTable createRadioButtonInTable(PdfFormField radioGroup) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(50);
        table.setWidths(new float[]{0.05f, 0.05f, 0.05f, 0.35f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("NA", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("Functional Limits", Element.ALIGN_LEFT, Font.BOLD));

        String[] rowWiseRadioBtnArr = new String[]{"Yes", "No", "NA"};
        String[] rowWiseTextArr = new String[]{"Patient has vision impairment", "Patient has hearing impairment", "Patient has speech impairment", "Patient has ambulatory impairment"};

        //table.addCell(createCellWithBodyRadioButton("Yes_1", radioGroup));
        //table.addCell(createCellWithBodyRadioButton("No_1", radioGroup));
        //table.addCell(createCellWithBodyRadioButton("NA_1", radioGroup));
        //table.addCell(createCellWithBodyLebelText("This is a Radio Button", Element.ALIGN_LEFT, Font.NORMAL));

        //table.addCell(createCellWithBodyRadioButton("Yes_2", radioGroup));
        //table.addCell(createCellWithBodyRadioButton("No_2", radioGroup));
        //table.addCell(createCellWithBodyRadioButton("NA_2", radioGroup));
        //table.addCell(createCellWithBodyLebelText("This is a Check Box", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable(PdfFormField checkboxGroup) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 0.15f, 0.05f, 0.15f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(createCellWithBodyLebelText("1", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("ABC", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("2", Element.ALIGN_CENTER, Font.BOLD));
        table.addCell(createCellWithBodyLebelText("DEF", Element.ALIGN_LEFT, Font.BOLD));

        //table.addCell(createCellWithBodyCheckBox("Checkbox_1", checkboxGroup));
        //table.addCell(createCellWithBodyLebelText("This is a Check Box", Element.ALIGN_LEFT, Font.NORMAL));
        //table.addCell(createCellWithBodyRadioButton("No_1", radioGroup));
        //table.addCell(createCellWithBodyRadioButton("NA_1", radioGroup));
        //table.addCell(createCellWithBodyLebelText("This is a Radio Button", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(72);
        table.setWidths(new float[]{0.05f, 0.23f, 0.05f, 0.3f, 0.05f, 0.15f, 0.05f, 0.35f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Check the checkbox based on dynamic values
        boolean isCaneChecked = responseObject.getTypeOfMobilityCane();
        boolean isCrutchesChecked = responseObject.getTypeOfMobilityCrutches();
        boolean isWalkerChecked = responseObject.getTypeOfMobilityWalker();
        boolean isManualWheelChairChecked = responseObject.getTypeOfMobilityManualwheelchair();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Cane", elementType, isCaneChecked));
        table.addCell(createCellWithBodyLebelText("Cane", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "Crutches", elementType, isCrutchesChecked));
        table.addCell(createCellWithBodyLebelText("Crutches", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "Walker", elementType, isWalkerChecked));
        table.addCell(createCellWithBodyLebelText("Walker", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "manualWheelchair", elementType, isManualWheelChairChecked));
        table.addCell(createCellWithBodyLebelText("Manual Wheelchair", Element.ALIGN_LEFT, Font.NORMAL));

        //table.addCell(createCellWithBodyInputBox());
        return table;
    }

    public static PdfPTable createCheckBoxAndInputBoxRowInTable(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(72);
        table.setWidths(new float[]{0.05f, 0.23f, 0.05f, 0.3f, 0.05f, 0.15f, 0.4f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Checkbox data
        boolean isPovScooterChecked = responseObject.getTypeOfMobilityPovscooter();
        boolean isPowerWheelchairChecked = responseObject.getTypeOfMobilityPowerwheelchair();
        boolean isOtherChecked = responseObject.getTypeOfMobilityOtherchk();
        String typeOfMobilityOtherVal = responseObject.getTypeOfMobilityOther();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "povScooter", elementType, isPovScooterChecked));
        table.addCell(createCellWithBodyLebelText("POV\\Scooter", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "powerWheelchair ", elementType, isPowerWheelchairChecked));
        table.addCell(createCellWithBodyLebelText("Power Wheelchair ", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "other", elementType, isOtherChecked));
        table.addCell(createCellWithBodyLebelText("Other", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBox(typeOfMobilityOtherVal));
        return table;
    }

    public static PdfPTable createCheckBoxInTable2(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 1.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Checkbox data
        boolean assessmentPerformedVerballyCheck = responseObject.getAssessmentPerformedVerballyCheck();
        boolean completedPreliminaryAssessmentCheck = responseObject.getCompletedPreliminaryAssessmentCheck();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk99", elementType, assessmentPerformedVerballyCheck));
        table.addCell(createCellWithBodyLebelText("The home assessment was performed verbally with the patient/caregiver and based upon this information the patient's home will accommodate the following MAE(s):(Check all that apply)", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk100", elementType, completedPreliminaryAssessmentCheck));
        table.addCell(createCellWithBodyLebelText("I have completed a preliminary assessment of the patient's home and conclude based upon this information the patient's home will accommodate the following MAE(s): (Check all that apply)", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable3(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(60);
        table.setWidths(new float[]{0.05f, 0.35f, 0.05f, 0.25f, 0.05f, 0.35f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Checkbox data
        boolean applyCaneCheck = responseObject.getApplyCane();
        boolean applyCrutchesCheck = responseObject.getApplyCrutches();
        boolean applyWalkerCheck = responseObject.getApplyWalker();
        boolean applyManualWheelchairCheck = responseObject.getApplyManualWheelchair();
        boolean applyPovScooterCheck = responseObject.getApplyPovScooter();
        boolean applyPowerWheelchairCheck = responseObject.getApplyPowerWheelchair();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "Cane1", elementType, applyCaneCheck));
        table.addCell(createCellWithBodyLebelText("Cane", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "Crutches1", elementType, applyCrutchesCheck));
        table.addCell(createCellWithBodyLebelText("Crutches", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "Walker1", elementType, applyWalkerCheck));
        table.addCell(createCellWithBodyLebelText("Walker", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "manualWheelchair", elementType, applyManualWheelchairCheck));
        table.addCell(createCellWithBodyLebelText("Manual Wheelchair", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "povScooter ", elementType, applyPovScooterCheck));
        table.addCell(createCellWithBodyLebelText("POV/Scooter", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "powerWheelchair", elementType, applyPowerWheelchairCheck));
        table.addCell(createCellWithBodyLebelText("Power Wheelchair", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxAndTextBoxInTable2(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(92);
        table.setWidths(new float[]{0.05f, 0.65f, 0.5f, 0.25f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data to populate
        boolean isOtherMEChecked = responseObject.getOthermedicalequipment();
        String otherMedicalEquipVal = responseObject.getOthermedicalequipmentValue();
        String providerByVal = responseObject.getProviderby();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk1", "checkbox", isOtherMEChecked));
        table.addCell(createCellWithBodyLebelText("Other Medical Equipment in the Home?", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBox(otherMedicalEquipVal));
        table.addCell(createCellWithBodyLebelText("Provided by:", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBox(providerByVal));

        return table;
    }

    public static PdfPTable createMultipleRadioButtonInTable(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(69);
        table.setWidths(new float[]{0.035f, 0.15f, 0.035f, 0.15f, 0.035f, 0.15f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Radio data
        String typeOfHomeVal = responseObject.getTypeOfHome();
        boolean isRadioCheckedSSH = false;
        boolean isRadioCheckedMSH = false;
        boolean isRadioCheckedAC = false;
        boolean isRadioCheckedMH = false;
        boolean isRadioCheckedHH = false;

        if (typeOfHomeVal.equals("singlestoryhouse")) {
            isRadioCheckedSSH = true;
        } else if (typeOfHomeVal.equals("multipleStoryHouse")) {
            isRadioCheckedMSH = true;
        } else if (typeOfHomeVal.equals("aptCondo")) {
            isRadioCheckedAC = true;
        } else if (typeOfHomeVal.equals("mobileHome")) {
            isRadioCheckedMH = true;
        } else if (typeOfHomeVal.equals("handicapHousing")) {
            isRadioCheckedHH = true;
        }

        table.addCell(createCellWithBodyRadioButton(radioGroup, "singlestoryhouse", "radioButton", isRadioCheckedSSH));
        table.addCell(createCellWithBodyLebelText("Single Story House", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "multipleStoryHouse", "radioButton", isRadioCheckedMSH));
        table.addCell(createCellWithBodyLebelText("Multi-Story House", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "aptCondo", "radioButton", isRadioCheckedAC));
        table.addCell(createCellWithBodyLebelText("Apt/Condo", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyRadioButton(radioGroup, "mobileHome", "radioButton", isRadioCheckedMH));
        table.addCell(createCellWithBodyLebelText("Mobile Home", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "handicapHousing", "radioButton", isRadioCheckedHH));
        table.addCell(createCellWithBodyLebelText("Handicap Housing", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyLebelText("", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }

    public static PdfPTable createRadioAndCheckBoxInTable(PdfFormField radioGroup, String elementValue, String elementType, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(50);
        table.setWidths(new float[]{0.035f, 0.2f, 0.05f, 0.05f, 0.05f, 0.1f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data
        boolean isHandicapAccessibleChecked = responseObject.getHandicapAccessible();
        boolean HandicapAccessibleValYes = responseObject.getHandicapAccessibleValue().equals("Yes") ? true : false;
        boolean HandicapAccessibleValNo = responseObject.getHandicapAccessibleValue().equals("No") ? true : false;

        boolean isRampsElevatorsChecked = responseObject.getRampsElevators();
        boolean rampsElevatorsValYes = responseObject.getRampsElevatorsValue().equals("Yes") ? true : false;
        boolean rampsElevatorsValNo = responseObject.getRampsElevatorsValue().equals("No") ? true : false;
        
        table.addCell(createCellWithBodyCheckboxLeftAligned(radioGroup, "handicapAccessible", elementType, isHandicapAccessibleChecked));
        table.addCell(createCellWithBodyLebelText("Handicap Accessible?", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", "radioButton", HandicapAccessibleValYes));
        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", "radioButton", HandicapAccessibleValNo));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL));

        table.addCell(createCellWithBodyCheckboxLeftAligned(radioGroup, "rampsElevators", elementType, isRampsElevatorsChecked));
        table.addCell(createCellWithBodyLebelText("(Ramps, Elevators)", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "Yes", "radioButton", rampsElevatorsValYes));
        table.addCell(createCellWithBodyLebelText("Yes", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyRadioButton(radioGroup, "No", "radioButton", rampsElevatorsValNo));
        table.addCell(createCellWithBodyLebelText("No", Element.ALIGN_LEFT, Font.NORMAL));

        return table;
    }


    public static PdfPTable createCheckBoxAndInputboxInTable(PdfFormField radioGroup, PatientHomeAssessmentFormInputs responseObject) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(50);
        table.setWidths(new float[]{0.035f, 0.2f, 0.45f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Dynamic data to populate
        boolean isOthers2Checked = responseObject.getOthers2();
        String others2Val = responseObject.getOther2value();

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chkBox7", "checkbox", isOthers2Checked));
        table.addCell(createCellWithBodyLebelText("  Others:", Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createCellWithBodyInputBox(others2Val));

        return table;
    }

    private static PdfPCell createCellWithBodyInputBox(String text) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0.1f);
        //cell.setFixedHeight(1f);
        return cell;
    }

    private static PdfPCell createCellWithBodyCheckbox(PdfFormField radioGroup, String elementValue, String elementType, boolean isChecked) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        String radioValue = elementValue;
        elementValue = "";
        PdfPCell cell = new PdfPCell(new Phrase(elementValue, font));
        tableRowCheckboxStyle(cell, radioGroup, radioValue, elementType, isChecked);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyCheckboxLeftAligned(PdfFormField radioGroup, String elementValue, String elementType, boolean isChecked) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        String radioValue = elementValue;
        elementValue = "";
        PdfPCell cell = new PdfPCell(new Phrase(elementValue, font));
        tableRowCheckboxStyleLeftAligned(cell, radioGroup, radioValue, elementType, isChecked);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void tableRowCheckboxStyleLeftAligned(PdfPCell cell, PdfFormField radioGroup, String radioValue, String elementType, boolean isChecked) {
        cell.setCellEvent(new CellFieldEvent(radioGroup, radioValue, elementType, isChecked));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(3f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
        //cell.setMinimumHeight(50);
    }

    private static void tableRowCheckboxStyle(PdfPCell cell, PdfFormField radioGroup, String radioValue, String elementType, boolean isChecked) {
        cell.setCellEvent(new CellFieldEvent(radioGroup, radioValue, elementType, isChecked));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
        //cell.setMinimumHeight(50);
    }
}
