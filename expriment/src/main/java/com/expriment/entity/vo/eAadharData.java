package com.expriment.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class eAadharData implements Serializable {

    private static final long serialVersionUID = 7370637946798079523L;
    @JsonProperty("CertificateData")
    private CertificateData certificateData;

    @JsonProperty("Signature")
    private Signature signature;

    @Override
    public String toString() {
        return "eAadharData{" +
                "certificateData=" + certificateData +
                ", signature=" + signature +
                '}';
    }

    public CertificateData getCertificateData() {
        return certificateData;
    }

    public void setCertificateData(CertificateData certificateData) {
        this.certificateData = certificateData;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }
}
class Signature{
    @JsonProperty("SignedInfo")
    private SignedInfo signedInfo;

    @JsonProperty("SignatureValue")
    private String signatureValue;

    @JsonProperty("KeyInfo")
    private KeyInfo keyInfo;


    public SignedInfo getSignedInfo() {
        return signedInfo;
    }

    public void setSignedInfo(SignedInfo signedInfo) {
        this.signedInfo = signedInfo;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }
}
class SignedInfo{
    @JsonProperty("CanonicalizationMethod")
    private CanonicalizationMethod canonicalizationMethod;

    @JsonProperty("SignatureMethod")
    private SignatureMethod signatureMethod;

    @JsonProperty("Reference")
    private Reference reference;

    public CanonicalizationMethod getCanonicalizationMethod() {
        return canonicalizationMethod;
    }

    public void setCanonicalizationMethod(CanonicalizationMethod canonicalizationMethod) {
        this.canonicalizationMethod = canonicalizationMethod;
    }

    public SignatureMethod getSignatureMethod() {
        return signatureMethod;
    }

    public void setSignatureMethod(SignatureMethod signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
class Reference {

    @JsonProperty("Transforms")
    private Transforms transforms;

    @JsonProperty("DigestMethod")
    private DigestMethod digestMethod;

     @JsonProperty("DigestValue")
    private String digestValue;

     @JsonProperty("URI")
    private Object uri;

     @JsonProperty("text")
    private String text;

    public Transforms getTransforms() {
        return transforms;
    }

    public void setTransforms(Transforms transforms) {
        this.transforms = transforms;
    }

    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    public void setDigestMethod(DigestMethod digestMethod) {
        this.digestMethod = digestMethod;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    public Object getUri() {
        return uri;
    }

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
class Transforms {

    @JsonProperty("Transform")
    private Transform transform;

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
class Transform {

    @ JsonProperty("Algorithm")
    private String algorithm;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
class SignatureMethod {
    @JsonProperty("Algorithm")
    private String algorithm;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
class DigestMethod {
    @JsonProperty("Algorithm")
    private String algorithm;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
 class CanonicalizationMethod {

     @JsonProperty("Algorithm")
     private String algorithm;

     public String getAlgorithm() {
         return algorithm;
     }

     public void setAlgorithm(String algorithm) {
         this.algorithm = algorithm;
     }
 }

class SignatureValue{

}
class KeyInfo{
     @JsonProperty("X509Data")
    private X509Data x509Data;

    public X509Data getX509Data() {
        return x509Data;
    }

    public void setX509Data(X509Data x509Data) {
        this.x509Data = x509Data;
    }
}
class X509Data {

    @JsonProperty("X509SubjectName")
    private List<String> x509SubjectName;

    @JsonProperty("X509Certificate")
    private List<Object> x509Certificate;

    public List<String> getX509SubjectName() {
        return x509SubjectName;
    }

    public void setX509SubjectName(List<String> x509SubjectName) {
        this.x509SubjectName = x509SubjectName;
    }

    public List<Object> getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(List<Object> x509Certificate) {
        this.x509Certificate = x509Certificate;
    }
}
class CertificateData implements Serializable{
    private static final long serialVersionUID = -6999002784809252914L;
    @JsonProperty("KycRes")
    private KycRes kycRes;
/*
    @JsonProperty("ret")
    private String ret;

    @JsonProperty("UidData")
    private UidData uidData;*/

    public KycRes getKycRes() {
        return kycRes;
    }

    public void setKycRes(KycRes kycRes) {
        this.kycRes = kycRes;
    }
}
class KycRes{

    @JsonProperty("code")
    private String code;

    @JsonProperty("ret")
    private String ret;

    @JsonProperty("UidData")
    private UidData uidData;

    @JsonProperty("ts")
    private String ts;

    @JsonProperty("ttl")
    private String ttl;

    @JsonProperty("txn")
    private String txn;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public UidData getUidData() {
        return uidData;
    }

    public void setUidData(UidData uidData) {
        this.uidData = uidData;
    }
}
class UidData{
    @JsonProperty("tkn")
    private String tkn;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("Poi") //class
    private Poi poi;

    @JsonProperty("Poa") //class
    private Poa poa;

    @JsonProperty("LData") //class
    private LData lData;

    @JsonProperty("Pht")
    private String pht;

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    public Poa getPoa() {
        return poa;
    }

    public void setPoa(Poa poa) {
        this.poa = poa;
    }

    public LData getlData() {
        return lData;
    }

    public void setlData(LData lData) {
        this.lData = lData;
    }

    public String getPht() {
        return pht;
    }

    public void setPht(String pht) {
        this.pht = pht;
    }
}

class Poi{
    @JsonProperty("dob")
    private String dob;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("name")
    private String name;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Poa{
    @JsonProperty("country")
    private String country;

    @JsonProperty("dist")
    private String dist;

    @JsonProperty("house")
    private String house;

    @JsonProperty("lm")
    private String lm;

    @JsonProperty("loc")
    private String loc;

    @JsonProperty("pc")
    private String pincode;

    @JsonProperty("state")
    private String state;

    @JsonProperty("street")
    private String street;

    @JsonProperty("vtc")
    private String vilageTownCity;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVilageTownCity() {
        return vilageTownCity;
    }

    public void setVilageTownCity(String vilageTownCity) {
        this.vilageTownCity = vilageTownCity;
    }
}
class LData{
    @JsonProperty("country")
    private String country;

    @JsonProperty("dist")
    private String dist;

    @JsonProperty("house")
    private String house;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("lm")
    private String lm;

    @JsonProperty("loc")
    private String loc;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pc")
    private String pincode;

    @JsonProperty("state")
    private String state;

    @JsonProperty("street")
    private String street;

    @JsonProperty("vtc")
    private String vilageTownCity;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVilageTownCity() {
        return vilageTownCity;
    }

    public void setVilageTownCity(String vilageTownCity) {
        this.vilageTownCity = vilageTownCity;
    }
}
