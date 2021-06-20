Hi there! Thank you for this interesting test project! It was useful to solve such a task.
I've remembered specific pattern usages and low level Java facts.

It was crucial for me not to use any 3rd party libs. 
I thought that I will show my skill in working with DBs, SQL, transaction (propagation, isolation),
competitive multithreading solutions.
But I guess that certain project implemented by me will show my high skills as well.

Sorry for any typos in docs, open-api... I just watched EURO-2020 parallel to writing documentation.
Of course, I am not distracting while writing production ones! Ahaha

So, I want to introduce here my approaches of solving business tasks in general:

- I created custom RuntimeException's and ErrorController for handling business errors
  
- I marked unimplemented business methods by @Stub annotation

- I created validation service with help of Strategy pattern and BillPughSingleton pattern per each validator

- I moved all business properties to the configurable application.properties file

- For unit testing I used Junit5 with SpringExtension, MockitoExtension and custom extension for simulating authorization. 
  I like using ```org.hamcrest``` and ```doReturn().```-like constructions.
  Sorry I have no time to write tests for all classes. 
  I chose the most different situations to show how flexible I am about writing different types of tests.
  
- For implementing Pub/Sub event system in general I used Observer pattern. 
  For imitating message queue I used @Scheduled procedures.
  I will try to be in time with creation of architectural diagram! You will see the picture below if I have time.
  
- As for Docker I used docker-compose with volume for logs
