package apiCalls.contract.builders;

import apiCalls.Utils.volBuilders.*;
import apiCalls.enums.TrackingStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Contract tests validating that builder classes serialise to JSON with
 * field names matching the olcs-transfer API contract.
 *
 * These tests catch:
 * - @JsonProperty typos or renames
 * - Missing fields in builders
 * - Broken Jackson serialisation
 * - Null-inclusion changes
 *
 * @see <a href="https://github.com/dvsa/olcs-transfer">olcs-transfer</a>
 */
@DisplayName("Builder serialisation matches olcs-transfer contract")
class BuilderContractTest {

    private static JsonNode toJson(Object builder) throws JsonProcessingException {
        var m = new ObjectMapper();
        return m.readTree(m.writeValueAsString(builder));
    }

    private static Set<String> jsonFieldNames(JsonNode node) {
        return StreamSupport.stream(
                ((Iterable<String>) node::fieldNames).spliterator(), false
        ).collect(Collectors.toSet());
    }

    @Nested
    @DisplayName("ApplicationBuilder → CreateApplication command")
    class ApplicationBuilderContract {

        @Test
        @DisplayName("serialises with correct field names")
        void fieldNames() throws JsonProcessingException {
            var builder = new ApplicationBuilder()
                    .withOperatorType("lcat_gv")
                    .withLicenceType("ltyp_sn")
                    .withNiFlag("N")
                    .withOrganisation("123")
                    .withAppliedVia("applied_via_post")
                    .withVehicleType("app_veh_type_mixed")
                    .withLgvDeclarationConfirmation("1");

            var json = toJson(builder);
            assertThat(jsonFieldNames(json)).containsExactlyInAnyOrder(
                    "operatorType", "licenceType", "niFlag", "organisation",
                    "appliedVia", "vehicleType", "lgvDeclarationConfirmation"
            );
        }

        @Test
        @DisplayName("excludes null fields from payload")
        void nullExclusion() throws JsonProcessingException {
            var builder = new ApplicationBuilder()
                    .withOperatorType("lcat_gv")
                    .withLicenceType("ltyp_sn");

            var json = toJson(builder);
            assertThat(json.has("vehicleType")).isFalse();
            assertThat(json.has("niFlag")).isFalse();
        }
    }

    @Nested
    @DisplayName("AddressBuilder → Address partial")
    class AddressBuilderContract {

        @Test
        @DisplayName("serialises with correct field names")
        void fieldNames() throws JsonProcessingException {
            var builder = new AddressBuilder()
                    .withAddressLine1("123 Test St")
                    .withTown("Leeds")
                    .withPostcode("LS1 1AA")
                    .withCountryCode("GB");

            var json = toJson(builder);
            assertThat(jsonFieldNames(json)).containsExactlyInAnyOrder(
                    "addressLine1", "town", "postcode", "countryCode"
            );
        }

        @Test
        @DisplayName("supports all address lines")
        void allLines() throws JsonProcessingException {
            var builder = new AddressBuilder()
                    .withAddressLine1("Line 1")
                    .withAddressLine2("Line 2")
                    .withAddressLine3("Line 3")
                    .withAddressLine4("Line 4")
                    .withTown("Town")
                    .withPostcode("AB1 2CD")
                    .withCountryCode("GB")
                    .withVersion("1");

            var json = toJson(builder);
            assertThat(jsonFieldNames(json)).containsExactlyInAnyOrder(
                    "version", "addressLine1", "addressLine2", "addressLine3",
                    "addressLine4", "town", "postcode", "countryCode"
            );
        }
    }

    @Nested
    @DisplayName("ContactDetailsBuilder → ContactDetails partial")
    class ContactDetailsBuilderContract {

        @Test
        @DisplayName("field names map to olcs-transfer contract")
        void fieldNameMapping() throws JsonProcessingException {
            // olcs-transfer expects: email, fao, phone_primary, person, address
            var builder = new ContactDetailsBuilder()
                    .withEmailAddress("test@example.com")
                    .withFullName("Test User")
                    .withPhoneNumber("01onal234567");

            var json = toJson(builder);
            // email (not emailAddress) — mapped via @JsonProperty
            assertThat(json.has("email")).isTrue();
            // fao (not fullName) — mapped via @JsonProperty
            assertThat(json.has("fao")).isTrue();
            // phone_primary (not phoneNumber) — mapped via @JsonProperty
            assertThat(json.has("phone_primary")).isTrue();
        }

