package com.mastermind.moneytransferapp.repository;

import com.mastermind.moneytransferapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Iterable<Transaction> findByUserId(Long id);
}
