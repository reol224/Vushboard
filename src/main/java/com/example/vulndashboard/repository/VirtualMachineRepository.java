package com.example.vulndashboard.repository;

import com.example.vulndashboard.entities.VirtualMachine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VirtualMachineRepository extends MongoRepository<VirtualMachine, Long> {

}
