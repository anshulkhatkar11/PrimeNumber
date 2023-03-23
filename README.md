# PrimeNumber
# RESTful service which calculates and returns all the prime numbers up to and including a number provided.

## About 

### 1.Default server port is 8080

### 2.Varying return content types have been provided such as XML based and JSON based depending on value of Accept key in headers
Eg 
In this request, the result would be in JSON

 A. curl --location 'localhost:8080/primes/345' \
--header 'Accept: application/json'

Meanwhile in this request, result would be in XML

 B. curl --location 'localhost:8080/primes/345' \
--header 'Accept: application/xml'


### 3.Caching has been implemented to increase overall performance

### 4.Two algorithms have been written, which can be switched based on optional parameters
Eg.
curl --location 'localhost:8080/primes/345?algorithm=Concurrent' \
--header 'Accept: application/json'

This algorithm uses multithreading

Meanwhile, if we do not provide the request param 'algorithm', then default algorithm used is Sieve of Eratosthenes

curl --location 'localhost:8080/primes/345' \
--header 'Accept: application/json'

### 5.Springboot Actuators are running. Test case has been written which checks the health of the application and whether it's status is UP.
For eg.
curl --location 'localhost:8080/actuator/health' \
--header 'Accept: application/json'


### 6.Swagger OpenAPI have been added to get information related to the API. It can be assessed at the following url
http://localhost:8080/swagger-ui/index.html


### 7.It has been deployed on Railways App
https://primenumber-production.up.railway.app/primes/15
