package com.example.vulndashboard.controller;

import com.example.vulndashboard.entities.CVRF;
import com.example.vulndashboard.repository.CVRFRepository;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public String addCVRF(@RequestBody CVRF cvrf){
        mongoTemplate.insert(cvrf, "Vulnerabilities");
        return "Added CVRF " + cvrf.getRHSA() + " " + cvrf.getSeverity() + " "
                +cvrf.getReleasedOn() + " " + cvrf.getResourceUrl() + " " + cvrf.getBugzillas()
                + " " + cvrf.getCVEs() + " " + cvrf.getReleasedPackages();
    }

    @GetMapping("/all")
    public List<CVRF> getAllCVRF(){return repository.findAll();}

}
