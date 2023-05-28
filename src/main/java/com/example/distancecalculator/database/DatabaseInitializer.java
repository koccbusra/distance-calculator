package com.example.distancecalculator.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseInitializer {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource;

    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void runSqlScript() {
        log.info("Data insertion from sql file has been started.");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("ukpostcodesmysql.sql"));
        resourceDatabasePopulator.execute(dataSource);
        log.info("Data insertion from sql file has been completed.");
    }
}