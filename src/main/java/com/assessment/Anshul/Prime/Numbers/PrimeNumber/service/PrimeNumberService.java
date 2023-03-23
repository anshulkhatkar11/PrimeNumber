package com.assessment.Anshul.Prime.Numbers.PrimeNumber.service;

import com.assessment.Anshul.Prime.Numbers.PrimeNumber.exceptions.NegativeInputException;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses.LocalResponse;

public interface PrimeNumberService {
  LocalResponse calculatePrimesUsingSieve(int n) throws NegativeInputException;

  LocalResponse calculatePrimesUsingConcurrentAlgorithm(int n) throws NegativeInputException;
}
