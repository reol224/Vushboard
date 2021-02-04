package com.example.vulndashboard;

import com.example.vulndashboard.entities.Package;
import com.example.vulndashboard.entities.WindowsVm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackageTests {

    @Test
    void exampleTest(){
        Package aPackage = new Package();
        aPackage.setName("name");
        aPackage.setVersion("1.0.0");
        assertEquals("name",aPackage.getName());
        assertEquals("1.0.0",aPackage.getVersion());
    }
}
