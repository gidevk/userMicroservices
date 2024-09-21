package com.expriment.service.serviceImpl;

import com.expriment.entity.ApplicationStatus;
import com.expriment.entity.AuditDetails;
import com.expriment.entity.vo.NameMatchKarzaRequest;
import com.expriment.entity.vo.NameMatchKarzaResponse;
import com.expriment.service.NameMatchingService;
import com.expriment.utils.ProjectConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Service
public class NameMatchingServiceImpl implements NameMatchingService {
    public static final Logger logger = LogManager.getLogger("NameMatchingServiceImpl.class");

    ObjectMapper objectMapper = new ObjectMapper();
   /* @Autowired
    TCLServiceManager tclServiceManager;

    @Autowired
    public AppCommonProps appCommonProps;

    @Autowired
    AppProps appProps;
*/

//    public Map<String, Boolean> nameMatchingApi(String okycname, String customerName, String leadId, CDIOfferModule offerModule) {
    public Map<String, Boolean> nameMatchingApi(NameMatchKarzaRequest request) {

      /*  logger.info("Customer name from AdharOkyc : " + request.getName());
        okycname = okycname != null?okycname.toString().trim().replaceAll("  ", " "):null;

        logger.info("Customer name from CDIOfferModule : " + customerName);
        customerName = customerName != null?customerName.toString().trim().replaceAll("  ", " "):null;

        try {
            offerModule = tclServiceManager.getCdiOfferModuleDataService().getOfferDataByLeadId(Long.valueOf(leadId));
        } catch (Exception e) {
            logger.error("couldn't get data from StagingAppDB",e);
        }
        String mobileNumber = null;
        if(offerModule!=null) {
            mobileNumber = offerModule.getMobileNo()+"";
            leadId = offerModule.getLeadId() != null ? offerModule.getLeadId().toString() : null;
        }
        Map<String, String> nameMatchingPayload = new HashMap<String, String>();
        nameMatchingPayload.put("name1", okycname != null?okycname.trim():null);
        nameMatchingPayload.put("name2", customerName != null ? customerName.trim():null);
        nameMatchingPayload.put("customerId", leadId);
        nameMatchingPayload.put("matchName", TCLConstants.CUSTOMER_NAME_MATCH);
        nameMatchingPayload.put("mobileNumber", mobileNumber);
        nameMatchingPayload.put("leadId", leadId);
        */
        Map<String, Boolean> customerNameMatchResponse = matchingInputsWithEvokeAPI(request);
        logger.info("customerNameMatchResponse :"+ customerNameMatchResponse);
        return customerNameMatchResponse;
    }


