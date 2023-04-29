package com.expriment.Testing;

import java.io.*;

//import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class FileCodecBase64 {

    private static final boolean IS_CHUNKED = true;

//    public static void main(String args[]) throws Exception {
//        C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/istockphoto-537373196-612x612.pdf
        /* Encode a file and write the encoded output to a text file. */
//        encode("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/istockphoto-537373196-612x612.pdf" ,
//                "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-encoded.txt", IS_CHUNKED);
//
//        /* Decode a file and write the decoded file to file system */
////        decode("C:/temp/something-encoded.txt", "c:/temp/something-decoded.pdf");
//        decode("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-encoded.txt",
//                "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/pdfFIle/something-decoded.pdf");
//    }

    /**
     * This method converts the content of a source file into Base64 encoded data and saves that to a target file.
     * If isChunked parameter is set to true, there is a hard wrap of the output  encoded text.
     */
//    public static void encode(String sourceFile, String targetFile, boolean isChunked) throws Exception {
    public static File encode(String sourceFile, String targetFile, boolean isChunked) throws Exception {

        byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), isChunked);

        return writeByteArraysToFile(targetFile, base64EncodedData);
//        return targetFile.getBytes();
    }

    public static File decode(String sourceFile, String targetFile) throws Exception {

        byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));

       return writeByteArraysToFile(targetFile, decodedBytes);
    }

    /**
     * This method loads a file from file system and returns the byte array of the content.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static byte[] loadFileAsBytesArray(String fileName) throws Exception {

        File file = new File(fileName);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
        return bytes;

    }

    /**
     * This method writes byte array content into a file.
     *
     * @param fileName
     * @param content
     * @throws IOException
     */
//    public static void writeByteArraysToFile(String fileName, byte[] content) throws IOException {
    public static File writeByteArraysToFile(String fileName, byte[] content) throws IOException {

        File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
        return file;
    }
}

