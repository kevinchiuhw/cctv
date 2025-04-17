package com.kevin.cctv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDetectorData {
    private Map<String, Object> dataMap = new HashMap<>();

    @JsonAnySetter
    public void set(String key, Object value) {
        dataMap.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> get() {
        return dataMap;
    }

    @JsonProperty("data_num")
    public int getData_num() {
        Object num = dataMap.get("data_num");
        return num != null ? (Integer) num : 0;
    }

    @JsonProperty("data_num")
    public void setData_num(int data_num) {
        dataMap.put("data_num", data_num);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleDetectorData that = (VehicleDetectorData) o;
        return Objects.equals(dataMap, that.dataMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataMap);
    }

    @Override
    public String toString() {
        return "VehicleDetectorData{" +
                "dataMap=" + dataMap +
                '}';
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataEntry {
        @JsonProperty("table")
        private String table;

        @JsonProperty("IPCamName")
        private String IPCamName;

        @JsonProperty("DataStartTime")
        private String DataStartTime;

        @JsonProperty("DataEndTime")
        private String DataEndTime;

        @JsonProperty("ABStartTime")
        private long ABStartTime;

        @JsonProperty("ABEndTime")
        private long ABEndTime;

        @JsonProperty("Dir")
        private String Dir;

        @JsonProperty("Direction")
        private String Direction;

        @JsonProperty("PersonVolume")
        private Integer PersonVolume;

        @JsonProperty("Info")
        private Map<String, Info> Info;

        @JsonProperty("RegionID")
        private Integer RegionID;

        @JsonProperty("RegionType")
        private Integer RegionType;

        @JsonProperty("Status")
        private Integer Status;

        @JsonProperty("table")
        public String getTable() {
            return table;
        }

        @JsonProperty("table")
        public void setTable(String table) {
            this.table = table;
        }

        @JsonProperty("IPCamName")
        public String getIPCamName() {
            return IPCamName;
        }

        @JsonProperty("IPCamName")
        public void setIPCamName(String IPCamName) {
            this.IPCamName = IPCamName;
        }

        @JsonProperty("DataStartTime")
        public String getDataStartTime() {
            return DataStartTime;
        }

        @JsonProperty("DataStartTime")
        public void setDataStartTime(String DataStartTime) {
            this.DataStartTime = DataStartTime;
        }

        @JsonProperty("DataEndTime")
        public String getDataEndTime() {
            return DataEndTime;
        }

        @JsonProperty("DataEndTime")
        public void setDataEndTime(String DataEndTime) {
            this.DataEndTime = DataEndTime;
        }

        @JsonProperty("ABStartTime")
        public long getABStartTime() {
            return ABStartTime;
        }

        @JsonProperty("ABStartTime")
        public void setABStartTime(long ABStartTime) {
            this.ABStartTime = ABStartTime;
        }

        @JsonProperty("ABEndTime")
        public long getABEndTime() {
            return ABEndTime;
        }

        @JsonProperty("ABEndTime")
        public void setABEndTime(long ABEndTime) {
            this.ABEndTime = ABEndTime;
        }

        @JsonProperty("Dir")
        public String getDir() {
            return Dir;
        }

        @JsonProperty("Dir")
        public void setDir(String Dir) {
            this.Dir = Dir;
        }

        @JsonProperty("Direction")
        public String getDirection() {
            return Direction;
        }

        @JsonProperty("Direction")
        public void setDirection(String Direction) {
            this.Direction = Direction;
        }

        @JsonProperty("PersonVolume")
        public Integer getPersonVolume() {
            return PersonVolume;
        }

        @JsonProperty("PersonVolume")
        public void setPersonVolume(Integer PersonVolume) {
            this.PersonVolume = PersonVolume;
        }

        @JsonProperty("Info")
        public Map<String, Info> getInfo() {
            return Info;
        }

        @JsonProperty("Info")
        public void setInfo(Map<String, Info> Info) {
            this.Info = Info;
        }

        @JsonProperty("RegionID")
        public Integer getRegionID() {
            return RegionID;
        }

        @JsonProperty("RegionID")
        public void setRegionID(Integer RegionID) {
            this.RegionID = RegionID;
        }

        @JsonProperty("RegionType")
        public Integer getRegionType() {
            return RegionType;
        }

        @JsonProperty("RegionType")
        public void setRegionType(Integer RegionType) {
            this.RegionType = RegionType;
        }

        @JsonProperty("Status")
        public Integer getStatus() {
            return Status;
        }

        @JsonProperty("Status")
        public void setStatus(Integer Status) {
            this.Status = Status;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataEntry dataEntry = (DataEntry) o;
            return ABStartTime == dataEntry.ABStartTime &&
                    ABEndTime == dataEntry.ABEndTime &&
                    Objects.equals(table, dataEntry.table) &&
                    Objects.equals(IPCamName, dataEntry.IPCamName) &&
                    Objects.equals(DataStartTime, dataEntry.DataStartTime) &&
                    Objects.equals(DataEndTime, dataEntry.DataEndTime) &&
                    Objects.equals(Dir, dataEntry.Dir) &&
                    Objects.equals(Direction, dataEntry.Direction) &&
                    Objects.equals(PersonVolume, dataEntry.PersonVolume) &&
                    Objects.equals(Info, dataEntry.Info) &&
                    Objects.equals(RegionID, dataEntry.RegionID) &&
                    Objects.equals(RegionType, dataEntry.RegionType) &&
                    Objects.equals(Status, dataEntry.Status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(table, IPCamName, DataStartTime, DataEndTime, ABStartTime, ABEndTime,
                    Dir, Direction, PersonVolume, Info, RegionID, RegionType, Status);
        }

        @Override
        public String toString() {
            return "DataEntry{" +
                    "table='" + table + '\'' +
                    ", IPCamName='" + IPCamName + '\'' +
                    ", DataStartTime='" + DataStartTime + '\'' +
                    ", DataEndTime='" + DataEndTime + '\'' +
                    ", ABStartTime=" + ABStartTime +
                    ", ABEndTime=" + ABEndTime +
                    ", Dir='" + Dir + '\'' +
                    ", Direction='" + Direction + '\'' +
                    ", PersonVolume=" + PersonVolume +
                    ", Info=" + Info +
                    ", RegionID=" + RegionID +
                    ", RegionType=" + RegionType +
                    ", Status=" + Status +
                    '}';
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Info {
        @JsonProperty("speed")
        private double speed;

        @JsonProperty("distance")
        private double distance;

        @JsonProperty("time")
        private double time;

        @JsonProperty("record_time")
        private long record_time;

        @JsonProperty("direction")
        private String direction;

        @JsonProperty(" birdview_pts ")
        private double[] birdviewPts;

        @JsonProperty("speed")
        public double getSpeed() {
            return speed;
        }

        @JsonProperty("speed")
        public void setSpeed(double speed) {
            this.speed = speed;
        }

        @JsonProperty("distance")
        public double getDistance() {
            return distance;
        }

        @JsonProperty("distance")
        public void setDistance(double distance) {
            this.distance = distance;
        }

        @JsonProperty("time")
        public double getTime() {
            return time;
        }

        @JsonProperty("time")
        public void setTime(double time) {
            this.time = time;
        }

        @JsonProperty("record_time")
        public long getRecord_time() {
            return record_time;
        }

        @JsonProperty("record_time")
        public void setRecord_time(long record_time) {
            this.record_time = record_time;
        }

        @JsonProperty("direction")
        public String getDirection() {
            return direction;
        }

        @JsonProperty("direction")
        public void setDirection(String direction) {
            this.direction = direction;
        }

        @JsonProperty(" birdview_pts ")
        public double[] getBirdviewPts() {
            return birdviewPts;
        }

        @JsonProperty(" birdview_pts ")
        public void setBirdviewPts(double[] birdviewPts) {
            this.birdviewPts = birdviewPts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return Double.compare(info.speed, speed) == 0 &&
                    Double.compare(info.distance, distance) == 0 &&
                    Double.compare(info.time, time) == 0 &&
                    record_time == info.record_time &&
                    Objects.equals(direction, info.direction) &&
                    Arrays.equals(birdviewPts, info.birdviewPts);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(speed, distance, time, record_time, direction);
            result = 31 * result + Arrays.hashCode(birdviewPts);
            return result;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "speed=" + speed +
                    ", distance=" + distance +
                    ", time=" + time +
                    ", record_time=" + record_time +
                    ", direction='" + direction + '\'' +
                    ", birdviewPts=" + Arrays.toString(birdviewPts) +
                    '}';
        }
    }
}
