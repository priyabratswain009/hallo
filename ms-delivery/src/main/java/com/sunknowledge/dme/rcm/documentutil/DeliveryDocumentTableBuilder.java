package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.service.dto.delivery.CommonDeliveryInputData;
import com.sunknowledge.dme.rcm.service.dto.delivery.CommonDocumentResponse;
import org.apache.commons.text.CaseUtils;

import java.io.File;
import java.util.List;

public class DeliveryDocumentTableBuilder {
    public static PdfPTable createDeliveryDocumentMainBodyTitle() throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.98f });
        table.addCell(createTitleRowLargeText("DELIVERY RECEIPT"));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyAddressDetails(DeliveryTicket deliveryTicket) throws DocumentException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 0.3f, 0.7f });
        table.addCell(createCellWithBodyLebelText("Care Medical"));
        table.addCell(createCellWithBodyLebelText("Branch"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getBranchName()));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getBranchAddressLine1()+" "+deliveryTicket.getBranchAddressLine2()));
        table.addCell(createCellWithBodyLebelText("Inv Location"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getInventoryLocationName()));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getBranchCity()+","+deliveryTicket.getBranchState()+","+deliveryTicket.getBranchZipCode()));
        table.addCell(createCellWithBodyLebelText("Date"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getBranchContactNo1()));
        table.addCell(createCellWithBodyLebelText("CSR"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Order #"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Patient ID"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getPatientIdNo()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Customer #"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Account #"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Age"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getAgeAsOnDate().toString()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyLebelText("Gender"));
        if(deliveryTicket.getGender().equalsIgnoreCase("M"))
            table.addCell(createCellWithBodyValueText("Male"));
        else if(deliveryTicket.getGender().equalsIgnoreCase("F"))
            table.addCell(createCellWithBodyValueText("Female"));
        else
            table.addCell(createCellWithBodyValueText("Other"));
        return table;
    }

    public static PdfPTable createDeliveryDocumentBillToDeliverToAddress(DeliveryTicket deliveryTicket) throws DocumentException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.25f, 0.65f, 0.45f, 0.65f });
        table.addCell(createCellWithBodyLebelText("Bill To"));
        String billingAddressLine1 = deliveryTicket.getBillingAddressLine1() != null ? deliveryTicket.getBillingAddressLine1() : "";
        String billingAddressLine2 = deliveryTicket.getBillingAddressLine2() != null ? deliveryTicket.getBillingAddressLine2() : "";
        String billingCity = deliveryTicket.getBillingCity()!= null ? deliveryTicket.getBillingCity() : "";
        String billingState = deliveryTicket.getBillingState()!= null ? deliveryTicket.getBillingState() : "";
        String billingZip = deliveryTicket.getBillingZip()!= null ? deliveryTicket.getBillingZip() : "";
        String billingCityStateZip = null;
        if(!billingCity.equals("")){
            billingCityStateZip = billingCity;
        }
        if(!billingState.equals("")){
            billingCityStateZip = billingCityStateZip+", "+billingState;
        }
        String deliveryAddressLine1 = deliveryTicket.getDeliveryAddress1()!= null ? deliveryTicket.getDeliveryAddress1() : "";
        String deliveryAddressLine2 = deliveryTicket.getDeliveryAddress2()!= null ? deliveryTicket.getDeliveryAddress2() : "";
        String deliveryCity = deliveryTicket.getDeliveryCity()!= null ? deliveryTicket.getDeliveryCity() : "";
        String deliveryState = deliveryTicket.getDeliveryState()!= null ? deliveryTicket.getDeliveryState() : "";
        String deliveryZip = deliveryTicket.getDeliveryZip()!= null ? deliveryTicket.getDeliveryZip() : "";
        String deliveryCityStateZip = null;
        if(!deliveryCity.equals("")){
            deliveryCityStateZip = deliveryCity;
        }
        if(!deliveryState.equals("")){
            deliveryCityStateZip = deliveryCityStateZip+", "+deliveryState;
        }
        if(!deliveryZip.equals("")){
            deliveryCityStateZip = deliveryCityStateZip+", "+deliveryZip;
        }

        table.addCell(createCellWithBodyValueText(billingAddressLine1));
        table.addCell(createCellWithBodyLebelText("Shipped/Delivered To"));
        table.addCell(createCellWithBodyValueText(deliveryAddressLine1));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(billingAddressLine2));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(deliveryAddressLine2));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(billingCityStateZip));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(deliveryCityStateZip));

        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(billingZip));
        table.addCell(createCellWithBodyLebelText("Phone"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getPhone1()));
        return table;
    }

    public static PdfPTable createDeliveryDocumentInsuranceHIPAAFileInfo(DeliveryTicket deliveryTicket) throws DocumentException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 0.7f, 0.5f, 0.5f });
        table.addCell(createCellWithBodyLebelText("Insurance"));
        table.addCell(createCellWithBodyValueText(deliveryTicket.getPrimaryInsurerName()));
        table.addCell(createCellWithBodyLebelText("HIPAA Signature on file"));
        table.addCell(createCellWithBodyValueText("No"));
        return table;
    }

    public static PdfPTable createDeliveryDocumentProductDetailInfo(List<DeliveryItems> deliveryItemsList) throws DocumentException
    {
        System.out.println("========================%%%%%%%%%%%%%%%%%%=============================");
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.2f, 0.2f, 0.3f, 1f, 0.4f, 0.4f, 0.4f, 0.4f });
        table.getDefaultCell().setBorder(Rectangle.BOX);
        table.addCell(createCellWithBodyLebelBorderedText("Ord Qty"));
        table.addCell(createCellWithBodyLebelBorderedText("Del Qty"));
        table.addCell(createCellWithBodyLebelBorderedText("Type"));
        table.addCell(createCellWithBodyLebelBorderedText("Item"));
        table.addCell(createCellWithBodyLebelBorderedText("Ext. Allow"));
        table.addCell(createCellWithBodyLebelBorderedText("Ext. Amt."));
        table.addCell(createCellWithBodyLebelBorderedText("Tax"));
        table.addCell(createCellWithBodyLebelBorderedText("Co-Pay"));
        deliveryItemsList.stream().forEach(product -> {
            table.addCell(createCellWithBodyValueBorderedText(product.getItemQuantity().toString()));
            table.addCell(createCellWithBodyValueBorderedText(product.getItemQuantity().toString()));
            table.addCell(createCellWithBodyValueBorderedText(product.getSoSaleType()));
            table.addCell(createCellWithBodyValueBorderedText(product.getItemName()));
            if(product.getAllowedAmount() == null)
                table.addCell(createCellWithBodyValueBorderedText("$0.00"));
            else
                table.addCell(createCellWithBodyValueBorderedText("$"+product.getAllowedAmount()));
            if(product.getChargedAmount() == null)
                table.addCell(createCellWithBodyValueBorderedText("$0.00"));
            else
                table.addCell(createCellWithBodyValueBorderedText("$"+product.getChargedAmount()));
            table.addCell(createCellWithBodyValueBorderedText("$0.00"));
            table.addCell(createCellWithBodyValueBorderedText("$0.00"));
        });
        return table;
    }

    public static PdfPTable createDeliveryDocumentSignatureDate(File patientSignatureFile, File techSignatureFile, String date) throws Exception {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 2f, 1.5f, 1.5f});
        String patientRepresentativeSignature = patientSignatureFile.getAbsolutePath();
        table.addCell(createCellWithAddSignatureImage(patientRepresentativeSignature));
        String techSignature = techSignatureFile.getAbsolutePath();
        table.addCell(createCellWithBodyLebelTextBottomAlign(date));
        table.addCell(createCellWithAddSignatureImage(techSignature));
        table.addCell("");
        return table;
    }

    public static PdfPTable createDeliveryDocumentSignatureDateHeader() throws DocumentException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 2f, 1.5f, 1.5f});
        table.addCell(createCellWithBodyLebelText("Signature of Patient or Authorized Representative"));
        table.addCell(createCellWithBodyLebelTextBottomAlign("Date"));
        table.addCell(createCellWithBodyLebelText("Tech Signature"));
        return table;
    }

    public static PdfPTable createDeliveryDocumentSignatureDateDetails(DeliveryTicket deliveryTicket) throws DocumentException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 2f, 1.5f, 1.5f});
        table.addCell(createCellWithBodyValueText(CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' ')));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(CaseUtils.toCamelCase(deliveryTicket.getOrderingProviderFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getOrderingProviderLastName(), true, ' ')));
        return table;
    }

    public static PdfPTable createDeliveryDocumentPatientInformation(CommonDeliveryInputData commonDeliveryInputData) throws DocumentException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 1.5f});
        table.addCell(createCellWithBodyLebelText("Relationship to Patient:"));
        table.addCell(createCellWithBodyValueText(commonDeliveryInputData.getPatientRelationship()));
        table.addCell(createCellWithBodyLebelText("Reason patient could not sign:"));
        table.addCell(createCellWithBodyValueText(commonDeliveryInputData.getReasonNotToSign()));
        return table;
    }

    //==========================================================================================================================================================
    public static PdfPTable createPatientRightsResponsibilityMainBodyTitle(DeliveryTicket deliveryTicket) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createTitleRowLargeBodyText(deliveryTicket.getBranchName()));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilityMainHeading(String text) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createTitleRowMediumText(text));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilitySubHeading(String text) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createTitleRowSmallText(text));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilitySubContents() throws DocumentException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.03f, 1f });
        table.addCell(createCellWithBodyTableText(" 1."));
        table.addCell(createCellWithBodyTableText("To select those who provide your home care services."));
        table.addCell(createCellWithBodyTableText(" 2."));
        table.addCell(createCellWithBodyTableText("To be provided with legitimate identification by any person or persons who enter your residence to provide home care services for you."));
        table.addCell(createCellWithBodyTableText(" 3."));
        table.addCell(createCellWithBodyTableText("To receive the appropriate or prescribed service in a professional manner without discrimination relative to your age, race, sex, religion, ethnic origin, sexual" +
            "preference or physical/mental handicap."));
        table.addCell(createCellWithBodyTableText(" 4."));
        table.addCell(createCellWithBodyTableText("To be dealt with and treated with friendliness, courtesy and respect by each and every individual representing the company who provides treatment or services for" +
            "you"));
        table.addCell(createCellWithBodyTableText(" 5."));
        table.addCell(createCellWithBodyTableText("To assist in the development and planning of your home care program so that it is designed to satisfy, as best as possible to your current needs"));
        table.addCell(createCellWithBodyTableText(" 6."));
        table.addCell(createCellWithBodyTableText("To be provided with adequate information from which you can give your informed consent for the commencement of service, the continuation of service, the transfer" +
            "of service to another home care provider, or the termination of service."));
        table.addCell(createCellWithBodyTableText(" 7."));
        table.addCell(createCellWithBodyTableText("To express concerns or grievances or recommend modifications to your home care services without fear of discrimination or reprisal. The Medicare hotline number" +
            "is 1-866-238-9650"));
        table.addCell(createCellWithBodyTableText(" 8."));
        table.addCell(createCellWithBodyTableText("To request and receive complete and up-to-date information relative to your condition, treatment, alternative treatments and risks of treatment."));
        table.addCell(createCellWithBodyTableText(" 9."));
        table.addCell(createCellWithBodyTableText("To receive treatment and services within the scope of your home care plan, promptly and professionally, while being fully informed as to company policies," +
            "procedures and charges."));
        table.addCell(createCellWithBodyTableText(" 10."));
        table.addCell(createCellWithBodyTableText("To refuse treatment and services within the boundaries set by law, and to receive professional information relative to the ramifications or consequences that will or" +
            "may result due to such refusal."));
        table.addCell(createCellWithBodyTableText(" 11."));
        table.addCell(createCellWithBodyTableText("To request and receive the opportunity to examine or review your medical records."));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilitySubContentsSecond() throws DocumentException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.03f, 1f });
        table.addCell(createCellWithBodyTableText(" 1."));
        table.addCell(createCellWithBodyTableText("To provide accurate and complete information and notify Punxsy/Rezk Medical Supply of any changes in status, including medical, change of address or insurance."));
        table.addCell(createCellWithBodyTableText(" 2."));
        table.addCell(createCellWithBodyTableText("To advise Punxsy/Rezk Medical Supply of any changes inphone number, address, physician, insurance company or payor source."));
        table.addCell(createCellWithBodyTableText(" 3."));
        table.addCell(createCellWithBodyTableText("To comply with Physician's prescribed treatment and be responsible for the outcomes if they do not follow the prescribed treatment."));
        table.addCell(createCellWithBodyTableText(" 4."));
        table.addCell(createCellWithBodyTableText("To make known whether you understand the products and services provided and what you are expected to do."));
        table.addCell(createCellWithBodyTableText(" 5."));
        table.addCell(createCellWithBodyTableText("To comply with the service plan and to communicate any change in the physician's order."));
        table.addCell(createCellWithBodyTableText(" 6."));
        table.addCell(createCellWithBodyTableText("To plan to any emergencies that may occur in the home."));
        table.addCell(createCellWithBodyTableText(" 7."));
        table.addCell(createCellWithBodyTableText("To respect the rights, professional integrity and dignity of those providing your care"));
        table.addCell(createCellWithBodyTableText(" 8."));
        table.addCell(createCellWithBodyTableText(" To notify our staff if you wish to cancel services or change a scheduled visit."));
        table.addCell(createCellWithBodyTableText(" 9."));
        table.addCell(createCellWithBodyTableText("To follow any instructions, rules and regulations as provided by Punxzsy\\Rezk Medical Supply."));
        table.addCell(createCellWithBodyTableText(" 10."));
        table.addCell(createCellWithBodyTableText("To properly store, clean and maintain your equipment as recommended by the manufacturer."));
        table.addCell(createCellWithBodyTableText(" 11."));
        table.addCell(createCellWithBodyTableText("To contact Punxsy\\Rezk Medical Supply when equipment is not working properly and to allow Punxsy\\Rezk Medical Supply staff access to equipment for repair and" +
            "maintenance."));
        table.addCell(createCellWithBodyTableText(" 12."));
        table.addCell(createCellWithBodyTableText("To meet the financial obligations agreed to with Punxsy\\Rezk Medical Supply."));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilityPatientHeading(String text) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.6f });
        table.addCell(createTitleRowMediumText(text));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilityPatientBody(CommonDocumentResponse commonDocumentResponse) throws Exception {
        File patientSignatureFile = commonDocumentResponse.getPatientSignatureFile();
        CommonDeliveryInputData commonDeliveryPatientData =commonDocumentResponse.getCommonDeliveryInputData();

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.6f, 1f});
        table.addCell(createCellWithBodyTableTextSignature("Signature of Patient or Patient's Representative:"));
        table.addCell(createCellWithAddSignatureImage(patientSignatureFile.getAbsolutePath()));
        table.addCell(createCellWithBodyTableText("Printed Name of Patient Representative:"));
        table.addCell(createCellWithBodyTableText(commonDeliveryPatientData.getPatientRepresentativeName()));
        table.addCell(createCellWithBodyTableText("If not \"Self,\" reason patient could not sign:"));
        table.addCell(createCellWithBodyTableText(commonDeliveryPatientData.getReasonNotToSign()));
        table.addCell(createCellWithBodyTableText("Date:"));
        table.addCell(createCellWithBodyTableText(commonDeliveryPatientData.getDate()));
        return table;
    }

    private static PdfPCell createCellWithBodyTableTextSignature(String text){
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        titleRowTextSmallSubTitleTableContentStyleSignature(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallSubTitleTableContentStyleSignature(PdfPCell cell){
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(19f);
        cell.setPaddingLeft(1f);
        //cell.setPaddingBottom(20f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        //cell.setPadding(0f);
        //cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithAddSignatureImage(String imageFile) throws Exception
    {
        Image image = Image.getInstance(imageFile);

        float maxWidth = 120f; // Adjust the maximum width as needed
        float maxHeight = 50f; // Adjust the maximum height as needed
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();
        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            float widthRatio = maxWidth / imageWidth;
            float heightRatio = maxHeight / imageHeight;
            float scaleRatio = Math.min(widthRatio, heightRatio);
            image.scaleToFit(imageWidth * scaleRatio, imageHeight * scaleRatio);
        }
        //image.setAlignment(Image.LEFT | Image.TEXTWRAP);
        image.setAlignment(Element.ALIGN_LEFT);
        //image.setAbsolutePosition(20, 10);
        //image.scalePercent(100f, 30f);
        PdfPCell cell = new PdfPCell(image, false);
        cell.setPadding(3f);
        //cell.setPadding(0);
        //cell.setFixedHeight(30);
        cell.setBorder(0);

        return cell;
    }

    public static PdfPTable createPatientRightsResponsibilityCompanyHeading(String text) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.5f });
        table.addCell(createTitleRowMediumText(text));
        return table;
    }

    public static PdfPTable createPatientRightsResponsibilityCompanyBody(CommonDocumentResponse commonDocumentResponse) throws Exception {
        File techSignatureFile = commonDocumentResponse.getTechSignatureFile();
        CommonDeliveryInputData commonDeliveryPatientData =commonDocumentResponse.getCommonDeliveryInputData();

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.6f, 1f});
        table.addCell(createCellWithBodyTableTextSignature("Signature:"));
        table.addCell(createCellWithAddSignatureImage(techSignatureFile.getAbsolutePath()));
        table.addCell(createCellWithBodyTableText("Name:"));
        table.addCell(createCellWithBodyTableText(commonDeliveryPatientData.getCompanyRepresentativeName()));
        return table;
    }

    private static PdfPCell createCellWithBodyTableText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        cell.addElement(p);
        titleRowTextSmallSubTitleTableContentStyle(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallSubTitleTableContentStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setPadding(0f);
//        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createTitleRowLargeBodyText(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(0);
//        titleRowTextLargeBodyStyle(cell);
        return cell;
    }

    private static PdfPCell createTitleRowLargeText(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextRightLargeStyle(cell);
        cell.setBorder(PdfPCell.BOTTOM);
        return cell;
    }

    private static PdfPCell createTitleRowMediumText(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(0);
        return cell;
    }

    private static PdfPCell createTitleRowSmallText(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(0);
//        titleRowTextLeftMediumStyle(cell);
        return cell;
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

    private static PdfPCell createCellWithBodyLebelTextBottomAlign(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5StyleBottomAlign(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setMinimumHeight(1);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        return cell;
    }
    private static void titleRowTextSmallSubTitle5StyleBottomAlign(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
        cell.setPaddingTop(0f);
        cell.setPaddingLeft(1f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    private static PdfPCell createCellWithBodyLebelText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
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
    private static PdfPCell createCellWithBodyValueText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelBorderedText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5BorderedStyle(cell);
        return cell;
    }

    private static PdfPCell createCellWithBodyValueBorderedText(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5BorderedStyle(cell);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5BorderedStyle(PdfPCell cell)
    {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(1);
        cell.setUseBorderPadding(true);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }

    public static void populateDataInPatientRightsResponsibilityDocs(Document document, DeliveryTicket deliveryTicket) throws DocumentException
    {
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityMainBodyTitle(deliveryTicket));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityMainHeading("Patient Rights & Responsibilities"));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilitySubHeading("As an individual receiving home care services, let it be known that you have the following rights:\n"));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilitySubContents());
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilitySubHeading("As an individual receiving home care services, let it be known that you have the following responsibilities:"));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilitySubContentsSecond());
    }

    public static void populateDataInDeliveryDocumentDocs(Document document) throws DocumentException {
        Chunk chunk1 = new Chunk("ASSIGNMENT OF BENEFITS AND RELEASE OF AUTHORIZATION");
        Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.BLACK);
        Paragraph paragraph1 = new Paragraph(String.valueOf(chunk1), font1);
        paragraph1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph1);

        Chunk chunk2 = new Chunk("I request payment under my medical insurance to be made directly to the above named company; in the event my medical insurance" +
            "does not make payment, I agree to be held personally liable for all charges. I authorize my medical providers to release any" +
            "information necessary to determine: services, benefits and payment on my behalf. I permit a copy of this authorization to be used in" +
            "place of the original. I permit the review of my record by accrediting and licensing agents and/or for the purpose of quality control.");
        Font font2 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Paragraph paragraph2 = new Paragraph(String.valueOf(chunk2), font2);
        paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph2);
        document.add(new Paragraph("\n"));

        Chunk chunk3 = new Chunk("CUSTOMER AGREEMENT");
        Font font3 = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.BLACK);
        Paragraph paragraph3 = new Paragraph(String.valueOf(chunk3), font3);
        paragraph3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph3);

        Chunk chunk4 = new Chunk("I have received the products listed above in good condition and proper working order. Thorough training has been provided by the" +
            "delivery technician to include; proper and safe use of the equipment, as well as, safe storage and environmental precautions. I agree" +
            "to receive e-mail communications, including but not limited to, receipts and copies of my signed paperwork. I have read and agree to" +
            "each and all of the terms and conditions. I consent to receive services from the above named company.");
        Font font4 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Paragraph paragraph4 = new Paragraph(String.valueOf(chunk4), font4);
        paragraph4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph4);
        document.add(new Paragraph("\n"));

        Chunk chunk5 = new Chunk("THIS DELIVERY TICKET/RENTAL AGREEMENT HAS BEEN COLLATERALLY ASSIGNED TO CIT BANK, N.A. AND ITS SUCCESSORS AND" +
            "ASSIGNS. THE FOREGOING SENTENCE SHALL CONSTITUTE NOTICE OF ASSIGNMENT PURSUANT TO SECTION 9-330(f) OF THE" +
            "UNIFORM COMMERCIAL CODE. ANY SALE, TRANSFER, ASSIGNMENT, CONVEYANCE, PLEDGE, GRANT OF A SECURITY INTEREST IN," +
            "PURCHASE, OR OTHER DISPOSITION OF THIS INSTRUMENT TO, BY, OR IN FAVOR OF ANY PARTY OTHER THAN CIT BANK, N.A., AS" +
            "AGENT, SHALL VIOLATE THE RIGHTS OF CIT BANK, N.A., AS AGENT.");
        Font font5 = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.BLACK);
        Paragraph paragraph5 = new Paragraph(String.valueOf(chunk5), font5);
        paragraph5.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph5);
    }
}
