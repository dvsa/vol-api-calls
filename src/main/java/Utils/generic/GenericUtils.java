//package Utils.generic;
//
//
//import activesupport.http.RestUtils;
//import io.restassured.response.ValidatableResponse;
//
//
//
//public class GenericUtil {
//
//    private String registrationNumber;
//    private static final String zipFilePath = "/src/test/resources/ESBR.zip";
//
//    public String getRegistrationNumber() {
//        return registrationNumber;
//    }
//
//    public void setRegistrationNumber(String registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }
//
//
////    public void modifyXML(String dateState, int months) {
////
////        CreateApplication createApplication = new CreateApplication();
////        try {
////            String xmlFile = "./src/test/resources/ESBR/ESBR.xml";
////            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
////            DocumentBuilder xmlBuilder = documentBuilderFactory.newDocumentBuilder();
////            Document xmlDoc = xmlBuilder.parse(xmlFile);
////            //update licence number
////            NodeList nodeList = xmlDoc.getElementsByTagName("*");
////            for (int i = 0; i < nodeList.getLength(); i++) {
////                Node node = nodeList.item(i);
////                if (node.getNodeType() == Node.ELEMENT_NODE) {
////                    // do something with the current element
////
////                    if ("StartDate".equals(node.getNodeName())) {
////                        node.setTextContent(getDates(dateState, months));
////                    }
////                    if ("LicenceNumber".equals(node.getNodeName())) {
////                        node.setTextContent(createApplication.getLicenceNumber());
////                    }
////                    if ("RegistrationNumber".equals(node.getNodeName())) {
////                        String getContent = node.getTextContent();
////                        int newRegNumber = Integer.parseInt(getContent);
////                        setRegistrationNumber(String.valueOf(newRegNumber + 1));
////                        node.setTextContent(getRegistrationNumber());
////                    }
////                    if ("TrafficAreaName".equals(node.getNodeName())) {
////                        switch (world.updateLicence.getTrafficAreaName()) {
////                            case "Wales":
////                                node.setTextContent("Welsh");
////                                break;
////                            case "Scotland":
////                                node.setTextContent("Scottish");
////                                break;
////                            default:
////                                node.setTextContent("WestMidlands");
////                                break;
////                        }
////
////                    }
////                }
////            }
////            // write the content on console
////            TransformerFactory transformerFactory = TransformerFactory.newInstance();
////            Transformer transformer = transformerFactory.newTransformer();
////            DOMSource source = new DOMSource(xmlDoc);
////            System.out.println("-----------Modified File-----------");
////
////            StreamResult result = new StreamResult(new File(xmlFile));
////            transformer.transform(source, result);
////            StreamResult consoleResult = new StreamResult(System.out);
////            transformer.transform(source, consoleResult);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    public static String getDates(String state, int months) {
////        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        LocalDateTime now = LocalDateTime.now();
////        String myDate = null;
////
////        switch (state) {
////            case "futureMonth":
////                myDate = date.format(now.plusMonths(months));
////                break;
////            case "futureDay":
////                myDate = date.format(now.plusDays(months));
////                break;
////            case "past":
////                myDate = date.format(now.minusMonths(months));
////                break;
////            case "current":
////                myDate = date.format(now);
////                break;
////            default:
////                System.out.println(state + ": does not exist, needs to either be 'current', or 'past' or 'futureDay' or 'futureMonth'");
////        }
////        return myDate;
////    }
////
////    public static void zipFolder() {
////        /*
////        / Uses Open source util zt-zip https://github.com/zeroturnaround/zt-zip
////         */
////        ZipUtil.pack(new File("./src/test/resources/ESBR"), new File("./src/test/resources/ESBR.zip"));
////    }
////
////    public void executeJenkinsBatchJob(String command) throws Exception {
////        HashMap<String, String> jenkinsParams = new HashMap<>();
////        jenkinsParams.put(JenkinsParameterKey.NODE.toString(), String.format("api&&%s&&olcs", Properties.get("env", true)));
////        jenkinsParams.put(JenkinsParameterKey.JOB.toString(), command);
////
////        Jenkins.trigger(Jenkins.Job.BATCH_PROCESS_QUEQUE, jenkinsParams);
////    }
////
////    public String stripNonAlphanumericCharacters(String value) {
////        return value.replaceAll("[^A-Za-z0-9]", "");
////    }
////
////    public String stripAlphaCharacters(String value) {
////        return value.replaceAll("[^0-9]", "");
////    }
////
////    public static String returnNthNumberSequenceInString(String value, int num) {
////        return value.replaceAll("[^\\d]+", " ").split(" ")[num];
////    }
////
////    public static LocalDate getFutureDate(@NotNull int month) {
////        LocalDate date = LocalDate.now().plusMonths(month);
////        return date;
////    }
////
////    public static LocalDate getPastDate(@NotNull int years) {
////        LocalDate date = LocalDate.now().minusYears(years);
////        return date;
////    }
////
////    public static String getCurrentDate(String datePattern) {
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
////        String formatDateTime = LocalDate.now().format(formatter);
////        return formatDateTime;
////    }
////
////    public static String getFutureFormattedDate(@NotNull int months, String datePattern) {
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
////        String formatDateTime = LocalDate.now().plusMonths(months).format(formatter);
////        return formatDateTime;
////    }
////
////    public String confirmationPanel(String locator, String cssValue) throws IllegalBrowserException, MalformedURLException {
////        return Browser.navigate().findElement(By.xpath(locator)).getCssValue(cssValue);
////    }
////
////    public void switchTab(int tab) throws IllegalBrowserException, MalformedURLException {
////        ArrayList<String> tabs = new ArrayList<>(Browser.navigate().getWindowHandles());
////        Browser.navigate().switchTo().window(tabs.get(tab));
////    }
////
////    public String readFileAsString(String fileName) throws Exception {
////        String data = new String(Files.readAllBytes(Paths.get(fileName)));
////        return data;
////    }
////
////    public static int getRandomNumberInts(int min, int max){
////        Random random = new Random();
////        return random.ints(min,(max+1)).findFirst().getAsInt();
////    }
////
////    public void writeToFile(String userId, String password, String fileName) throws Exception {
////        String CSV_HEADERS = "Username,Password";
////
////        FileWriter fileWriter = new FileWriter(fileName, true);
////        BufferedWriter writer = new BufferedWriter(fileWriter);
////        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
////
////        if (!searchForString(fileName, CSV_HEADERS)) {
////            csvPrinter.printRecord((Object[]) CSV_HEADERS.split(","));
////            csvPrinter.printRecord(Arrays.asList(userId, password));
////            csvPrinter.flush();
////        } else {
////            csvPrinter.printRecord(Arrays.asList(userId, password));
////            csvPrinter.flush();
////        }
////    }
////
////    private boolean searchForString(String file, String searchText) throws IOException {
////        boolean foundIt;
////        File f = new File(file);
////        if (f.exists() && (FileUtils.readFileToString(new File(file), "UTF-8").contains(searchText)))
////            foundIt = true;
////        else {
////            System.out.println("File not found or text not found");
////            foundIt = false;
////        }
////        return foundIt;
////    }
////
////    public static Scanner scanText(String input, String delimeter){
////        Scanner scanner = new Scanner(input);
////        scanner.useDelimiter(delimeter);
////        return scanner;
////    }
////
////    public boolean returnFeeStatus(String searchTerm) throws MalformedURLException, IllegalBrowserException {
////        return Browser.navigate().findElements(By.xpath("//*[contains(@class,'status')]")).stream().anyMatch(a -> a.getText().contains(searchTerm.toUpperCase()));
////    }
////
////    public static String readLineFromFile(String fileLocation, int lineNumber) throws IOException {
////        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))){
////            String line = null;
////            String prevLine = null;
////            int lineCounter = 0;
////            while (lineNumber == -1 ? (line = br.readLine()) != null : lineCounter <= lineNumber) {
////                line = br.readLine();
////                prevLine = line;
////                lineCounter++;
////            }
////            br.close();
////            if (lineNumber == -1){
////                return prevLine;
////            } else {
////                return line;
////            }
////        }
////    }
////
////    public static String readLastLineFromFile(String fileLocation) throws IOException {
////        return readLineFromFile(fileLocation, -1);
////    }
////
////    public void writeLineToFile(String data, String fileLocation) throws IOException {
////        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation, true))){
////                bw.append(data);
////                bw.newLine();
////        }
////    }
//
//    public static String retrieveAPIData(String url, String jsonPath, String defaultReturn) {
//        Headers.headers.put("x-pid", adminApiHeader());
//        ValidatableResponse response = RestUtils.get(url, Headers.getHeaders());
//        try {
//            return response.extract().response().jsonPath().getString(jsonPath);
//        } catch (NullPointerException ne) {
//            return defaultReturn;
//        }
//    }
//}
//
