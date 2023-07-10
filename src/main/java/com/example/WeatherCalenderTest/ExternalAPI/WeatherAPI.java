package com.example.WeatherCalenderTest.ExternalAPI;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherAPI {

    public static void main(String[] args) throws IOException, InterruptedException {

        String uristring = URIstring("vctria");
        System.out.println(uristring);
        JSONObject json = WeatherAPIresponse(uristring);
        System.out.println(json.getJSONArray("days").getJSONObject(0).getJSONArray("hours").getJSONObject(0));

    }

    public static JSONObject WeatherAPIresponse(String uristring)throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uristring))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body());
    }

    public static String URIstring(String City){

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https").host("weather.visualcrossing.com")
                .path("/VisualCrossingWebServices/rest/services/timeline/{location}/next14days")
                .query("key=TQRSXJ6KHCMYLJ63MNU6EEHWV").query("unitGroups=metric").query("include=hours");

        return builder.buildAndExpand(City).toUriString();

    }
}
