package com.example.vulndashboard.controller;

import com.example.vulndashboard.entities.WindowsVm;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/vms")
public class VirtualMachineController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping({"/list"})
    public String hello() {
        return "list-vms";
    }

    @GetMapping("/show")
    public String showMine() throws UnknownHostException {
        String hostName = SystemUtils.getHostName();
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String systemManufacturer = System.getProperty("os.arch");
        String ip = InetAddress.getLocalHost().getHostAddress();
        return hostName + "\n" + osName + "\n" + osVersion + "\n"
                + "\n" + systemManufacturer
                + "\n" + ip;
    }

    @PostMapping("/add")
    public String addVm(@RequestBody WindowsVm windowsVm){
        mongoTemplate.insert(windowsVm, "Virtual Machine");

        return "Added machine with host: " + windowsVm.getHostName() + " OS name: " + windowsVm.getOSName()
                + " OS version: " + windowsVm.getOSVersion() +
                 " processor type: " + windowsVm.getProcessorType()
                 + " IP address: " + windowsVm.getIpAddress();
    }

    @PostMapping("/addMine")
    public String addMyVm(WindowsVm windowsVm) throws UnknownHostException {
        windowsVm.setHostName(SystemUtils.getHostName());
        windowsVm.setOSName(System.getProperty("os.name"));
        windowsVm.setOSVersion(System.getProperty("os.version"));
        windowsVm.setProcessorType(System.getProperty("os.arch"));
        windowsVm.setIpAddress(InetAddress.getLocalHost().getHostAddress());
        mongoTemplate.insert(windowsVm, "Virtual Machine");

        return "Added machine with host " + windowsVm.getHostName() + " OS name " + windowsVm.getOSName()
                + " OS version " + windowsVm.getOSVersion() +
                " processor type " + windowsVm.getProcessorType()
                + " IP address " + windowsVm.getIpAddress();
    }
}
