package com.codechallege.mendel.controller;

import com.codechallege.mendel.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @PostMapping("/addTransaction")
    ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        //TODO: generar el servicio
        return new ResponseEntity<>("El servicio inserto correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/getTransactionByType")
    ResponseEntity<List<Transaction>> getTransactionByType(@RequestParam String type){
        //TODO: generar el servicio
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction("1", "payment", 100.5, "2");
        Transaction transaction2 = new Transaction("2", "payment", 101.5, "1");
        Transaction transaction3 = new Transaction("3", "payment", 102.5, "2");
        Transaction transaction4 = new Transaction("4", "payment", 103.5, "1");
        Transaction transaction5 = new Transaction("5", "payment", 104.5, "1");
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        transactionList.add(transaction5);
        return ResponseEntity.ok(transactionList);
    }
    

}
