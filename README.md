Approaches of solving business tasks in general:

- I created custom RuntimeException's and ErrorController for handling business errors
  
- I created validation service with help of Strategy pattern and BillPughSingleton pattern per each validator

- I moved all business properties to the configurable application.properties file

- For unit testing I used Junit5 with SpringExtension, MockitoExtension and custom extension for simulating authorization. 
  I prefer using ```org.hamcrest``` and ```doReturn().```-like constructions.
  
- For implementing Pub/Sub event system in general I used Observer pattern. 
  For imitating message queue I used @Scheduled procedures.
  
- As for Docker I used docker-compose with volume for logs
