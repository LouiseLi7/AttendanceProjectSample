package edu.duke.ece651.team2.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class StudentSubmission {

    private String code;
    private String ipAddress;
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    private String state;
    private String org;
    private String isp;
    private String time;
    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    final String IP_API_URL = "http://ip-api.com/json/";

    public StudentSubmission(String code) throws IOException {
        this.code = code;
        parseGeoLocation();
        this.time = sdf.format(new Date());
    }

    public StudentSubmission() {
    }

    private void parseGeoLocation() throws IOException {
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.ipAddress = ipAddress;
        System.out.println("IP Address: " + ipAddress);
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
        String countryName = jsonObject.getString("country");
        this.country = countryName;
        System.out.println("Country: " + countryName);

        String provinceName = jsonObject.getString("region");
        this.state = provinceName;
        System.out.println("Province: " + provinceName);

        String cityName = jsonObject.getString("city");
        this.city = cityName;
        System.out.println("City: " + cityName);

        double longitude = jsonObject.getDouble("lon");
        this.longitude = longitude;
        double latitude = jsonObject.getDouble("lat");
        this.latitude = latitude;
        System.out.println("Coordinates: " + String.format("%.6f", longitude) + ", " + String.format("%.6f", latitude));

        String isp = jsonObject.getString("isp");
        this.isp = isp;
        System.out.println("ISP: " + isp);

        String org = jsonObject.getString("org");
        this.org = org;
        System.out.println("ORG: " + org);
    }

    public String getCode() {
        return code;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getOrg() {
        return org;
    }

    public String getIsp() {
        return isp;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "StudentSubmission{" +
                "code='" + code + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", org='" + org + '\'' +
                ", isp='" + isp + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
