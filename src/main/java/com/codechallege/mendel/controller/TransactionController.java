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



}
