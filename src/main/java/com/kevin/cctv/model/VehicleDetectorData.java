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

    public int getData_num() {
        Object num = dataMap.get("data_num");
        return num != null ? (Integer) num : 0;
    }

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
        private String table;
        private String IPCamName;
        private String DataStartTime;
        private String DataEndTime;
        private long ABStartTime;
        private long ABEndTime;
        private String Dir;
        private String Direction;
        private Integer PersonVolume;
        private Map<String, Info> Info;
        private Integer RegionID;
        private Integer RegionType;
        private Integer Status;

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getIPCamName() {
            return IPCamName;
        }

        public void setIPCamName(String IPCamName) {
            this.IPCamName = IPCamName;
        }

        public String getDataStartTime() {
            return DataStartTime;
        }

        public void setDataStartTime(String DataStartTime) {
            this.DataStartTime = DataStartTime;
        }

        public String getDataEndTime() {
            return DataEndTime;
        }

        public void setDataEndTime(String DataEndTime) {
            this.DataEndTime = DataEndTime;
        }

        public long getABStartTime() {
            return ABStartTime;
        }

        public void setABStartTime(long ABStartTime) {
            this.ABStartTime = ABStartTime;
        }

        public long getABEndTime() {
            return ABEndTime;
        }

        public void setABEndTime(long ABEndTime) {
            this.ABEndTime = ABEndTime;
        }

        public String getDir() {
            return Dir;
        }

        public void setDir(String Dir) {
            this.Dir = Dir;
        }

        public String getDirection() {
            return Direction;
        }

        public void setDirection(String Direction) {
            this.Direction = Direction;
        }

        public Integer getPersonVolume() {
            return PersonVolume;
        }

        public void setPersonVolume(Integer PersonVolume) {
            this.PersonVolume = PersonVolume;
        }

        public Map<String, Info> getInfo() {
            return Info;
        }

        public void setInfo(Map<String, Info> Info) {
            this.Info = Info;
        }

        public Integer getRegionID() {
            return RegionID;
        }

        public void setRegionID(Integer RegionID) {
            this.RegionID = RegionID;
        }

        public Integer getRegionType() {
            return RegionType;
        }

        public void setRegionType(Integer RegionType) {
            this.RegionType = RegionType;
        }

        public Integer getStatus() {
            return Status;
        }

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
        private double speed;
        private double distance;
        private double time;
        private long record_time;
        private String direction;
        @JsonProperty(" birdview_pts ")
        private double[] birdviewPts;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public long getRecord_time() {
            return record_time;
        }

        public void setRecord_time(long record_time) {
            this.record_time = record_time;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public double[] getBirdviewPts() {
            return birdviewPts;
        }

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
