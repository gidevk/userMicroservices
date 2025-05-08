
package com.expriment.Controller;
/*
import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main1(String[] args) {




        List<Object> docTypeDataList= new ArrayList<>();
        docTypeDataList.add("");
//        if((custmerHash !=null || opportunityId !=null) && docTypeDataList.size() >0 ){
            int flag=0;
            for (DocTypeData doc :docTypeDataList){
                if (doc.getDoc() !=null && !doc.getDoc().equals("") && doc.getDocType() != null && !doc.getDocType().equals("")) {
//                        if (doc.getLeadId()== null){doc.setLeadId(leadId); }

                    rootResponse1= saveDocForEmudra(doc,leadId,custmerHash);
                    LoggerClass.appLayerLogger.info("Saved docuementType {} with Status {}",doc.getDocType(), rootResponse1.getRetStatus());
                }else if(doc.getDocType() == null && doc.getDocType().equals("")){
                    flag=1;
                    if (!doc.getDocType().equals("sl"))  // TODO: 4/10/2023 hhhh
                        continue;
                }else {
                    flag=2;
                    continue;
                }
            }
            if (flag==1) rootResponse.setSysErrorMessage("");
            else if (flag==2) rootResponse.setSysErrorMessage("");
            else if(flag ==0) rootResponse.setSysErrorMessage("Given document is saved.");
        }
//        else{
//            rootResponse.setSysErrorMessage("DocTypeData is not available");
//        }
    }
}
*/
/*class Parent {
     void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
     void display() { // Method Hiding (not overriding)
        System.out.println("Static method in Child");
    }
}

public class TestMain {
    public static void main(String[] args) {
        Parent obj1 = new Child();
        obj1.display(); // Calls Parent's display (resolved at compile-time)

        Child obj2 = new Child();
        obj2.display(); // Calls Child's display

    }
}*/

