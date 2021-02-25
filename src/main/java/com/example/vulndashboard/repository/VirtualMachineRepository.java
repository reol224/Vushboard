package com.example.vulndashboard.repository;

import com.example.vulndashboard.entities.VirtualMachine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VirtualMachineRepository extends MongoRepository<VirtualMachine, Long> {
    Long findByVmId(Long vmId);
    String findByHostname(String hostname);
    String findByIpAddr(String ipAddr);
    String findByOs(String os);
}
