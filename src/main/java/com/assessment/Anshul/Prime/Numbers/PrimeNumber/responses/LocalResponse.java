package com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocalResponse implements ResponseData {
  private int Initial;
  private List<Integer> Primes;
}
