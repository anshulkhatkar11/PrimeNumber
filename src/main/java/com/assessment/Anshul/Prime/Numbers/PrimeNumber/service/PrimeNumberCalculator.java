package com.assessment.Anshul.Prime.Numbers.PrimeNumber.service;


import java.util.concurrent.Callable;

class PrimeNumberCalculator implements Callable<Integer> {
  private int number;

  PrimeNumberCalculator(int number) {
    this.number = number;
  }

  @Override
  public Integer call() {
    if (number < 2) {
      return -1;
    }
    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        return -1;
      }
    }
    return number;
  }
}
