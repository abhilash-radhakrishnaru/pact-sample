package com.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.VersionSelector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("provider_A")
@Consumer("consumer_A")
@PactBroker(
        host = "localhost",
        port = "9292",
        consumerVersionSelectors = {
                @VersionSelector(tag = "my-tag-v1") //this tag should match with whats given in consumer pom.xml
        })
public class ProducerPactTest {
        @TestTemplate
        @ExtendWith(PactVerificationInvocationContextProvider.class)
        void pactVerificationTestTemplate(PactVerificationContext context) {
                context.verifyInteraction();
        }

        @BeforeAll
        static void enablePublishingPact() {
                System.setProperty("pact.verifier.publishResults", "true");
        }

        @BeforeEach
        void before(PactVerificationContext context) {
                //make sure the producer is running in this URL (host & port)
                context.setTarget(new HttpTestTarget("localhost", 9090));
        }

        @State("Test for getConsOneData end point")
        /*
        The @State annotation must match the given() parameter value that was provided in
        the ConsumerAPactTest class
         */
        public void validConsumerOneMessage() {

        }
}
