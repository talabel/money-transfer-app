package com.mastermind.moneytransferapp.controller;

import com.mastermind.moneytransferapp.model.Transaction;
import com.mastermind.moneytransferapp.model.User;
import com.mastermind.moneytransferapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping(value = "/")
    public ResponseEntity<Iterable<Transaction>> findAll() {
        Iterable <Transaction> transactions = transactionRepository.findAll();
        if(!transactions.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(!optionalTransaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Transaction transaction = optionalTransaction.get();
        return ResponseEntity.ok(transaction);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Transaction> post(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(!optionalTransaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Transaction newTransaction = optionalTransaction.get();
        newTransaction.setTransactionId(transaction.getTransactionId());
        transactionRepository.save(newTransaction);
        return ResponseEntity.ok(newTransaction);
    }

}
