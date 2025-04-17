package com.kevin.cctv.service;

import com.kevin.cctv.model.VehicleDetectorData;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:9036/VehicleDetector/data";

    public VehicleDetectorService(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    public void sendData() {
        try {
            String jsonString = """
            {
              "1": {
                "table": "pedflow",
                "IPCamName": "Taoyuan-YanpingZhshan_PL925-70",
                "DataStartTime": "2024-04-01 00:00:12",
                "DataEndTime": "2024-04-01 00:00:15",
                "ABStartTime": 1711900812,
                "ABEndTime": 1711900815,
                "Dir": "D1_2",
                "Direction": "U",
                "PersonVolume": 1
              },
              // ... 其他資料 ...
              "data_num": 5
            }
            """;

            VehicleDetectorData data = objectMapper.readValue(jsonString, VehicleDetectorData.class);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<VehicleDetectorData> request = new HttpEntity<>(data, headers);

            String response = restTemplate.postForObject(baseUrl, request, String.class);
            log.info("資料發送成功: {}", response);
        } catch (Exception e) {
            log.error("準備或發送資料時發生錯誤: {}", e.getMessage());
        }
    }
}
