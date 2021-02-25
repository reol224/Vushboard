package com.example.vulndashboard.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinuxVmTests {

    @Test
    void constructorTest(){
        LinuxVm linuxVm = new LinuxVm(34,"localhost","185.64.48.92","Linux");
        assertEquals(34, linuxVm.getVmId());
    }
}
