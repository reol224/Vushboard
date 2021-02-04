package com.example.vulndashboard;

import com.example.vulndashboard.entities.WindowsVm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindowsVMTests {

    @Test
    void constructorTest(){
        WindowsVm windowsVm = new WindowsVm(28,"localhost","175.218.56.37","Windows","site");
        assertEquals(28, windowsVm.getVmId());
    }
}
