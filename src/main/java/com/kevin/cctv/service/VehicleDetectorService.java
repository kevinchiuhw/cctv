package com.kevin.cctv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleDetectorService {
    private static final Logger log = LoggerFactory.getLogger(VehicleDetectorService.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:9036/VehicleDetector/data";

    public VehicleDetectorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 定義資料模型
    public static class VDData {
        private final String ipcamName;
        private final String dir;
        private final int regionId;
        private final int regionType;
        private final int status;
        private final String startTime;
        private final String endTime;
        private final long abStartTime;
        private final long abEndTime;

        public VDData(String ipcamName, String dir, int regionId, int regionType,
                      int status, String startTime, String endTime,
                      long abStartTime, long abEndTime) {
            this.ipcamName = ipcamName;
            this.dir = dir;
            this.regionId = regionId;
            this.regionType = regionType;
            this.status = status;
            this.startTime = startTime;
            this.endTime = endTime;
            this.abStartTime = abStartTime;
            this.abEndTime = abEndTime;
        }

        // Getters
        public String getIpcamName() { return ipcamName; }
        public String getDir() { return dir; }
        public int getRegionId() { return regionId; }
        public int getRegionType() { return regionType; }
        public int getStatus() { return status; }
        public String getStartTime() { return startTime; }
        public String getEndTime() { return endTime; }
        public long getAbStartTime() { return abStartTime; }
        public long getAbEndTime() { return abEndTime; }
    }

    public void sendData() {
        try {
            log.info("開始發送新一輪的VD資料...");

            LocalDateTime now = LocalDateTime.now();
            long currentTimeSeconds = System.currentTimeMillis() / 1000;

            // 準備四組不同的資料
            VDData[] vdDataArray = {
                    createVDData("Taoyuan-YanpingZhshan_PL925-70", "D1-1", 1, 1, now, currentTimeSeconds),
                    createVDData("Taoyuan-YanpingZhshan_PL925-71", "D1-2", 2, 2, now, currentTimeSeconds),
                    createVDData("Taoyuan-YanpingZhshan_PL925-72", "D2-1", 3, 1, now, currentTimeSeconds),
                    createVDData("Taoyuan-YanpingZhshan_PL925-73", "D2-2", 4, 2, now, currentTimeSeconds)
            };

            log.info("已準備 4 組VD資料，開始非同步發送...");

            CompletableFuture<?>[] futures = new CompletableFuture[vdDataArray.length];
            for (int i = 0; i < vdDataArray.length; i++) {
                final VDData data = vdDataArray[i];
                futures[i] = CompletableFuture.runAsync(() -> sendVDData(data), executor);
            }

            CompletableFuture.allOf(futures).get(10, TimeUnit.SECONDS);
            log.info("本輪VD資料發送完成");
        } catch (Exception e) {
            log.error("發送資料過程中發生錯誤: {}", e.getMessage(), e);
        }
    }

    private VDData createVDData(String ipcamName, String dir, int regionId, int regionType,
                                LocalDateTime now, long currentTimeSeconds) {
        return new VDData(
                ipcamName,
                dir,
                regionId,
                regionType,
                1,
                formatDateTime(now),
                formatDateTime(now.plusSeconds(5)), // 結束時間加5秒
                currentTimeSeconds,
                currentTimeSeconds + 5 // 結束時間加5秒
        );
    }

    private void sendVDData(VDData data) {
        try {
            String jsonString = String.format("""
            {
               "1": {
                 "table": "pedevent",
                 "IPCamName": "%s",
                 "DataStartTime": "%s",
                 "DataEndTime": "%s",
                 "ABStartTime": %d,
                 "ABEndTime": %d,
                 "Dir": "%s",
                 "RegionID": %d,
                 "RegionType": %d,
                 "Status": %d
               },
               "2": {
                 "table": "flowevent",
                 "IPCamName": "%s",
                 "DataStartTime": "%s",
                 "DataEndTime": "%s",
                 "ABStartTime": %d,
                 "ABEndTime": %d,
                 "Dir": "%s",
                 "RegionID": %d,
                 "Status": %d
               },
               "data_num": 2
            }
            """,
                    data.getIpcamName(),
                    data.getStartTime(),
                    data.getEndTime(),
                    data.getAbStartTime(),
                    data.getAbEndTime(),
                    data.getDir(),
                    data.getRegionId(),
                    data.getRegionType(),
                    data.getStatus(),
                    data.getIpcamName(),
                    data.getStartTime(),
                    data.getEndTime(),
                    data.getAbStartTime(),
                    data.getAbEndTime(),
                    data.getDir(),
                    data.getRegionId(),
                    data.getStatus()
            );

            log.info("準備發送資料到 {}, 資料內容: \n{}", baseUrl, jsonString);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

            String response = restTemplate.postForObject(baseUrl, request, String.class);
            log.info("VD {} 資料發送成功，回應: {}", data.getIpcamName(), response);

        } catch (Exception e) {
            log.error("VD {} 發送資料時發生錯誤: {}", data.getIpcamName(), e.getMessage(), e);
            throw new RuntimeException("發送資料失敗", e);
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 應用程式關閉時清理資源
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
