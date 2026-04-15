package apiCalls.contract.enums;

import apiCalls.enums.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Contract tests validating that enum values in vol-api-calls match
 * the InArray validators defined in olcs-transfer Command/Query classes.
 *
 * If these tests fail, it means the backend contract has changed and
 * the enum values here need updating to match.
 *
 * @see <a href="https://github.com/dvsa/olcs-transfer">olcs-transfer</a>
 */
@DisplayName("Enum values match olcs-transfer contract")
class EnumContractTest {

    @Nested
    @DisplayName("OperatorType → CreateApplication.operatorType")
    class OperatorTypeContract {
        // olcs-transfer: InArray {"lcat_gv", "lcat_psv"}
        @Test void goods()  { assertThat(OperatorType.GOODS.asString()).isEqualTo("lcat_gv"); }
        @Test void psv()    { assertThat(OperatorType.PUBLIC.asString()).isEqualTo("lcat_psv"); }
        @Test void count()  { assertThat(OperatorType.values()).hasSize(2); }
    }

    @Nested
    @DisplayName("LicenceType → CreateApplication.licenceType")
    class LicenceTypeContract {
        // olcs-transfer: InArray {"ltyp_r", "ltyp_sn", "ltyp_si", "ltyp_sr"}
        @Test void restricted()             { assertThat(LicenceType.RESTRICTED.asString()).isEqualTo("ltyp_r"); }
        @Test void standardNational()       { assertThat(LicenceType.STANDARD_NATIONAL.asString()).isEqualTo("ltyp_sn"); }
        @Test void standardInternational()  { assertThat(LicenceType.STANDARD_INTERNATIONAL.asString()).isEqualTo("ltyp_si"); }
        @Test void specialRestricted()      { assertThat(LicenceType.SPECIAL_RESTRICTED.asString()).isEqualTo("ltyp_sr"); }
        @Test void count()                  { assertThat(LicenceType.values()).hasSize(4); }
    }

    @Nested
    @DisplayName("BusinessType → RegisterUserSelfserve.businessType")
    class BusinessTypeContract {
        // olcs-transfer: InArray {"org_t_p", "org_t_pa", "org_t_rc", "org_t_llp", "org_t_st"}
        @Test void limitedCompany()    { assertThat(BusinessType.LIMITED_COMPANY.asString()).isEqualTo("org_t_rc"); }
        @Test void soleTrader()        { assertThat(BusinessType.SOLE_TRADER.asString()).isEqualTo("org_t_st"); }
        @Test void partnership()       { assertThat(BusinessType.PARTNERSHIP.asString()).isEqualTo("org_t_p"); }
        @Test void limitedPartnership(){ assertThat(BusinessType.LIMITED_PARTNERSHIP.asString()).isEqualTo("org_t_llp"); }
        @Test void other()             { assertThat(BusinessType.OTHER.asString()).isEqualTo("org_t_pa"); }
        @Test void count()             { assertThat(BusinessType.values()).hasSize(5); }
    }

    @Nested
    @DisplayName("VehicleType → CreateApplication.vehicleType")
    class VehicleTypeContract {
        // olcs-transfer: InArray {"app_veh_type_mixed", "app_veh_type_lgv"}
        @Test void mixed()   { assertThat(VehicleType.MIXED_FLEET.asString()).isEqualTo("app_veh_type_mixed"); }
        @Test void lgvOnly() { assertThat(VehicleType.LGV_ONLY_FLEET.asString()).isEqualTo("app_veh_type_lgv"); }
        @Test void count()   { assertThat(VehicleType.values()).hasSize(2); }
    }

    @Nested
    @DisplayName("TransportManagerType → Tm/Create.type")
    class TransportManagerTypeContract {
        // olcs-transfer: InArray {"tm_t_e", "tm_t_i", "tm_t_b"}
        @Test void internal() { assertThat(TransportManagerType.INTERNAL.asString()).isEqualTo("tm_t_e"); }
        @Test void external() { assertThat(TransportManagerType.EXTERNAL.asString()).isEqualTo("tm_t_i"); }
        @Test void count()    { assertThat(TransportManagerType.values()).hasSize(2); }
    }

    @Nested
    @DisplayName("TrackingStatus → ApplicationTracking InArray")
    class TrackingStatusContract {
        // olcs-transfer: InArray {"0", "1", "2", "3"}
        @Test void notStarted()  { assertThat(TrackingStatus.NOT_STARTED.asString()).isEqualTo("0"); }
        @Test void accepted()    { assertThat(TrackingStatus.ACCEPTED.asString()).isEqualTo("1"); }
        @Test void inProgress()  { assertThat(TrackingStatus.IN_PROGRESS.asString()).isEqualTo("2"); }
        @Test void updated()     { assertThat(TrackingStatus.UPDATED.asString()).isEqualTo("3"); }
        @Test void count()       { assertThat(TrackingStatus.values()).hasSize(4); }
    }

