/*
package com.expriment.Controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {

    public static void main(String[] args) {
        String filePath = "data.pdf"; // Output file path
        String jsonData = "{...}"; // Provided data in JSON format

        // Parse the JSON data and convert it to a PDF table format
        List<List<String>> tableData = parseJsonToTableData(jsonData);

        // Generate the PDF file
        generatePDFTable(tableData, filePath);
    }

    public static void generatePDFTable(List<List<String>> data, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;
            float bottomMargin = 70;
            float yStartNewPage = page.getMediaBox().getHeight() - margin;

            int numberOfRows = data.size();
            int numberOfColumns = data.get(0).size();
            float cellHeight = 20f;
            float tableWidthPercentage = 100f;

            // Set column widths
            float[] columnWidths = new float[numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                columnWidths[i] = tableWidth / numberOfColumns;
            }

            // Draw table headers
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yStart);
            for (int i = 0; i < numberOfColumns; i++) {
                contentStream.showText(data.get(0).get(i));
                contentStream.newLineAtOffset(columnWidths[i], 0);
            }
            contentStream.endText();

            // Draw table data
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (int i = 1; i < numberOfRows; i++) {
                List<String> rowData = data.get(i);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yStart - (i * cellHeight));
                for (int j = 0; j < numberOfColumns; j++) {
                    contentStream.showText(rowData.get(j));
                    contentStream.newLineAtOffset(columnWidths[j], 0);
                }
                contentStream.endText();
            }

            contentStream.close();

            document.save(new File(filePath));
            document.close();

            System.out.println("PDF generated successfully at: " + filePath);
        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> parseJsonToTableData(String jsonData) {
        // Implement your logic to parse the JSON data and extract the required information
        // Return the data in a table format (List<List<String>>)
        return null;
    }
}
*/
