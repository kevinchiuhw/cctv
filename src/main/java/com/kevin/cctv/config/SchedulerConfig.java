package com.kevin.cctv.config;

import com.kevin.cctv.service.VehicleDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private VehicleDetectorService vehicleDetectorService;

    @Scheduled(fixedRate = 1000)
    public void scheduleDataSending() {
        vehicleDetectorService.sendData();
    }
}
