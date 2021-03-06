package com.example.vulndashboard.entities;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Document(collection = "Package")
public class Package {

    @Id
    private ObjectId _id;
    private long identifyingNumber;
    private String name;
    private String vendor;
    private String version;
    private String arch;

    private Set<VirtualMachine> vms;

    public Package() {

    }

    public long getIdentifyingNumber() {
        return identifyingNumber;
    }

    public void setIdentifyingNumber(long identifyingNumber) {
        this.identifyingNumber = identifyingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public Set<VirtualMachine> getVms() {
        return vms;
    }

    public void setVms(Set<VirtualMachine> vms) {
        this.vms = vms;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
