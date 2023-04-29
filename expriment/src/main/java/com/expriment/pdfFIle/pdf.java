/*
package com.expriment.pdfFIle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//import com.itextpdf.kernel.counter.context.IContext;
//import com.lowagie.text.pdf.PdfCopy;
//import com.lowagie.text.pdf.PdfImportedPage;
//import com.lowagie.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.dom4j.Document;
import org.dom4j.DocumentException;

class Pdf {
    public static void main1(String args[])
    {
        new Pdf().createNew();
        new Pdf().combine();
    }

    public void combine()
    {
        try
        {
            PDFMergerUtility mergePdf = new PDFMergerUtility();
            String folder ="pdf";
            File _folder = new File(folder);
            File[] filesInFolder;
            filesInFolder = _folder.listFiles();
            for (File string : filesInFolder)
            {
                mergePdf.addSource(string);
            }
            mergePdf.setDestinationFileName("Combined.pdf");
            mergePdf.mergeDocuments();
        }
        catch(Exception e)
        {

        }
    }

   */
/* public static byte[] mergePDF(List<byte[]> pdfFilesAsByteArray) throws DocumentException, IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Document document = null;
        PdfCopy writer = null;

        for (byte[] pdfByteArray : pdfFilesAsByteArray) {

            try {
                PdfReader reader = new PdfReader(pdfByteArray);
                int numberOfPages = reader.getNumberOfPages();

                if (document == null) {
                    document = new Document(reader.getPageSizeWithRotation(1));
                    writer = new PdfCopy(document, outStream); // new
                    document.open();
                }
                PdfImportedPage page;
                for (int i = 0; i < numberOfPages;) {
                    ++i;
                    page = writer.getImportedPage(reader, i);
                    writer.addPage(page);
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }

        document.close();
        outStream.close();
        return outStream.toByteArray();

    }
*//*

    public void createNew()
    {
        PDDocument document = null;
        try
        {
            String filename="test.pdf";
            document=new PDDocument();
            PDPage blankPage = new PDPage();
            document.addPage( blankPage );
            document.save( filename );
        }
        catch(Exception e)
        {

        }
    }

   */
/* public static boolean mergePDF(IContext context, List<FileDocument> documents, IMendixObject mergedDocument ) throws IOException{
        if (getMergeMultiplePdfs_MaxAtOnce() <= 0 || documents.size() <= getMergeMultiplePdfs_MaxAtOnce()) {

            List<InputStream> sources = new ArrayList<>();
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                PDFMergerUtility  mergePdf = new  PDFMergerUtility();

                for(FileDocument file: documents) {
                    InputStream content = Core.getFileDocumentContent(context, file.getMendixObject());
                    sources.add(content);
                }
                mergePdf.addSources(sources);
                mergePdf.setDestinationStream(out);
                mergePdf.mergeDocuments(null);

                Core.storeFileDocumentContent(context, mergedDocument, new ByteArrayInputStream(out.toByteArray()));

                out.reset();
                documents.clear();
            } catch (IOException e) {
                throw new RuntimeException("Failed to merge documents" + e.getMessage(), e);
            } finally { // We cannot use try-with-resources because streams would be prematurely closed
                for (InputStream is : sources) {
                    is.close();
                }
            }

            return true;
        } else {
            throw new IllegalArgumentException("MergeMultiplePDFs: you cannot merge more than " + getMergeMultiplePdfs_MaxAtOnce() +
                    " PDF files at once. You are trying to merge " + documents.size() + " PDF files.");
        }
    }*//*



}
*/
/*class pdf2{
    InputStream is1 = null;



          if (file1 != null) {

        FileInputStream fis1 = new FileInputStream(file1);

        byte[] file1Data = new byte[(int) file1.length()];

        fis1.read(file1Data);

        is1 = new java.io.ByteArrayInputStream(file1Data);

    }



    //

    InputStream mainContent = <ur main content>



    org.apache.pdfbox.pdmodel.PDDocument mergedPDF = new org.apache.pdfbox.pdmodel.PDDocument();

    org.apache.pdfbox.pdmodel.PDDocument mainDoc = org.apache.pdfbox.pdmodel.PDDocument.load(mainContent);

    org.apache.pdfbox.multipdf.PDFMergerUtility merger = new org.apache.pdfbox.multipdf.PDFMergerUtility();



          merger.appendDocument(mergedPDF, mainDoc);



    PDDocument doc1 = null;



          if (is1 != null) {

        doc1 = PDDocument.load(is1);

        merger.appendDocument(mergedPDF, doc1);

        //1st file appended to main pdf");

    }





    ByteArrayOutputStream baos = new ByteArrayOutputStream();

          mergedPDF.save(baos);
}*//*


*/
