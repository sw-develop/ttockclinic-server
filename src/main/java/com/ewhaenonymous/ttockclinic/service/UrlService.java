package com.ewhaenonymous.ttockclinic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
    public Object covidApi() throws JsonProcessingException {
        StringBuffer result = new StringBuffer();
        SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        String t_date = dtf.format(dateObj);
        String y_date = dtf.format(calendar.getTime());

        try{
            String urlstr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
                    "serviceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D" +
                    "&pageNo=1" + "&startCreateDt=" + y_date + "&endCreateDt=" + t_date;

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
        //System.out.println(result);
        String xml = result.toString();
        JSONObject jObject = XML.toJSONObject(xml);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Object json = mapper.readValue(jObject.toString(), Object.class);
        return json;
    }

    public Object clinicApi() throws JsonProcessingException {
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
        //System.out.println(result);
        String xml = result.toString();
        JSONObject jObject = XML.toJSONObject(xml);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Object json = mapper.readValue(jObject.toString(), Object.class);
        return json;
    }
}
