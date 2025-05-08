package com.expriment.service.serviceImpl;

import com.expriment.Controller.UI.AccountAnalysis;
import com.expriment.DAO.CDIOfferModuleDataDAO;
import com.expriment.DAO.DigilockerDetailsDAO;
import com.expriment.entity.CDIOfferModule;
import com.expriment.entity.DigilockerDetails;
import com.expriment.entity.vo.NameMatchKarzaRequest;
import com.expriment.entity.vo.NameMatchKarzaResponse;
import com.expriment.service.XmlToJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class XmlToJsonServiceImpl implements XmlToJsonService {

    Logger logger = LogManager.getLogger("XmlToJsonServiceImpl");

    ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    DigilockerDetailsDAO digilockerDetailsDAO;

    @Autowired
    CDIOfferModuleDataDAO cdiOfferModuleDataDAO;

    static long leadId= 38480;
        static String xmlData="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
           "<Certificate>\n" +
           "    <CertificateData>\n" +
           "        <KycRes code=\"b04435e2edb44a58b640aa1297b74833\" ret=\"Y\" ts=\"2023-06-30T16:39:32.831+05:30\" ttl=\"2024-06-29T16:39:32\" txn=\"UKC:65e6349b814db80c13c42096a4f3cf8620230630163906\">\n" +
           "            <UidData tkn=\"01002274pXz5K0nb6HDYD2V92zKMKqFvjOJj8iBfH/T/bLSsLgeDMClpN0CApRTnqA9VDJQK\" uid=\"xxxxxxxx5057\">\n" +
           "                <Poi dob=\"02-12-1993\" gender=\"M\" name=\"Edwin James Peranickal\"/>\n" +
           "                <Poa country=\"India\" dist=\"Thane\" house=\"9, Seasons Corner, Plot No-11\" loc=\"Sector-4, Koparkhairane, Navi Mumbai\" pc=\"400709\" state=\"Maharashtra\" vtc=\"Kopar Khairne\"/>\n" +
           "                <LData country=\" \" dist=\"ठाणे\" house=\"9, सिसन्स कॉर्नर, प्लॉट नं-11\" lang=\"13\" loc=\"सेक्टर-4,कोपरखैरणे, नवी मुंबई\" name=\"एडविन जेम्स पेरानिक्काल\" pc=\"400709\" state=\"महाराष्ट्र\" vtc=\"कोपर खैरणे\"/>\n" +
           "                <Pht></Pht>" +
           "            </UidData>\n" +
           "        </KycRes>\n" +
           "    </CertificateData>\n" +
           "    <Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
           "        <SignedInfo>\n" +
           "            <CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" +
           "            <SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n" +
           "            <Reference URI=\"\">\n" +
           "                <Transforms>\n" +
           "                    <Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
           "                </Transforms>\n" +
           "                <DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
           "                <DigestValue>0OOvb3uwo30ucbgi26Xv4I5DoIfR/o3NEqcjoQ7alac=</DigestValue>\n" +
           "            </Reference>\n" +
           "        </SignedInfo>\n" +
           "        <SignatureValue>WSMUGqHnJSRI0Yei3NFQTUzyYEAFaWel6YSQRQBRyDlRmu4eCeD6pT/JMtdFkkjBunfa6o1lMQO9FEVH1DW14eYdm6gWgo+FlqkBcIeZEinM0g+Donn9ad1lSoEnFNQFVN72JhWi0Ujm+GK+URagX7sMH825pyg95I9dGFwu8grt3Cph/JBWd4XJ0ObX97UW8o31D2ab5Z6d9BYPIYmuhMA0l7yobiH82uLwTWP8oetu/xpV8R9R+BekXLmsWWp+/yP9iCd6uzklayNMnqNefG//LE6vNcyw2dBrh3DWuPRvHh9bXl64cPNL1U6888JZ5aAh6X3PVdYzFzA/BSd+Qw==</SignatureValue>\n" +
           "        <KeyInfo>\n" +
           "            <X509Data>\n" +
           "                <X509SubjectName>CN=DS NATIONAL E-GOVERNANCE DIVISION,serialNumber=0f30cb67333f634db11fdb41868b343a6f0469581bdb9fd2a2e4cb59e7797a43,houseIdentifier=6 CGO COMPLEX,street=ELECTRONICS NIKETAN,CGO COMPLEX,NEW DELHI,NEW DELHI,ST=Delhi,postalCode=110003,OU=MINISTRY OF ELECTRONICS AND INFORMATION TECHNOLOGY,O=NATIONAL E-GOVERNANCE DIVISION,C=IN</X509SubjectName>\n" +
           "                <X509Certificate>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</X509Certificate>\n" +
           "                <X509SubjectName>CN=CCA India 2014,O=India PKI,C=IN</X509SubjectName>\n" +
           "                <X509Certificate>MIIDIzCCAgugAwIBAgICJ60wDQYJKoZIhvcNAQELBQAwOjELMAkGA1UEBhMCSU4xEjAQBgNVBAoTCUluZGlhIFBLSTEXMBUGA1UEAxMOQ0NBIEluZGlhIDIwMTQwHhcNMTQwMzA1MTAxMDQ5WhcNMjQwMzA1MTAxMDQ5WjA6MQswCQYDVQQGEwJJTjESMBAGA1UEChMJSW5kaWEgUEtJMRcwFQYDVQQDEw5DQ0EgSW5kaWEgMjAxNDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAN7IUL2K/yINrn+sglna9CkJ1AVrbJYBvsylsCF3vhStQC9kb7t4FwX7s+6AAMSakL5GUDJxVVNhMqf/2paerAzFACVNR1AiMLsG7ima4pCDhFn7t9052BQRbLBCPg4wekx6j+QULQFeW9ViLV7hjkEhKffeuoc3YaDmkkPSmA2mz6QKbUWYUu4PqQPRCrkiDH0ikdqR9eyYhWyuI7Gm/pc0atYnp1sru3rtLCaLS0ST/N/ELDEUUY2wgxglgoqEEdMhSSBL1CzaA8Ck9PErpnqC7VL+sbSyAKeJ9n56FttQzkwYjdOHMrgJRZaPb2i5VoVo1ZFkQF3ZKfiJ25VH5+8CAwEAAaMzMDEwDwYDVR0TAQH/BAUwAwEB/zARBgNVHQ4ECgQIQrjFz22zV+EwCwYDVR0PBAQDAgEGMA0GCSqGSIb3DQEBCwUAA4IBAQAdAUjv0myKyt8GC1niIZplrlksOWIR6yXLg4BhFj4ziULxsGK4Jj0sIJGCkNJeHl+Ng9UlU5EI+r89DRdrGBTF/I+g3RHcViPtOne9xEgWRMRYtWD7QZe5FvoSSGkW9aV6D4iGLPBQML6FDUkQzW9CYDCFgGC2+awRMx61dQVXiFv3Nbkqa1Pejcel8NMAmxjfm5nZMd3Ft13hy3fNF6UzsOnBtMbyZWhS8Koj2KFfSUGX+M/DS1TG2ZujwKKXCuKq7+67m0WF6zohoHJbqjkmKX34zkuFnoXaXco9NkOi0RBvLCiqR2lKfzLM7B69bje+z0EqnRNo5+s8PWSdy+xt</X509Certificate>\n" +
           "                <X509SubjectName>CN=SafeScrypt CA 2014,houseIdentifier=II Floor, Tidel Park,street=No.4, Rajiv Gandhi Salai, Taramani, Chennai,ST=Tamil Nadu,postalCode=600 113,OU=Certifying Authority,O=Sify Technologies Limited,C=IN</X509SubjectName>\n" +
           "                <X509Certificate>MIIEfDCCA2SgAwIBAgICJ7IwDQYJKoZIhvcNAQELBQAwOjELMAkGA1UEBhMCSU4xEjAQBgNVBAoTCUluZGlhIFBLSTEXMBUGA1UEAxMOQ0NBIEluZGlhIDIwMTQwHhcNMTQwMzA1MTEyOTIyWhcNMjQwMzA1MDYzMDAwWjCB6TELMAkGA1UEBhMCSU4xIjAgBgNVBAoTGVNpZnkgVGVjaG5vbG9naWVzIExpbWl0ZWQxHTAbBgNVBAsTFENlcnRpZnlpbmcgQXV0aG9yaXR5MRAwDgYDVQQREwc2MDAgMTEzMRMwEQYDVQQIEwpUYW1pbCBOYWR1MTQwMgYDVQQJEytOby40LCBSYWppdiBHYW5kaGkgU2FsYWksIFRhcmFtYW5pLCBDaGVubmFpMR0wGwYDVQQzExRJSSBGbG9vciwgVGlkZWwgUGFyazEbMBkGA1UEAxMSU2FmZVNjcnlwdCBDQSAyMDE0MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1ItyK8oZbGY2Fy/xGL3yEhelRPVZjdY+tsihvRWhTNKIwarcecMtH36qOKpw8vsjdd6kqZwWuPrvwjM9tKKvKw8/puL+kCAm4uXmrRcWG/yyQBghPc3m/Sqy4/4t+HFIr/F0CHJX5a2mnw6hCLaj0FBQjSX4OAX3C2NsCAHFlhCA+5Ozfq+ktNN0HPnzLmdN5Fudlws1FoCcmm8yl5cIylS6uZNxhJnrYmbuavJcM8BF1YJT3XteITUed6UAWxxM9EJD3WL/iWja8fEoW46c62/vuaVAx7LMyB4f9Jk4i1zO8mAtzoCoqm1ZXth3QyQSoksicjiIGH6oMYCoJEoVFwIDAQABo4HbMIHYMBIGA1UdEwEB/wQIMAYBAf8CAQEwEQYDVR0OBAoECEw+jj2YAqV+MBIGA1UdIAQLMAkwBwYFYIJkZAIwEwYDVR0jBAwwCoAIQrjFz22zV+EwLgYIKwYBBQUHAQEEIjAgMB4GCCsGAQUFBzABhhJodHRwOi8vb2N2cy5nb3YuaW4wDgYDVR0PAQH/BAQDAgEGMEYGA1UdHwQ/MD0wO6A5oDeGNWh0dHA6Ly9jY2EuZ292LmluL3J3L3Jlc291cmNlcy9DQ0FJbmRpYTIwMTRMYXRlc3QuY3JsMA0GCSqGSIb3DQEBCwUAA4IBAQBGtgAcDVM1lYRjAHxObKkvdaHTL0NxVsQj9dp1FbO/xpLralElHJIgHBMBge2VfzuzFRvG2Sthfv266e5eXJ3O3SsHEh/rZOjfB945VSIaPMl8EZkdNkGZv+crVhC12uMx9XinbAHl2iCdxqHDAGM/gUFzD1O+sLJIzh4Oup11dlNVAdsTAl2+itFnbjZ8KOhC8g42BcAW7Y6Hk1g814wNIBq3Pj450PHWhBFjDdZzLZb7nX+5DhxWQYZtpE+yFQ0Y+oX9mo06Vraf46K6G40aZv4merE37P4B2owNHjY7lw1HvoqlxqJFX/H0iX2IxHxPBxA0k5s00c3jIlNfslW8</X509Certificate>\n" +
           "                <X509SubjectName>CN=SafeScrypt sub-CA for RCAI Class 2 2014,OU=Sub-CA,O=Sify Technologies Limited,C=IN</X509SubjectName>\n" +
           "                <X509Certificate>MIIFNzCCBB+gAwIBAgIFGeOxJAEwDQYJKoZIhvcNAQELBQAwgekxCzAJBgNVBAYTAklOMSIwIAYDVQQKExlTaWZ5IFRlY2hub2xvZ2llcyBMaW1pdGVkMR0wGwYDVQQLExRDZXJ0aWZ5aW5nIEF1dGhvcml0eTEQMA4GA1UEERMHNjAwIDExMzETMBEGA1UECBMKVGFtaWwgTmFkdTE0MDIGA1UECRMrTm8uNCwgUmFqaXYgR2FuZGhpIFNhbGFpLCBUYXJhbWFuaSwgQ2hlbm5haTEdMBsGA1UEMxMUSUkgRmxvb3IsIFRpZGVsIFBhcmsxGzAZBgNVBAMTElNhZmVTY3J5cHQgQ0EgMjAxNDAeFw0xNDAzMDYwNDMwMDBaFw0yNDAzMDUwNDMwMDBaMHQxCzAJBgNVBAYTAklOMSIwIAYDVQQKExlTaWZ5IFRlY2hub2xvZ2llcyBMaW1pdGVkMQ8wDQYDVQQLEwZTdWItQ0ExMDAuBgNVBAMTJ1NhZmVTY3J5cHQgc3ViLUNBIGZvciBSQ0FJIENsYXNzIDIgMjAxNDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMdLWmI2QwuMUaDmmA9sA31KCi+x3jTbmvx6+dPrqjeaN2/41l6XExq6i9g5AjK4XSwQM6pmtpi3VgUDQYud/v9my5BNasTc3GGVKfsMMCMZaSxa9JFs6BCIztjOP6Q+nKamNqiIleGCH1EFt5dlRooks0XCtTvYmzaLu72pY9/7w8yopPDDFRcAkiRP/+jEiMF3XrT+ksMkwAa2izFaWFQskxApPVZbJV007GL861c4vNeIIWMpaUPeJAWaRkC0RvuSdW19/dM3wA4pp45fwunlYao10hQwEgvN9rvT44rFmT5UYsSfEOfOSinWiemObzOdQ+h8UtcbhCcHOlsoUp8CAwEAAaOCAVgwggFUMBIGA1UdEwEB/wQIMAYBAf8CAQAwDgYDVR0PAQH/BAQDAgEGMBMGA1UdIwQMMAqACEw+jj2YAqV+MBEGA1UdDgQKBAhDDjdX6SfZCDArBgNVHREEJDAipCAwHjEcMBoGA1UEAxMTU0FGRVNDUllQVE9OTElORV8xNTA/BgNVHR8EODA2MDSgMqAwhi5odHRwOi8vY3JsLnNhZmVzY3J5cHQuY29tL1NhZmVTY3J5cHRDQTIwMTQuY3JsMIGDBggrBgEFBQcBAQR3MHUwSwYIKwYBBQUHMAKGP2h0dHBzOi8vd3d3LnNhZmVzY3J5cHQuY29tL2RydXBhbC9kb3dubG9hZC9TYWZlU2NyeXB0Q0EyMDE0LmNlcjAmBggrBgEFBQcwAYYaaHR0cDovL29jc3Auc2FmZXNjcnlwdC5jb20wEgYDVR0gBAswCTAHBgVggmRkAjANBgkqhkiG9w0BAQsFAAOCAQEAj0h3yH3B0yBhS3Ye8CS1ZdPlpTUFuyX3Fx7EEoM1TB8R1IznKBrlKCxRs9kZ2XX23Td0plriJgnATTEfAxZou4KwIs0cAtWSSQpL3AquW4/PzuYTOVCUt9cAPXziqQISVoIqWcJjsg8fIXAffumLDHvZD/0qPq6xhZvFX1hfUv2wA6LSITB9VB4YzV5OyPm+SM1BFkq3HA07YY8rfLtCfDbF6y79HV49bVESK0ZWNZqofMS9mvYM8xgAADpefe4ICh9TetGIOg3XL0vgX13MzLOy4xM/tDkRPkNXuuUZ7n1jUwGkz8LJAOAc59zr2QI/DzAoEaeeBUrlNJZUCZoFAA==</X509Certificate>\n" +
           "            </X509Data>\n" +
           "        </KeyInfo>\n" +
           "    </Signature>\n" +
           "</Certificate>";
   static String jsonData=null;

//   public String convertXmlToJson(String XmlData){

public  void main1(String[] args) {
//        Create XmlMapper and ObjectMapper instances
       XmlMapper xmlMapper = new XmlMapper();
       ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
       String jsonString=null;
       try {
           // Read XML string and convert to JSON string
           Object xmlObject = xmlMapper.readValue(xmlData, Object.class);
           jsonString = jsonMapper.writeValueAsString(xmlObject);

          logger.info(jsonString);

          /*eAadharData eAadhar = jsonMapper.readValue(jsonString, eAadharData.class);
         logger.info("________________________________________________________________________________________________________________________");
         logger.info(jsonMapper.writeValueAsString(eAadhar));
         logger.info("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{["+eAadhar.getCertificateData());
         */ logger.info("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[");
//           JSONObject json = new JSONObject(eAadhar.toString());
           JSONObject json = new JSONObject(jsonString);
           JSONObject certificateData = json.getJSONObject("CertificateData");
           JSONObject kycRes = certificateData.getJSONObject("KycRes");
           JSONObject uidai = kycRes.getJSONObject("UidData");
           JSONObject poa = uidai.getJSONObject("Poa");
           JSONObject poi = uidai.getJSONObject("Poi");
          /* JSONObject geometry = kycRes.getJSONObject("geometry");
           JSONObject locat = geometry.getJSONObject("location");
*/

          logger.info("uid-->"+uidai.get("uid"));
          logger.info("Name-->"+poi.get("name"));
          logger.info("gender-->"+poi.get("gender"));
          logger.info("dob-->"+poi.get("dob"));
          logger.info("country-->"+poa.get("country"));
          logger.info("state-->"+poa.get("state"));
          logger.info("dist-->"+poa.get("dist"));
          logger.info("house-->"+poa.get("house"));
          logger.info("loc-->"+poa.get("loc"));
          logger.info("pinCode-->"+poa.get("pc"));
          logger.info("VilageTownCity-->"+poa.get("vtc"));
          logger.info("VilageTownCity-->"+uidai.get("Pht"));
           //"iterate onto level of location";

         /*  double lat = locat.getDouble("lat");
           double lng = locat.getDouble("lng");*/

       } catch (Exception e) {
           e.printStackTrace();
       }
//       return jsonString;
   }

    public static void main(String[] args)  {
//        extractingXmlData(xmlData);
        try {
            String xmlData=new String(Files.readAllBytes(Paths.get("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/Controller/Sample XML.xml")));
           /* String xmlData="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<PIR:Data xmlns:PIR=\"https://www.finbox.in/\">\n" +
                    "<AccountAnalysis accountNo=\"XXXXXXXX3756\" accountID=\"5914dd0e-5e14-4553-a197-64019f826b25\" accountType=\"SAVINGS\">\n" +
                    "\n" +
                    "<MonthlyDetails>\n" +
                    "<MonthlyDetail monthName=\"Sep-2023\" avgUtilization=\"0.0\" bal10=\"\" bal15=\"2.05\" bal17=\"2.05\" bal2=\"\" bal20=\"6.6\" bal25=\"6.6\" bal30=\"6.6\" bal4=\"\" bal5=\"\" balAvg=\"4.92\" balLast=\"6.6\" balMax=\"6.6\" balMin=\"2.05\" balTopNAvg=\"5.69\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"2\" creditsSC=\"\" creditsSelf=\"0\" debits=\"2\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2023-09-12\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"6900.0\" totalCreditCardPayment=\"6893.4\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"6893.4\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Oct-2023\" avgUtilization=\"0.0\" bal10=\"6.6\" bal15=\"6.6\" bal17=\"6.6\" bal2=\"6.6\" bal20=\"6.6\" bal25=\"6.6\" bal30=\"6.6\" bal4=\"6.6\" bal5=\"6.6\" balAvg=\"6.6\" balLast=\"6.6\" balMax=\"6.6\" balMin=\"6.6\" balTopNAvg=\"6.6\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"0\" creditsSC=\"\" creditsSelf=\"0\" debits=\"0\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2023-10-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"0.0\" totalCreditCardPayment=\"0.0\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"0.0\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Nov-2023\" avgUtilization=\"0.0\" bal10=\"12.7\" bal15=\"12.7\" bal17=\"12.7\" bal2=\"12.7\" bal20=\"12.7\" bal25=\"12.7\" bal30=\"12.7\" bal4=\"12.7\" bal5=\"12.7\" balAvg=\"12.7\" balLast=\"12.7\" balMax=\"12.7\" balMin=\"12.7\" balTopNAvg=\"12.7\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"1\" creditsSC=\"\" creditsSelf=\"0\" debits=\"1\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2023-11-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"1415.0\" totalCreditCardPayment=\"1408.9\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"1408.9\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Dec-2023\" avgUtilization=\"0.0\" bal10=\"3258.7\" bal15=\"3258.7\" bal17=\"3258.7\" bal2=\"12.7\" bal20=\"3258.7\" bal25=\"10.7\" bal30=\"14.7\" bal4=\"12.7\" bal5=\"12.7\" balAvg=\"1687.86\" balLast=\"14.7\" balMax=\"3258.7\" balMin=\"10.7\" balTopNAvg=\"3258.7\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"4\" creditsSC=\"\" creditsSelf=\"0\" debits=\"3\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2023-12-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"16820.0\" totalCreditCardPayment=\"16818.0\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"16818.0\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"4.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Jan-2024\" avgUtilization=\"0.0\" bal10=\"14.7\" bal15=\"14.7\" bal17=\"14.7\" bal2=\"14.7\" bal20=\"14.7\" bal25=\"14.7\" bal30=\"14.7\" bal4=\"14.7\" bal5=\"14.7\" balAvg=\"14.7\" balLast=\"14.7\" balMax=\"14.7\" balMin=\"14.7\" balTopNAvg=\"14.7\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"0\" creditsSC=\"\" creditsSelf=\"0\" debits=\"0\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2024-01-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"0.0\" totalCreditCardPayment=\"0.0\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"0.0\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Feb-2024\" avgUtilization=\"0.0\" bal10=\"15.7\" bal15=\"15.7\" bal17=\"15.7\" bal2=\"15.7\" bal20=\"15.7\" bal25=\"15.7\" bal30=\"\" bal4=\"15.7\" bal5=\"15.7\" balAvg=\"16.18\" balLast=\"29.5\" balMax=\"29.5\" balMin=\"15.7\" balTopNAvg=\"16.62\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"2\" creditsSC=\"\" creditsSelf=\"0\" debits=\"2\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2024-02-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"5805.0\" totalCreditCardPayment=\"5790.2\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"5790.2\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Mar-2024\" avgUtilization=\"0.0\" bal10=\"29.5\" bal15=\"29.5\" bal17=\"29.5\" bal2=\"29.5\" bal20=\"29.5\" bal25=\"29.5\" bal30=\"33.13\" bal4=\"29.5\" bal5=\"29.5\" balAvg=\"29.85\" balLast=\"33.13\" balMax=\"33.13\" balMin=\"29.5\" balTopNAvg=\"30.23\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"1\" creditsSC=\"\" creditsSelf=\"0\" debits=\"1\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2024-03-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"14230.0\" totalCreditCardPayment=\"14226.37\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"14226.37\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "<MonthlyDetail monthName=\"Apr-2024\" avgUtilization=\"0.0\" bal10=\"33.13\" bal15=\"33.13\" bal17=\"33.13\" bal2=\"33.13\" bal20=\"33.13\" bal25=\"33.13\" bal30=\"33.13\" bal4=\"33.13\" bal5=\"33.13\" balAvg=\"33.13\" balLast=\"33.13\" balMax=\"33.13\" balMin=\"33.13\" balTopNAvg=\"33.13\" cashDeposits=\"0\" cashWithdrawals=\"0\" chqDeposits=\"0\" chqIssues=\"0\" credits=\"0\" creditsSC=\"\" creditsSelf=\"0\" debits=\"0\" debitsSC=\"\" debitsSelf=\"0\" dpLimit=\"\" emiOrLoans=\"0\" highestSalaryDate=\"\" intPayDelay=\"\" inwBounceNonTechnical=\"0\" inwBounceTechnical=\"0\" inwBounces=\"0\" inwChqBounceNonTechnical=\"0\" inwEMIBounces=\"\" loanDisbursals=\"0\" outwBounces=\"0\" overdrawnAmount=\"\" overdrawnDays=\"\" overdrawnDaysPeak=\"\" overdrawnInstances=\"\" salaries=\"0.0\" snLimit=\"\" startDate=\"2024-04-01\" totalBankCharge=\"0.0\" totalCashDeposit=\"0.0\" totalCashWithdrawal=\"0.0\" totalChqDeposit=\"0.0\" totalChqIssue=\"0.0\" totalCredit=\"0.0\" totalCreditCardPayment=\"0.0\" totalCreditSC=\"\" totalCreditSelf=\"0.0\" totalDebit=\"0.0\" totalDebitSC=\"\" totalDebitSelf=\"0.0\" totalEmiIssue=\"\" totalEmiOrLoan=\"0.0\" totalInterestCharged=\"0.0\" totalInterestIncome=\"0.0\" totalInvExpense=\"0.0\" totalInvIncome=\"0.0\" totalLoanDisbursal=\"0.0\" totalOtherExpense=\"0.0\" totalOtherIncome=\"0.0\" totalPaidSalary=\"0.0\" totalPension=\"0.0\" totalSalary=\"0.0\" totalUtilityExpense=\"0.0\" />\n" +
                    "</MonthlyDetails>\n" +
                    "</AccountAnalysis>\n" +
                    "</PIR:Data>";*/
            xmlData = xmlData.replace("\\n", "")     // Remove \n
                    .replace("\\r", "")     // Remove \r
                    .replace("\\", "") // Replace \\" with "
//                    .replace("\", "") // Replace \\" with "
                    .replace("&", "&amp;");


            // Step 1: Convert XML to Java object
            XmlMapper xmlMapper = new XmlMapper();
            Object xmlObject = xmlMapper.readValue(xmlData, Object.class);

            // Step 2: Convert Java object to JSON string
            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonString = jsonMapper.writeValueAsString(xmlObject);

            // Step 3: Parse JSON string to JSONObject
            JSONObject json = new JSONObject(jsonString);
            JSONObject accountAnalysis= json.getJSONObject("AccountAnalysis");
            System.out.println("accountAnalysis accountNo "+accountAnalysis.getString("accountNo"));
            JSONObject monthlyDetails = accountAnalysis.getJSONObject("MonthlyDetails");
            System.out.println("Original MonthlyDetails JSON: " + monthlyDetails);

            // Step 4: Handle MonthlyDetail based on type
            Object monthlyDetailObj = monthlyDetails.opt("MonthlyDetail");
            System.out.println("Type of MonthlyDetail: " + (monthlyDetailObj != null ? monthlyDetailObj.getClass() : "null"));

            JSONArray monthlyDetailArray;

            if (monthlyDetailObj == null) {
                throw new IllegalArgumentException("MonthlyDetail is missing or null.");
            } else if (monthlyDetailObj instanceof JSONObject) {
                // Single object: Wrap it into a JSONArray
                monthlyDetailArray = new JSONArray();
                monthlyDetailArray.put(monthlyDetailObj);
            } else if (monthlyDetailObj instanceof JSONArray) {
                // Already a JSONArray: Use it directly
                monthlyDetailArray = (JSONArray) monthlyDetailObj;
            } else {
                throw new IllegalArgumentException("Unexpected type for MonthlyDetail: " + monthlyDetailObj.getClass());
            }

            // Step 5: Replace MonthlyDetail with the normalized array
            monthlyDetails.put("MonthlyDetail", monthlyDetailArray);

            System.out.println("Normalized MonthlyDetails JSON: " + monthlyDetails);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        try {
            String xmlData2=new String(Files.readAllBytes(Paths.get("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/src/main/java/com/expriment/Controller/Sample XML.xml")));

            JAXBContext context = JAXBContext.newInstance(AccountAnalysis.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlData2);
            AccountAnalysis accountAnalysis = (AccountAnalysis) unmarshaller.unmarshal(reader);

            AccountAnalysis.MonthlyDetails monthlyDetails= accountAnalysis.getMonthlyDetails();
            List<AccountAnalysis.MonthlyDetails.MonthlyDetail> monthlyDetail= monthlyDetails.getMonthlyDetailList();
            for (AccountAnalysis.MonthlyDetails.MonthlyDetail monthlyDetail1: monthlyDetails.getMonthlyDetailList()){
                System.out.println("MOnthName: " + monthlyDetail1.getMonthName() + ", Total salary: " + monthlyDetail1.getTotalSalary());
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
   public void extractingXmlData(String xmleAadharData){
       XmlMapper xmlMapper = new XmlMapper();
       ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
       String jsonString=null;
       DigilockerDetails digilockerDetails= new DigilockerDetails();
       try {
           // Read XML string and convert to JSON string
           Object xmlObject = xmlMapper.readValue(xmlData, Object.class);
           jsonString = jsonMapper.writeValueAsString(xmlObject);

          logger.info(jsonString);

           JSONObject json = new JSONObject(jsonString);
           JSONObject accountAnalysis = json.getJSONObject("AccountAnalysis");
           JSONObject monthlyDetails = accountAnalysis.getJSONObject("MonthlyDetails");
           JSONObject monthlyDetail = monthlyDetails.getJSONObject("MonthlyDetail");

           logger.info("monthlyDetail {}",monthlyDetail);
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


           digilockerDetails.setLeadId(leadId++);
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

           digilockerDetails =digilockerDetailsDAO.saveOrUpdate(digilockerDetails);
            logger.info("digilocker {}",objectMapper.writeValueAsString(digilockerDetails) );

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   @Override
   public void dobMatch(long leadId){
        logger.info("Inside checkingMachingDetails----->>>");
       String matchFaild = null;
       boolean flag = true;
       StringBuilder matchFailedError = new StringBuilder();
       NameMatchKarzaResponse nameMatchKarzaResponse = new NameMatchKarzaResponse();
       NameMatchKarzaRequest nameMatchKarzaRequest = new NameMatchKarzaRequest();
       DigilockerDetails digilockerDetails = new DigilockerDetails();


       try {
           digilockerDetails = digilockerDetailsDAO.getDigilockerDetails(leadId);

           if (digilockerDetails != null) {
               String name = digilockerDetails.getDigiCustomerName() != null ? digilockerDetails.getDigiCustomerName() : "";
               String pinCode = digilockerDetails.getPinCode() != null ? digilockerDetails.getPinCode() : null;
               String dob = digilockerDetails.getDob() != null ? digilockerDetails.getDob() : null;
               CDIOfferModule offerModule = cdiOfferModuleDataDAO.getOfferDataByPlLeadId(Long.valueOf(leadId));

               if (dob != null && offerModule != null && offerModule.getCustomerEnteredDob() != null) {
                   String ckycDob = null;
                   String offerModuledob2 = null;

                   offerModuledob2 = null;

                   try {

                      logger.info("CKYC date before format : " + dob);
                       Date ckycDate = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
                       SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
                       ckycDob = form.format(ckycDate);

                      logger.info("CKYC date after format : {} " + ckycDob);

                       Date offerModuledob = offerModule.getCustomerEnteredDob();
                      logger.info("offer module date before format : " + offerModuledob);
                       offerModuledob2 = form.format(offerModuledob);
                      logger.info("offer module date after format : " + offerModuledob2);
                   } catch (Exception e) {
                      logger.info("Exception while dob match failed .."+ e);
                       matchFailedError = matchFailedError.append(",DOB_MISMATCH");

                   }

                  logger.info("ckycDob and dobofferModule : " + ckycDob + "====>" + offerModuledob2);

                   if (!ckycDob.equals(offerModuledob2)) {
                      logger.info("dob match failed");
                       matchFailedError = matchFailedError.append(",DOB_MISMATCH");
                       //drop customer
                       flag = false;
                   }
               }
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
   }


}
