package com.kevin.cctv.service;

import com.kevin.cctv.model.VehicleDetectorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VehicleDetectorService {
    private static final Logger log = LoggerFactory.getLogger(VehicleDetectorService.class);

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:9036/VehicleDetector/data";

    public VehicleDetectorService(ObjectMapper objectMapper) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
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
  "2": {
    "table": "pedflow",
    "IPCamName": "Taoyuan-YanpingZhshan_PL925-70",
    "DataStartTime": "2024-04-01 00:00:12",
    "DataEndTime": "2024-04-01 00:00:15",
    "ABStartTime": 1711900812,
    "ABEndTime": 1711900815,
    "Dir": "D1_2",
    "Direction": "D",
    "PersonVolume": 3
  },
  "3": {
    "table": "pedcross",
    "IPCamName": " Taoyuan-YanpingZhshan_PL925-70",
    "DataStartTime": "2024-04-01 00:00:14",
    "DataEndTime": "2024-04-01 00:00:15",
    "ABStartTime": 1711900814,
    "ABEndTime": 1711900815,
    "Dir": "D1_2",
    "Info": {
      "21": {
        "speed": 1.3700000000000001,
        "distance": 9.9000000000000004,
        "time": 7.2400000000000002,
        "record_time": 1711900818000,
        "direction": "L",
        " birdview_pts ": [0.30285, 0.11238]
      },
      "60": {
        "speed": 2.4500000000000002,
        "distance": 11.33,
        "time": 4.6200000000000001,
        "record_time": 1711900818000,
        "direction": "L",
        " birdview_pts ": [0.70103, 0.45678]
      }
    }
  },
  "4": {
    "table": "pedevent",
    "IPCamName": " Taoyuan-YanpingZhshan_PL925-70",
    "DataStartTime": "2024-04-01 00:00:19",
    "DataEndTime": "2024-04-01 00:00:20",
    "ABStartTime": 1711900819,
    "ABEndTime": 1711900820,
    "Dir": "D1",
    "RegionID": 1,
    "RegionType": 1,
    "Status": 1
  },
  "5": {
    "table": "flowevent",
    "IPCamName": " Taoyuan-YanpingZhshan_PL925-70",
    "DataStartTime": "2024-04-01 00:00:19",
    "DataEndTime": "2024-04-01 00:00:20",
    "ABStartTime": 1711900819,
    "ABEndTime": 1711900820,
    "Dir": "D1",
    "RegionID": 1,
    "Status": 0
  },
  "data_num": 5
}

            """;

            VehicleDetectorData data = objectMapper.readValue(jsonString, VehicleDetectorData.class);

            webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(data)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(
                            response -> log.info("資料發送成功: {}", response),
                            error -> log.error("發送資料時發生錯誤: {}", error.getMessage())
                    );
        } catch (Exception e) {
            log.error("準備或發送資料時發生錯誤: {}", e.getMessage());
        }
    }
}