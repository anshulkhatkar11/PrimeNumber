package com.assessment.Anshul.Prime.Numbers.PrimeNumber.service;

import com.assessment.Anshul.Prime.Numbers.PrimeNumber.exceptions.NegativeInputException;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses.LocalResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Cacheable(key="#n",value="LocalResponse")
@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

  @Override
  public LocalResponse calculatePrimesUsingSieve(int n) throws NegativeInputException {
    if (n < 0) {
      throw new NegativeInputException("Input number is negative");
    }
    boolean prime[] = new boolean[n + 1];
    Arrays.fill(prime, true);
    for (int p = 2; p * p <= n; p++) {
      if (prime[p]) {
        for (int i = p * 2; i <= n; i += p) {
          prime[i] = false;
        }
      }
    }
    List<Integer> primeNumbers = new ArrayList<>();
    for (int i = 2; i <= n; i++) {
      if (prime[i]) {
        primeNumbers.add(i);
      }
    }

    return mapper(n, primeNumbers);
  }


  @Override
  public LocalResponse calculatePrimesUsingConcurrentAlgorithm(int n) throws NegativeInputException {
    if (n < 0) {
      throw new NegativeInputException("Input number is negative");
    }

    ExecutorService executor = Executors.newFixedThreadPool(4);

    List<Future<Integer>> primeFutures = new ArrayList<>();

    for (int i = 2; i <= n; i++) {
      primeFutures.add(executor.submit(new PrimeNumberCalculator(i)));
    }

    List<Integer> primes = new ArrayList<>();

    for (Future<Integer> primeFuture : primeFutures) {
      try {
        int prime = primeFuture.get();
        if (prime != -1) {
          primes.add(prime);
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    List<Integer> ans=new ArrayList<>();
    for (int prime : primes) {
      ans.add(prime);
    }
    executor.shutdown();
    return mapper(n,ans);
  }


  LocalResponse mapper(int number, List<Integer> primes) {
    LocalResponse localResponse = new LocalResponse();
    localResponse.setInitial(number);
    localResponse.setPrimes(primes);
    return localResponse;
  }
}
