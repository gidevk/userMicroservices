package com.expriment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProjectUtils  {//AmcDAO

//    protected static String uploadLoc;
//
//    static Tika tika;

//    @Autowired
//    private AmcDAO amcDAO;

/*
    @Autowired
    SchemeIdentityDao schemeIdentityDao;

    @Autowired
    private SchemeIdentityService schemeIdentityService;

    @Autowired
    private MfFundHouseService mfFundHouseService;

    @Autowired
    private TenureService tenureService;

    @Autowired
    private CustomerPrincipalBankAddlService customerPrincipalBankAddlService;

    @Autowired
    private SchemeWitService schemeWitService;
*/

//    @Autowired
//    public void loadApacheTika(Tika t) {
//        tika = t;
//    }

/*
    @Autowired
    TclServiceManager serviceManager;

    @Autowired
    CVLSolicitPanService cvlSolicitPanService;

    @Autowired
    CvlStatusCodesService cvlStatusCodesService;

    @Autowired
    CVLPANInquiry cvlpanInquiry;

    @Autowired
    HibernatePatchUtils hibernatePatchUtils;

    @Autowired
    private MoneyfyApplicationService applicationService;

    @Autowired
    CustomerPrincipalService customerPrincipalService;

    @Autowired
    BSEAccountService bseAccountService;

    @Autowired
    CommonDao commonDao;

    @Autowired
    DocumentDAO documentDAO;

    @Autowired
    HibernateUtils hibernateUtils;

    @Autowired(required = false)
    private IMap<String, Object> cache;

    @Autowired
    ServicesLogReportService servicesLogReportService;
*/


    static Logger logger = LoggerFactory.getLogger(ProjectUtils.class);

    /**
     * preparing file storage location to upload files
     *
     * @param storageLoc
     */

/*
    @Value("${update.elastic.pennydrop.status:null}")
    String updateElasticPennyDropStatusUrl;

    @Value("${elastic.search.service.id:null}")
    String serviceId;
*/

  /*  @Autowired
    private LoadBalancerClient loadBalancer;


    private static String DLP_SERVER = "server";

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

//    @Value("${dms.storage.filestorage.location}")

    public void setStorageLoc(String storageLoc) {
        if (isEndsWithFileSeparator(storageLoc)) {
            uploadLoc = storageLoc;
            return;
        }

        uploadLoc = storageLoc + File.separator;
    }

    public static String getStorageLoc() {
        return uploadLoc;
    }

    *//**
     * parsing the media type based on content type
     *
     * @return
     *//*

    public static DefaultResponse prepareDefaultResponse(String message, int statusCode) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatusCode(statusCode);
        defaultResponse.setMessage(message);

        if (statusCode == 200) {
            defaultResponse.setStatus(ProjectConstants.SUCCESS);
            defaultResponse.setContainsErrors(true);
        } else {
            defaultResponse.setStatus(ProjectConstants.FAIL);
            defaultResponse.setContainsErrors(false);
        }
        return defaultResponse;
    }

    public static MediaType getContentType(String mediaType) {
        return MediaType.parseMediaType(mediaType);
    }

    *//**
     * creating inputstreamresource with inputstream
     *
     * @param inputStream
     * @return
     * @throws IOException
     *//*

    public static InputStreamResource getInputStreamResource(InputStream inputStream) throws IOException {
        return new InputStreamResource(inputStream);
    }

    *//**
     * It will check whether the path contains slash at the end
     *
     * @param path
     * @return
     *//*

    public static boolean isEndsWithFileSeparator(String path) {
        if (StringUtils.isEmpty(path))
            return true;


        return path.endsWith(File.separator) || path.endsWith("/");
    }
*/
    /**
     * Uploading the document in dms storage
     *
     * @param file
     * @param dirName
     * @return
     */
/*
    public String uploadDocument(MultipartFile file, String dirName) {
        try {
            return uploadDocument(file.getBytes(), getFileName(file), dirName);
        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. :: ", e);
            throw new RuntimeException(e);
        }
    }

    public String uploadDocumentSipXsip(FilePayload file, String dirName) {
        try {
            return uploadDocument(file.getValue().getBytes(), getFileNameSipXsip(file), dirName);
        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. :: ", e);
            throw new RuntimeException(e);
        }
    }

    public String uploadPublicDocument(MultipartFile file, String fileName, String dirName) {
        try {
            return uploadPublicDocument(file.getBytes(), fileName, dirName);
        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. :: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getStoragePath(){
        String uploadPath= "";
        List<TclConstants> tclConstants;
        tclConstants = serviceManager.getTclConstantsService().getAllTclConstantsUsingConstantName(TclMoneyfyConstants.UPLOAD_PATH,TclMoneyfyConstants.UPLOAD_SERVICE, TclMoneyfyConstants.MONEYFY);
        if(!tclConstants.isEmpty()) {
            uploadPath = tclConstants.get(0).getAppConstantValue();
        }
        return uploadPath;
    }

    *//**
     * Uploading the document in dms storage
     *
     * @param content  File content in byte[]
     * @param fileName Name of file
     * @param dirName  Directory location to store file
     * @return Absolute path of stored file location
     *//*

    public String uploadDocument(byte[] content, String fileName, String dirName) {
        File directory;
        String absoluteFilePath;
        try {
            if (!isEndsWithFileSeparator(dirName)) {
                dirName = dirName + File.separator;
            }

            directory = new File(uploadLoc + dirName);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            absoluteFilePath = uploadLoc + dirName + fileName;
            Files.write(content, new File(absoluteFilePath));
        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. Please check the Upload Location properties :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        return absoluteFilePath;
    }

    public String uploadPublicDocument(byte[] content, String fileName, String dirName) {
        File directory;
        String absoluteFilePath;
        try {
            if (!isEndsWithFileSeparator(dirName)) {
                dirName = dirName + File.separator;
            }

            directory = new File(dirName);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            absoluteFilePath = dirName + fileName;
            Files.write(content, new File(absoluteFilePath));
        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. Please check the Banner Upload Location properties :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        return absoluteFilePath;
    }

    *//**
     * preparing random file name with timestamp
     * removing Non-ASCII characters
     * @param file
     * @return
     *//*

    public String getFileName(MultipartFile file) {
        String fileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
        String fileName = new StringBuilder().append(new Date().getTime()).append(fileNameWithOutExt).toString();
        fileName = fileName.replaceAll("[^A-Za-z0-9]", "").concat("."+fileNameExtension);
        return fileName;
    }

    public String getFileNameSipXsip(FilePayload file) {
        return new StringBuilder().append(new Date().getTime()).append(file.getFilename()).toString();
    }

    *//**
     * it will check whether the file contains multiple extensions for the file name
     *
     * @param originalFilename
     * @return
     *//*

    public boolean isFileContainsMultipleExtensions(String originalFilename) {
        if (StringUtils.isEmpty(originalFilename)) {
            return false;
        }

        return Arrays.asList(originalFilename.substring(originalFilename.indexOf('.') + 1).split("\\.")).size() > 1;
    }

    *//**
     * Validating the file extension contains in given @param formats
     *
     * @param originalFilename
     * @param formats
     * @return
     *//*

    public boolean isValidFile(String originalFilename, List<String> formats) {
        String extension;
        if (StringUtils.isEmpty(originalFilename)) {
            return false;
        }

        extension = FilenameUtils.getExtension(originalFilename);
        if (StringUtils.isEmpty(extension)) {
            return false;
        }

        return formats.stream().anyMatch(format -> extension.equalsIgnoreCase(format));
    }

    *//**
     * preparing defaultResponse here
     *
     * @param message
     * @param statusCode
     * @return
     *//*

    public static DefaultResponse prepareDefaultResponse(String message, int statusCode) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatusCode(statusCode);
        defaultResponse.setMessage(message);

        if (statusCode == 200) {
            defaultResponse.setStatus(GenericConstants.SUCCESS);
            defaultResponse.setContainsErrors(true);
        } else {
            defaultResponse.setStatus(GenericConstants.FAILED);
            defaultResponse.setContainsErrors(false);
        }
        return defaultResponse;
    }

    *//**
     * preparing defaultResponse here
     *
     * @param message
     * @param statusCode
     * @return
     *//*

    public static Map<String, Object> prepareResponse(String message, int statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", statusCode);
        response.put("message", message);

        if (statusCode == 200) {
            response.put("status", GenericConstants.SUCCESS);
            response.put("containsErrors", true);
            response.put("httpStatusCode", HttpStatus.OK);
        } else {
            response.put("status", GenericConstants.FAILED);
            response.put("containsErrors", false);
            response.put("httpStatusCode", statusCode == 0 ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(statusCode));
        }
        return response;
    }

    *//**
     * downloading the file based on absolutePath
     *
     * @param absolutePath
     * @return
     *//*

    public DocumentFilePayload downloadFile(String absolutePath) {
        try {
            File file = new File(absolutePath);
            DocumentFilePayload documentFilePayload = new DocumentFilePayload();
            documentFilePayload.setFileName(file.getName());
            documentFilePayload.setMimeType(tika.detect(file.getName()));

            try (InputStream inputStream = new FileInputStream(file)) {
                documentFilePayload.setFile(new ByteArrayInputStream(IOUtils.toByteArray(inputStream)));
            }

            return documentFilePayload;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    *//**
     * It will give the file size in kb's
     *
     * @param file
     * @return
     *//*

    public long getFileSize(File file) {
        try {
            return java.nio.file.Files.size(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    *//**
     * just creating the file based on uploadedFilePath
     *
     * @param uploadedFilePath
     * @return
     *//*

    public File getFile(String uploadedFilePath) {
        return new File(uploadedFilePath);
    }

    public List<Scheme> getValidatedScheme(List<Scheme> schemeList) {
        *//*1. AMC is active

        *******************
        SQL
        1. Scheme Classification is active
        2. Scheme plan is regular
        3. Scheme status is active

       **********************
       IN transaction
       1. Transaction mode is physical or not
       2. SIP is active or not
        *//*

        List<Scheme> schemes = new LinkedList<>();
        List<AMC> amcs = amcDAO.getAllActiveAMC();
        List<SchemeIdentity> schemeIdentities = schemeIdentityDao.getAllSchemeIdentity();
        List<SchemeRestrict> schemeRestrictList = commonDao.getCompleteList(SchemeRestrict.class);
        Set<Long> restrictedSchemeSet = null;
        Set<Integer> restrictedTypeSet = null;
        if(!schemeRestrictList.isEmpty()){
            restrictedSchemeSet = schemeRestrictList.stream().filter(sr->(sr.getScheme()!=null && sr.getRestrictStatus().equals((byte)0)))
                    .map(sr->sr.getScheme().getSchemeId()).collect(Collectors.toSet());
            restrictedTypeSet = schemeRestrictList.stream().filter(sr->(sr.getSchemeType()!=null && sr.getRestrictStatus().equals((byte)0))).map(sr->sr.getSchemeType()).collect(Collectors.toSet());
        }

        if (!CollectionUtils.isEmpty(amcs) && !CollectionUtils.isEmpty(schemeIdentities)) {
            Map<Integer, AMC> amcMap = amcs.stream().collect(Collectors.toMap(a -> a.getAmcId(), Function.identity()));
            Map<Long, SchemeIdentity> schemeIdentityMap = schemeIdentities.stream().collect(Collectors.toMap(s -> s.getSchemeId(), Function.identity()));

            for (Scheme scheme : schemeList) {
                if (scheme.getSchemeId() != null && schemeIdentityMap.containsKey(scheme.getSchemeId())) {
                    if (amcMap.containsKey(schemeIdentityMap.get(scheme.getSchemeId()).getAmcId().getAmcId())) {
                        if (CollectionUtils.isEmpty(restrictedSchemeSet) || !restrictedSchemeSet.contains(scheme.getSchemeId())) {
                            if (CollectionUtils.isEmpty(restrictedTypeSet) || !restrictedTypeSet.contains(scheme.getSchemeOption().valueId))
                                schemes.add(scheme);
                        }
                    }
                }
            }
        }
        return schemes;
    }

    public List<Object> getValidatedSchemeObj(List<Object> schemeList) {
        *//*1. AMC is active

        *******************
        SQL
        1. Scheme Classification is active
        2. Scheme plan is regular
        3. Scheme status is active

       **********************
       IN transaction
       1. Transaction mode is physical or not
       2. SIP is active or not
        *//*

        List<Object> schemes = new LinkedList<>();
        List<AMC> amcs = amcDAO.getAllActiveAMC();
        List<SchemeIdentity> schemeIdentities = schemeIdentityDao.getAllSchemeIdentity();
        List<SchemeRestrict> schemeRestrictList = commonDao.getCompleteList(SchemeRestrict.class);
        Set<Long> restrictedSchemeSet = null;
        Set<Integer> restrictedTypeSet = null;
        if(!schemeRestrictList.isEmpty()){
            restrictedSchemeSet = schemeRestrictList.stream().filter(sr->(sr.getScheme()!=null && sr.getRestrictStatus().equals((byte)0)))
                    .map(sr->sr.getScheme().getSchemeId()).collect(Collectors.toSet());
            restrictedTypeSet = schemeRestrictList.stream().filter(sr->(sr.getSchemeType()!=null && sr.getRestrictStatus().equals((byte)0))).map(sr->sr.getSchemeType()).collect(Collectors.toSet());
        }

        if (!CollectionUtils.isEmpty(amcs) && !CollectionUtils.isEmpty(schemeIdentities)) {
            Map<Integer, AMC> amcMap = amcs.stream().collect(Collectors.toMap(a -> a.getAmcId(), Function.identity()));
            Map<Long, SchemeIdentity> schemeIdentityMap = schemeIdentities.stream().collect(Collectors.toMap(s -> s.getSchemeId(), Function.identity()));

            net.minidev.json.parser.JSONParser parser = new JSONParser();
            for (Object obj : schemeList) {
                try {
                    net.minidev.json.JSONObject jsonObject = (net.minidev.json.JSONObject) parser.parse(obj.toString());
                    Long schemeId = jsonObject.get("scheme_id") != null ? Long.valueOf(jsonObject.get("scheme_id").toString()) : null;
                    if (schemeId != null && schemeIdentityMap.containsKey(schemeId)) {
                        if (amcMap.containsKey(schemeIdentityMap.get(schemeId).getAmcId().getAmcId())) {
                            if (CollectionUtils.isEmpty(restrictedSchemeSet) || !restrictedSchemeSet.contains(schemeId)) {
                                if (CollectionUtils.isEmpty(restrictedTypeSet) || !restrictedTypeSet.contains(jsonObject.get("scheme_option_valueId") != null ? Long.valueOf(jsonObject.get("scheme_option_valueId").toString()) : null))
                                    schemes.add(obj);
                            }
                        }
                    }
                }catch(Exception e){
                    logger.error("Error while validation scheme ", e);
                }
            }
        }
        return schemes;
    }

    public Set<Long> getValidatedSchemeBySchemeIdList(Set<Long> schemeIdSet) {
        Set<Long> schemeIds = new HashSet<>();
        List<AMC> amcs = amcDAO.getAllActiveAMC();
        List<SchemeIdentity> schemeIdentities = schemeIdentityDao.getAllSchemeIdentity();
        List<SchemeRestrict> schemeRestrictList = commonDao.getCompleteList(SchemeRestrict.class);
        Set<Long> restrictedSchemeSet = null;
        if(!schemeRestrictList.isEmpty()){
            restrictedSchemeSet = schemeRestrictList.stream().filter(sr->(sr.getScheme()!=null && sr.getRestrictStatus().equals((byte)0)))
                    .map(sr->sr.getScheme().getSchemeId()).collect(Collectors.toSet());
        }

        if (!CollectionUtils.isEmpty(amcs) && !CollectionUtils.isEmpty(schemeIdentities)) {
            Map<Integer, AMC> amcMap = amcs.stream().collect(Collectors.toMap(a -> a.getAmcId(), Function.identity()));
            Map<Long, SchemeIdentity> schemeIdentityMap = schemeIdentities.stream().collect(Collectors.toMap(s -> s.getSchemeId(), Function.identity()));

            for (Long schemeId : schemeIdSet) {
                if (schemeId != null && schemeIdentityMap.containsKey(schemeId)) {
                    if (amcMap.containsKey(schemeIdentityMap.get(schemeId).getAmcId().getAmcId())) {
                        if (CollectionUtils.isEmpty(restrictedSchemeSet) || !restrictedSchemeSet.contains(schemeId)) {
                            schemeIds.add(schemeId);
                        }

                    }
                }
            }
        }
        return schemeIds;
    }

    public boolean isSortOrderEmpty(String returnSortOrder, String morningStarRatingSortOrder,
                                    String valueResearchRatingSortOrder, String riskSortOrder, String minSipSortOrder,
                                    String totalFundSizeSortOrder) {
        return StringUtils.isEmpty(returnSortOrder)
                && StringUtils.isEmpty(morningStarRatingSortOrder)
                && StringUtils.isEmpty(valueResearchRatingSortOrder)
                && StringUtils.isEmpty(riskSortOrder)
                && StringUtils.isEmpty(minSipSortOrder)
                && StringUtils.isEmpty(totalFundSizeSortOrder);
    }

    public TenureVO getTenureDetails(String tenure) {
        TenureVO tenureVO = new TenureVO();
        Short tenure1 = null;
        String tenureUnit = null;
        tenure = tenure!=null?tenure.toUpperCase():"";
        switch (tenure) {
            case YEAR3:
                tenure1 = SEBI_TENURE_3;
                tenureUnit = SEBI_TENURE_UNIT_YEARS;
                break;
            case YEAR1:
                tenure1 = SEBI_TENURE_1;
                tenureUnit = SEBI_TENURE_UNIT_YEAR;
                break;
            case YEAR5:
                tenure1 = SEBI_TENURE_5;
                tenureUnit = SEBI_TENURE_UNIT_YEARS;
                break;
            case MONTHS6:
                tenure1 = SEBI_TENURE_6;
                tenureUnit = SEBI_TENURE_UNIT_MONTHS;
                break;
            case MONTHS3:
                tenure1 = SEBI_TENURE_3;
                tenureUnit = SEBI_TENURE_UNIT_MONTHS;
                break;
            case MONTHS1:
                tenure1 = SEBI_TENURE_1;
                tenureUnit = SEBI_TENURE_UNIT_MONTH;
                break;
            case MAX:
                tenure1 = SEBI_TENURE_0;
                tenureUnit = SEBI_TENURE_UNIT_MAX;
                break;

        }
        tenureVO.setTenure(tenure1);
        tenureVO.setTenureUnit(tenureUnit);
        return tenureVO;
    }

    *//**
     * Extract data from cell by data type
     *
     * @param cell
     * @param <T>
     * @return
     *//*

    public <T> T extractDataFromCell(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return (T) cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return (T) Double.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return (T) Boolean.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:
                return (T) Byte.valueOf(cell.getErrorCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return (T) cell.getCellFormula();
            default:
                return null;
        }
    }

    public static <T> T convertNumber(Number number, Class cl) {
        switch (cl.getSimpleName()) {
            case "Long":
                return (T) Long.valueOf(number.longValue());
            case "Integer":
                return (T) Integer.valueOf(number.intValue());
            case "Double":
                return (T) Double.valueOf(number.doubleValue());
            case "Byte":
                return (T) Byte.valueOf(number.byteValue());
            case "Float":
                return (T) Float.valueOf(number.floatValue());
            default:
                return (T) Short.valueOf(number.shortValue());
        }
    }

    *//**
     * It will convert base64 string to file based on extension(ex:jpg,png(images),pdf..etc)
     *
     * @param base64String
     * @param extension
     * @return
     *//*

    public InputStream convertBase64StringToFile(String base64String, String extension) throws IOException {
        File file;
        InputStream stream;

        String tempPath = prepareTempPath();
        String tempFileName = prepareFileName("base64File", extension);
        String absoultePath = tempPath + tempFileName;
        OutputStream outputStream = new FileOutputStream(absoultePath);
        outputStream.write(Base64.getDecoder().decode(base64String.replaceAll("\\s", "")));
        outputStream.close();

        file = new File(absoultePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            stream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
        }

        file.delete();
        return stream;
    }

    *//**
     * It will convert base64 string to file based on extension(ex:jpg,png(images),pdf..etc)
     *
     * @param base64String
     * @param orginalFileName
     * @param absolutePath
     * @return
     *//*

    public String writeBase64File(String base64String, String orginalFileName, String absolutePath) throws IOException {
        String absoluteFilePath;
        File file = new File(absolutePath);

        if (!file.exists()) {
            file.mkdirs();
        }

        absoluteFilePath = RestUtils.append(absolutePath, prepareFileName(FilenameUtils.getBaseName(orginalFileName), FilenameUtils.getExtension(orginalFileName)));

        try (OutputStream outputStream = new FileOutputStream(absoluteFilePath)) {

            outputStream.write(Base64.getDecoder().decode(base64String.replaceAll("\\s", "")));
            outputStream.flush();

        }

        return absoluteFilePath;
    }

    *//**
     * It Prepares Temp File path if not there in System it creates temp Path
     *
     * @return
     * @throws Exception
     *//*

    public String prepareTempPath() {
        String tmp = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(tmp);
        if (!tmpDir.exists()) {
            LoggerClass.appLayerLogger.info("Directory Created: " + tmpDir.mkdir());
        }
        return tmp;
    }

    *//**
     * prepares file name
     *
     * @param tempFileName
     * @return
     * @throws Exception
     *//*

    private String prepareFileName(String tempFileName, String extension) {
        return new StringBuilder(File.separator)
                .append(tempFileName)
                .append("_")
                .append(new Random().nextInt(100000))
                .append(new Date().getTime())
                .append(".")
                .append(extension).toString();
    }


    *//**
     * Validating file name if contains multiple extensions or matching with given file formats
     *
     * @param fileName
     * @param fileFormats
     *//*

    public void validateFile(String fileName, String... fileFormats) {

        if (isFileContainsMultipleExtensions(fileName))
            throw new BadRequestException("File Contains Multiple Extensions");

        if (!isValidFile(fileName, Arrays.asList(fileFormats)))
            throw new BadRequestException(RestUtils.append("Only ", String.join(",", fileFormats), " formats is allowed "));
    }

    *//**
     * It gives intially we can add and decrese the time
     *
     * @param field
     * @param amount
     * @return
     *//*

    public static Calendar getCalender(int field, int amount) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(field, amount);
        return calender;
    }

    public static boolean check(Number number) {
        return number != null && number.shortValue() == 1;
    }

    public static String getMimeType(String fileName) {
        return tika.detect(fileName);
    }


    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        if (original_width > bound_width) {
            new_width = bound_width;
            new_height = (new_width * original_height) / original_width;
        }

        if (new_height > bound_height) {
            new_height = bound_height;
            new_width = (new_height * original_width) / original_height;
        }
        return new Dimension(new_width, new_height);
    }

    *//**
     * It gives path of the scheme image
     *
     * @param schemeId ID of scheme
     * @return Image Path of scheme
     *//*
    public String getSchemeImagePath(Long schemeId) {
        SchemeIdentity schemeIdentity = schemeIdentityService.fetchSchemeIdentityDetailsBySchemeId(schemeId);
        if (schemeIdentity != null) {
            MfFundHouse mfFundHouse = mfFundHouseService.getMfFundHouseById(schemeIdentity.getMfHouseId());
            return getImagePathByFundHouse(mfFundHouse);
        }
        return null;
    }
    public String getImagePathByFundHouse(MfFundHouse mfFundHouse) {
        if (mfFundHouse != null) {
            String baseUrl = getBaseUrl();
            return baseUrl + FUND_IMAGE_DIR + mfFundHouse.getImagePath();
        }
        return null;
    }
    public String getFullImagePath(String imagePath) {
        if (imagePath != null) {
            String baseUrl = getBaseUrl();
            return baseUrl + FUND_IMAGE_DIR + imagePath;
        }
        return null;
    }

    public Double getRorPaPctBySchemeIdAndTenure(Long schemeId, String tenurePeriod) {
        Tenure tenure = tenureService.fetchTenureDetailsBySchemeIdAndTenurePeriod(schemeId, tenurePeriod);
        if (tenure != null && tenure.getRorPaPct() != null) {
            return Math.round(tenure.getRorPaPct() * 100.0) / 100.0;
        } else {
            tenure = tenureService.fetchTenureDetailsBySchemeIdAndTenurePeriod(schemeId, MAX);
            if (tenure != null && tenure.getRorPaPct() != null) {
                return Math.round(tenure.getRorPaPct() * 100.0) / 100.0;
            }
        }
        return null;
    }

    public Double getMinSipAmountBySchemeId(Long schemeId) {
        Double minSipAmt = null;
        SchemeWit schemeWit;
        if (schemeId != null) {
            schemeWit = schemeWitService.getSchemeWitBySchemeIdWitFlagAndFreq(schemeId, MIN_SIP_WIT_FLAG, MIN_SIP_WIT_FREQ);
            if (schemeWit != null) {
                minSipAmt = schemeWit.getMinAmt();
            }
            *//*schemeIdentity = schemeIdentityService.fetchSchemeIdentityDetailsBySchemeId(schemeId);
            List<BseScheme> bseSchemeList;
            List<BseSchemeWit> bseSchemeWitList = new ArrayList<>();
            if (schemeIdentity != null) {
                if (schemeIdentity.getRtaId() != null) {
                    bseSchemeList = bseSchemeService.getBseSchemeListByChannelPartnerCode(schemeIdentity.getRtaId().getRtaCode());
                        for (BseScheme bseScheme : bseSchemeList) {
                            BseSchemeWit bseSchemeWit = bseSchemeWitService.getBseSchemeWitByWitFlagFrequencyAndBseScheme(TclMoneyfyConstants.MIN_SIP_WIT_FLAG,TclMoneyfyConstants.MIN_SIP_WIT_FREQ,bseScheme);
                            if (bseSchemeWit != null)
                                bseSchemeWitList.add(bseSchemeWit);
                        }
                    if (!CollectionUtils.isEmpty(bseSchemeWitList)) {
                        minSipAmt = bseSchemeWitList.stream().min(Comparator.comparingDouble(BseSchemeWit::getMinAmt)).get().getMinAmt();
                    }
                }
            }*//*
        }
        return minSipAmt;
    }

    *//**
     * To Delete file with absolutePath
     *
     * @param absolutePath
     * @return
     *//*

    public boolean deleteFile(String absolutePath) throws FileNotFoundException {
        File file = new File(absolutePath);

        if (!file.exists())
            throw new FileNotFoundException("File Not Found in this location {" + absolutePath + "}");

        return file.delete();
    }

    public String getExitLoad(EntryExitLoad entryExitLoad) {
        Pattern exitLoadOfPattern = Pattern.compile("[Ee]xit [Ll]oad [Oo]f (\\S+)%.*");
        Pattern willBeChargedPattern = Pattern.compile(".*[ ,](\\S+)% will be charged.*");
        Pattern percentagePattern = Pattern.compile("(\\S+)%.*");
        if (entryExitLoad.getRemarks() != null) {
            Matcher exitLoadOfMatcher = exitLoadOfPattern.matcher(entryExitLoad.getRemarks());
            if (exitLoadOfMatcher.find()) {
                return exitLoadOfMatcher.group(1);
            }
            Integer index = entryExitLoad.getRemarks().indexOf("will be charged");
            if (index != null && index != -1) {
                Matcher willBeChargedMatcher = willBeChargedPattern.matcher(entryExitLoad.getRemarks().substring(0, index + 15));
                if (willBeChargedMatcher.find()) {
                    return willBeChargedMatcher.group(1);
                }
            }
            Matcher percentageMatcher = percentagePattern.matcher(entryExitLoad.getRemarks());
            if (percentageMatcher.find()) {
                return percentageMatcher.group(1);
            }
        }
        return null;
    }

    *//**
     * Convertion of local file to base64String
     *
     * @param absoluteFilePath
     * @return
     *//*

    public String getBase64String(String absoluteFilePath) {

        try (InputStream inputStream = new FileInputStream(new File(absoluteFilePath))) {

            return Base64.getEncoder().encodeToString(IOUtils.toByteArray(inputStream));

        } catch (FileNotFoundException fe) {

            throw new FileConversionException("FileNotFound", fe);

        } catch (Exception e) {

            throw new FileConversionException("Unable To Convert to base64", e);

        }

    }
    public Double getReturnPABySchemeIdAndTenure(Long schemeId, String tenure){
        List<String> tenureList = new ArrayList<>(Arrays.asList(MONTHS6, YEAR1, YEAR3, YEAR5, MAX));
        Double returnPA;
        if(schemeId!=null && tenure!=null) {
            int tenureIndex = tenureList.indexOf(tenure.toUpperCase());
            while (tenureIndex != -1) {
                tenure = tenureList.get(tenureIndex);
                Tenure tenureForReturnPa = tenureService.fetchTenureDetailsBySchemeIdAndTenurePeriod(schemeId, tenure);
                if (tenureForReturnPa != null && tenureForReturnPa.getRorPaPct() != null) {
                    returnPA = tenureForReturnPa.getRorPaPct();
                    return returnPA;
                }
                tenureIndex -= 1;
            }
        }
        return null;
    }

    public void setBankPennyOrChequeLastActionTime(CpBankDtls cpBankDtls){
        CustomerPrincipalBankAddl cpBankAddl = customerPrincipalBankAddlService
                .getCustomerPrincipalBankAddlById(cpBankDtls.getCpBankDtlsId());
        if(cpBankAddl == null){
            cpBankAddl = new CustomerPrincipalBankAddl();
            cpBankAddl.setCpBankDtls(cpBankDtls);
        }
        cpBankAddl.setLastActionTime(System.currentTimeMillis());
        customerPrincipalBankAddlService.saveCustomerPrincipalBankAddl(cpBankAddl);

        // call ucc api once bank verification done
        if(BANK_STATE_VERIFIED.equals(cpBankDtls.getBankState())){
            bseAccountService.callBSEUccApi(cpBankDtls.getPrincipal().getCpId());
        }
    }

    public CVLSolicitResponse callFetchAllKraApi(CustomerPrincipal customerPrincipal, DateFormat dateFormat, CustomerPrincipalAddlExt cpAdlExt) {
        CVLPANInquiryPayload cvlpanInquiryPayload = new CVLPANInquiryPayload();
        List<TclConstants> tclConstants = serviceManager.getTclConstantsService().getAllTclConstantsUsingApiName(
                TCLServiceConstants.API_NAMES.CVL_PASSWORD, MONEYFY);
        if (tclConstants != null && !tclConstants.isEmpty()) {
            for (TclConstants constant : tclConstants) {
                switch (constant.getConstantName()) {
                    case "passWord":
                        cvlpanInquiryPayload.setPassWord(constant.getAppConstantValue());
                        break;
                    case "passKey":
                        cvlpanInquiryPayload.setPassKey(constant.getAppConstantValue());
                        break;
                    case "userName":
                        cvlpanInquiryPayload.setUserName(constant.getAppConstantValue());
                        break;
                    case "posCode":
                        cvlpanInquiryPayload.setPosCode(constant.getAppConstantValue());
                        break;
                }
            }
        }
        cvlpanInquiryPayload.setMobileNumber(customerPrincipal.getContactNo());
        cvlpanInquiryPayload.setTatId(String.valueOf(customerPrincipal.getCpId()));
        cvlpanInquiryPayload.setPanNo(customerPrincipal.getTaxIdNo());

        AppPanInq appPanInq = new AppPanInq();
        appPanInq.setAppPanNo(customerPrincipal.getTaxIdNo());
        appPanInq.setAppDob(customerPrincipal.getDateOfBirth() != null ? dateFormat.format(
                customerPrincipal.getDateOfBirth()) : null);
        cvlpanInquiryPayload.setAppDOBINCORP(customerPrincipal.getDateOfBirth() != null ? dateFormat.format(customerPrincipal.getDateOfBirth()) : null);
        cvlpanInquiryPayload.setAppPanInq(appPanInq);
        if(cpAdlExt != null) {
            String cvlStatusCode = cpAdlExt.getCvlStatusCode();
            if(StringUtils.isEmpty(cvlStatusCode)) {
                RegistrationPayload registrationPayload = new RegistrationPayload();
                registrationPayload.setPan(customerPrincipal.getTaxIdNo());
                registrationPayload.setMobileNumber(customerPrincipal.getContactNo());
                registrationPayload.setProduct(TclMoneyfyConstants.MONEYFY);
                registrationPayload.setTatId(String.valueOf(customerPrincipal.getCpId()));
                CVLResponse cvlResponse = callPanInquiry(registrationPayload);
                if (cvlResponse != null && cvlResponse.getStatus() != null && cvlResponse.getStatus().getStatusMessage().equalsIgnoreCase("Success")) {
                    cvlStatusCode = cvlResponse.getKycRequired();
                    if (cvlStatusCode != null) {
                        Map<String, Object> cpAddlParams = EntityBuilder.build(CustomerPrincipalAddlExt.class)
                                .with(c -> c.setCustomerPrincipal(cpAdlExt.getCustomerPrincipal()))
                                .with(c -> c.setCvlStatusCode(cvlResponse.getKycRequired())).get();
                        hibernatePatchUtils.patchEntity(cpAddlParams, CustomerPrincipalAddlExt.class);
                    }
                }
            }
            if(!StringUtils.isEmpty(cvlStatusCode)) {
                CvlStatusCodes cvlStatusCodes = cvlStatusCodesService.getCvlStatusDescriptionBYCode(cvlStatusCode);
                cvlpanInquiryPayload.setAppKraCode(getKraCode(cvlStatusCodes, cvlStatusCode));
            }
        }
        return cvlSolicitPanService.cvlSolicitPan(cvlpanInquiryPayload);  //calling cdsl solicit pan
    }

    public String getKraCode(CvlStatusCodes cvlStatusCodes, String code) {
        if(cvlStatusCodes != null) {
            try {
                Field[] fields = CvlStatusCodes.class.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(cvlStatusCodes) != null) {
                        KraCode columnAnnotation = field.getAnnotation(KraCode.class);
                        if(code.equals(field.get(cvlStatusCodes))) {
                            return columnAnnotation.code();
                        }
                    }
                }
            }catch (Exception e){
                logger.error("Error occurred while getting kra code", e);
            }
        }
        return null;
    }

    public CVLResponse callPanInquiry(RegistrationPayload registrationPayload){
        CVLPANInquiryPayload cvlpanInquiryPayload = new CVLPANInquiryPayload();
        List<TclConstants> tclConstants = serviceManager.getTclConstantsService().getAllTclConstantsUsingApiName(TCLServiceConstants.API_NAMES.CVL_PASSWORD, TclMoneyfyConstants.MONEYFY);
        if (tclConstants != null && !tclConstants.isEmpty()) {
            for (TclConstants constant : tclConstants) {
                switch (constant.getConstantName()) {
                    case "passWord":
                        cvlpanInquiryPayload.setPassWord(constant.getAppConstantValue());
                        break;
                    case "passKey":
                        cvlpanInquiryPayload.setPassKey(constant.getAppConstantValue());
                        break;
                    case "userName":
                        cvlpanInquiryPayload.setUserName(constant.getAppConstantValue());
                        break;
                    case "posCode":
                        cvlpanInquiryPayload.setPosCode(constant.getAppConstantValue());
                        break;
                }
            }
        }
        cvlpanInquiryPayload.setMobileNumber(registrationPayload.getMobileNumber());
        cvlpanInquiryPayload.setTatId(registrationPayload.getTatId());
        cvlpanInquiryPayload.setPanNo(registrationPayload.getPan());
        LoggerClass.appLayerLogger.info("CVL Pan Inquiry request details:{} {}", cvlpanInquiryPayload.getPanNo(), cvlpanInquiryPayload.getTatId());
        return cvlpanInquiry.cvlPANInquiry(cvlpanInquiryPayload);
    }

    public String getCFDImagePath(String apiName) {
        List<TclConstants> constants = serviceManager.getTclConstantsService()
                .getAllTclConstantsUsingConstantName("Logo", apiName, MONEYFY);
        return !CollectionUtils.isEmpty(constants)? constants.get(0).getAppConstantValue() : null;
    }

    public String getAppCodeByCpId(Long cpId) {
        String userId = "";
        try {
            CustomerPrincipal customerPrincipal = customerPrincipalService.getCustomerPrincipal(cpId);
            return getAppCodeByCp(customerPrincipal);
        } catch (Exception e) {
            logger.error("Error while fetching App Code by Customer Principal");
        }
        return userId;
    }

    public String getAppCodeByCp(CustomerPrincipal customerPrincipal) {
        String userId = "";
        try {
            if (customerPrincipal != null) {
                Application application = applicationService.getApplicationByCustomerPrincipalAndProductType(
                        customerPrincipal, MONEYFY);
                if (application != null)
                    userId = application.getAppCode();
            }
        } catch (Exception e) {
            logger.error("Error while fetching App Code by Customer Principal");
        }
        return userId;
    }

    public String getCustomerFullName(CustomerPrincipal customerPrincipal) {
        String firstName = (customerPrincipal != null ? customerPrincipal.getFirstName() : null) != null ?
                customerPrincipal.getFirstName() : "";
        String middleName = (customerPrincipal != null ? customerPrincipal.getMiddleName() : null) != null ?
                customerPrincipal.getMiddleName() : "";
        String lastName = (customerPrincipal != null ? customerPrincipal.getLastName() : null) != null ?
                customerPrincipal.getLastName() : "";
        StringBuilder fullName = new StringBuilder();

        if (firstName != null) {
            fullName.append(firstName);
        }

        if (middleName != null) {
            if (fullName.length() > 0) {
                fullName.append(" ").append(middleName);
            } else {
                fullName.append(middleName);
            }
        }

        if (lastName != null) {
            if (fullName.length() > 0) {
                fullName.append(" ").append(lastName);
            } else {
                fullName.append(lastName);
            }
        }
        return fullName.toString();
    }

    public String getCommaSeparatedString(String existingString, String newString) {
        List<String> StringList;
        boolean flag = true;
        if (existingString == null || existingString.isEmpty() && newString != null && !newString.isEmpty()) {
            existingString = newString;
        }
        if (existingString != null && !existingString.isEmpty() && newString != null && !newString.isEmpty()) {
            StringList = new ArrayList<>(Arrays.asList(existingString.split(",")));
            while(StringList.size() > 5){
                StringList.remove(0);
                existingString = String.join(",", StringList);
            }
            if (StringList.size() > 0) {
                for (String value : StringList) {
                    if (value.equals(newString)) {
                        flag = false;
                        break;
                    }
                }
                if (flag && StringList.size() < 5) {
                    existingString = String.join(",", existingString, newString);
                } else if (flag && StringList.size() == 5) {
                    StringList.remove(0);
                    StringList.add(newString);
                    existingString = String.join(",", StringList);
                }
            }
        }
        return existingString;
    }

    public String getChequeImageDirPath(){
        List<TclConstants> constants = serviceManager.getTclConstantsService()
                .getAllTclConstantsUsingConstantName("CHEQUE_IMAGE_PATH", "CHEQUE_SIGNATURE_IMAGE", MONEYFY);
        return !CollectionUtils.isEmpty(constants)? constants.get(0).getAppConstantValue() : "";
    }

    public String getSignatureImageDirPath(){
        List<TclConstants> constants = serviceManager.getTclConstantsService()
                .getAllTclConstantsUsingConstantName("SIGNATURE_IMAGE_PATH", "CHEQUE_SIGNATURE_IMAGE", MONEYFY);
        return !CollectionUtils.isEmpty(constants)? constants.get(0).getAppConstantValue() : "";
    }

    public String getBaseUrl(){
        List<TclConstants> constants = serviceManager.getTclConstantsService()
                .getAllTclConstantsUsingConstantName("BASE_URL", "MONEYFY", MONEYFY);
        return !CollectionUtils.isEmpty(constants)? constants.get(0).getAppConstantValue() : "";
    }

    public List<Document> getSfdcDocs(Long cpId) {
        return documentDAO.getDocumentsByCpID(cpId, null);
    }

    public Boolean getSfdcDocFlag(List<Document> documents, String docType){
        Document document = null;
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(documents)) {
            document = documents.stream().filter(d -> d.getDocumentType() != null
                            && docType.equalsIgnoreCase(d.getDocumentType().getDocumentType()))
                    .findFirst().orElse(null);
        }
        if (document == null) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean getSfdcProofOfIdentityFlag(List<Document> documents) {
        Document document = null;
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(documents)) {
            document = documents.stream().filter(d -> d.getDocumentType() != null
                            && d.getDocumentType().getDocumentCode() != null
                            && (d.getDocumentType().getDocumentCode().contains("front") || d.getDocumentType().getDocumentCode().contains("back"))
                            && ("ind_aadhaar".equalsIgnoreCase(d.getDocumentType().getDocumentType())
                            || "ind_voter_id".equalsIgnoreCase(d.getDocumentType().getDocumentType())
                            || "ind_passport".equalsIgnoreCase(d.getDocumentType().getDocumentType())
                            || "ind_driving_license".equalsIgnoreCase(d.getDocumentType().getDocumentType())))
                    .findFirst().orElse(null);
        }
        if (document == null) {
            return false;
        } else {
            return true;
        }
    }

    *//**
     * Fetching records on the basis of batch size.
     * @param queryStr sql query.
     * @param fetchSize
     * @param batchSize how many records you want to fetch at one time.
     * @return return list of records acc. to batch size.
     *//*
    public List<Long> getRecordsByLimit(String queryStr, int fetchSize, int batchSize) {
        try (Session session = hibernateUtils.getSession()) {
            Query query = null;
            query = session.createQuery(queryStr);
            if (fetchSize > 0) {
                query.setFirstResult(fetchSize);
            }
            if (batchSize > 0) {
                query.setMaxResults(batchSize);
            }
            return query.list();
        } catch (Exception ex) {
            logger.error("Error while getting records by limit ", ex);
        }
        return Collections.emptyList();
    }



    public Boolean otpGenValidate(CustomerOtpDetails customerotpDetails, Integer otpCounter, LocalDateTime otpGenDate, CustomerPrincipal customerPrincipal, Long currentDate){
        if (customerotpDetails != null) {
            if (customerotpDetails.getOtpGenerationCounter() != null && customerotpDetails.getOtpGeneratedDate() != null) {
                otpCounter = customerotpDetails.getOtpGenerationCounter();
                otpGenDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(customerotpDetails.getOtpGeneratedDate()), TimeZone.getDefault().toZoneId());
            }
            long diffInMin = Duration.between(otpGenDate, LocalDateTime.now()).toMinutes();
            Integer otpGenCoolingPeriod = null;
            Integer otpGenCounterLkpVal = null;
            List<LookupValue> coolingPeriodLkpList = getAllLookupsByType(COOLING_PERIOD_LOOKUP);
            for (LookupValue lkpValue : coolingPeriodLkpList) {
                if (lkpValue.getDisplayName().equalsIgnoreCase(OTP_GENERATE_COOLING_PERIOD))
                    otpGenCoolingPeriod = Integer.parseInt(lkpValue.getDescription());
                if (lkpValue.getDisplayName().equalsIgnoreCase(OTP_GENERATE_COUNTER))
                    otpGenCounterLkpVal = Integer.parseInt(lkpValue.getDescription());
            }

            if (otpGenCounterLkpVal != null && otpGenCoolingPeriod != null &&
                    otpCounter >= otpGenCounterLkpVal & diffInMin <= otpGenCoolingPeriod) {
                LoggerClass.appLayerLogger.info("Exceeded the number of tries");
                return false;
            }
            if (otpGenCoolingPeriod != null && diffInMin > otpGenCoolingPeriod) {
                otpCounter = 0;
                customerotpDetails.setOtpGenerationCounter(0);
            }
        }
        return true;
    }
    public Boolean otpVerifyValidate(CustomerOtpDetails customerotpDetails, Integer otpCounter, LocalDateTime otpValDate, CustomerPrincipal customerPrincipal, Long currentDate) {

        if (customerotpDetails.getOtpValidateCounter() != null && customerotpDetails.getOtpValidatedDate() != null) {
            otpCounter = customerotpDetails.getOtpValidateCounter();
            otpValDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(customerotpDetails.getOtpValidatedDate()), TimeZone.getDefault().toZoneId());
        }
        long diffInMin = Duration.between(otpValDate, LocalDateTime.now()).toMinutes();
        Integer otpValCoolingPeriod = null;
        Integer otpValCounterLkpVal = null;
        List<LookupValue> coolingPeriodLkpList = getAllLookupsByType(COOLING_PERIOD_LOOKUP);
        for (LookupValue lkpValue : coolingPeriodLkpList) {
            if (lkpValue.getDisplayName().equalsIgnoreCase(OTP_VALIDATE_COOLING_PERIOD))
                otpValCoolingPeriod = Integer.parseInt(lkpValue.getDescription());
            if (lkpValue.getDisplayName().equalsIgnoreCase(OTP_VALIDATE_COUNTER))
                otpValCounterLkpVal = Integer.parseInt(lkpValue.getDescription());
        }

        if (otpValCounterLkpVal != null && otpValCoolingPeriod != null &&
                otpCounter >= otpValCounterLkpVal & diffInMin <= otpValCoolingPeriod) {
            LoggerClass.appLayerLogger.info("Exceeded the number of tries");
            return false;
        }
        if (otpValCoolingPeriod != null && diffInMin > otpValCoolingPeriod) {
            otpCounter = 0;
            customerotpDetails.setOtpValidateCounter(otpCounter);
        }
        return true;
    }

    public LookupValue getMfStatusByName(String mfStatusValue){
        return getLookupValueByName(mfStatusValue,MONEYFY_MF_STATUS_LKP_TYPE);
    }

    public String generateSixDigitOtp()
    {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);

    }

    public ResponseEntity<?> updateElasticPennyDropStatus(String cpId, String indexName) {
        LoggerClass.appLayerLogger.info("Request received to perform the updateElasticPennyDropStatus is : cpId : {}", cpId);
        LoggerClass.appLayerLogger.info("Request received to perform the updateElasticPennyDropStatus is : indexName : {}", indexName);
        ErrorResponseVO errorResponse = new ErrorResponseVO();
        BankDetailsVO bankDetailsVO = new BankDetailsVO();
        List<CpBankDtls> cpBankDtlsList;
        String bankStatus;
        StringBuilder append = null;
        ResponseEntity<String> responseEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder sbStr = new StringBuilder();

        try {
//            bankDetailsVO = objectMapper.readValue(cpId, BankDetailsVO.class);
            cpBankDtlsList = moneyfyCpBankDtlsService.getAccountDtlsbyCpId(Long.parseLong(cpId));
            for (CpBankDtls cpBankDtls : cpBankDtlsList) {
                String bankState = cpBankDtls.getBankState();
                if(bankState == null){
                    bankState = "";
                } else if (bankState.equalsIgnoreCase("1")) {
                    bankState = "C";
                } else if (bankState.equalsIgnoreCase("0")) {
                    bankState = "PR";
                } else if (bankState.equalsIgnoreCase("3")) {
                    bankState = "CR";
                } else if (bankState.equalsIgnoreCase("4")) {
                    bankState = "CIP";
                } else if (bankState.equalsIgnoreCase("2")) {
                    bankState = "PIP";
                } else {
                    bankState = "";
                }
                bankStatus = org.apache.commons.lang3.StringUtils.join(bankState, " | ");
                append = sbStr.append(bankStatus);
            }
            if (append != null)
                append.deleteCharAt(append.length() - 2);

            String pennyDropStatus = String.valueOf(append);
            bankDetailsVO.setBankStatus(pennyDropStatus);
            bankDetailsVO.setIndexName(indexName);
            bankDetailsVO.setTatId(cpId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject jsonObject = new JSONObject(bankDetailsVO);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String bankDetailsJson = objectMapper.writeValueAsString(jsonObject.toMap());
            HttpEntity<String> httpRequestEntity = new HttpEntity<>(bankDetailsJson, headers);
            String url = UriComponentsBuilder.fromHttpUrl(getHomePageUrl(null, serviceId) + updateElasticPennyDropStatusUrl)
                    .toUriString();
            LoggerClass.appLayerLogger.info("URL " + url);
            try {
                LoggerClass.appLayerLogger.info("Started - External call");
                responseEntity = restTemplate.postForEntity(url, httpRequestEntity, String.class);
                LoggerClass.appLayerLogger.info("Ended - External call");
                if (responseEntity != null) {
                    bankDetailsVO.setStatus(SUCCESS);
                    bankDetailsVO.setDescription(SUCCESS);
                } else {
                    bankDetailsVO.setStatus(FAILURE);
                    bankDetailsVO.setDescription(FAILURE_MESSAGE);
                }
            } catch (Exception e) {
                errorResponse.setStatus(FAILURE);
                errorResponse.setDescription(FAILURE_MESSAGE);
                logger.error("Error occurred while getting the updateElasticPennyDropStatus : ", e);
            }
            return new ResponseEntity<>(bankDetailsVO, HttpStatus.OK);
        } catch (Exception e) {
            errorResponse.setStatus(FAILURE);
            errorResponse.setDescription(FAILURE_MESSAGE);
            logger.error("Error occurred while getting the updateElasticPennyDropStatus : ", e);
        }
        return new ResponseEntity<>(errorResponse.toString(), HttpStatus.OK);
    }

    public String getHomePageUrl(RequestContext context, String serviceId) {

        try {
            LoggerClass.appLayerLogger.info("getHomePageUrl() RequestContext :: {}, serviceId :: {}", context, serviceId);
            if (serviceId != null) {
                DiscoveryEnabledServer instance = (DiscoveryEnabledServer) new Mirror().on(loadBalancer.choose(serviceId)).get().field(DLP_SERVER);
                return instance.getInstanceInfo().getHomePageUrl();
            }
        } catch (Exception e) {
            logger.error("While generating url it throws error:{} ", e.getMessage());
        }
        return null;
    }

    public String getAbsoluteFilePath(String dirName, String fileName){
        String absoluteFilePath;
        File directory;
        try {
            if (!isEndsWithFileSeparator(dirName)) {
                dirName = dirName + File.separator;
            }

            directory = new File(uploadLoc + dirName);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            absoluteFilePath = uploadLoc + dirName + fileName;

        } catch (Exception e) {
            logger.error("MoneyfyUtils IOException. While creating absolute file path :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        return absoluteFilePath;
    }

    public DigilockerAadhaarAddressVO getDigilockerAddressVO(String address, String address1, String address2, String landmark, String city){
        DigilockerAadhaarAddressVO digilockerAadhaarAddressVO = new DigilockerAadhaarAddressVO();
        String addressNew1 = address +" "+ address1;
        addressNew1 = addressNew1.trim();
        if(StringUtils.isEmpty(addressNew1)){
            if(!StringUtils.isEmpty(address2) && !StringUtils.isEmpty(address2.trim()))
                digilockerAadhaarAddressVO.setAddress1(address2);
            else if(!StringUtils.isEmpty(landmark) && !StringUtils.isEmpty(landmark.trim()))
                digilockerAadhaarAddressVO.setAddress1(landmark);
            else
                digilockerAadhaarAddressVO.setAddress1(city);
        }
        else if(addressNew1.length()>120){
            if(!StringUtils.isEmpty(address1))
                digilockerAadhaarAddressVO.setAddress1(address1);
            else
                digilockerAadhaarAddressVO.setAddress1(address);
        }else{
            digilockerAadhaarAddressVO.setAddress1(addressNew1);
        }
        if(!StringUtils.isEmpty(address2))
            digilockerAadhaarAddressVO.setAddress2(address2);
        else if(!StringUtils.isEmpty(landmark)){
            digilockerAadhaarAddressVO.setAddress2(landmark);
        }else
            digilockerAadhaarAddressVO.setAddress2(city);

        if(!StringUtils.isEmpty(landmark)){
            digilockerAadhaarAddressVO.setAddress3(landmark);
        }
        else
            digilockerAadhaarAddressVO.setAddress3(city);
        return digilockerAadhaarAddressVO;
    }

    public String getLockinPeriod(Integer lockinPeriod){
        if(lockinPeriod!=null){
            NumberFormat formatter = new DecimalFormat("0.0");

            String formmatedFloatValue = formatter.format(lockinPeriod/365);


            return formmatedFloatValue + " years";
        }
        return null;
    }

    public void saveLogServicesDetails(ServiceLogReport serviceLogReport){
        if(serviceLogReport != null && serviceLogReport.getServiceName() != null && (serviceLogReport.getRequest() != null || serviceLogReport.getResponse() != null)){
            if(serviceLogReport.getCreatedDate() == null){
                serviceLogReport.setCreatedDate(new Date());
            }
            if(serviceLogReport.getUpdatedDate() == null){
                serviceLogReport.setUpdatedDate(new Date());
            }
            servicesLogReportService.saveServicesLogReport(serviceLogReport);
        }
    }

    public List<?> getEntitiesFromCache(String cacheKey, Supplier<?> supplier) {
        if (cacheKey != null) {
            List<?> list = null;
            if (cache != null) {
                list = (List<?>) cache.get(cacheKey);
            }
            if (list == null) {
                list = (List<?>) supplier.get();
                if (list != null && cache != null) {
                    cache.put(cacheKey, list);
                }
            }
            return list;
        }
        return null;
    }*/
}
