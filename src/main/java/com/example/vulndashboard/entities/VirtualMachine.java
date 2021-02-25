package com.example.vulndashboard.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
@Getter
@Setter
@ToString

@Document(collection = "Virtual Machine")
public abstract class VirtualMachine {

    @Id
    private ObjectId _id;
    private long vmId;
    private String hostname;
    private String ipAddr;
    private String os;
    private Set<Package> packages;

    public VirtualMachine(long vmId, String hostname, String ipAddr, String os) {
        this.vmId = vmId;
        this.hostname = hostname;
        this.ipAddr = ipAddr;
        this.os = os;
    }

    public long getVmId() {
        return vmId;
    }

    public void setVmId(long vmId) {
        this.vmId = vmId;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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