        @Test
        @DisplayName("nested person and address serialise correctly")
        void nestedObjects() throws JsonProcessingException {
            var person = new PersonBuilder()
                    .withTitle("title_mr")
                    .withForename("John")
                    .withFamilyName("Smith");

            var address = new AddressBuilder()
                    .withAddressLine1("123 Test St")
                    .withTown("Leeds")
                    .withPostcode("LS1 1AA")
                    .withCountryCode("GB");

            var builder = new ContactDetailsBuilder()
                    .withEmailAddress("test@example.com")
                    .withPerson(person)
                    .withAddress(address);

            var json = toJson(builder);
            assertThat(json.get("person").has("forename")).isTrue();
            assertThat(json.get("person").has("familyName")).isTrue();
            assertThat(json.get("address").has("addressLine1")).isTrue();
            assertThat(json.get("address").has("postcode")).isTrue();
        }
    }

    @Nested
    @DisplayName("TrackingBuilder → ApplicationTracking partial")
    class TrackingBuilderContract {

        // All field names expected by olcs-transfer ApplicationTracking
        private static final Set<String> EXPECTED_STATUS_FIELDS = Set.of(
                "addressesStatus", "businessDetailsStatus", "businessTypeStatus",
                "communityLicencesStatus", "conditionsUndertakingsStatus",
                "convictionsPenaltiesStatus", "discsStatus", "financialEvidenceStatus",
                "financialHistoryStatus", "licenceHistoryStatus", "operatingCentresStatus",
                "peopleStatus", "safetyStatus", "taxiPhvStatus", "transportManagersStatus",
                "typeOfLicenceStatus", "declarationsInternalStatus",
                "vehiclesDeclarationsStatus", "vehiclesPsvStatus", "vehiclesStatus",
                "vehiclesSizeStatus", "psvOperateSmallStatus", "psvOperateLargeStatus",
                "psvSmallConditionsStatus", "psvOperateNoveltyStatus",
                "psvSmallPartWrittenStatus", "psvDocumentaryEvidenceSmallStatus",
                "psvDocumentaryEvidenceLargeStatus", "psvMainOccupationUndertakingsStatus"
        );

        @Test
        @DisplayName("withAllStatuses populates all 29 status fields")
        void withAllStatuses() throws JsonProcessingException {
            var builder = new TrackingBuilder()
                    .withId("123")
                    .withVersion(1)
                    .withAllStatuses(TrackingStatus.ACCEPTED.asString());

            var json = toJson(builder);
            var fields = jsonFieldNames(json);

            // Must contain id + version + all 29 status fields
            assertThat(fields).containsAll(EXPECTED_STATUS_FIELDS);
            assertThat(fields).contains("id", "version");

            // All status values should be "1" (ACCEPTED)
            for (String field : EXPECTED_STATUS_FIELDS) {
                assertThat(json.get(field).asText())
                        .as("Field %s should be ACCEPTED", field)
                        .isEqualTo("1");
            }
        }

        @Test
        @DisplayName("contains exactly 29 status fields (no missing, no extras)")
        void statusFieldCount() throws JsonProcessingException {
            var builder = new TrackingBuilder()
                    .withId("1")
                    .withVersion(1)
                    .withAllStatuses("0");

            var json = toJson(builder);
            var statusFields = jsonFieldNames(json);
            statusFields.remove("id");
            statusFields.remove("version");

            assertThat(statusFields).hasSameSizeAs(EXPECTED_STATUS_FIELDS);
            assertThat(statusFields).containsExactlyInAnyOrderElementsOf(EXPECTED_STATUS_FIELDS);
        }

        @Test
        @DisplayName("individual status setters produce correct field names")
        void individualSetters() throws JsonProcessingException {
            var builder = new TrackingBuilder()
                    .withId("1")
                    .withVersion(1)
                    .withAddressesStatus("1")
                    .withVehiclesPsvStatus("2")
                    .withPsvOperateSmallStatus("3");

            var json = toJson(builder);
            assertThat(json.get("addressesStatus").asText()).isEqualTo("1");
            assertThat(json.get("vehiclesPsvStatus").asText()).isEqualTo("2");
            assertThat(json.get("psvOperateSmallStatus").asText()).isEqualTo("3");
        }
    }

    @Nested
    @DisplayName("PersonBuilder → Person partial")
    class PersonBuilderContract {

        @Test
        @DisplayName("serialises with correct field names")
        void fieldNames() throws JsonProcessingException {
            var builder = new PersonBuilder()
                    .withTitle("title_mr")
                    .withForename("John")
                    .withFamilyName("Smith")
                    .withBirthDate("1990-01-01");

            var json = toJson(builder);
            assertThat(jsonFieldNames(json)).containsExactlyInAnyOrder(
                    "title", "forename", "familyName", "birthDate"
            );
        }
    }
}
