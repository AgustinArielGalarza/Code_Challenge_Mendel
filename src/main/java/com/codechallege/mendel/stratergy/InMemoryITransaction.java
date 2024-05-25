package com.codechallege.mendel.stratergy;

import com.codechallege.mendel.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InMemoryITransaction implements ITransactionStrategy {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByType(String type) {
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalAmountByParentId(String parentId) {
        return transactions.stream()
                .filter(transaction -> transaction.getParent_id().equals(parentId))
                .mapToDouble(transaction -> transaction.getAmount())
                .sum();
    }
}