package com.kevin.cctv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleDetectorService {
    private static final Logger log = LoggerFactory.getLogger(VehicleDetectorService.class);

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:9036/VehicleDetector/data";
    //private final String baseUrl = "http://tcis-post-controller-service:9036/VehicleDetector/data";


    public VehicleDetectorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendData() {
        try {
            String jsonString = """
            {
               "1": {
                 "table": "pedevent",
                 "IPCamName": "Taoyuan-YanpingZhshan_PL925-70",
                 "DataStartTime": "2024-04-01 06:00:19",
                 "DataEndTime": "2024-04-01 11:00:20",
                 "ABStartTime": 1711900819,
                 "ABEndTime": 1711900820,
                 "Dir": "D1-1",
                 "RegionID": 1,
                 "RegionType": 1,
                 "Status": 1
               },
               "2": {
                 "table": "flowevent",
                 "IPCamName": "Taoyuan-YanpingZhshan_PL925-70",
                 "DataStartTime": "2024-04-01 06:00:19",
                 "DataEndTime": "2024-04-01 11:00:20",
                 "ABStartTime": 1711900819,
                 "ABEndTime": 1711900820,
                 "Dir": "D1-1",
                 "RegionID": 1,
                 "Status": 0
               },
               "data_num": 2
            }
            """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

            String response = restTemplate.postForObject(baseUrl, request, String.class);
            log.info("資料發送成功: {}", response);
        } catch (Exception e) {
            log.error("準備或發送資料時發生錯誤: {}", e.getMessage(), e);
        }
    }
}
