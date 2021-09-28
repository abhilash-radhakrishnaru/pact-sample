package com;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumerA {

    public static void main(String[] args) {
        ( new ConsumerA()).callAPI();
    }

    /*
        APIs consumer.
        This will have logic of passing request and also processing the response from producer
        In real world scenarios, this method will get broken down to multiple methods as per the business logic
        so that request formation, service call, response handling, response processing etc can be
        individually unit tested using say JUnit
    */
    public String callAPI(){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type", "application/json");

            //here the port is hard coded. Make sure we specify the same port for mock server in ConsumerAPactTest class
            String uri = "http://localhost:9090/getConsOneData";
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);

            assert responseEntity.getBody() != null;
            System.out.println(responseEntity.getBody());

            return responseEntity.getBody();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
