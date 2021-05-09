package com.example.vulndashboard.services.databaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {
    private final String DATABASE_SERVICE = "MongoDB";
    @Value("${spring.data.mongodb.database}")
    private String DATABASE_NAME;
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Health health() {

        if(isDatabaseHealthGood()){
            return Health.up().withDetail(DATABASE_SERVICE, "MongoDB service is running").build();
        }
        return Health.down().withDetail(DATABASE_SERVICE, "MongoDB service is not running").build();
    }

    private boolean isDatabaseHealthGood() {

        return mongoTemplate.getDb().getName().equals(DATABASE_NAME);
    }
}
