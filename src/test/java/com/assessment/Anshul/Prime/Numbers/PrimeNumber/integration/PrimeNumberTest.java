package com.assessment.Anshul.Prime.Numbers.PrimeNumber.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Get Prime Numbers when input number is valid")
  public void whenInputNumberIsValid() throws Exception {

    List<Integer> outputPrimes = createOutputList();
    this.mockMvc.perform(get("/primes/10")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.initial").value(10))
      .andExpect(jsonPath("$.primes").value(outputPrimes));
  }


  @Test
  @DisplayName("Get Prime Numbers when input number is negative")
  public void whenInputNumberIsNegative() throws Exception {
    this.mockMvc.perform(get("/primes/-4")).andDo(print()).andExpect(status().is4xxClientError())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.message").value("Input number is negative"));
  }

  @Test
  @DisplayName("Get Prime Numbers when input number is valid and Concurrent Algorithm is Used")
  public void whenInputNumberIsValidAndConcurrentAlgorithmIsUsed() throws Exception {
    List<Integer> outputPrimes = createOutputList();
    this.mockMvc.perform(get("/primes/10?algorithm=Concurrent")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.initial").value(10))
      .andExpect(jsonPath("$.primes").value(outputPrimes));
  }

  @Test
  @DisplayName("Get Prime Numbers when input number is invalid and Concurrent Algorithm is Used")
  public void whenInputNumberIsInvalidAndConcurrentAlgorithmIsUsed() throws Exception {
    List<Integer> outputPrimes = createOutputList();
    this.mockMvc.perform(get("/primes/-4?algorithm=Concurrent")).andDo(print()).andExpect(status().is4xxClientError())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.message").value("Input number is negative"));
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
