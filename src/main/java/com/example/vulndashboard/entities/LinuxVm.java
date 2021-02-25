package com.example.vulndashboard.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "Virtual Machine")
public class LinuxVm extends VirtualMachine{

    public LinuxVm(long vmId, String hostname, String ipAddr, String os) {
        super(vmId, hostname, ipAddr, os);
    }
}
