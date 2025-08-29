package apiCalls.enums;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

public enum TrafficArea {
    NORTH_EAST("B"),
    NORTH_WEST("C"),
    MIDLANDS("D"),
    EAST("F"),
    WALES("G"),
    WEST("H"),
    LONDON("K"),
    SCOTLAND("M"),
    NORTHERN_IRELAND("N");

    private final String trafficAreas;
    static Random random = new Random();

    private TrafficArea(String trafficAreas) {
        this.trafficAreas = trafficAreas;
    }

    public String value() {
        return trafficAreas;
    }

    public static String getPostCode(TrafficArea trafficArea) {
        String postCode;
        switch (trafficArea) {
            case NORTH_EAST:
                int district = random.nextInt(24) + 1;
                int sector = random.nextInt(9) + 1;
                postCode = String.format("BD%d %d%s", district, sector, randomString());
                break;
            case NORTH_WEST:
                district = random.nextInt(50) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("M%d %d%s", district, sector, randomString());
                break;
            case MIDLANDS:
                district = random.nextInt(98) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("B%d %d%s", district, sector, randomString());
                break;
            case EAST:
                district = random.nextInt(33) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("IP%d %d%s", district, sector, randomString());
                break;
            case WALES:
                district = random.nextInt(48) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("CF%d %d%s", district, sector, randomString());
                break;
            case WEST:
                district = random.nextInt(49) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("OX%d %d%s", district, sector, randomString());
                break;
            case LONDON:
                district = random.nextInt(20) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("E%d %d%s", district, sector, randomString());
                break;
            case SCOTLAND:
                district = random.nextInt(55) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("EH%d %d%s", district, sector, randomString());
                break;
            case NORTHERN_IRELAND:
                district = random.nextInt(94) + 1;
                sector = random.nextInt(9) + 1;
                postCode = String.format("BT%d %d%s", district, sector, randomString());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + trafficArea);
        }
        return postCode;
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(2).toUpperCase();
    }

    public static int randomTwoDigitInt() {
        return random.nextInt(99);
    }

    public static int randomOneDigitInt() {
        return random.nextInt(9);
    }

    public static TrafficArea[] trafficAreaList() {
        return new TrafficArea[]{TrafficArea.NORTH_EAST, TrafficArea.NORTH_WEST, TrafficArea.MIDLANDS,
                TrafficArea.EAST, TrafficArea.WALES, TrafficArea.WEST, TrafficArea.LONDON,
                TrafficArea.SCOTLAND, TrafficArea.NORTHERN_IRELAND};
    }
}