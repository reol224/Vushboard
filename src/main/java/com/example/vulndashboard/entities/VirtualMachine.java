package com.example.vulndashboard.entities;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class VirtualMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vm_id")
    private long vmId;
    private String hostname;
    private String ipAddr;
    private String os;
    private String site;
    @ManyToMany(fetch=FetchType.LAZY,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="vms_packages",
            joinColumns = @JoinColumn(name="vm_id"),
            inverseJoinColumns=@JoinColumn(name="package_id") )
    private Set<Package> packages;

    public VirtualMachine(long vmId, String hostname, String ipAddr, String os, String site) {
        this.vmId = vmId;
        this.hostname = hostname;
        this.ipAddr = ipAddr;
        this.os = os;
        this.site = site;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
