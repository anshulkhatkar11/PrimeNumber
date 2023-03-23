package com.assessment.Anshul.Prime.Numbers.PrimeNumber.controller;

import com.assessment.Anshul.Prime.Numbers.PrimeNumber.exceptions.NegativeInputException;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses.LocalResponse;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.service.PrimeNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
public class PrimeController {

  @Autowired private PrimeNumberService primeNumberService;

  private final Logger LOGGER = LoggerFactory.getLogger(PrimeController.class);

  @Operation(summary="This API returns all prime numbers upto and including the input number")
  @ApiResponses(
    value={
      @ApiResponse(responseCode = "200",
      description="Returned all prime numbers till given number"),
      @ApiResponse(responseCode = "400",
        description="Bad Request")
    }
  )
  @GetMapping(value = "/primes/{number}",
    produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<LocalResponse> getPrimes(@PathVariable("number") int number, @RequestParam(required = false) String algorithm)
      throws NegativeInputException {
    LocalResponse response = null;
    LOGGER.info("Inside primeNumberService of PrimeNumber Controller");
    if(algorithm!=null && algorithm.equals("Concurrent")){
      response = primeNumberService.calculatePrimesUsingConcurrentAlgorithm(number);
    }else{
      response = primeNumberService.calculatePrimesUsingSieve(number);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
