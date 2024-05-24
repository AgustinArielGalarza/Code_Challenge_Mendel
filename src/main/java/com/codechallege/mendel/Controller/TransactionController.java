package com.codechallege.mendel.Controller;

import com.codechallege.mendel.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @PostMapping("/add")
    ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        //TODO: generar el servicio
        String message = "Success";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
