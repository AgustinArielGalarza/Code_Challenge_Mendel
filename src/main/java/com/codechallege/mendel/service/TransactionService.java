package com.codechallege.mendel.service;

import com.codechallege.mendel.model.Transaction;
import com.codechallege.mendel.stratergy.ITransactionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final ITransactionStrategy ITransactionStrategy;

    @Autowired
    public TransactionService(ITransactionStrategy ITransactionStrategy) {
        this.ITransactionStrategy = ITransactionStrategy;
    }

    public void addTransaction(Transaction transaction) {
        ITransactionStrategy.addTransaction(transaction);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return ITransactionStrategy.getTransactionsByType(type);
    }

    public double getTotalAmountByParentId(String parentId) {
        return ITransactionStrategy.getTotalAmountByParentId(parentId);
    }
}