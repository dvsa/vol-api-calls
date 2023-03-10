package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class InterimApplicationBuilder {

    @JsonProperty("authHgvVehicles")
    private String authHgvVehicles;
    @JsonProperty("requested")
    private String requested;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("authTrailers")
    private String authTrailers;
    @JsonProperty("operatingCentres")
    private List<Object> operatingCentres = null;
    @JsonProperty("vehicles")
    private List<Object> vehicles = null;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("action")
    private String action;
    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private int version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("authHgvVehicles")
    public String getAuthHgvVehicles() {
        return authHgvVehicles;
    }

    @JsonProperty("authHgvVehicles")
    public void setAuthHgvVehicles(String authHgvVehicles) {
        this.authHgvVehicles = authHgvVehicles;
    }

    public InterimApplicationBuilder withAuthHgvVehicles(String authHgvVehicles){
        this.authHgvVehicles = authHgvVehicles;
        return this;
    }

    @JsonProperty("requested")
    public String getRequested() {
        return requested;
    }

    @JsonProperty("requested")
    public void setRequested(String requested) {
        this.requested = requested;
    }

    public InterimApplicationBuilder withRequested(String requested){
        this.requested = requested;
        return this;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    public InterimApplicationBuilder withReason(String reason){
        this.reason = reason;
        return this;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public InterimApplicationBuilder withStartDate(String startDate){
        this.startDate = startDate;
        return this;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public InterimApplicationBuilder withEndDate(String endDate){
        this.endDate = endDate;
        return this;
    }

    @JsonProperty("authTrailers")
    public String getAuthTrailers() {
        return authTrailers;
    }

    @JsonProperty("authTrailers")
    public void setAuthTrailers(String authTrailers) {
        this.authTrailers = authTrailers;
    }

    public InterimApplicationBuilder withAuthTrailers(String authTrailers){
        this.authTrailers = authTrailers;
        return this;
    }

    @JsonProperty("operatingCentres")
    public List<Object> getOperatingCentres() {
        return operatingCentres;
    }

    @JsonProperty("operatingCentres")
    public void setOperatingCentres(List<Object> operatingCentres) {
        this.operatingCentres = operatingCentres;
    }

    public InterimApplicationBuilder withOperatingCentres(List<Object> operatingCentres){
        this.operatingCentres = operatingCentres;
        return this;
    }

    @JsonProperty("vehicles")
    public List<Object> getVehicles() {
        return vehicles;
    }

    @JsonProperty("vehicles")
    public void setVehicles(List<Object> vehicles) {
        this.vehicles = vehicles;
    }

    public InterimApplicationBuilder withVehicles(List<Object> vehicles){
        this.vehicles = vehicles;
        return this;
    }

    @JsonProperty("status")
    public Object getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Object status) {
        this.status = status;
    }

    public InterimApplicationBuilder withStatus(Object status){
        this.status = status;
        return this;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    public InterimApplicationBuilder withAction(String action){
        this.action = action;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public InterimApplicationBuilder withId(String id){
        this.id = id;
        return this;
    }

    @JsonProperty("version")
    public int getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(int version) {
        this.version = version;
    }

    public InterimApplicationBuilder withVersion(int version){
        this.version = version;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("authHgvVehicles", authHgvVehicles)
                .append("requested", requested)
                .append("reason", reason)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .append("authTrailers", authTrailers)
                .append("operatingCentres", operatingCentres)
                .append("vehicles", vehicles)
                .append("status", status)
                .append("action", action)
                .append("id", id)
                .append("version", version)
                .append("additionalProperties", additionalProperties).toString();
    }
}