package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.IcdDetails;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationTableBuilder {
    public static PdfPTable createOrderConfirmationMainTitle(int pageno) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.98f });
        table.addCell(createTitleRowLargeText("Written Order Form", pageno));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainHeaderTitle() throws DocumentException, IOException{
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.98f });
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(20);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyTitle() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1.40f, 1.35f});
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder("PROVIDER"));
        table.addCell(createCellWithBodyLebelWithBorder("PATIENT"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyProviderPatientDetails(SWODataDTO swoDataDTO, CmnDocumentMasterDTO cmnDocumentMaster) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.35f, 1, 0.3f, 1 });
//        table.setWidths(new float[]{ 0.50f, 2, 0.80f, 1 });
        table.addCell(createCellWithBodyLebelText("Name:"));
//        table.setWidths(new float[]{ 0.50f, 1, 0.3f, 1 });
        if(swoDataDTO.getBilling_branch_name() != null)
            table.addCell(createCellWithBodyValueText(swoDataDTO.getBilling_branch_name().toUpperCase()));
        else
            table.addCell(createCellWithBodyValueText(" "));
        table.addCell(createCellWithBodyLebelText("Name:"));
        if(swoDataDTO.getPatient_middle_name() != null)
            table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase()+" "+swoDataDTO.getPatient_middle_name()+" "+swoDataDTO.getPatient_last_name().toUpperCase()));
        else
            table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase()+" "+swoDataDTO.getPatient_last_name().toUpperCase()));

        table.addCell(createCellWithBodyLebelText("Address:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_1()));
        table.addCell(createCellWithBodyLebelText("Address:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_address_line_1()));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_2()));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_address_line_2()));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_city()+" "+swoDataDTO.getBranch_state()+" "+swoDataDTO.getBranch_zip_code()));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getCity_name()+" "+swoDataDTO.getState_name()+" "+swoDataDTO.getZip_code()));

        table.addCell(createCellWithBodyLebelText("Phone:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_contact_no_1()));
        table.addCell(createCellWithBodyLebelText("Phone:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_contact_no_1()));

        table.addCell(createCellWithBodyLebelText("Fax:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_fax()));
        table.addCell(createCellWithBodyLebelText("DOB:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_dob().toString()));

        table.addCell(createCellWithBodyLebelText("DOC ID:"));
        table.addCell(createCellWithBodyValueText(cmnDocumentMaster.getCmnNo()));
        table.addCell(createCellWithBodyLebelText("Policy:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getPrimary_insurer_policy_no()));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyPhysicianOrderHeader() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 3f, 1.8f});
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder("PHYSICIAN"));
        table.addCell(createCellWithBodyLebelWithBorder("ORDER"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyPhysicianDetailsFirst(SWODataDTO swoDataDTO, CmnDTO cmnDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.60f, 3.1f, 1.5f, 0.7f });
        table.addCell(createCellWithBodyLebelText("Name:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_first_name()+" "+swoDataDTO.getOrdering_provider_middle_name()+" "+swoDataDTO.getOrdering_provider_last_name()));
        table.addCell(createCellWithBodyLebelText("Initial Date:"));
        table.addCell(createCellWithBodyValueText(""));

        table.addCell(createCellWithBodyLebelText("Address:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_1()));
        table.addCell(createCellWithBodyLebelText("Revised Date:"));
        table.addCell(createCellWithBodyValueText(""));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_2()));
        table.addCell(createCellWithBodyLebelText("Recertification Date:"));
        table.addCell(createCellWithBodyValueText(""));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_city()+","+swoDataDTO.getOrdering_provider_state()+" "+swoDataDTO.getOrdering_provider_zip()));
        table.addCell(createCellWithBodyLebelText("Length of Need:"));
        table.addCell(createCellWithBodyValueText(""));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createTitleRowSmallText("1-99 (99 = Lifetime)"));
        table.addCell(createCellWithBodyValueText(""));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyPhysicianDetailsSecond(SWODataDTO swoDataDTO, CmnDTO cmnDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.60f, 1.5f, 0.3f, 1.3f, 1.5f, 0.7f });
        table.addCell(createCellWithBodyLebelText("License:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_dea()));
        table.addCell(createCellWithBodyLebelText("NPI:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_npi()));
        table.addCell(createCellWithBodyLebelText("Prognosis:"));
        table.addCell(createCellWithBodyValueText(" "));

        table.addCell(createCellWithBodyLebelText("Phone:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_contact_no_1()));
        table.addCell(createCellWithBodyLebelText("Fax:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_fax()));
        table.addCell(createTitleRowSmallText("( 1 = Good, 2 = Fair, 3 = Poor)"));
        table.addCell(createCellWithBodyValueText(" "));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyDiagnosis() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1 });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder("DIAGNOSIS"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyDiagnosisHead() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.7f, 2.3f });
        table.addCell(createCellWithBodyLebelWithBorder("ICD-10 Code"));
        table.addCell(createCellWithBodyLebelWithBorder("Description"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyDiagnosisHeadData(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.7f, 2.3f });
        String code = "";
        String description = "";
//        code = getICDCodeOnDiagnosis(swoDataDTO);

        List<IcdDetails> icdDetailsList = getICDListOfCodes(swoDataDTO);
        System.out.println("=============>"+icdDetailsList.size());
        if(icdDetailsList.size() > 0){
            int x = 0;
            if(icdDetailsList.size() == 1)
                x = 4;
            if(icdDetailsList.size() == 2)
                x = 3;
            if(icdDetailsList.size() == 3)
                x = 2;
            if(icdDetailsList.size() == 4)
                x = 1;
            for(int i = 0; i <= x; i++) {
                IcdDetails icdDetails = new IcdDetails();
                icdDetails.setCode(" ");
                icdDetails.setDescription(" ");
                icdDetailsList.add(icdDetails);
            }
        }
        icdDetailsList.stream()
            .forEach(icd -> {
                table.addCell(createCellWithBodyValueText(icd.getCode()));
                table.addCell(createCellWithBodyValueTextWithJustified(icd.getDescription()));
            });
//        table.addCell(createCellWithBodyValueText(code));
//        table.addCell(createCellWithBodyValueText("Walker, rigid, wheeled, adjustable or fixed height"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainTitleEquipment() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1 });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder("EQUIPMENT/SERVICES"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainTitleEquipmentTitle() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 0.5f, 2.5f, 0.5f, 0.5f});
        table.addCell(createCellWithBodyLebelWithBorder("Qty"));
        table.addCell(createCellWithBodyLebelWithBorder("Proc. Code"));
        table.addCell(createCellWithBodyLebelWithBorder("Item Name/Narrative"));
        table.addCell(createCellWithBodyLebelWithBorder("Frequency"));
        table.addCell(createCellWithBodyLebelWithBorder("Intervals"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainTitleEquipmentData(List<EquipmentDetailsDTO> equipmentDetailsDTO, PdfWriter writer, int condition) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 0.5f, 2.5f, 0.5f, 0.5f});
        if(condition == 1) {
            var wrapper = new Object() {
                int ordinal = 1;
            };
            equipmentDetailsDTO.stream()
                .filter(value -> wrapper.ordinal <= 5)
                .forEach(equipment -> {
                    String freqCount = equipment.getFrequency_count()!=null && equipment.getFrequency_count()!=0 ? String.valueOf(equipment.getFrequency_count()) : " ";
                    String freqInterval = equipment.getFrequency_interval()!=null && equipment.getFrequency_count()!=0 ? String.valueOf(equipment.getFrequency_interval())+" month(s)" : " ";
                    table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_qty().toString()));
                    table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_proc_code()));
                    table.addCell(createCellWithBodyValueTextWithJustified(equipment.getSales_order_details_item_name()));
                    table.addCell(createCellWithBodyValueText(freqCount));
                    table.addCell(createCellWithBodyValueText(freqInterval));
                    wrapper.ordinal++;
                    System.out.println("wrapper.ordinal=1===>" + wrapper.ordinal);
                });
        }
        else{;
            equipmentDetailsDTO.stream()
                .skip(equipmentDetailsDTO.size() - 5)
                .forEach(equipment -> {
                    String freqCount = equipment.getFrequency_count()!=null && equipment.getFrequency_count()!=0 ? String.valueOf(equipment.getFrequency_count()) : " ";
                    String freqInterval = equipment.getFrequency_interval()!=null && equipment.getFrequency_count()!=0 ? String.valueOf(equipment.getFrequency_interval()) : " ";
                    table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_qty().toString()));
                    table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_proc_code()));
                    table.addCell(createCellWithBodyValueTextWithJustified(equipment.getSales_order_details_item_name()));
                    table.addCell(createCellWithBodyValueText(freqCount));
                    table.addCell(createCellWithBodyValueText(freqInterval));
                });
        }
        return table;
    }

    public static PdfPTable createOrderConfirmationMainWofHeader() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.35f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder(" "));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainTitleSignature(Document document, PdfWriter writer, SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.20f, 0.80f, 0.10f, 0.65f});
        table.addCell(createCellWithBodyLebelText("Signature:"));
        table.addCell(createCellWithBodyLebelWithBorder(" "));
        table.addCell(createCellWithBodyLebelText("Date:"));
        table.addCell(createCellWithBodyLebelWithBorder(" "));
        table.addCell(createCellWithBodyLebelText(" "));
        if(swoDataDTO.getBilling_branch_name() != null)
            table.addCell(createCellWithBodyLebelText(swoDataDTO.getBilling_branch_name().toUpperCase()));
        else
            table.addCell(createCellWithBodyLebelText(" "));
        table.addCell(createCellWithBodyLebelText(" "));
        table.addCell(createCellWithBodyLebelText(" "));
        final int FIRST_ROW = 0;
        final int LAST_ROW = -1;
        if(table.getTotalWidth() == 0) {
            table.setTotalWidth((document.right() - document.left()) * table.getWidthPercentage() / 100f);
        }
        table.writeSelectedRows(FIRST_ROW, LAST_ROW, document.left(), (document.bottom() + 50), writer.getDirectContent());
        return table;
    }

    public static PdfPTable createOrderConfirmationMainAdditionalNoteHeader() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.35f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelWithBorder("ADDL. NOTE"));
        return table;
    }

    public static PdfPTable createOrderConfirmationMainBodyProviderName(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.35f, 1, 0.3f, 1 });
        table.addCell(createCellWithBodyLebelText("Name:"));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBilling_branch_name().toUpperCase()));
        return table;
    }

    public static PdfPTable createOrderConfirmationSecondBodyProviderDetails(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createCellWithBodyLebelText(swoDataDTO.getBilling_branch_name().toUpperCase()));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_1()));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_2()));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_city()+" "+swoDataDTO.getBranch_state()+" "+swoDataDTO.getBranch_zip_code()));
        table.addCell(createCellWithBodyValueText("Phone: "+swoDataDTO.getBranch_contact_no_1()));
        if(swoDataDTO.getBranch_fax() != null)
            table.addCell(createCellWithBodyValueText("Fax: "+swoDataDTO.getBranch_fax()));
        else
            table.addCell(createCellWithBodyValueText("Fax: "));
        return table;
    }

    public static PdfPTable createOrderConfirmationSecondBodyDatePatientName(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.20f, 0.80f });
        table.setSpacingBefore(20);
        table.addCell(createCellWithBodyLebelText("Date:"));
        table.addCell(createCellWithBodyValueText("1/24/2023 12:00:00 AM"));
        table.addCell(createCellWithBodyLebelText("Patient:"));
        if(swoDataDTO.getPatient_middle_name() != null)
            table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase()+" "+swoDataDTO.getPatient_middle_name()+" "+swoDataDTO.getPatient_last_name().toUpperCase()));
        else
            table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase()+" "+swoDataDTO.getPatient_last_name().toUpperCase()));
        return table;
    }

    public static PdfPTable createOrderConfirmationSecondBodyPhysicianDetails(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 1f });
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_first_name()+" "+swoDataDTO.getOrdering_provider_middle_name()+" "+swoDataDTO.getOrdering_provider_last_name()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_1()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_2()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_city()+","+swoDataDTO.getOrdering_provider_state()+" "+swoDataDTO.getOrdering_provider_zip()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText("Phone: "+swoDataDTO.getOrdering_provider_contact_no_1()));
        table.addCell(createCellWithBodyValueText(""));
        if(swoDataDTO.getOrdering_provider_fax() != null)
            table.addCell(createCellWithBodyValueText("Fax: "+swoDataDTO.getOrdering_provider_fax()));
        else
            table.addCell(createCellWithBodyValueText("Fax: "));

        return table;
    }

    public static PdfPTable createOrderConfirmationSecondTitleEquipmentData(List<EquipmentDetailsDTO> equipmentDetailsDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 3f});
        equipmentDetailsDTO.stream().forEach(equipment -> {
            table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_proc_code()));
            table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_item_name()));
        });
        return table;
    }

    public static PdfPTable createOrderConfirmationSecondTitleInformationAnswers(SWODataDTO swoDataDTO) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1.8f, 1.2f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyValueTextWithTopBottomBorder("Information Provided by the Physician's Office"));
        table.addCell(createCellWithBodyValueTextWithTopBottomBorder("Answers"));

        table.addCell(createCellWithBodyValueText("Diagnosis of Patient?"));
        table.addCell(createCellWithBodyValueBoldText("ICD-10 Code     Desc"));

        String code = "";
        String description = "";
        code = getICDCodeOnDiagnosis(swoDataDTO);
        table.addCell(createCellWithBodyValueBoldText(""));
        table.addCell(createCellWithBodyValueBoldText(code+"     "+"Other acute paralytic poliomyelitis"));

        table.addCell(createCellWithBodyValueBoldText(" "));
        table.addCell(createCellWithBodyValueBoldText(" "));

        table.addCell(createCellWithBodyValueTextWithBottomBorder(" "));
        table.addCell(createCellWithBodyValueTextWithBottomBorder(" "));

        table.addCell(createCellWithBodyValueTextWithTopBottomBorder("Estimated Length of Need? 1-99 (99 = Lifetime)"));
        table.addCell(createCellWithBodyValueTextWithTopBottomBorder("99"));

        table.addCell(createCellWithBodyValueTextWithTopBottomBorder("Prognosis of Patient ( 1 = Good, 2 = Fair, 3 = Poor)"));
        table.addCell(createCellWithBodyValueTextWithTopBottomBorder(""));

        return table;
    }

    private static PdfPCell createCellWithBodyValueText(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueTextWithJustified(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithJustified(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueBoldText(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelText(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelWithBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleBorder(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static PdfPCell createTitleRowLargeText(String text, int pageno)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        if(pageno == 1)
            titleRowTextRightLargeStyle(cell);
        if(pageno == 2)
            titleRowTextCenterLargeStyle(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static PdfPCell createBodyRowLargeText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLargeStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowPeriodText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextLargeAddressStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSmallText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSmallOfficeText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallOfficeStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSubTitle1Text(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle1Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createSubTitleRowCenterText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallCenterStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSubTitle2Text(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle2Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSubTitle3Text(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle3Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBottomBorderText(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle3Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new CustomDottedCell(PdfPCell.BOTTOM));
        return cell;
    }

    private static PdfPCell createCellWithBottomBorderTextForNegativeArrearAdjustment(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.RED);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle3Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setCellEvent(new CustomDottedCell(PdfPCell.BOTTOM));
        return cell;
    }

    private static PdfPCell createTitleRowSubTitle4Text(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle4Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createTitleRowSubTitle5Text(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        return cell;
    }

    public static PdfPCell createTitleRowSmallTextBoldUnderline(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallBoldUnderlineStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static void titleRowTextSmallBoldUnderlineStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(10f);
        cell.setPaddingBottom(4f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextLargeStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(205f);
        cell.setPaddingBottom(0f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.5f);
    }

    private static void titleRowTextRightLargeStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(205f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.55f);
    }

    private static void titleRowTextCenterLargeStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(5f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.55f);
    }

    private static void titleRowTextLargeAddressStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(236f);
        cell.setPaddingBottom(0f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderWidthBottom(0.5f);
    }

    private static void titleRowTextSmallStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallOfficeStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(320f);
        cell.setPaddingBottom(1f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallSubTitle1Style(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(10f);
        cell.setPaddingLeft(5f);
        cell.setPaddingBottom(1f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallTopSolidBottomDottedBorderStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(5f);
        cell.setPaddingBottom(4f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setCellEvent(new CustomDottedCell(PdfPCell.BOTTOM));
        cell.setBorderColorBottom(BaseColor.BLACK);
    }

    private static void titleRowTextSmallCenterStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(10f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(1f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallSubTitle2Style(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(1f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallSubTitle3Style(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(1f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
    }

    private static void titleRowTextSmallSubTitle4Style(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(2f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
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

    private static void titleRowTextSmallSubTitle5StyleWithJustified(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static void titleRowTextSmallSubTitle5StyleBorder(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(2);
        cell.setBorderWidthBottom(0.5f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyValueTextWithBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithBorder(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueTextWithTableBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithBorder(cell);
        cell.setBorder(PdfPCell.RECTANGLE);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueTextWithTopBottomBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithBorder(cell);
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueTextWithBottomBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithBorder(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueTextWithBottomRightBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleWithBorder(cell);
        cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5StyleWithBorder(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(2);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static String getICDCodeOnDiagnosis(SWODataDTO swoDataDTO){
        String code = "";
        if(swoDataDTO.getPrimary_diagnosis() != null){
            if(swoDataDTO.getPrimary_diagnosis().equals("1")) {
                System.out.println("ICD Code=====>"+swoDataDTO.getIcd_10_diagnosis_code_1());
                code = swoDataDTO.getIcd_10_diagnosis_code_1();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("2")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_2();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("3")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_3();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("4")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_4();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("5")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_5();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("6")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_6();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("7")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_7();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("8")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_8();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("9")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_9();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("10")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_10();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("11")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_11();
            }
            else if(swoDataDTO.getPrimary_diagnosis().equals("12")) {
                code = swoDataDTO.getIcd_10_diagnosis_code_12();
            }
        }
        return code;
    }

    private static List<IcdDetails> getICDListOfCodes(SWODataDTO swoDataDTO){
        List<IcdDetails> icdDetailsList  = new ArrayList<>();
        IcdDetails icdDetails = null;
        if(swoDataDTO.getIcd_10_diagnosis_code_1() != null) {
            icdDetails = new IcdDetails();
//            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_1().substring(0, swoDataDTO.getIcd_10_diagnosis_code_1().indexOf("||")));
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_1());
//            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_1().substring(swoDataDTO.getIcd_10_diagnosis_code_1().lastIndexOf("||") + 2));
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_1());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_2() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_2());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_2());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_3() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_3());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_3());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_4() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_4());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_4());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_5() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_5());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_5());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_6() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_6());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_6());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_7() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_7());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_7());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_8() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_8());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_8());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_9() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_9());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_9());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_10() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_10());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_10());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_11() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_11());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_11());
            icdDetailsList.add(icdDetails);
        }
        if(swoDataDTO.getIcd_10_diagnosis_code_12() != null) {
            icdDetails = new IcdDetails();
            icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_12());
            icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_12());
            icdDetailsList.add(icdDetails);
        }
        return icdDetailsList;
    }

    public static void addQrCodeOnFooter(Long salesOrderId, PdfWriter writer){
        String img = ApplicationConstants.TEMP_QRFILE_PATH + "/" + salesOrderId + ".png";
        Image image;
        try{
            image = Image.getInstance(img);
            image.setAlignment(Element.ALIGN_LEFT);
            image.setAbsolutePosition(30, 10);
            image.scalePercent(100f, 100f);
            writer.getDirectContent().addImage(image, true);
        }
        catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
