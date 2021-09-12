package com.ewhaenonymous.ttockclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
    public void covidApi(){
        try{
            String urlstr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
                    "serviceKey=$x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
        }
        catch(Exception e){
            e.getMessage();
        }
    }

    public void clinicApi(){
        try{
            String urlstr = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?" +
                    "ServiceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
        }
        catch(Exception e){
            e.getMessage();
        }
    }
}