    @Override
    public Map<String, Boolean> matchingInputsWithEvokeAPI(NameMatchKarzaRequest request){
       /* double plsResult = 0.0;
        double lvnResult = 0.0;*/
        Double baseFloatScore = 0.6;
        ApplicationStatus applicationStatus = null ;//tclServiceManager.getApplicationStatusService().getApplicationStatus(request.getPlLeadId());
        NameMatchKarzaResponse response =new NameMatchKarzaResponse();
        //String input1, String input2, String appId, String matchName,String mobileNumber
        Map<String, Boolean> matchResponse = new HashMap<>();

        if(request != null) {
//            String name1 = req.get("name1");
//            String name2 = req.get("name2");

//            if(name1 != null && !name1.isEmpty() && name2 != null && !name2.isEmpty()){
            if(request != null && request.getName() != null && request.getCustomerHash() !=null){
               /* plsResult = compareStrings(name1, name2);

                lvnResult = getSimilarity(name1, name2);

                Float plsFloatScore = Float.valueOf(Double.toString(plsResult));
                Float lvnFloatScore = Float.valueOf(Double.toString(lvnResult));*/

                /*if(baseFloatScore != null && plsFloatScore != null && lvnFloatScore != null){
                    if(plsFloatScore >= baseFloatScore && lvnFloatScore >= baseFloatScore){*/
                response = NameMatchByKarza(request);
                try {
                    logger.info("checkingMachingDetails request is {} response is {}",objectMapper.writeValueAsString(request),objectMapper.writeValueAsString(response));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                Double plsFloatScore= response.getResponse().getScore();
                logger.info("Base Float Result :"+ baseFloatScore);
                logger.info("Pls Float Result :"+ plsFloatScore);

                if(plsFloatScore != null){
                    if(plsFloatScore >= baseFloatScore){
                        logger.info("Inside name match if block");
                        matchResponse.put("proceed", true);
                        logger.info("PairLettersSimilarity Name match if block res------->"+plsFloatScore);
//                        logger.info("Levenshtein Name match if block res------->"+lvnFloatScore);
                    }else{
                        logger.info("Inside name match else block");
                        matchResponse.put("proceed", false);
                        logger.info("PairLettersSimilarity Name match else block res------->"+plsFloatScore);
//                        logger.info("Levenshtein Name match else block res------->"+lvnFloatScore);
                    }
                }else{
                    matchResponse = new HashMap<>();
                    matchResponse.put("exception", true);
                }
            }else{
                matchResponse = new HashMap<>();
                matchResponse.put("proceed", false);
            }
        }
        if(applicationStatus!=null && response.getRetStatus().equalsIgnoreCase(ProjectConstants.SUCCESS)) {
            logger.info("saving score in Application table for leadId {}",request.getPlLeadId());
            applicationStatus.setCkycNameMatchScore(response.getResponse().getScore());
//            tclServiceManager.getApplicationStatusService().saveOrUpdateApplicationStatus(applicationStatus);
        }
        return matchResponse;
    }

    public  double getSimilarity(String sourceStr, String targetStr) {
        if (sourceStr.length() < targetStr.length()) { // sourceStr should always be bigger
            String swap = sourceStr; sourceStr = targetStr; targetStr = swap;
        }
        int bigLen = sourceStr.length();
        logger.info("bigLen -> "+ bigLen);
        if (bigLen == 0) { return 0.0; /* both strings are zero length */ }
        return (bigLen - computeEditDistance(sourceStr, targetStr)) / (double) bigLen;
    }

    public  int computeEditDistance(String sourceStr, String targetStr) {
        sourceStr = sourceStr.toLowerCase();
        targetStr = targetStr.toLowerCase();
        logger.info("sourceStr -> "+ sourceStr +" targetStr ->" + targetStr);
        int[] costs = new int[targetStr.length() + 1];
        for (int i = 0; i <= sourceStr.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= targetStr.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (sourceStr.charAt(i - 1) != targetStr.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[targetStr.length()] = lastValue;
        }
        return costs[targetStr.length()];
    }


    public  double compareStrings(String str1, String str2) {
        List<String> pairs1 = wordLetterPairs(str1);
        List<String> pairs2 = wordLetterPairs(str2);

        try {
            int intersection = 0;
            int sizeP1 = pairs1.size();
            int sizeP2 = pairs2.size();

            int union = sizeP1 + sizeP2;
            for (int i = 0; i < sizeP1; i++) {
                Object pair1 = pairs1.get(i);
                for (int j = 0; j < pairs2.size(); j++) {
                    Object pair2 = pairs2.get(j);
                    if (pair1.equals(pair2)) {
                        logger.info("ComaprePair -> ", pair1);
                        intersection++;
                        pairs2.remove(j);
                        break;
                    }
                }
            }

            return (2.0 * intersection) / union;
        } catch (Exception e) {
            logger.error("error got at compareStrings", e);
        }
        return 0;
    }


    public  List<String> wordLetterPairs(String str) {
        List<String> allPairs = new ArrayList<String>();
        try {
            if (str != null && !str.isEmpty()) {
                str = str.toLowerCase();
                // Tokenize the string and put the tokens/words into an array
                String[] words = str.split(" ");
                words = mergeSingleCharacters(words);
                // For each word
                for (int w = 0; w < words.length; w++) {
                    // Find the pairs of characters
                    String[] pairsInWord = letterPairs(words[w]);
                    if (pairsInWord != null) {
                        for (int p = 0; p < pairsInWord.length; p++) {
                            allPairs.add(pairsInWord[p]);
                        }
                    }
                }
            }
            return allPairs;
        } catch (Exception e) {
            logger.error("Error @ wordLetterPairs", e);
        }
        return allPairs;
    }


    public static String[] mergeSingleCharacters(String[] words) {
        if(words!=null && words.length==1 && words[0].length()==1){
            return words;
        }
        List<String> list = new ArrayList<String>();
        int n = words.length;
        String tempWord = "";
        // I have changed this beforeword string from inner for loop to outside
        // of this loop
        String beforeWord = "";
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }
            if (words[i].length() == 1) {
                // if it is not final char
                if (i != n - 1) {
                    tempWord += words[i];
                } else { // if it is final char
                    if (tempWord.length() > 0) {
                        tempWord = tempWord + words[i];
                        list.add(tempWord);
                    } else {
                        if (list.size() > 0) {
                            beforeWord = list.get(list.size() - 1);
                        }
                        beforeWord += words[i];
                        if (list.size() > 0) {
                            list.remove(list.size() - 1);
                        }
                        list.add(beforeWord);
                    }
                }
            } else {
                if (tempWord.length() == 1) {
                    word = tempWord + word;
                    tempWord = "";
                } else if (tempWord.length() > 1) {
                    list.add(tempWord);
                    tempWord = "";
                }
                list.add(word);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public  String[] letterPairs(String str) {
        try {
            if (str != null && !str.trim().equalsIgnoreCase("null") && !str.trim().isEmpty()) {
                int numPairs = str.length() - 1;
                String[] pairs = new String[numPairs];
                for (int i = 0; i < numPairs; i++) {
                    pairs[i] = str.substring(i, i + 2);
                }
                return pairs;
            }
        } catch (Exception e) {
            logger.error("Error @ letterPairs", e);
        }
        return null;
    }

    public double getSimilarity(String inputStr1, List<String> inputPairStr1, String inputStr2,
                                Map<String, List<String>> targetStrPairs) {
        try {
            Double score = 0d;
            int mandLength = 2;

            if (inputStr1 == null || inputStr2 == null)
                return score;

            /*
             * inputStr1=inputStr1.toLowerCase();
             * inputStr2=inputStr2.toLowerCase();
             */

            if (inputStr1.equals(inputStr2)) {
                return 1;
            }

            int srcLen = inputStr1.length();
            int tarLen = inputStr2.length();
            if (srcLen <= (tarLen >> 1) || tarLen <= (srcLen >> 1)) {
                return 0;
            }

            // if either inputStr1 or inputStr2 length is <2 then the score is 0
            if (srcLen > mandLength - 1 && tarLen > mandLength - 1) {
                if (((inputStr1.charAt(1) == ' ' || inputStr2.charAt(1) == ' ')
                        && !inputStr1.substring(0, 1).equals(inputStr2.substring(0, 1)))) {
                } // this condition executes when src and target names are like
                // N Ajay and S Ajay, initials are different so score is 0
                else if (inputStr1.length() > 2 && inputStr2.length() > 2
                        && (((inputStr1.charAt(2) == ' ') || (inputStr2.charAt(2) == ' '))
                        && !inputStr1.substring(0, 2).equals(inputStr2.substring(0, 2)))) {
                    // example (BR Ambedkar and B Ambedkar)
                    if (inputStr1.substring(0, 1).equals(inputStr2.substring(0, 1))
                            && (inputStr1.charAt(1) == ' ' || inputStr2.charAt(1) == ' ')) {
                        score = compareStrings(inputStr1, inputStr2);
                    }
                } else {
                    score = compareStrings(inputPairStr1, inputStr2, targetStrPairs);
                }
            } else {
                if (inputStr1.trim().length() == 1 && inputStr1.trim().equals(inputStr2.trim())) {
                    score = 1D;
                }
            }
            return score;
        } catch (Exception e) {
            logger.error("Exception-->", e);
        }
        return 0;
    }


    public  double compareStrings(List<String> pairs1, String str2, Map<String, List<String>> targetStrPairs) {

        // List<String> pairs2 = wordLetterPairs(str2);
        String[] destinations = str2.split(" ");
        List<String> dests = Arrays.asList(destinations);

        List<String> pairs2 = new ArrayList<String>();
        for (String str : dests) {
            pairs2.addAll(targetStrPairs.get(str));
        }

        try {
            int intersection = 0;
            if (pairs1 == null || pairs2 == null) {
                return 0;
            }
            int sizeP1 = pairs1.size();
            int sizeP2 = pairs2.size();

            int union = sizeP1 + sizeP2;

            for (int i = 0; i < sizeP1; i++) {
                Object pair1 = pairs1.get(i);
                for (int j = 0; j < pairs2.size(); j++) {
                    Object pair2 = pairs2.get(j);
                    if (pair1.equals(pair2)) {
                        intersection++;
                        pairs2.remove(j);
                        break;
                    }
                }
            }

            return (2.0 * intersection) / union;
        } catch (Exception e) {
            logger.error("error got at compareStrings", e);
        }
        return 0;
    }

    @Override
    public NameMatchKarzaResponse NameMatchByKarza(NameMatchKarzaRequest request){//NameMatchbyKarza

        RestTemplate restTemplate = new RestTemplate();
        NameMatchKarzaResponse response = new NameMatchKarzaResponse();
        String karzaURL = "";//appProps.getKarzaUrl();;
        String conversationId = String.valueOf(new Date().getTime());

        try {
         /*   final String proxyUrl = appCommonProps.getProxyIpaddress();
            final int port = appCommonProps.getProxyPort();
            Boolean enabledProxy = appCommonProps.getEnableProxy();

            logger.info("Enabled proxy is set to {}",enabledProxy);*/
            logger.info("NameMatchKarzaRequest payload {}",objectMapper.writeValueAsString(request));

            logger.info("karza api URL {}",karzaURL);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.set("Content-Type", "application/json");
            headers.add("Authorization", "Basic MTNlMzkxNzY6am9jYXRhdWF0");
            headers.add("ConversationID", conversationId);
            headers.add("SourceName", "Jocata");
            logger.info("headers: "+headers);

            HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

           response= restTemplate.postForObject(karzaURL, httpEntity, NameMatchKarzaResponse.class);
           logger.info("NameMatchKarzaResponse payload {}",objectMapper.writeValueAsString(response));
            saveAuditDetails(request,response,"NameMatchScore",karzaURL,conversationId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private void saveAuditDetails(NameMatchKarzaRequest requestPayload, NameMatchKarzaResponse response, String apiName
    , String url, String conversationId) {
        ObjectMapper objMapper = new ObjectMapper();
        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setApiName(apiName);
        auditDetails.setLeadId(requestPayload.getPlLeadId());
        auditDetails.setRequestUrl(url);
        auditDetails.setConversationId(conversationId);
//        auditDetails.setMobileNumber(cdiOfferModule.getMobileNo());
        auditDetails.setResponseTime(new Date());
        auditDetails.setRequestTime(new Date());
        try {
            logger.info("saveAuditDetails request payload {} /n response palyload {}", objMapper.writeValueAsString(requestPayload), objMapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String path = "";//appProps.getTclApiRequestDetailsFile();
        StringBuilder fullPath = new StringBuilder(path);
        fullPath.append(File.separator).append(requestPayload.getPlLeadId()).append(File.separator).append(apiName)
                .append(File.separator);

        logger.info("full path->>" + fullPath);

        File fileDir = new File(fullPath.toString());

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        StringBuilder requestFileName = new StringBuilder(fileDir.getPath());
        requestFileName.append(File.separator).append("request_file.txt");
        StringBuilder responseFileName = new StringBuilder(fileDir.getPath());
        responseFileName.append(File.separator).append("response_file.txt");

        File requestFile = new File(requestFileName.toString());
        File responseFile = new File(responseFileName.toString());

        if (!requestFile.exists()) {
            try {
                requestFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!responseFile.exists()) {
            try {
                responseFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter requestWriter = new FileWriter(requestFile);
             PrintWriter printWriter = new PrintWriter(requestWriter);){

            printWriter.println(objMapper.writeValueAsString(requestPayload));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try (FileWriter responseWriter = new FileWriter(responseFile);
             PrintWriter printWriter = new PrintWriter(responseWriter);)
        {
            printWriter.println(objMapper.writeValueAsString(response));
        } catch (Exception e) {
            logger.error("Error while saving request payload for application id " + requestPayload.getPlLeadId() + "", e);
        }
        auditDetails.setResponseFileName(responseFileName.toString());
        auditDetails.setRequestFileName(requestFileName.toString());
        if (response != null) {
            auditDetails.setStatus("SUCCESS");
        } else {
            auditDetails.setStatus("FAIL");
        }

//        tclServiceManager.getAuditDetailsService().saveAuditDetails(auditDetails);
    }

}
