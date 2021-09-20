package com.ewhaenonymous.ttockclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
    public void covidApi(){
        StringBuffer result = new StringBuffer();
        try{
            String urlstr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
                    "serviceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null){
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();

        }
        catch(Exception e){
            e.getMessage();
        }
        System.out.println(result);
    }

    public void clinicApi(){
        StringBuffer result = new StringBuffer();
        try{
            String urlstr = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?" +
                    "ServiceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        System.out.println(result);
    }
}
