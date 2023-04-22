package com.expriment.Testing;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PdfMerging {

    public void combineTwoPdf(File[] filePaths,String destinationFileName) throws IOException, COSVisitorException {

        // loading all the pdf files we wish to merge

 File file1 = new File(
                "/Users/piyushkumar/Desktop/Merging Pdfs/file1.pdf");
        File file2 = new File(
                "/Users/piyushkumar/Desktop/Merging Pdfs/file2.pdf");


        // Instantiating PDFMergerUtility class

//        PDFMergerUtility obj = new PDFMergerUtility();

        PDFMergerUtility obj = new PDFMergerUtility();
        // Setting the destination file path

//        obj.setDestinationFileName("/Users/piyushkumar/Desktop/Merging Pdfs/newMerged.pdf");
        obj.setDestinationFileName(destinationFileName);

        // Add all source files, to be merged
        for (File files : filePaths){
            obj.addSource(files);
        }

//        obj.addSource(filePaths[1]);

        // Merging documents

        obj.mergeDocuments();

        System.out.println("PDF Documents merged to a single file");
    }
}

