package edu.duke.ece651.team2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import edu.duke.ece651.team2.shared.StudentSubmission;

public class CacheForLecture {
    private int lectureId;
    private Set<String> ipAddresses; // cache students' ip
    private ConcurrentHashMap<String, String> timeCodeMap; // cache dynamic codes for lecture <time,code>
    private double lat;
    private double lon;
    private String lastGeneratedCode;
    private final static String IP_API_URL = "http://ip-api.com/json/";
    private Set<Integer> studentIds;
    private StudentSubmission professorInfo;
    private List<String> orderCode;

    public CacheForLecture(int lectureId) throws IOException {
        this.lectureId = lectureId;
        this.ipAddresses = Collections.synchronizedSet(new HashSet<>());
        this.timeCodeMap = new ConcurrentHashMap<>();
        this.studentIds = Collections.synchronizedSet(new HashSet<>());
        getGeoLocation();
        lastGeneratedCode = "";
        professorInfo = new StudentSubmission("PROESSOR");
        this.orderCode = new ArrayList<>();
    }

    public void setLastGeneratedCode(String code) {
        this.lastGeneratedCode = code;
    }

    public StudentSubmission getProfessorInfo() {
        return this.professorInfo;
    }

    public void addCodeInOrder(String code) {
        orderCode.add(code);
    }

    public String getLastGeneratedCode() {
        return this.lastGeneratedCode;
    }

    private void getGeoLocation() throws IOException {
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        URL url = new URL(IP_API_URL + "?ip=" + ipAddress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed: HTTP status error: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        conn.disconnect();

        JSONObject jsonObject = new JSONObject(response.toString());
        double longitude = jsonObject.getDouble("lon");
        this.lon = longitude;
        double latitude = jsonObject.getDouble("lat");
        this.lat = latitude;

    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public boolean containsIpAddress(String ipAddress) {
        synchronized (ipAddresses) {
            return ipAddresses.contains(ipAddress);
        }
    }

    public boolean containsStuentId(int sid) {
        synchronized (studentIds) {
            return studentIds.contains(sid);
        }
    }

    public void addStudentId(int sid) {
        studentIds.add(sid);
    }

    public int getLectureId() {
        return lectureId;
    }

    public void addTimeCode(String time, String code) {
        timeCodeMap.put(time, code);
    }

    public ConcurrentHashMap<String, String> getTimeCodeMap() {
        return timeCodeMap;
    }

    // Method to find a time for a given code
    public String getTimeByCode(String code) {
        for (String key : timeCodeMap.keySet()) {
            if (timeCodeMap.get(key).equals(code)) {
                return key; // Returns the first matching key
            }
        }
        return null; // No match found
    }

    public void addIpAddress(String ipAddress) {
        ipAddresses.add(ipAddress);
    }

    public Entry<String, String> getLastElementMap() {
        // last code
        String lastCode = orderCode.get(orderCode.size() - 1);

        Entry<String, String> lastEntry = null;
        for (Entry<String, String> entry : timeCodeMap.entrySet()) {
            if (entry.getValue().equals(lastCode)) {
                lastEntry = entry;
                break;
            }
        }
        return lastEntry;
    }

    // public static <K, V> Map.Entry<K, V> getEntryByIndex(Map<K, V> map, int
    // index) {
    // if (index < 0 || index >= map.size()) {
    // return null; // index out of bounds
    // }
    // Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
    // for (int i = 0; i < index; i++) {
    // iterator.next();
    // }
    // return iterator.next();
    // }

    public int getMapSize() {
        return timeCodeMap.size();
    }
}
