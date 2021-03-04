package com.example.vulndashboard.controller;

import com.example.vulndashboard.entities.CVRF;
import com.example.vulndashboard.repository.CVRFRepository;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cvrf")
public class CVRFController{

    @Autowired
    private CVRFRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/home")
    public String cvrf(@RequestParam(required = false) String before,
                       @RequestParam(required = false) String after,
                       @RequestParam(required = false) String[] rhsa_ids,
                       @RequestParam(required = false) Long bug,
                       @RequestParam(required = false) String[] cve,
                       @RequestParam(required = false) String severity,
                       @RequestParam(required = false) String packageList,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "100") Integer per_page,
                       @RequestParam(defaultValue = "10") String created_days_ago){
        JsonNode response = Unirest.get("https://access.redhat.com/hydra/rest/securitydata/cvrf.json")
                .queryString("before", before)
                .queryString("after", after)
                .queryString("rhsa_ids", rhsa_ids)
                .queryString("bug",bug)
                .queryString("cve",cve)
                .queryString("severity",severity)
                .queryString("package", packageList)
                .queryString("page", page)
                .queryString("per_page", per_page)
                .queryString("created_days_ago", created_days_ago)
                .header("content-type","application/json")
                .asJson()
                .getBody();

        return response.toPrettyString();
    }

    @PostMapping("/all/add")
    public String addCVRF(@RequestBody CVRF cvrf){
        mongoTemplate.insert(cvrf, "Vulnerabilities");
        return "Added CVRF " + cvrf.getRHSA() + " " + cvrf.getSeverity() + " "
                +cvrf.getReleasedOn() + " " + cvrf.getResourceUrl() + " " + cvrf.getBugzillas()
                + " " + cvrf.getCVEs() + " " + cvrf.getReleasedPackages();
    }

    @GetMapping("/all/{severity}")
    public Object getCVRFBySeverity(@PathVariable String severity){
        return repository.findBySeverity(severity);
    }

    @PutMapping("/all/{severity}")
    public ResponseEntity<CVRF> updateCVRF(@PathVariable String severity, @RequestBody CVRF cvrf){
        Optional<CVRF> optional = repository.findBySeverity(severity);

        if(optional.isPresent()){
            CVRF newData = optional.get();
            newData.setRHSA(cvrf.getRHSA());
            newData.setSeverity(cvrf.getSeverity());
            newData.setReleasedOn(cvrf.getReleasedOn());
            newData.setCVEs(cvrf.getCVEs());
            newData.setBugzillas(cvrf.getBugzillas());
            newData.setReleasedPackages(cvrf.getReleasedPackages());
            newData.setResourceUrl(cvrf.getResourceUrl());

            return new ResponseEntity<>(repository.save(newData), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all/{severity}")
    public ResponseEntity<HttpStatus> deleteBySeverity(@PathVariable("severity") String severity) {
        try {
            repository.deleteBySeverity(severity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<CVRF> getAllCVRF(){return repository.findAll();}

}
