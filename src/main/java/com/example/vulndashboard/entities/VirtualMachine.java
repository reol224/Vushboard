package com.example.vulndashboard.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "host_name",
        "os_name",
        "os_version",
        "processor_type",
        "ip_address"
})
@Document(collection = "Virtual Machine")
public abstract class VirtualMachine {

    @Id
    private ObjectId _id;
    @JsonProperty("host_name")
    private String hostName;
    @JsonProperty("os_name")
    private String OSName;
    @JsonProperty("os_version")
    private String OSVersion;
    @JsonProperty("processor_type")
    private String processorType;
    @JsonProperty("ip_address")
    private String ipAddress;
    private Set<Package> packages;

    public VirtualMachine(String hostName, String OSName, String OSVersion, String processorType, String ipAddress) {
        this.hostName = hostName;
        this.OSName = OSName;
        this.OSVersion = OSVersion;
        this.processorType = processorType;
        this.ipAddress = ipAddress;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }
    public void addPackage(Set<Package> packages) {
        /** add packages to existing set
         * if the set is empty, call setPackages
         * **/
        if (this.packages.isEmpty()) {
            setPackages(packages);
        } else {
            this.packages.addAll(packages);
        }
    }
}
