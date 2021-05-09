package com.example.vulndashboard.controller;

import com.example.vulndashboard.entities.VirtualMachine;
import com.example.vulndashboard.entities.WindowsVm;
import com.example.vulndashboard.exceptions.ResourceNotFoundException;
import com.example.vulndashboard.repository.VirtualMachineRepository;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vms")
public class VirtualMachineController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VirtualMachineRepository repository;

    @GetMapping("/list")
    public List<VirtualMachine> getAllVMs(){return repository.findAll();}

    @PostMapping("/add")
    public String addVm(@RequestBody WindowsVm windowsVm){
        mongoTemplate.insert(windowsVm, "Virtual Machine");

        return "Added machine with host: " + windowsVm.getHostName() + " OS name: " + windowsVm.getOSName()
                + " OS version: " + windowsVm.getOSVersion() +
                 " processor type: " + windowsVm.getProcessorType()
                 + " IP address: " + windowsVm.getIpAddress();
    }

    @GetMapping("/{OSName}")
    public Object getVMByOSName(@PathVariable String OSName){
        Optional<WindowsVm> optional = repository.findByOSName(OSName);
        if(optional.isPresent()){
            return repository.findByOSName(OSName);
        }else{
            return new ResourceNotFoundException("There's no " + OSName + " type of machine!");
        }
    }

    @PutMapping("/{OSName}")
    public ResponseEntity<WindowsVm> updateVirtualMachineByOSName(@PathVariable String OSName, @RequestBody WindowsVm virtualMachine){
        Optional<WindowsVm> optional = Optional.ofNullable(repository.findByOSName(OSName)
                .orElseThrow(() -> new ResourceNotFoundException("There's no " + OSName + " type of machine!")));

        if(optional.isPresent()){
            WindowsVm newData = optional.get();
            newData.setOSName(virtualMachine.getOSName());
            return new ResponseEntity<>(repository.save(newData), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{hostName}")
    public ResponseEntity<WindowsVm> updateVirtualMachineByHostName(@PathVariable String hostName, @RequestBody WindowsVm virtualMachine){
        Optional<WindowsVm> optional = Optional.ofNullable(repository.findByHostName(hostName)
                .orElseThrow(() -> new ResourceNotFoundException("There's no " + hostName + " type of machine!")));

        if(optional.isPresent()){
            WindowsVm newData = optional.get();
            newData.setHostName(virtualMachine.getHostName());
            return new ResponseEntity<>(repository.save(newData), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{OSName}")
    public ResponseEntity<HttpStatus> deleteBySeverity(@PathVariable("OSName") String OSName) {
        try {
            repository.deleteByOSName(OSName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAllVMs() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/mine")
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

    @GetMapping("/mine")
    public String showMine() throws UnknownHostException {
        String hostName = SystemUtils.getHostName();
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String processorType = System.getProperty("os.arch");
        String ip = InetAddress.getLocalHost().getHostAddress();
        return hostName + "\n" + osName + "\n" + osVersion + "\n"
                + "\n" + processorType
                + "\n" + ip;
    }
}