import com.expriment.entity.DigilockerDetails;
import com.expriment.service.serviceImpl.XmlToJsonServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
        import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class TestMain {
    private static XmlToJsonServiceImpl xmlToJsonService;
    /*@Autowired
    XmlToJsonServiceImpl xmlToJsonService;*/
    static Logger logger = LogManager.getLogger("XmlToJsonServiceImpl");


    public static void main(String[] args) {

        try {
            ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            XmlMapper xmlMapper = new XmlMapper();

            // Step 1: Load the XML file
//            FileReader xmlFile = new FileReader("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/Controller/Sample XML.xml");
//            BufferedReader bf = new BufferedReader(xmlFile);
            String xmlData=new String(Files.readAllBytes(Paths.get("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/Controller/Sample XML.xml")));

            xmlData = xmlData.trim(); // Remove leading/trailing whitespace
            if (!xmlData.startsWith("<")) {
                throw new IllegalArgumentException("Invalid XML: Content does not start with '<'");
            }
           /* while (bf.readLine() !=null){
//                bf.readLine();
                xmlData.append(bf.readLine());
            }*/
//            System.out.println("XmlDAta "+ jsonMapper.writeValueAsString(xmlData));

            xmlData = xmlData.replace("\\n", "")     // Remove \n
                    .replace("\\r", "")     // Remove \r
                    .replace("\\", "") // Replace \\" with "
                    .replace("&", "&amp;"); // Replace invalid ampersands
//            System.out.println("XmlDAta "+ jsonMapper.writeValueAsString(xmlData));
            Object xmlObject1 = xmlMapper.readValue(xmlData.toString(), Object.class);
            String jsonString1 = jsonMapper.writeValueAsString(xmlObject1);
//            System.out.println("jsonString1"+jsonString1);
/*
//**************************************************
            String jsonResponseDataString = jsonMapper.writeValueAsString(jsonString1);

//            org.json.JSONObject jsonResponseData = new org.json.JSONObject(jsonResponseDataString);
//            org.json.JSONObject jsonResponseData = new org.json.JSONObject(jsonResponseDataString);
            JSONArray jsonResponseData = new JSONArray(jsonResponseDataString);

            System.out.println("jsonREsponseData {}"+jsonMapper.writeValueAsString(jsonResponseData));

            org.json.JSONObject jsonAccountAnalysis;
            org.json.JSONObject jsonMonthlyDetails;
            org.json.JSONObject jsonMonthlyDetail;
            if (jsonResponseData.has("AccountAnalysis")){
                jsonAccountAnalysis=new org.json.JSONObject((Map) jsonResponseData.getJSONObject("AccountAnalysis"));
                System.out.println("AccountAnalysis {}"+jsonAccountAnalysis.toString().substring(0,100));
                */
/*if (jsonResponseData.has("MonthlyDetails")) {
                    jsonMonthlyDetails = new org.json.JSONObject((Map) jsonResponseData.getJSONObject("MonthlyDetails"));
                    System.out.println("MonthlyDetails {}"+jsonMonthlyDetails.toString().substring(0,100));

                    if (jsonResponseData.has("MonthlyDetail")) {
                        jsonMonthlyDetail = new org.json.JSONObject((Map) jsonResponseData.getJSONObject("MonthlyDetail"));
                        System.out.println("MonthlyDetail {}"+jsonMonthlyDetail.toString().substring(0,100));

                    }
                }*//*


            }else{
                System.out.println("AccountAnalysis json data Not found !");
            }



//            *********************************************
*/

//            System.exit(0);
//            extractXmlinJoson(xmlData);
            extractingXmlData(xmlData);
            // Step 2: Convert XML to JSON
//            JsonNode jsonNode = xmlMapper.readTree(xmlFile);

            Object xmlObject = xmlMapper.readValue(xmlData.toString(), Object.class);
//            String jsonString = jsonMapper.writeValueAsString(xmlObject);

            // Step 3: Convert JSON Tree into String
//            ObjectMapper jsonMapper = new ObjectMapper();
//            String jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            String jsonString = jsonMapper.writeValueAsString(xmlObject);

            // Step 4: Print JSON output
//            System.out.println("Converted JSON:");
//            System.out.println(jsonString);
//            ResponseData responseData= new ResponseData();
            jsonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

            /*ResponseData responseData = jsonMapper.readValue(jsonString, ResponseData.class);
            System.out.println("Size of string "+jsonString.length());
            System.out.println("jsonMapper Data :-"+jsonMapper.writeValueAsString(responseData).length());
*/

            // Step 5: Extract specific data from JSON
//            System.out.println("\nExtracted Data:");
//            System.out.println("Customer Name: " + jsonNode.at("/CustomerInfo/@name").asText());
//            System.out.println("Bank: " + jsonNode.at("/CustomerInfo/@bank").asText());

//            JsonNode monthlyDetails = jsonNode.at("/AdditionalMonthlyDetails/MonthlyDetail1");
            /*for (JsonNode detail : monthlyDetails) {
                System.out.println("Month: " + detail.at("/@monthName").asText());
                System.out.println("Total Credit: " + detail.at("/@totalCredit").asText());
                System.out.println("Total Debit: " + detail.at("/@totalDebit").asText());
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractXmlinJoson(String xmlResponseData) {
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = null;
        try {
            // Read XML string and convert to JSON string
            Object xmlObject = xmlMapper.readValue(xmlResponseData, Object.class);
            jsonString = jsonMapper.writeValueAsString(xmlObject);


            JSONObject jsonResponseData = new JSONObject(jsonString);
            System.out.println("jsonResponseData "+jsonResponseData);

            JSONObject accountAnalysis = jsonResponseData.getJSONObject("AccountAnalysis");

            System.out.println("AccountAnalysis "+accountAnalysis);

            JSONObject SummaryInfo1 = accountAnalysis.getJSONObject("SummaryInfo")/*.getJSONObject("MonthlyDetail")*/;

            String bankName=SummaryInfo1.getString("instName");
            String bankAcc=SummaryInfo1.getString("accNo");
            String accType=SummaryInfo1.getString("accType");
            System.out.println("BankName "+bankName+" bankAcc "+ bankAcc+" AccType "+accType);//SummaryInfo

            JSONObject MonthlyDetails = accountAnalysis.getJSONObject("MonthlyDetails")/*.getJSONObject("MonthlyDetail")*/;
            System.out.println("MonthlyDetails "+MonthlyDetails);
//            JSONArray jsonArray = MonthlyDetails.getJSONArray("MonthlyDetail");
            JSONObject jsonArray = accountAnalysis.getJSONObject("MonthlyDetails");

//            List<ResponseData.AccountAnalysis.MonthlyDetails.MonthlyDetail> monthlyDetailsvo = jsonMapper.readValue(jsonString, new TypeReference<List<ResponseData.AccountAnalysis.MonthlyDetails.MonthlyDetail>>() {});
            System.out.println("jsonArray  "+jsonArray);
//            JSONObject statement_id = statementdetails.getJSONObject("statement_id");
//            System.out.printf("statement_id "+statement_id);

//            JSONObject EODBalances2 = accountAnalysis.getJSONObject("EODBalances")/*.getJSONObject("MonthlyDetail")*/;
//            System.out.println("EODBalances "+EODBalances2);
//            JSONArray jArrEODBalances = MonthlyDetails.getJSONArray("EODBalances");
            JSONArray jArrEODBalances = accountAnalysis.getJSONArray("EODBalances");
            System.out.println("EODBalances "+jArrEODBalances);



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void extractingXmlData(String xmleAadharData){
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString=null;
        DigilockerDetails digilockerDetails= new DigilockerDetails();
        try {
            // Read XML string and convert to JSON string
            Object xmlObject = xmlMapper.readValue(xmleAadharData, Object.class);
            jsonString = jsonMapper.writeValueAsString(xmlObject);

            logger.info(jsonString);

            JSONObject json = new JSONObject(jsonString);
            JSONObject accountAnalysis = json.getJSONObject("AccountAnalysis");
            JSONObject monthlyDetails = accountAnalysis.getJSONObject("MonthlyDetails");
            logger.info("monthlyDetails {}",monthlyDetails);
            System.out.println("monthlyDetails :-"+monthlyDetails);

            JSONArray monthlyDetail = monthlyDetails.getJSONArray("MonthlyDetail");

            System.out.println("monthlyDetail "+monthlyDetail);
            /*
            JSONObject poa = monthlyDetail.getJSONObject("Poa");
            JSONObject poi = monthlyDetail.getJSONObject("Poi");

            logger.info("uid-->"+monthlyDetail.get("uid")+"Name-->"+poi.get("name")+"gender-->"+poi.get("gender")+"dob-->"+poi.get("dob")
                    +"country-->"+poa.get("country")+"state-->"+poa.get("state")+"dist-->"+poa.get("dist")+"house-->"+poa.get("house")+
                    "loc-->"+poa.get("loc")+"pinCode-->"+poa.get("pc")+"VilageTownCity-->"+poa.get("vtc"));
//          logger.info();
//          logger.info();
            logger.info("dob-->"+poi.get("dob"));
            logger.info("country-->"+poa.get("country"));
            logger.info("state-->"+poa.get("state"));
            logger.info("dist-->"+poa.get("dist"));
            logger.info("house-->"+poa.get("house"));
            logger.info("loc-->"+poa.get("loc"));
            logger.info("pinCode-->"+poa.get("pc"));
            logger.info("VilageTownCity-->"+poa.get("vtc"));
            logger.info("photograph {}",monthlyDetail.get("Pht"));


//            digilockerDetails.setLeadId(leadId++);
            digilockerDetails.setDigiCustomerName((String) poi.get("name"));
            digilockerDetails.setGender((String)poi.get("gender"));
            digilockerDetails.setDob((String)poi.get("dob"));
            digilockerDetails.setCountry((String)poa.get("country"));
            digilockerDetails.setState((String)poa.get("state"));
            digilockerDetails.setDist((String)poa.get("dist"));
            digilockerDetails.setHouse((String)poa.get("house"));
            digilockerDetails.setLocality((String)poa.get("loc"));
            digilockerDetails.setPinCode((String)poa.get("pc"));
            digilockerDetails.setVillageTownCity((String)poa.get("vtc"));
            digilockerDetails.setDigiPhotograph(((String)monthlyDetail.get("Pht")).length() >0 ? (String)monthlyDetail.get("Pht"):null);
            digilockerDetails.setCreatedDate(new Date());
*/
//            digilockerDetails =digilockerDetailsDAO.saveOrUpdate(digilockerDetails);
//            logger.info("digilocker {}",objectMapper.writeValueAsString(digilockerDetails) );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

