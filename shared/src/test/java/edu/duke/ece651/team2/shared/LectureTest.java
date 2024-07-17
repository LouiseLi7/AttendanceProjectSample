package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class LectureTest {

    Integer secId = 4;
    final static String IP_API_URL = "http://ip-api.com/json/";

    @Test
    public void testConstructorsGettersAndSetters() {
        Lecture l1 = new Lecture(secId);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        assertEquals(year, l1.getYear());
        assertEquals(month, l1.getMonth());
        assertEquals(day, l1.getDay());
        LocalDate specificDate = LocalDate.of(2023, 8, 25);
        Lecture l2 = new Lecture(secId, specificDate);
        assertEquals(2023, l2.getYear());
        assertEquals(8, l2.getMonth());
        assertEquals(25, l2.getDay());
        assertEquals(secId, l2.getSectionId());
        l2.setLectureID(89);
        assertEquals(89, l2.getLectureID());
    }

    // @Test
    // public void testGeoLocaiton() {
    // try {
    // String response = getGeoLocation();
    // System.out.println(response);
    // InetAddress inetAddress = InetAddress.getLocalHost();

    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // public static String getGeoLocation() throws Exception {
    // String ipAddress = InetAddress.getLocalHost().getHostAddress();
    // System.out.println("IP Address: " + ipAddress);
    // URL url = new URL(IP_API_URL + "?ip=" + ipAddress);
    // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // conn.setRequestMethod("GET");
    // conn.setRequestProperty("Accept", "application/json");

    // if (conn.getResponseCode() != 200) {
    // throw new RuntimeException("Failed: HTTP status error: " +
    // conn.getResponseCode());
    // }

    // BufferedReader br = new BufferedReader(new
    // InputStreamReader(conn.getInputStream()));
    // String output;
    // StringBuilder response = new StringBuilder();
    // while ((output = br.readLine()) != null) {
    // response.append(output);
    // }
    // conn.disconnect();

    // System.out.println("Data: " + response);
    // JSONObject jsonObject = new JSONObject(response.toString());
    // String countryName = jsonObject.getString("country");
    // System.out.println("Country: " + countryName);

    // String provinceName = jsonObject.getString("region");
    // System.out.println("Province: " + provinceName);
    // String cityName = jsonObject.getString("city");
    // System.out.println("City: " + cityName);
    // double longitude = jsonObject.getDouble("lon");
    // double latitude = jsonObject.getDouble("lat");
    // System.out.println("Coordinates: " + String.format("%.6f", longitude) + ", "
    // + String.format("%.6f", latitude));
    // String ip = jsonObject.getString("query");
    // System.out.println("IP Address: " + ip);
    // String isp = jsonObject.getString("isp");
    // System.out.println("ISP: " + isp);
    // String timezone = jsonObject.getString("timezone");
    // System.out.println("Timezone: " + timezone);
    // String province = jsonObject.getString("regionName");
    // System.out.println("Region: " + province);

    // return response.toString();
    // }
}
