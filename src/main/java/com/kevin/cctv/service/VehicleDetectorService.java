package com.kevin.cctv.service;

import com.kevin.cctv.model.VehicleDetectorData;
import com.kevin.cctv.model.VehicleDetectorData.DataEntry;
import com.kevin.cctv.model.VehicleDetectorData.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleDetectorService {
    private static final Logger log = LoggerFactory.getLogger(VehicleDetectorService.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:9036/VehicleDetector/data";

    public VehicleDetectorService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendData() {
        try {
            // 建立完整的資料結構
            VehicleDetectorData data = createSampleData();

            // 設定 HTTP 標頭
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<VehicleDetectorData> request = new HttpEntity<>(data, headers);

            // 發送請求
            String response = restTemplate.postForObject(baseUrl, request, String.class);
            log.info("資料發送成功: {}", response);
        } catch (Exception e) {
            log.error("準備或發送資料時發生錯誤: {}", e.getMessage(), e);
        }
    }

    private VehicleDetectorData createSampleData() {
        VehicleDetectorData data = new VehicleDetectorData();
        Map<String, DataEntry> entries = new HashMap<>();

        // 建立第一筆資料 (pedflow)
        DataEntry entry1 = new DataEntry();
        entry1.setTable("pedflow");
        entry1.setIPCamName("Taoyuan-YanpingZhshan_PL925-70");
        entry1.setDataStartTime("2024-04-01 00:00:12");
        entry1.setDataEndTime("2024-04-01 00:00:15");
        entry1.setABStartTime(1711900812);
        entry1.setABEndTime(1711900815);
        entry1.setDir("D1_2");
        entry1.setDirection("U");
        entry1.setPersonVolume(1);
        entries.put("1", entry1);

        // 建立第二筆資料 (pedflow)
        DataEntry entry2 = new DataEntry();
        entry2.setTable("pedflow");
        entry2.setIPCamName("Taoyuan-YanpingZhshan_PL925-70");
        entry2.setDataStartTime("2024-04-01 00:00:12");
        entry2.setDataEndTime("2024-04-01 00:00:15");
        entry2.setABStartTime(1711900812);
        entry2.setABEndTime(1711900815);
        entry2.setDir("D1_2");
        entry2.setDirection("D");
        entry2.setPersonVolume(3);
        entries.put("2", entry2);

        // 建立第三筆資料 (pedcross)
        DataEntry entry3 = new DataEntry();
        entry3.setTable("pedcross");
        entry3.setIPCamName("Taoyuan-YanpingZhshan_PL925-70");
        entry3.setDataStartTime("2024-04-01 00:00:14");
        entry3.setDataEndTime("2024-04-01 00:00:15");
        entry3.setABStartTime(1711900814);
        entry3.setABEndTime(1711900815);
        entry3.setDir("D1_2");

        // 建立 Info 資料
        Map<String, Info> infoMap = new HashMap<>();

        Info info21 = new Info();
        info21.setSpeed(1.37);
        info21.setDistance(9.90);
        info21.setTime(7.24);
        info21.setRecord_time(1711900818000L);
        info21.setDirection("L");
        info21.setBirdviewPts(new double[]{0.30285, 0.11238});
        infoMap.put("21", info21);

        Info info60 = new Info();
        info60.setSpeed(2.45);
        info60.setDistance(11.33);
        info60.setTime(4.62);
        info60.setRecord_time(1711900818000L);
        info60.setDirection("L");
        info60.setBirdviewPts(new double[]{0.70103, 0.45678});
        infoMap.put("60", info60);

        entry3.setInfo(infoMap);
        entries.put("3", entry3);

        // 建立第四筆資料 (pedevent)
        DataEntry entry4 = new DataEntry();
        entry4.setTable("pedevent");
        entry4.setIPCamName("Taoyuan-YanpingZhshan_PL925-70");
        entry4.setDataStartTime("2024-04-01 00:00:19");
        entry4.setDataEndTime("2024-04-01 00:00:20");
        entry4.setABStartTime(1711900819);
        entry4.setABEndTime(1711900820);
        entry4.setDir("D1");
        entry4.setRegionID(1);
        entry4.setRegionType(1);
        entry4.setStatus(1);
        entries.put("4", entry4);

        // 建立第五筆資料 (flowevent)
        DataEntry entry5 = new DataEntry();
        entry5.setTable("flowevent");
        entry5.setIPCamName("Taoyuan-YanpingZhshan_PL925-70");
        entry5.setDataStartTime("2024-04-01 00:00:19");
        entry5.setDataEndTime("2024-04-01 00:00:20");
        entry5.setABStartTime(1711900819);
        entry5.setABEndTime(1711900820);
        entry5.setDir("D1");
        entry5.setRegionID(1);
        entry5.setStatus(0);
        entries.put("5", entry5);

        // 設定資料到 VehicleDetectorData
        data.setEntries(entries);
        data.setData_num(5);

        return data;
    }
}
