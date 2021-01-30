package com.example.vulndashboard.entities;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="package_id")
    private long pkId;
    private String name;
    private String version;
    private String arch;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade ={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="vms_packages",
            joinColumns = @JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="vm_id") )
    private Set<VirtualMachine> vms;

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
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
}
