package com.example.vulndashboard.repository;

import com.example.vulndashboard.entities.CVRF;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CVRFRepository extends MongoRepository<CVRF, Long> {
    String findByRHSA(String RHSA);
    Optional<CVRF> findBySeverity(String severity);
    String findByReleasedOn(String releasedOn);
    ArrayList<String> findByCVEs(List<String> CVE);
    Long findByBugzillas(List<String> bugzillas);
    ArrayList<String> findByReleasedPackages(List<String> releasedPackages);
    String findByResourceUrl(String resourceUrl);
    String deleteBySeverity(String severity);
}
