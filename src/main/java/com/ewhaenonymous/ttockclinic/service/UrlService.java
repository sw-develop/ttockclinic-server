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

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
    public Object xmlToJson(StringBuffer result) throws JsonProcessingException { // XML을 JSON으로 변환시키는 메서드
        String xml = result.toString();
        JSONObject jObject = XML.toJSONObject(xml);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.readValue(jObject.toString(), Object.class);
    }

    public StringBuffer makeUrlConnection(String urlStr){
        StringBuffer result = new StringBuffer();
        try{
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while((returnLine = br.readLine()) != null){
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();

        } catch(Exception e){
            e.getMessage();
        }

        return result;
    }

    public Object covidApi() throws JsonProcessingException {
        String urlStr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?" +
                "serviceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
        StringBuffer result = makeUrlConnection(urlStr);
        return xmlToJson(result);
    }

    public Object clinicApi() throws JsonProcessingException {
        String urlStr = "http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?" +
                "ServiceKey=x7h3QsoSTvqMkdINB49pnMFwFob%2BGJk5XPJPBNLtTt3GErqxwAHg%2F2Au%2FgUlIA%2FKcjlrK%2BbhRPRJI7AJnGh5Ag%3D%3D";
        StringBuffer result = makeUrlConnection(urlStr);
        return xmlToJson(result);
    }
}
