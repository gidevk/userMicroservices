package com.expriment.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.*;
  
public class ConvertXML {
public static String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Certificate>\n" +
                "    <CertificateData>\n" +
                "        <KycRes code=\"3f241181f5db47c99e53a3f2ec724cdf\" ret=\"Y\" ts=\"2022-12-21T10:42:10.232+05:30\" ttl=\"2023-12-21T10:42:10\" txn=\"UKC:1f0f3074f7e80ae5e79d41e0a2e7ee9220221221104153\">\n" +
                "            <UidData tkn=\"01002274Jg/uZYk+RuDpSGx5Fn2Pbcs2HPv0YH/+Jd+/Oaj+z2ugcc0XmR9dC2LqE3+NsqgG\" uid=\"xxxxxxxx7074\">\n" +
                "                <Poi dob=\"21-06-1991\" gender=\"M\" name=\"Kishan Kumar Sharma\"/>\n" +
                "                <Poa country=\"India\" dist=\"Thane\" house=\"I Think Lodha\" lm=\"Near Vivana Mall\" loc=\"Thane\" pc=\"400607\" state=\"Maharashtra\" street=\"Plot No.31\" vtc=\"Mumbai\"/>\n" +
                "                <LData country=\" \" dist=\"ठाणे\" house=\"साई सिद्धि चस\" lang=\"06\" lm=\"साई बाबा मंदिर के पास\" loc=\"सेआऊडस\" name=\"काशिफ रशीद खान\" pc=\"400607\" state=\"महाराष्ट्र\" street=\"प्लाट न.२८\" vtc=\"नवी मुंबई\"/>\n" +
                "                <Pht></Pht>\n" +
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
                "                <DigestValue>T4NjrX7XftPfMezPNVU0Iqy6Fd9iRO5CmeDT18rToiw=</DigestValue>\n" +
                "            </Reference>\n" +
                "        </SignedInfo>\n" +
                "        <SignatureValue></SignatureValue>\n" +
                "        <KeyInfo>\n" +
                "            <X509Data>\n" +
                "                <X509SubjectName>CN=DS NATIONAL E-GOVERNANCE DIVISION,serialNumber=,houseIdentifier=6 CGO COMPLEX,street=ELECTRONICS NIKETAN,CGO COMPLEX,NEW DELHI,NEW DELHI,ST=Delhi,postalCode=110003,OU=MINISTRY OF ELECTRONICS AND INFORMATION TECHNOLOGY,O=NATIONAL E-GOVERNANCE DIVISION,C=IN</X509SubjectName>\n" +
                "                <X509Certificate></X509Certificate>\n" +
                "                <X509SubjectName>CN=CCA India 2014,O=India PKI,C=IN</X509SubjectName>\n" +
                "                <X509Certificate></X509Certificate>\n" +
                "                <X509SubjectName>CN=SafeScrypt CA 2014,houseIdentifier=II Floor, Tidel Park,street=No.4, Rajiv Gandhi Salai, Taramani, Chennai,ST=Tamil Nadu,postalCode=600 113,OU=Certifying Authority,O=Sify Technologies Limited,C=IN</X509SubjectName>\n" +
                "                <X509Certificate></X509Certificate>\n" +
                "                <X509SubjectName>CN=SafeScrypt sub-CA for RCAI Class 2 2014,OU=Sub-CA,O=Sify Technologies Limited,C=IN</X509SubjectName>\n" +
                "                <X509Certificate></X509Certificate>\n" +
                "            </X509Data>\n" +
                "        </KeyInfo>\n" +
                "    </Signature>\n" +
                "</Certificate>"; /*"<?xml version=\"1.0\" ?><root><test       attribute=\"text1\">javatpoint</test><test attribute=\"text2\">JTP</test></root>";
  */
public static void main(String[] args) {  
// TODO Auto-generated method stub  
/*try {
JSONObject json = XML.toJSONObject(xml);
        String jsonString = json.toString(5);
        System.out.println(jsonString);  
  
}catch (JSONException e) {  
// TODO: handle exception  
System.out.println(e.toString());  
}  */
        // XML string to convert
        String xmlString = "<root><name>John</name><age>25</age></root>";

        // Create XmlMapper and ObjectMapper instances
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper jsonMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);

        try {
                // Read XML string and convert to JSON string
                Object xmlObject = xmlMapper.readValue(xml, Object.class);
                String jsonString = jsonMapper.writeValueAsString(xmlObject);

                System.out.println(jsonString);
        } catch (Exception e) {
                e.printStackTrace();
        }
  
}  
  
  
}  