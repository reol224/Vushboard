package com.example.vulndashboard.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinuxVmTests {

    @Test
    void constructorTest(){
        LinuxVm linuxVm = new LinuxVm("hostName",
                 "OSName",
                "OSVersion",
                "SystemManufacturer",
                 "ipAddress");
        assertEquals("hostName", linuxVm.getHostName());
    }
}
