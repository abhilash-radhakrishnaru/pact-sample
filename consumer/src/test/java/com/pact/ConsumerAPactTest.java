package com.pact;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.ConsumerA;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Rule;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class ConsumerAPactTest {

    @Rule
    /* the server name & port should match with the URL which consumer uses to send request.
        pact will start a mock server and this helps pact to get an idea what the consumers request and what it expects
        This will be converted as the contract.
    */
    public PactProviderRule mockProvider = new PactProviderRule("provider_A", "localhost", 9090, this);

    @Pact(consumer = "consumer_A")
    //this method needs to be written for each end point
    public RequestResponsePact createPactForGetString(PactDslWithProvider builder)
            throws JsonProcessingException {

        String expectedString = "Hello ConsumerA";

        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","text/plain");

        //this will be stored as the contract for the endpoint from consumer - consumer_one
        return builder
                .given("Test for getConsOneData end point") //this will be used in provider test
                .uponReceiving("GET request for string")
                .path("/getConsOneData")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(expectedString)
                .toPact();
    }

    @Test
    @PactVerification(value = "provider_A", fragment = "createPactForGetString")
    /* here the test will be executed with the server started with name
     "provider_one" and execute the method createPactForGetString
     */
    public void testConsumerGetRequestToString() {
        System.out.println(mockProvider.getUrl());
        String expectedString = "Hello ConsumerA";

        String resultString = (new ConsumerA()).callAPI();
        assertEquals(expectedString, resultString);
    }

}
