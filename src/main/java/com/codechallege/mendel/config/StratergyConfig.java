package com.codechallege.mendel.config;

import com.codechallege.mendel.stratergy.InMemoryITransaction;
import com.codechallege.mendel.stratergy.ITransactionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StratergyConfig {

    @Bean
    public ITransactionStrategy iTransactionStrategy() {
        return new InMemoryITransaction();
    }
}