    @Nested
    @DisplayName("TrafficArea → traffic area codes")
    class TrafficAreaContract {
        @Test void northEast()  { assertThat(TrafficArea.NORTH_EAST.value()).isEqualTo("B"); }
        @Test void northWest()  { assertThat(TrafficArea.NORTH_WEST.value()).isEqualTo("C"); }
        @Test void midlands()   { assertThat(TrafficArea.MIDLANDS.value()).isEqualTo("D"); }
        @Test void east()       { assertThat(TrafficArea.EAST.value()).isEqualTo("F"); }
        @Test void wales()      { assertThat(TrafficArea.WALES.value()).isEqualTo("G"); }
        @Test void west()       { assertThat(TrafficArea.WEST.value()).isEqualTo("H"); }
        @Test void london()     { assertThat(TrafficArea.LONDON.value()).isEqualTo("K"); }
        @Test void scotland()   { assertThat(TrafficArea.SCOTLAND.value()).isEqualTo("M"); }
        @Test void ni()         { assertThat(TrafficArea.NORTHERN_IRELAND.value()).isEqualTo("N"); }
        @Test void count()      { assertThat(TrafficArea.values()).hasSize(9); }
    }

    @Nested
    @DisplayName("EnforcementArea → enforcement area codes")
    class EnforcementAreaContract {
        @Test void northEast()  { assertThat(EnforcementArea.NORTH_EAST.value()).isEqualTo("EA-B"); }
        @Test void northWest()  { assertThat(EnforcementArea.NORTH_WEST.value()).isEqualTo("EA-C"); }
        @Test void midlands()   { assertThat(EnforcementArea.MIDLANDS.value()).isEqualTo("EA-D"); }
        @Test void east()       { assertThat(EnforcementArea.EAST.value()).isEqualTo("EA-F"); }
        @Test void wales()      { assertThat(EnforcementArea.WALES.value()).isEqualTo("EA-E"); }
        @Test void west()       { assertThat(EnforcementArea.WEST.value()).isEqualTo("EA-J"); }
        @Test void london()     { assertThat(EnforcementArea.LONDON.value()).isEqualTo("EA-H"); }
        @Test void scotland()   { assertThat(EnforcementArea.SCOTLAND.value()).isEqualTo("EA-A"); }
        @Test void ni()         { assertThat(EnforcementArea.NORTHERN_IRELAND.value()).isEqualTo("EA-N"); }
        @Test void count()      { assertThat(EnforcementArea.values()).hasSize(9); }
    }

    @Nested
    @DisplayName("UserRoles → CreateUser.roles")
    class UserRolesContract {
        @Test void systemAdmin()       { assertThat(UserRoles.SYSTEM_ADMIN.asString()).isEqualTo("system-admin"); }
        @Test void internalAdmin()     { assertThat(UserRoles.INTERNAL_ADMIN.asString()).isEqualTo("internal-admin"); }
        @Test void limitedReadOnly()   { assertThat(UserRoles.INTERNAL_LIMITED_READ_ONLY.asString()).isEqualTo("internal-limited-read-only"); }
        @Test void readOnly()          { assertThat(UserRoles.INTERNAL_READ_ONLY.asString()).isEqualTo("internal-read-only"); }
        @Test void caseWorker()        { assertThat(UserRoles.INTERNAL_CASE_WORKER.asString()).isEqualTo("internal-case-worker"); }
        @Test void internal()          { assertThat(UserRoles.INTERNAL.asString()).isEqualTo("internal"); }
        @Test void count()             { assertThat(UserRoles.values()).hasSize(6); }
    }

    @Nested
    @DisplayName("Realm → auth realm identifiers")
    class RealmContract {
        @Test void selfServe() { assertThat(Realm.SELF_SERVE.asString()).isEqualTo("selfserve"); }
        @Test void internal()  { assertThat(Realm.INTERNAL.asString()).isEqualTo("internal"); }
        @Test void count()     { assertThat(Realm.values()).hasSize(2); }
    }

    @Nested
    @DisplayName("UserTitle → Person.title")
    class UserTitleContract {
        @Test void mr()    { assertThat(UserTitle.MR.asString()).isEqualTo("title_mr"); }
        @Test void mrs()   { assertThat(UserTitle.MRS.asString()).isEqualTo("title_mrs"); }
        @Test void ms()    { assertThat(UserTitle.MS.asString()).isEqualTo("title_ms"); }
        @Test void count() { assertThat(UserTitle.values()).hasSize(3); }
    }

    @Nested
    @DisplayName("UserType → user type mapping")
    class UserTypeContract {
        @Test void internal() { assertThat(UserType.INTERNAL.asString()).isEqualTo("internal"); }
        @Test void external() { assertThat(UserType.EXTERNAL.asString()).isEqualTo("selfserve"); }
        @Test void count()    { assertThat(UserType.values()).hasSize(2); }
    }

    @Nested
    @DisplayName("FinancialStandingRateVehicleType → financial standing rates")
    class FinancialStandingRateVehicleTypeContract {
        @Test void na()    { assertThat(FinancialStandingRateVehicleType.NA.asString()).isEqualTo("fin_sta_veh_typ_na"); }
        @Test void hgv()   { assertThat(FinancialStandingRateVehicleType.HGV.asString()).isEqualTo("fin_sta_veh_typ_hgv"); }
        @Test void lgv()   { assertThat(FinancialStandingRateVehicleType.LGV.asString()).isEqualTo("fin_sta_veh_typ_lgv"); }
        @Test void count() { assertThat(FinancialStandingRateVehicleType.values()).hasSize(3); }
    }
}
