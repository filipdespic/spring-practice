package com.example.services;

import com.example.exceptions.AccountNotFoundException;
import com.example.model.Account;
import com.example.model.PaymentDetails;
import com.example.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.function.EntityResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Transactional
    public void transferMoney(long idSourceAccount, long idDestinationAccount, BigDecimal amount) {
        Account sourceAccount = accountRepository.findById(idSourceAccount).orElseThrow(() -> new AccountNotFoundException());
        Account destinationAccount = accountRepository.findById(idDestinationAccount).orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount = sourceAccount.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = destinationAccount.getAmount().add(amount);
        
        accountRepository.changeAmount(idSourceAccount, senderNewAmount);
        accountRepository.changeAmount(idDestinationAccount, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
