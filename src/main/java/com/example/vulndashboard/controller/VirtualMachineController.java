package com.example.vulndashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vms")
public class VirtualMachineController {

    @GetMapping({"/list"})
    public String hello() {
        return "list-vms";
    }
}
