package com.assessment.Anshul.Prime.Numbers.PrimeNumber.controller;

import com.assessment.Anshul.Prime.Numbers.PrimeNumber.responses.LocalResponse;
import com.assessment.Anshul.Prime.Numbers.PrimeNumber.service.PrimeNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrimeController.class)
class PrimeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrimeNumberService primeNumberService;

    private LocalResponse localResponse;

    @BeforeEach
    void setup(){
      List<Integer> outputPrimes =new ArrayList<>();
      outputPrimes.add(2);
      outputPrimes.add(3);
      outputPrimes.add(5);
      outputPrimes.add(7);

      localResponse=new LocalResponse();
      localResponse.setInitial(10);
      localResponse.setPrimes(outputPrimes);
    }

    @Test
    @DisplayName("Get Prime Numbers when input number is valid Using Sieve Algorithm")
    void whenInputIsValidAndAlgorithmIsSieve() throws Exception {
      int input=10;
      Mockito.when(primeNumberService.calculatePrimesUsingSieve(input)).thenReturn(localResponse);

      mockMvc.perform(MockMvcRequestBuilders.get("/primes/{input}",input)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.initial").value(localResponse.getInitial()))
        .andExpect(jsonPath("$.primes").value(localResponse.getPrimes()));
    }

  @Test
  @DisplayName("Get Prime Numbers when input number is valid Using Concurrent Algorithm")
  void whenInputIsValidAndAlgorithmIsConcurrent() throws Exception {
    int input=10;
    Mockito.when(primeNumberService.calculatePrimesUsingConcurrentAlgorithm(input)).thenReturn(localResponse);

    mockMvc.perform(MockMvcRequestBuilders.get("/primes/{input}?algorithm=Concurrent",input)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.initial").value(localResponse.getInitial()))
      .andExpect(jsonPath("$.primes").value(localResponse.getPrimes()));
  }

}