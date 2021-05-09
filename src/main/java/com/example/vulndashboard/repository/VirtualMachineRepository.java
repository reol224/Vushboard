package com.example.vulndashboard.repository;

import com.example.vulndashboard.entities.VirtualMachine;
import com.example.vulndashboard.entities.WindowsVm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VirtualMachineRepository extends MongoRepository<VirtualMachine, Long> {
    Optional<WindowsVm> findByHostName(String hostName);
    Optional<WindowsVm> findByOSName(String OSName);
    Optional<WindowsVm> findByOSVersion(String OSVersion);
    Optional<WindowsVm> findByProcessorType(String processorType);
    Optional<WindowsVm> findByIpAddress(String ipAddress);
    String deleteByOSName(String OSName);
}
