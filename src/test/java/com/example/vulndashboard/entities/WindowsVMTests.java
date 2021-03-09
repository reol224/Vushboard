package com.example.vulndashboard.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindowsVMTests {

    @Test
    void constructorTest(){
        WindowsVm windowsVm = new WindowsVm("hostName",
                 "OSName",
                 "OSVersion",
                 "SystemManufacturer",
                "ipAddress");
        assertEquals("hostName", windowsVm.getHostName());
    }
}
