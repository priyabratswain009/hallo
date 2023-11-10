package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;

import java.io.IOException;

public class PAPSetupFormTableBuilder {
    public static PdfPTable createDeliveryDocumentMainBodyTitle(DeliveryTicket deliveryTicket) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createTitleRowLargeText(deliveryTicket.getBranchName()));
        table.addCell(createTitleRowLargeText("Plan of Care - CPAP-BIPAP"));
        return table;
    }

    private static PdfPCell createTitleRowLargeText(String text) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLeftLargeStyle(cell);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
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

    public static PdfPTable createDeliveryDocumentBodyTableHeader(DeliveryTicket deliveryTicket) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(85);
        table.setWidths(new float[]{0.12f, 0.43f, 0.2f, 0.5f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        //table.setSpacingAfter(3);

        String mName = deliveryTicket.getPatientMiddleName()!=null && !deliveryTicket.getPatientMiddleName().equals("") ? deliveryTicket.getPatientMiddleName().trim() : "";
        String fullName = null;
        if(mName.equals("")){
            fullName = deliveryTicket.getPatientFirstName()+" "+deliveryTicket.getPatientLastName();
        }else{
            fullName = deliveryTicket.getPatientFirstName()+" "+mName+" "+deliveryTicket.getPatientLastName();
        }
        String addr1 = deliveryTicket.getDeliveryAddress1()!=null && !deliveryTicket.getDeliveryAddress1().equals("")? deliveryTicket.getDeliveryAddress1() : "";
        String addr2 = deliveryTicket.getDeliveryAddress2()!=null && !deliveryTicket.getDeliveryAddress2().equals("")? deliveryTicket.getDeliveryAddress2() : "";
        String combinedAddr = null;
        if(!addr1.equals("")){
            if(!addr2.equals("")){
                combinedAddr = addr1+", "+addr2;
            }else{
                combinedAddr = addr1;
            }
        }else
        {
            if(!addr2.equals("")) {
                combinedAddr = addr2;
            }
        }

        String city = deliveryTicket.getDeliveryCity()!=null && !deliveryTicket.getDeliveryCity().equals("")? deliveryTicket.getDeliveryCity() : "";
        String state = deliveryTicket.getDeliveryState()!=null && !deliveryTicket.getDeliveryState().equals("")? deliveryTicket.getDeliveryState() : "";
        String zip = deliveryTicket.getDeliveryZip()!=null && !deliveryTicket.getDeliveryZip().equals("")? deliveryTicket.getDeliveryZip() : "";
        String combinedCityStateZip = null;
        if(!city.equals("")){
            if(!state.equals("")){
                if(!zip.equals("")){
                    combinedCityStateZip = city+", "+state+", "+zip;
                }else{
                    combinedCityStateZip = city+", "+state;
                }
            }else{
                if(!zip.equals("")){
                    combinedCityStateZip = city+", "+zip;
                }else{
                    combinedCityStateZip = city;
                }
            }
        }else{
            if(!state.equals("")){
                if(!zip.equals("")){
                    combinedCityStateZip = state+", "+zip;
                }else{
                    combinedCityStateZip = state;
                }
            }else{
                if(!zip.equals("")){
                    combinedCityStateZip = zip;
                }
            }
        }
        table.addCell(createCellWithBodyLabelNormalTextRight("Patient:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(fullName));
        table.addCell(createCellWithBodyLabelNormalTextRight("Patient ID:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(deliveryTicket.getPatientIdNo()));

        table.addCell(createCellWithBodyLabelNormalTextRight("Address:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(combinedAddr));
        table.addCell(createCellWithBodyLabelNormalTextRight("Account #:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(""));

        table.addCell(createCellWithBodyLabelNormalTextRight("", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(combinedCityStateZip));
        table.addCell(createCellWithBodyLabelNormalTextRight("DOB:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(""));
        return table;
    }

    public static PdfPTable createDeliveryDocumentBodyTableHeader1(DeliveryTicket deliveryTicket) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(85);
        table.setWidths(new float[]{0.12f, 0.43f, 0.2f, 0.1f, 0.1f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyLabelNormalTextRight("Phone:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(deliveryTicket.getPhone1()));
        table.addCell(createCellWithBodyLabelNormalTextRight("HT:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(" "));
        table.addCell(createCellWithBodyLabelNormalTextRight("WT:", Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyLabelNormalText(" "));
        return table;
    }

    private static PdfPCell createCellWithBodyLabelNormalText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyLabelNormalTextRight(String text, int elementAlignement) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallPAPShape(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallPAPShape(PdfPCell cell, int elementAlignement) {
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

    public static PdfPTable createDeliveryDocumentMainFormSubTitlePAP(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(1);
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

    public static PdfPTable createTableForTextAndInputBoxMultipleSpacedPAP() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{ 0.08f, 0.4f, 0.15f, 0.4f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyLebelTextFontResize("Mfg: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createTableForTextAndInputBoxMultipleSpacedPAP("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize("  Model #: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createTableForTextAndInputBoxMultipleSpacedPAP("", "BLACK", 8));

        return table;
    }

    public static PdfPTable createTableForTextAndInputBoxMultipleSpacedPAP1() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(55);
        table.setWidths(new float[]{ 0.08f, 0.4f, 0.15f, 0.4f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(5);

        table.addCell(createCellWithBodyLebelTextFontResize("Mfg: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createTableForTextAndInputBoxMultipleSpacedPAP("", "BLACK", 8));
        table.addCell(createCellWithBodyLebelTextFontResize("  Model #: ", Element.ALIGN_LEFT, Font.NORMAL, 8));
        table.addCell(createTableForTextAndInputBoxMultipleSpacedPAP("", "BLACK", 8));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextFontResize(String text, int elementAlignement, int fontType, int fontSize) {
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallPAPShape(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTableForTextAndInputBoxMultipleSpacedPAP(String inputBoxVal, String inputboxBorderColor, int fontSize) throws DocumentException, IOException {
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

    public static PdfPTable createCheckBoxInTable2(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 1.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(5);

        //Prepare Checkbox data
        boolean generalInfo = false;
        boolean generalInfo1 = false;
        boolean generalInfo2 = false;
        boolean generalInfo3 = false;
        boolean generalInfo4 = false;
        boolean generalInfo5 = false;
        boolean generalInfo6 = false;

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk1", elementType, generalInfo));
        table.addCell(createCellWithBodyLebelTextJustified("Ensure that the appropriate person(s), in addition to the patient, are present during instruction.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk2", elementType, generalInfo1));
        table.addCell(createCellWithBodyLebelTextJustified("Explain the patient's prescription.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk3", elementType, generalInfo2));
        table.addCell(createCellWithBodyLebelTextJustified("Advise that no one change the prescription without consulting with the patient's physician.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk4", elementType, generalInfo3));
        table.addCell(createCellWithBodyLebelTextJustified("Advise that no one should attempt to make repairs on the equipment.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk5", elementType, generalInfo4));
        table.addCell(createCellWithBodyLebelTextJustified("Explain the company service policy (after hours response, equipment malfunction, etc).", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk6", elementType, generalInfo5));
        table.addCell(createCellWithBodyLebelTextJustified("On-call phone number and contact information provided to patient and caregiver.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk7", elementType, generalInfo6));
        table.addCell(createCellWithBodyLebelTextJustified("Instruction/Warranty Booklet provided to patient/caregiver.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable3(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 1.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(5);

        //Prepare Checkbox data
        boolean safetyInfo = false;
        boolean safetyInfo1 = false;

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk8", elementType, safetyInfo));
        table.addCell(createCellWithBodyLebelTextJustified("Explain the importance of grounding all electrical equipment.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk9", elementType, safetyInfo1));
        table.addCell(createCellWithBodyLebelTextJustified("Explain the importance of never turning the unit on/off while oxygen is flowing through the circuit.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable4(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 1.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Checkbox data
        boolean operationalInfo = false;
        boolean operationalInfo1 = false;
        boolean operationalInfo2 = false;

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk10", elementType, operationalInfo));
        table.addCell(createCellWithBodyLebelTextJustified("Describe and identify all the components of the CPAP/BI-PAP unit.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk11", elementType, operationalInfo1));
        table.addCell(createCellWithBodyLebelTextJustified("Demonstrate the proper assembly of all components.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk12", elementType, operationalInfo2));
        table.addCell(createCellWithBodyLebelTextJustified("Explain operating instructions (check all that apply):", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable5(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 0.05f, 1.65f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        //Prepare Checkbox data
        boolean placementOfUnit = false;
        boolean delayFeature = false;
        boolean adjustmentAndApplicationOfNasalAppliance = false;
        boolean useOfHumidifier = false;
        boolean useOfSupplementalOxygen = false;
        boolean headgearAssemblingPositioningReplacementFrequency = false;
        boolean filtersChangingCleaningReplacementFrequency = false;
        boolean systemLeaksHowToCheckFor = false;

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk13", elementType, placementOfUnit));
        table.addCell(createCellWithBodyLebelTextJustified("Placement of unit", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk14", elementType, delayFeature));
        table.addCell(createCellWithBodyLebelTextJustified("Delay Feature", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk15", elementType, adjustmentAndApplicationOfNasalAppliance));
        table.addCell(createCellWithBodyLebelTextJustified("Adjustment and application of nasal appliance", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk16", elementType, useOfHumidifier));
        table.addCell(createCellWithBodyLebelTextJustified("Use of humidifier", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk17", elementType, useOfSupplementalOxygen));
        table.addCell(createCellWithBodyLebelTextJustified("Use of supplemental oxygen", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk18", elementType, headgearAssemblingPositioningReplacementFrequency));
        table.addCell(createCellWithBodyLebelTextJustified("Headgear: Assembling/Positioning/Replacement Frequency", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk19", elementType, filtersChangingCleaningReplacementFrequency));
        table.addCell(createCellWithBodyLebelTextJustified("Filters: Changing/Cleaning/Replacement Frequency", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyLabelNormalText(""));
        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk20", elementType, systemLeaksHowToCheckFor));
        table.addCell(createCellWithBodyLebelTextJustified("System Leaks - How to check for", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    public static PdfPTable createCheckBoxInTable6(PdfFormField radioGroup, String elementValue, String elementType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{0.05f, 1.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(5);

        //Prepare Checkbox data
        boolean operationalInfo3 = false;
        boolean operationalInfo4 = false;
        boolean operationalInfo5 = false;
        boolean operationalInfo6 = false;

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk21", elementType, operationalInfo3));
        table.addCell(createCellWithBodyLebelTextJustified("Explain that nasal appliance and circuit should be cleaned daily.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk22", elementType, operationalInfo4));
        table.addCell(createCellWithBodyLebelTextJustified("Explain cleaning procedures.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk23", elementType, operationalInfo5));
        table.addCell(createCellWithBodyLebelTextJustified("Explain and demonstrate filter maintenance procedures.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        table.addCell(createCellWithBodyCheckbox(radioGroup, "chk24", elementType, operationalInfo6));
        table.addCell(createCellWithBodyLebelTextJustified("Patient/caregiver was able to provide a return demonstration/explanation.", Element.ALIGN_JUSTIFIED, Font.NORMAL));

        return table;
    }

    private static PdfPCell createCellWithBodyLebelTextJustified(String text, int elementAlignement, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
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
