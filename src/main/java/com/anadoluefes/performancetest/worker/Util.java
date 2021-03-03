package com.anadoluefes.performancetest.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Util {

    public   static RestTemplate getRestTemplate( ) {

         RestTemplate restTemplate = new RestTemplate();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        return restTemplate;

    }

    @SneakyThrows
    public static String readFile(String path, String transactionid){

        String everything="";
        String filename="src/main/resources/request/"+path +"/"+path+transactionid+".json";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
      return String.format(everything, transactionid);

    }


    public static MultiValueMap<String, String> getHttpHeaders(){
        MultiValueMap<String, String> map = new HttpHeaders();
        map.add("MW_USERNAME","hip-test");
        map.add("MW_PASSWORD","yF5WnSCxV2x*btqHryV?gP66");
        return  map;
    }
    @SneakyThrows
    public void sendDate (int iteration, String path, String url,Integer waitByMillisecond,String integration) {

        RestTemplate restTemplate = getRestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> httpHeaders = Util.getHttpHeaders();

        for (int i = 1; i <iteration ; i++) {

            String exchangerate = Util.readFile(path, String.valueOf(i%3));
            Map jsonNode = objectMapper.readValue(exchangerate, Map.class);
            HttpEntity entity = new HttpEntity(jsonNode, httpHeaders);
            System.out.print(integration+i+"\t\t");
            LocalDateTime startingdate = LocalDateTime.now();
            System.out.print(startingdate+"\t\t");
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            LocalDateTime enddate = LocalDateTime.now();
            System.out.print(enddate+"\t\t");
            System.out.println(Duration.between(startingdate,enddate));
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(waitByMillisecond));

        }
    }
}
