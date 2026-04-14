package apiCalls.enums;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public enum TrafficArea {
    NORTH_EAST("B", "BD", 24),
    NORTH_WEST("C", "M", 50),
    MIDLANDS("D", "B", 98),
    EAST("F", "IP", 33),
    WALES("G", "CF", 48),
    WEST("H", "OX", 49),
    LONDON("K", "E", 20),
    SCOTLAND("M", "EH", 55),
    NORTHERN_IRELAND("N", "BT", 94);

    private final String value;
    private final String postcodePrefix;
    private final int maxDistrict;
    private static final Pattern POSTCODE_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private static final int MAX_POSTCODE_GENERATION_ATTEMPTS = 3;

    TrafficArea(String value, String postcodePrefix, int maxDistrict) {
        this.value = value;
        this.postcodePrefix = postcodePrefix;
        this.maxDistrict = maxDistrict;
    }

    public String value() {
        return value;
    }

    public static String getPostCode(TrafficArea trafficArea) {
        for (int attempt = 1; attempt <= MAX_POSTCODE_GENERATION_ATTEMPTS; attempt++) {
            var postCode = trafficArea.generatePostCode();
            if (isValidPostcode(postCode)) {
                return postCode;
            }
            System.err.println("Generated postcode '%s' failed validation (attempt %d/%d). Retrying..."
                    .formatted(postCode, attempt, MAX_POSTCODE_GENERATION_ATTEMPTS));
        }
        throw new IllegalStateException("Failed to generate valid postcode for %s after %d attempts"
                .formatted(trafficArea, MAX_POSTCODE_GENERATION_ATTEMPTS));
    }

    public static boolean isValidPostcode(String postcode) {
        return postcode != null && !postcode.isEmpty() && POSTCODE_VALIDATION_PATTERN.matcher(postcode).matches();
    }

    private String generatePostCode() {
        var random = ThreadLocalRandom.current();
        int district = random.nextInt(maxDistrict) + 1;
        int sector = random.nextInt(9) + 1;
        return "%s%d %d%s".formatted(postcodePrefix, district, sector, randomString());
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(2).toUpperCase();
    }

    /**
     * @deprecated Use {@link #values()} instead.
     */
    @Deprecated(forRemoval = true)
    public static TrafficArea[] trafficAreaList() {
        return values();
    }
}