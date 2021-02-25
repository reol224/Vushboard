package com.example.vulndashboard.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "RHSA",
        "severity",
        "released_on",
        "CVEs",
        "bugzillas",
        "released_packages",
        "resource_url"
})
@Document(collection = "Vulnerabilities")
public class CVRF {
    @Id
    private ObjectId _id;
    @JsonProperty("RHSA")
    private String RHSA;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("released_on")
    private String releasedOn;
    @JsonProperty("CVEs")
    private List<String> CVEs;
    @JsonProperty("bugzillas")
    private List<String> bugzillas;
    @JsonProperty("released_packages")
    private List<String> releasedPackages;
    @JsonProperty("resource_url")
    private String resourceUrl;
}
