package com.codechallege.mendel.controller;

import com.codechallege.mendel.model.Transaction;
import com.codechallege.mendel.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;


@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void getTransactionByType() throws Exception{

        Transaction transaction1 = new Transaction("1", "payment", 100.5, "2");
        Transaction transaction2 = new Transaction("2", "payment", 101.5, "1");
        Transaction transaction3 = new Transaction("3", "payment", 102.5, "2");
        Transaction transaction4 = new Transaction("4", "payment", 103.5, "1");
        Transaction transaction5 = new Transaction("5", "payment", 104.5, "1");

        Mockito.when(transactionService.getTransactionsByType("payment"))
                .thenReturn(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transaction/getTransactionByType")
                .param("type", "payment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", is("payment")));
    }

    @Test
    void getTotalAmountByParentId() throws Exception{

        Mockito.when(transactionService.getTotalAmountByParentId("2"))
                .thenReturn(203.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transaction/getTotalAmountByParentId")
                        .param("parentId", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(203.0)));
    }


    @Test
    void addTransaction() throws Exception{
        Transaction transaction = new Transaction("1", "payment", 100.50, "1");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transaction/addTransaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("El servicio inserto correctamente"));
    }

    public static String toJsonString (final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
