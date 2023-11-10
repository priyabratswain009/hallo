package com.sunknowledge.dme.rcm.commonutil;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bimal K Sahoo
 * @since 05/06/2023
 */
@Slf4j
public class CommonPDFStubs {
    public String generateQRCode(String qrcodeFileName, String qrcodeDocumentPath) throws IOException, WriterException {
        log.info("===============================SERVICE:create QR Code======================================");
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrcodeFileName.getBytes(charset), charset), BarcodeFormat.QR_CODE, 60, 60);
        String path = qrcodeDocumentPath+"/"+qrcodeFileName+".png";
        System.out.println("QR Document Path=>"+qrcodeDocumentPath);

        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        return path;
    }

    public byte[] generateQRCodeInAmazon(String qrcodeFileName) throws IOException, WriterException {
        log.info("===============================SERVICE:create QR Code======================================");
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrcodeFileName.getBytes(charset), charset), BarcodeFormat.QR_CODE, 60, 60);
        System.out.println("QR Document =>");

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        System.out.println("QR Document Into Byte =>"+ pngData.length);
        return pngData;
    }

    public String generateQRCodeOnNewPage(String qrcodeFileName, String qrcodeDocumentPath, int width, int height) throws IOException, WriterException {
        log.info("===============================SERVICE:create QR Code======================================");
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrcodeFileName.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        String path = qrcodeDocumentPath+"/"+qrcodeFileName+".png";
        System.out.println("QR Document Path=>"+qrcodeDocumentPath);

        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        return path;
    }

    public void addQrCodeOnFooter(String qrcodeFilePath, PdfWriter writer){
        Image image;
        try{
            image = Image.getInstance(qrcodeFilePath);
            image.setAlignment(Element.ALIGN_LEFT);
            image.setAbsolutePosition(30, 10);
            image.scalePercent(100f, 100f);
            writer.getDirectContent().addImage(image, true);
        }
        catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addPageNumber(PdfWriter writer){
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(new Phrase(String.format(writer.getPageNumber()+ " | Page"), font)), 560, 30, 0);
    }

    //Added By Arijit
	public boolean jsonToSignatureConverter(String base64String, String outputPath) {
		int width = 400; // Width of the image
		int height = 200; // Height of the image

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// Create a blank image with transparent background
		Graphics2D graphics = image.createGraphics();
		graphics.dispose();

		File output = new File(outputPath);
		try {
			ImageIO.write(image, "png", output);
			// Decode Base64 string
			byte[] decodedBytes = Base64.getDecoder().decode(base64String);

			// Write the decoded bytes to a file
			FileOutputStream outputStream = new FileOutputStream(outputPath);
			outputStream.write(decodedBytes);
			outputStream.close();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

    public String readQRCode(File QRFile, String charset, Map hashMap) throws WriterException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(QRFile)))));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }

    public ServiceOutcome<String> checkOwnOrOutsideDocument(PDDocument document, Path path){
        ServiceOutcome<String> returnTypeServiceOutcome = new ServiceOutcome<>();
        try {
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage pageImage = renderer.renderImage(document.getNumberOfPages() - 1);

            // Crop the image to the footer area
            BufferedImage footerImage = pageImage.getSubimage(10, 760, 100, 80);
            if(footerImage != null) {
                log.info("===========================IF:QR Code exists===============================");
                log.info("================>=====================>" + path);
                // Save the cropped image to a file
                String qrOutput = path + "/" + "output.png";
                ImageIO.write(footerImage, "png", new File(qrOutput));
                File qrFile = new File(path + "/" + "output.png");

                String charset = "UTF-8";
                Map<com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel> hintMap = new HashMap<com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel>();
                hintMap.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
                String qrcode = new CommonPDFStubs().readQRCode(qrFile, charset, hintMap);
                try {
                    System.out.println("File Name=>" + qrFile.getName());
                    System.out.println("File Path=>" + qrFile.getAbsolutePath());
                    qrFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("QRCode output: " + qrcode);
                returnTypeServiceOutcome.setOutcome(true);
                returnTypeServiceOutcome.setData(qrcode);
                returnTypeServiceOutcome.setMessage("Success");
            }
            else{
                log.info("===========================ELSE:QR Code not exists===============================");
            }
        }
        catch (Exception e){
            returnTypeServiceOutcome.setData(null);
            returnTypeServiceOutcome.setOutcome(false);
            returnTypeServiceOutcome.setMessage("Failure");
        }
        return returnTypeServiceOutcome;
    }

    public static void deleteDirectory(File directory) {
        // Check if the provided File object represents a directory
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            // Delete all files and subdirectories recursively
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        // Delete the current directory
        directory.delete();
    }

    public void addQrcodePageToOutsideDocuments(PDDocument document, String path, String generatedFileName) throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);
        log.info("=======Create New Page and create new Page=========");

        PDImageXObject pdImage = PDImageXObject.createFromFile(path, document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.drawImage(pdImage, 250, 300);

        PDFont pdfFont = PDType1Font.HELVETICA_BOLD;
        int fontSize = 12;
        contentStream.setFont(pdfFont, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 485);
        contentStream.showText("This is system generated to make the document as more informative.");
        contentStream.endText();

        contentStream.close();
        document.save(generatedFileName);
        document.close();
    }
}
