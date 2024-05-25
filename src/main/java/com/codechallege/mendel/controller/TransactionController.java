package com.codechallege.mendel.controller;

import com.codechallege.mendel.model.Transaction;
import com.codechallege.mendel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/addTransaction")
    ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(201).body("El servicio inserto correctamente");
    }

    @GetMapping("/getTransactionByType")
    ResponseEntity<List<Transaction>> getTransactionByType(@RequestParam String type){
        List<Transaction> transactions = transactionService.getTransactionsByType(type);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/getTotalAmountByParentId")
    ResponseEntity<Double> getTotalAmountByParentId(@RequestParam String parentId){
        double totalAmount = transactionService.getTotalAmountByParentId(parentId);
        return ResponseEntity.ok(totalAmount);
    }

}
