package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PurchaseOrderTableBuilder {
    public static PdfPTable createPurchaseOrderMainBodyTitle() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.98f });
        table.addCell(createTitleRowLargeText("Purchase Order"));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyPurchaseDetails(PurchaseOrder purchaseOrder) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 0.5f, 0.5f });
        table.addCell(createCellWithBodyLebelText(purchaseOrder.getVendorName()));
        table.addCell(createCellWithBodyLebelText("Purchase Order ID"));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getPoNumber()));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getBillingAddressLine1()+", "+purchaseOrder.getBillingAddressLine2()));
        table.addCell(createCellWithBodyLebelText("Purchase Order Date"));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getPoDate().toString()));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getBillingAddressCity()+", "+purchaseOrder.getBillingAddressState()+" "+purchaseOrder.getBillingAddressZip()));
        table.addCell(createCellWithBodyLebelText("Reference"));
        table.addCell(createCellWithBodyValueText(""));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyVendorDetails(VendorMaster vendorMaster) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 0.5f, 0.5f });
        table.addCell(createCellWithBodyLebelText("VENDOR"));
        if(vendorMaster != null) {
            table.addCell(createCellWithBodyLebelText("Vendor Number"));
            table.addCell(createCellWithBodyValueText(vendorMaster.getVendorNo()));
            table.addCell(createCellWithBodyValueText(vendorMaster.getAddressLine1()));
            table.addCell(createCellWithBodyLebelText("Vendor Account Number"));
            table.addCell(createCellWithBodyValueText(vendorMaster.getVendorAccountNo()));
            table.addCell(createCellWithBodyValueText(vendorMaster.getAddressLine2()));
            table.addCell(createCellWithBodyLebelText("Expected Delivery Date"));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyValueText(vendorMaster.getCity() + ", " + vendorMaster.getState() + " " + vendorMaster.getZip()));
        }
        else{
            table.addCell(createCellWithBodyLebelText("Vendor Number"));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyLebelText("Vendor Account Number"));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyLebelText("Expected Delivery Date"));
            table.addCell(createCellWithBodyValueText(""));
            table.addCell(createCellWithBodyValueText(""));
        }
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyValueText(""));
        return table;
    }
    public static PdfPTable createPurchaseOrderMainBodyVendorDetailsShipto(DeliveryAddress deliveryAddress) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 0.7f, 0.3f, 0.7f });
        table.addCell(createCellWithBodyLebelText("Phone"));
        table.addCell(createCellWithBodyValueText(deliveryAddress.getDeliveryPhoneNo_1()));
        table.addCell(createCellWithBodyLebelText("SHIP TO"));
        table.addCell(createCellWithBodyValueText(deliveryAddress.getDeliveryAddressLine_1()));
        table.addCell(createCellWithBodyLebelText("FAX"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(deliveryAddress.getDeliveryAddressLine_2()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(deliveryAddress.getDeliveryCityName()+", "+deliveryAddress.getDeliveryStateName()+" "+deliveryAddress.getDeliveryZipCode()));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyVendorDetailsShipto(PurchaseOrder purchaseOrder) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.5f, 0.5f, 0.5f, 0.5f });
        table.addCell(createCellWithBodyLebelText("Phone"));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getDeliveryContactNo()));
        table.addCell(createCellWithBodyLebelText("SHIP TO"));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getDeliveryAddressLine1()));
        table.addCell(createCellWithBodyLebelText("FAX"));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getDeliveryAddressLine2()));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(""));
        table.addCell(createCellWithBodyValueText(purchaseOrder.getDeliveryAddressCity()+", "+purchaseOrder.getDeliveryAddressState()+" "+purchaseOrder.getDeliveryAddressZip()));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyItemOrdered() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1.40f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelText("ITEMS ORDERED"));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainHeaderItemOrderedDetails() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 1f, 0.3f, 0.5f });
        table.addCell(createCellWithBodyLebelWithBorder("Qty/UOM"));
        table.addCell(createCellWithBodyLebelWithBorder("Item ID/Item Name"));
        table.addCell(createCellWithBodyLebelWithBorder("Unit Amount"));
        table.addCell(createCellWithBodyLebelWithBorder("Extended Amount"));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyItemOrderedDetails(List<PurchaseOrderItems> purchaseOrderItemsList) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 1f, 0.3f, 0.5f });
        purchaseOrderItemsList.stream().forEach(item -> {
            table.addCell(createCellWithBodyValueTextWithBottomBorder(item.getOrderedQty().toString()));
            table.addCell(createCellWithBodyValueTextWithBottomBorder(item.getItemNo()+" / "+item.getItemName()));
            table.addCell(createCellWithBodyValueTextWithBottomBorder("$"+item.getUnitPrice().toString()));
            table.addCell(createCellWithBodyValueTextWithBottomBorder("$"+item.getTotalAmount().toString()));
        });
        return table;
    }

    public static PdfPTable createPurchaseOrderMainHeaderNote() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f });
        table.addCell(createCellWithBodyLebelWithBorder("Note:"));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyTotalExtendedAmt(AtomicReference<Double> total) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f, 0.3f, 0.3f, 0.5f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelText("TOTAL EXTENDED AMOUNT"));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyLebelText("$"+total));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyFreight(PurchaseOrder purchaseOrder) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.3f, 1f, 0.3f, 0.5f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelText("Freight"));
        table.addCell(createCellWithBodyLebelText(""));
        table.addCell(createCellWithBodyLebelText(""));
        if(purchaseOrder.getFreightCharges() != null)
            table.addCell(createCellWithBodyValueText("$"+purchaseOrder.getFreightCharges()));
        else
            table.addCell(createCellWithBodyValueText("$0.00"));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodyNotes() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1.5f, 0.5f });
        table.setSpacingBefore(10);
        table.addCell(createCellWithBodyLebelText("Notes"));
        table.addCell(createCellWithBodyLebelText(""));
        return table;
    }

    public static PdfPTable createPurchaseOrderMainBodySignatureDate() throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.20f, 0.90f, 0.10f, 0.60f});
        table.addCell(createCellWithBodyLebelText("Signature:"));
        table.addCell(createCellWithBodyLebelWithBorder(" "));
        table.addCell(createCellWithBodyLebelText("Date:"));
        table.addCell(createCellWithBodyLebelWithBorder(" "));
        table.addCell(createCellWithBodyLebelText(" "));
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

    private static PdfPCell createCellWithBodyValueTextWithBottomBorder(String text)
    {
        Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.BOTTOM);
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

    private static PdfPCell createTitleRowLargeText(String text)
    {
        Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextRightLargeStyle(cell);
        cell.setBorder(PdfPCell.BOTTOM);
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
}
