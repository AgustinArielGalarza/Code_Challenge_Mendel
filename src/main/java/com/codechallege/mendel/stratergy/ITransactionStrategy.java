package com.codechallege.mendel.stratergy;

import com.codechallege.mendel.model.Transaction;

import java.util.List;

public interface ITransactionStrategy {
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByType(String type);
    double getTotalAmountByParentId(String parentId);
}