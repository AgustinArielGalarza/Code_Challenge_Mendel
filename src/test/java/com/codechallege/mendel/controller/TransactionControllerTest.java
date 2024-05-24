package com.codechallege.mendel.controller;

import com.codechallege.mendel.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void transactionPost() throws Exception{
        Transaction transaction = Transaction.builder()
                .id("1")
                .type("payment")
                .amount("100.50")
                .parent_id("1")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transaction/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    public static String toJsonString (final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
