package com.example.services;

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
@Transactional
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(long idSourceAccount, long idDestinationAccount, BigDecimal amount) {
        Account sourceAccount = accountRepository.findAccountById(idSourceAccount);
        Account destinationAccount = accountRepository.findAccountById(idDestinationAccount);
        accountRepository.changeAmount(idSourceAccount, sourceAccount.getAmount().subtract(amount));
        accountRepository.changeAmount(idDestinationAccount, destinationAccount.getAmount().add(amount));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
