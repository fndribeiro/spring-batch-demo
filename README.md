# spring-batch-demo

Demo project with Spring Batch.

This is a spring batch project with different jobs examples.

This project was built alongside Udemy course "Batch Processing with Spring Batch & Spring Boot" provided by Infybuzz Learning.
https://www.udemy.com/course/data-batch-processing-with-spring-batch-spring-boot-spring-framework/

### Main feature:

One of the main features is an asynchronous CSV batch job that simulates a real production job that I had to implement. 
This feature receives a CSV file through an endpoint, reads with Spring Batch and writes to a Postgres database.

![image](https://user-images.githubusercontent.com/45038374/177605190-e3f6374e-1d55-4ca5-a699-11015484fc7e.png)

Besides that, this project covers the following topics:

- Tasklet Steps
- Chunk Oriented Steps
- Job Handling with Rest API and Spring Scheduler
- Different item readers.
- Different item writers.
- Item processors
- Fault Tolerance
