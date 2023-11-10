package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeItem;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeReqandResp;

import java.io.IOException;
import java.util.List;

public class PickupExchangeDocumentTableBuilder {
    public static PdfPTable createDeliveryDocumentPickUpExchangeComments(String comment, int fontType) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f});
        table.addCell(createCellWithBodyLebelComments(comment, fontType));
        table.setSpacingAfter(3);
        return table;
    }

    private static PdfPCell createCellWithBodyLebelComments(String text, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    public static PdfPTable createDeliveryDocumentDynamicRowTableBodyExchange(PickupExchangeReqandResp pickupExchangeReqandResp) throws Exception {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.1f, 0.18f, 0.82f, 0.3f, 0.15f, 0.15f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxPickUpExchange("QTY", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("TYPE", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("ITEM", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("Remarks", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("INVOICE", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("BILLED", "BLACK", Font.BOLD, Element.ALIGN_CENTER));

        List<PickupExchangeItem> pickupExchangeItems = pickupExchangeReqandResp.getPickupExchangeItems();
        for(int i = 1; i <= pickupExchangeItems.size(); i++){
            String quantity = String.valueOf((Math.round(pickupExchangeItems.get(i-1).getQuantity())));
            String item = pickupExchangeItems.get(i-1).getItemNo() + " " +pickupExchangeItems.get(i-1).getItemName()+" "+pickupExchangeItems.get(i-1).getPickupItemSerialNo()+" "+pickupExchangeItems.get(i-1).getPickupItemAssetNo()+" (Picked Up)";
            String exchangedItems = i+". "+pickupExchangeItems.get(i-1).getItemNo() + " " +pickupExchangeItems.get(i-1).getItemName()+" "+pickupExchangeItems.get(i-1).getReplacementItemSerialNo()+" (Delivered)";
            String itemPickupExchangeComment = pickupExchangeItems.get(i-1).getItemPickupExchangeComment()!= null ? pickupExchangeItems.get(i-1).getItemPickupExchangeComment() : "";

            table.addCell(createCellWithBodyInputBoxPickUpExchange(quantity, "BLACK", Font.NORMAL, Element.ALIGN_CENTER));
            table.addCell(createCellWithBodyInputBoxPickUpExchange(pickupExchangeItems.get(i-1).getItemPickupExchangeType(), "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxExchangeMultiline(item, exchangedItems,"BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange(itemPickupExchangeComment, "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange("", "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange("", "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
        }

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTableTotalExchange() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.2f, 0.2f, 1f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LRB", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LRB", Font.NORMAL, Element.ALIGN_LEFT));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Total", "LB", Font.BOLD, Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "BLACK", Font.NORMAL, Element.ALIGN_CENTER));

        return table;
    }

    public static PdfPTable createDeliveryDocumentTableBodyPickUpExchange(PickupExchangeReqandResp pickupExchangeReqandResp, FileUploadConfigProperties fileUploadConfigProperties, String getPOESignature) throws Exception {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.7f, 0.01f, 0.6f, 0.01f ,0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String pickupExchangeNo = pickupExchangeReqandResp.getPickupExchangeNo()!=null ? pickupExchangeReqandResp.getPickupExchangeNo() : "";
        String poeSignatureFilePath = null;
        if(getPOESignature.equals("default")){
            //This condition will be executed when delivery-resource/preparePickUpExchangeTicketReport Api is directly called
            poeSignatureFilePath = fileUploadConfigProperties.getSignatureProperties().getLocation();
        }
        else{
            poeSignatureFilePath = getPOESignature;
        }

        if(pickupExchangeReqandResp.getPatientSignature() != null && !pickupExchangeReqandResp.getPatientSignature().isEmpty()){
            int begin = pickupExchangeReqandResp.getPatientSignature().indexOf(",");
            int last = pickupExchangeReqandResp.getPatientSignature().length();
            String b_64 = pickupExchangeReqandResp.getPatientSignature().substring(begin + 1, last);
            String beneficiarySignFilePath = poeSignatureFilePath+"/"+pickupExchangeNo+"_Signature_beneficiary.png";
            commonPDFStubs.jsonToSignatureConverter(b_64, beneficiarySignFilePath);
        }else{
            System.out.println("==============Beneficiary/Patient Signature does not Exist==================");
        }

        if(pickupExchangeReqandResp.getTechnicianSignature() != null && !pickupExchangeReqandResp.getTechnicianSignature().isEmpty()){
            int begin1 = pickupExchangeReqandResp.getTechnicianSignature().indexOf(",");
            int last1 = pickupExchangeReqandResp.getTechnicianSignature().length();
            String b_64_1 = pickupExchangeReqandResp.getTechnicianSignature().substring(begin1 + 1, last1);
            String technicianSignFilePath = poeSignatureFilePath+"/"+pickupExchangeNo+"_Signature_technician.png";
            commonPDFStubs.jsonToSignatureConverter(b_64_1, technicianSignFilePath);
        }else{
            System.out.println("==============Technician Signature does not Exist==================");
        }

        if(pickupExchangeReqandResp.getPatientSignature() != null && !pickupExchangeReqandResp.getPatientSignature().isEmpty() && pickupExchangeReqandResp.getPickupExchangeNo()!=null) {
            table.addCell(createCellWithAddSignatureImage(poeSignatureFilePath + "/" + pickupExchangeNo + "_Signature_beneficiary.png"));
        }else{
            table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LTR", Font.NORMAL, Element.ALIGN_CENTER));
        }
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "ALL", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom(pickupExchangeReqandResp.getRelationshipWithPatient(), "LTR", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "ALL", Font.NORMAL, Element.ALIGN_CENTER));
        if(pickupExchangeReqandResp.getTechnicianSignature() != null && !pickupExchangeReqandResp.getTechnicianSignature().isEmpty() && pickupExchangeReqandResp.getPickupExchangeNo()!=null) {
            table.addCell(createCellWithAddSignatureImage(poeSignatureFilePath + "/" + pickupExchangeNo + "_Signature_technician.png"));
        }else{
            table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LTR", Font.NORMAL, Element.ALIGN_CENTER));
        }
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Beneficiary (or Parent/Guardian/Representative)", "LBR", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "ALL", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Relationship of Beneficiary (if Applicable)", "LBR", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "ALL", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Technician Initials", "LBR", Font.NORMAL, Element.ALIGN_CENTER));
        return table;
    }

    public static PdfPTable createDeliveryDocumentTableBodyPickUpExchange1(PickupExchangeReqandResp pickupExchangeReqandResp) throws Exception {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(15);
        table.setWidths(new float[]{ 1f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        String patientSignatureDate = String.valueOf(pickupExchangeReqandResp.getPatientSignedDateTime());

        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom(patientSignatureDate, "LTR", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Date", "LBR", Font.NORMAL, Element.ALIGN_CENTER));

        return table;
    }

    private static PdfPCell createCellWithAddSignatureImage(String imageFile) throws Exception
    {
        Image image = Image.getInstance(imageFile);

        float maxWidth = 90f; // Adjust the maximum width as needed
        float maxHeight = 20f; // Adjust the maximum height as needed
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();
        System.out.println("imageWidth: "+imageWidth+ " maxWidth: "+maxWidth);
        System.out.println("imageHeight: "+imageHeight+ " maxHeight: "+maxHeight);
        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            float widthRatio = maxWidth / imageWidth;
            float heightRatio = maxHeight / imageHeight;
            float scaleRatio = Math.min(widthRatio, heightRatio);
            image.scaleToFit(imageWidth * scaleRatio, imageHeight * scaleRatio);
        }
        //image.setAlignment(Image.LEFT | Image.TEXTWRAP);
        image.setAlignment(Element.ALIGN_JUSTIFIED);
        //image.setAbsolutePosition(20, 10);
        //image.scalePercent(100f, 30f);
        PdfPCell cell = new PdfPCell(image, false);
        cell.setPadding(3f);
        //cell.setPadding(0);
        //cell.setFixedHeight(30);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidthBottom(0.55f);

        return cell;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTableBodyPickUpExchange(PickupExchangeReqandResp pickupExchangeReqandResp) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.1f, 0.18f, 0.82f, 0.3f, 0.15f, 0.15f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxPickUpExchange("QTY", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("TYPE", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("ITEM", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("Remarks", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("INVOICE", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("BILLED", "BLACK", Font.BOLD, Element.ALIGN_CENTER));

        List<PickupExchangeItem> pickupExchangeItems = pickupExchangeReqandResp.getPickupExchangeItems();
        for(int i = 1; i <= pickupExchangeItems.size(); i++){

            String quantity = String.valueOf((Math.round(pickupExchangeItems.get(i-1).getQuantity())));
            String item = pickupExchangeItems.get(i-1).getItemNo() + " " +pickupExchangeItems.get(i-1).getItemName()+" "+pickupExchangeItems.get(i-1).getPickupItemSerialNo();
            String itemPickupExchangeComment = pickupExchangeItems.get(i-1).getItemPickupExchangeComment()!= null ? pickupExchangeItems.get(i-1).getItemPickupExchangeComment() : "";

            table.addCell(createCellWithBodyInputBoxPickUpExchange(quantity, "BLACK", Font.NORMAL, Element.ALIGN_CENTER));
            table.addCell(createCellWithBodyInputBoxPickUpExchange(pickupExchangeItems.get(i-1).getItemPickupExchangeType(), "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange(item, "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange(itemPickupExchangeComment, "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange("", "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
            table.addCell(createCellWithBodyInputBoxPickUpExchange("", "BLACK", Font.NORMAL, Element.ALIGN_LEFT));
        }

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTableTotalPickUpExchange() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.2f, 0.2f, 1f, 0.3f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LRB", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "LRB", Font.NORMAL, Element.ALIGN_LEFT));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("Total", "LB", Font.BOLD, Element.ALIGN_RIGHT));
        table.addCell(createCellWithBodyInputBoxPickUpExchangeCustom("", "BLACK", Font.NORMAL, Element.ALIGN_CENTER));

        return table;
    }

    private static PdfPCell createCellWithBodyInputBoxPickUpExchangeCustom(String inputBoxVal, String inputboxBorderColor, int fontType, int alignment) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        /*if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }*/
        if(inputboxBorderColor.equals("LRB")){
            cell.setBorderColor(BaseColor.WHITE);
        } else if (inputboxBorderColor.equals("LB")) {
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setBorderColorBottom(BaseColor.WHITE);
            cell.setBorderColorTop(BaseColor.WHITE);
        }
        else if (inputboxBorderColor.equals("R")) {
            cell.setBorderColorRight(BaseColor.WHITE);
        }
        else if (inputboxBorderColor.equals("L")) {
            cell.setBorderColorLeft(BaseColor.WHITE);
        }
        else if (inputboxBorderColor.equals("BLACK")) {
            cell.setBorderColorLeft(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("LTR")) {
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setBorderColorTop(BaseColor.WHITE);
            cell.setBorderColorRight(BaseColor.WHITE);
        }
        else if (inputboxBorderColor.equals("LBR")) {
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setBorderColorBottom(BaseColor.WHITE);
            cell.setBorderColorRight(BaseColor.WHITE);
        }
        else if (inputboxBorderColor.equals("ALL")) {
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.BOX); // Set the border style to a box
        //cell.setBorderWidth(2);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBorderWidth(0.1f);
        cell.setBorderWidthBottom(0.55f);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTablePickUpExchange(PickupExchangeReqandResp pickupExchangeReqandResp) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.25f, 0.25f, 0.25f, 0.25f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxPickUpExchange("Pickup/Exchange Date", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("CSR", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("BRANCH", "BLACK", Font.BOLD, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("WAREHOUSE", "BLACK", Font.BOLD, Element.ALIGN_CENTER));

        String pickupExchangeDate = String.valueOf(pickupExchangeReqandResp.getPickupExchangeScheduleDateTime());
        //Will be made Dynamic
        table.addCell(createCellWithBodyInputBoxPickUpExchange(pickupExchangeDate, "BLACK", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange("", "BLACK", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange(pickupExchangeReqandResp.getBranchName(), "BLACK", Font.NORMAL, Element.ALIGN_CENTER));
        table.addCell(createCellWithBodyInputBoxPickUpExchange(pickupExchangeReqandResp.getInventoryLocationName(), "BLACK", Font.NORMAL, Element.ALIGN_CENTER));

        return table;
    }

    private static PdfPCell createCellWithBodyInputBoxExchangeMultiline(String text1, String text2, String inputboxBorderColor, int fontType, int alignment) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font boldUnderlinedFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.UNDERLINE);

        String exchangeItemHeaderBold ="Exchange Items: \n\n";

        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingAfter(50);
        paragraph.add(new Phrase(text1+"\n\n", normalFont));
        //paragraph.add(new Phrase(exchangeItemContentId, normalFont));
        paragraph.add(new Phrase(exchangeItemHeaderBold, boldUnderlinedFont));
        paragraph.add(new Phrase(text2, normalFont));
        //paragraph.add(new Phrase(exchangeItemContentIdNew, normalFont));
        //paragraph.add(new Phrase(exchangeItemCellBlankSpace, normalFont));


        PdfPCell cell = new PdfPCell(new Phrase(paragraph));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.BOX); // Set the border style to a box
        //cell.setBorderWidth(2);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(0.1f);
        cell.setBorderWidthBottom(0.55f);

        return cell;
    }

    private static PdfPCell createCellWithBodyInputBoxPickUpExchange(String inputBoxVal, String inputboxBorderColor, int fontType, int alignment) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.BOX); // Set the border style to a box
        //cell.setBorderWidth(2);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBorderWidth(0.1f);
        cell.setBorderWidthBottom(0.55f);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentMainFormSubTitlePickUpExchange(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.addCell(createCellWithBodyLebelTextFontTen(text));
        table.setSpacingAfter(1);
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyTitlePickUpExchange(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(3);
        table.addCell(createTitleRowLargeTextPickUpExchange(text));
        return table;
    }

    private static PdfPCell createTitleRowLargeTextPickUpExchange(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.WHITE);
        return cell;
    }

    public static PdfPTable createDeliveryDocumentMainBodyHeaderPickUpExchange(PickupExchangeReqandResp pickupExchangeReqandResp) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.8f, 0.5f, 0.3f});
        table.setSpacingAfter(3);

        String[] branchAddress = pickupExchangeReqandResp.getBranchAddress().split(",");

        table.addCell(createHeaderContentPickUpExchange(pickupExchangeReqandResp.getBranchName()+"\n"+branchAddress[0]+"\n"+branchAddress[1].trim()+", "+branchAddress[2]+", "+branchAddress[3]+"\n"+branchAddress[4].trim(), Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createHeaderContentPickUpExchange("\n\nDate: \n Order: \n Customer ID: \n Document Id: \n", Element.ALIGN_RIGHT, Font.BOLD));
        table.addCell(createHeaderContentPickUpExchange("\n\n "+pickupExchangeReqandResp.getCurrentDate()+" \n "+pickupExchangeReqandResp.getPickupExchangeNo()+" \n "+pickupExchangeReqandResp.getPatientIdNo()+" \n "+pickupExchangeReqandResp.getPickupExchangeDocumentId()+" \n", Element.ALIGN_LEFT, Font.NORMAL));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyHeaderPickUpExchange1(PickupExchangeReqandResp pickupExchangeReqandResp) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.1f, 0.35f, 0.2f, 0.35f});
        table.setSpacingAfter(0);
        String firstName = pickupExchangeReqandResp.getPatientFirstName() != null ? pickupExchangeReqandResp.getPatientFirstName(): "";
        String middleName = pickupExchangeReqandResp.getPatientMiddleName()!= null ? pickupExchangeReqandResp.getPatientMiddleName(): "";
        String lastName = pickupExchangeReqandResp.getPatientLastName()!= null ? pickupExchangeReqandResp.getPatientLastName(): "";
        String fullName = firstName+" "+middleName+" "+lastName;
        table.addCell(createHeaderContentPickUpExchange("Patient: ", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createHeaderContentPickUpExchange(fullName, Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createHeaderContentPickUpExchange("", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createHeaderContentPickUpExchange("", Element.ALIGN_LEFT, Font.NORMAL));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyHeaderPickUpExchange2(PickupExchangeReqandResp pickupExchangeReqandResp) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.1f, 0.35f, 0.2f, 0.35f});
        table.setSpacingAfter(0);

        table.addCell(createHeaderContentPickUpExchange("Bill To: ", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createHeaderContentPickUpExchange(pickupExchangeReqandResp.getPatientBillingAddressLine1()+"\n"+pickupExchangeReqandResp.getPatientBillingAddressLine2()+"\n"+pickupExchangeReqandResp.getPatientBillingAddressCity()+" ,"+pickupExchangeReqandResp.getPatientBillingAddressState()+"\n"+pickupExchangeReqandResp.getPatientBillingAddressZip(), Element.ALIGN_LEFT, Font.NORMAL));
        table.addCell(createHeaderContentPickUpExchange("Pickup/Exchange Address: ", Element.ALIGN_LEFT, Font.BOLD));
        table.addCell(createHeaderContentPickUpExchange(pickupExchangeReqandResp.getPatientDeliveyAddressLine1()+" \n"+pickupExchangeReqandResp.getPatientDeliveyAddressLine2()+"\n"+pickupExchangeReqandResp.getPatientDeliveyAddressCity()+" ,"+pickupExchangeReqandResp.getPatientDeliveyAddressState()+"\n"+pickupExchangeReqandResp.getPatientDeliveyAddressZip(), Element.ALIGN_LEFT, Font.NORMAL));
        return table;
    }

    private static PdfPCell createHeaderContentPickUpExchange(String text, int textAlignment, int fontType)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(textAlignment);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.WHITE);
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

    private static PdfPCell createCellWithBodyLebelTextFontTen(String text)
    {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}
