package com.assessment.Anshul.Prime.Numbers.PrimeNumber.service;

import com.assessment.Anshul.Prime.Numbers.PrimeNumber.exceptions.NegativeInputException;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses.LocalResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PrimeNumberServiceTest {

  @Autowired private PrimeNumberService primeNumberService;


  @Test
  @DisplayName("Get Prime Numbers based on Valid input and Sieve Algorithm")
  public void whenValidInput_thenPrimeNumbersFound() throws NegativeInputException {
    int inputNumber = 10;
    List<Integer> outputResult = createOutputList();

    LocalResponse primes = primeNumberService.calculatePrimesUsingSieve(inputNumber);

    assertNotNull(primes);
    assertEquals(inputNumber, primes.getInitial());
    assertEquals(outputResult, primes.getPrimes());
  }

  @Test
  @DisplayName("Get Prime Numbers based on Valid input and Concurrent Algorithm")
  public void whenValidInputThenPrimeNumbersFoundUsingConcurrentAlgorithm() throws NegativeInputException {
    int inputNumber = 10;
    List<Integer> outputResult = createOutputList();

    LocalResponse primes = primeNumberService.calculatePrimesUsingConcurrentAlgorithm(inputNumber);

    assertNotNull(primes);
    assertEquals(inputNumber, primes.getInitial());
    assertEquals(outputResult, primes.getPrimes());
  }

  @Test
  @DisplayName("Get Prime Numbers based on Invalid input and Sieve Algorithm")
  public void whenInValidInputThenPrimeNumbersNotFound() {
    int inputNumber = -4;
    String message="Input number is negative";

    NegativeInputException thrown = Assertions.assertThrows(NegativeInputException.class, () -> {
      primeNumberService.calculatePrimesUsingSieve(inputNumber);
    });

    assertEquals(message, thrown.getMessage());
  }

  @Test
  @DisplayName("Get Prime Numbers based on Invalid input And Concurrent Algorithm")
  public void whenInValidInputThenPrimeNumbersNotFoundUsingConcurrentAlgorithm() {
    int inputNumber = -20;
    String message="Input number is negative";

    NegativeInputException thrown = Assertions.assertThrows(NegativeInputException.class, () -> {
      primeNumberService.calculatePrimesUsingConcurrentAlgorithm(inputNumber);
    });

    assertEquals(message, thrown.getMessage());
  }

  @Test
  @DisplayName("Get Prime Numbers based on Input Out of Range And Sieve Algorithm")
  public void whenInputOutOfRangeThenPrimeNumbersNotFound() {
    int inputNumber = 21474900;
    String message="Integer Overflow. Please enter a number in the range of 0 to 21474899";

    ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
      primeNumberService.calculatePrimesUsingSieve(inputNumber);
    });

    assertEquals(message, thrown.getMessage());
  }

  private List<Integer> createOutputList() {
    List<Integer> output=new ArrayList<>();
    output.add(2);
    output.add(3);
    output.add(5);
    output.add(7);
    return output;
  }
}
