package apiCalls.Utils.volBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class CaseNotesBuilder {

    @JsonProperty("licence")
    private String licence;
    @JsonProperty("application")
    private String application;
    @JsonProperty("case")
    private String _case;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("priority")
    private String priority;

    @JsonProperty("licence")
    public String getLicence() {
        return licence;
    }

    @JsonProperty("licence")
    public void setLicence(String licence) {
        this.licence = licence;
    }

    public CaseNotesBuilder withLicence(String licence) {
        this.licence = licence;
        return this;
    }

    @JsonProperty("application")
    public String getApplication() {
        return application;
    }

    @JsonProperty("application")
    public void setApplication(String application) {
        this.application = application;
    }

    public CaseNotesBuilder withApplication(String application) {
        this.application = application;
        return this;
    }

    @JsonProperty("case")
    public String getCase() {
        return _case;
    }

    @JsonProperty("case")
    public void setCase(String _case) {
        this._case = _case;
    }

    public CaseNotesBuilder withCase(String _case) {
        this._case = _case;
        return this;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    public CaseNotesBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    @JsonProperty("priority")
    public String getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public CaseNotesBuilder withPriority(String priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.JSON_STYLE)
                .append("licence", getLicence())
                .append("application", getApplication())
                .append("_case", getCase())
                .append("comment", getComment())
                .append("priority", getPriority()).toString();
    }
}