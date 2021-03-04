package com.example.vulndashboard.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "Virtual Machine")
public class LinuxVm extends VirtualMachine {

    public LinuxVm(String hostName,
                   String OSName,
                   String OSVersion,
                   String SystemManufacturer,
                   String ipAddress) {
        super(hostName, OSName, OSVersion,SystemManufacturer,ipAddress);
    }
}
