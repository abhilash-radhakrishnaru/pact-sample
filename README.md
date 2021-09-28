# This project demos contract testing using PACT

#Disclaimer
The idea here is to show a simple quick demo of pact with minimum code. So I have done a lot of hard coding here. The code you find here may not be optimal or of high standard

#Steps
1) Start pact broker using "docker-compose -f "docker-compose.yml" up -d --build"
   This will be listening on port 9292. Refer docker-compose.yml file for details
    Can check the console using http://localhost:9292

2)  Make sure code is working fine 
    - start the provider. It will start service in http://loclhost:9090
    - Run the ConsumerA main class. It will connect to above service and should run without errors
    - looks like things are working fine. Now stop the producer
    
3) Now see the contract getting created
    - Run ConsumerAPactTest by "mvn test". This will start a mock server on http://localhost:9090 and run the tests. All tests should pass. It will also create contract json under target/pacts folder
    - Run "mvn pact:publish". This will publish the contract to PACT server running locally. Can verify this from http://localhost:9292
    - Now its time for producer contract testing. For this, 
    	- make sure the producer is running (PACT will be sending requests to this producer to make sure things are working fine)
    	- Now run the ProducerPactTest class. Here PACT checks the test class, picks up the @State value and gets the test from contract matching the "given" attribute and executes it
    
    
