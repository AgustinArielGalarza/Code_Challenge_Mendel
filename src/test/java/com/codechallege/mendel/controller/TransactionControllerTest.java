package com.codechallege.mendel.controller;

import com.codechallege.mendel.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTransactionByType() throws Exception{

        String type = "payment";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transaction/getTransactionByType")
                .param("type", type))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", is(type)));
    }

    @Test
    void getTotalAmountByParentId() throws Exception{

        String parentId = "2";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transaction/getTotalAmountByParentId")
                        .param("parentId", parentId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(203)));
    }


    @Test
    void addTransaction() throws Exception{
        Transaction transaction = Transaction.builder()
                .id("1")
                .type("payment")
                .amount("100.50")
                .parent_id("1")
                .build();

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
