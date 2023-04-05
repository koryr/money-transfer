package com.pezesha.service.impl;

import com.pezesha.exception.BadRequestException;
import com.pezesha.exception.RecordNotFoundException;
import com.pezesha.model.Account;
import com.pezesha.repository.AccountRepository;
import com.pezesha.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(UUID id) {
        return null;
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Account not found" + id));
    }

    @Override
    public Account saveOrUpdate(Account entity) {
        Optional<Account> account = accountRepository.findByAccountNo(entity.getAccountNo());
        if (account.isPresent()) {
            throw new BadRequestException("Account with id " + entity.getAccountNo() + " already exists");
        }

        Account acct = accountRepository.saveAndFlush(entity);

        LOGGER.info("Account created with id " + acct.getId() + " with initial deposit " + acct.getBalance());

        return acct;
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public Account deposit(String accountNo, float amount) {
        Account account = findAccount(accountNo);
        account.addToBalance(amount);
        Account accountUpdate = accountRepository.saveAndFlush(account);
        LOGGER.info(amount + " deposited to account " + accountNo);
        return accountUpdate;
    }

    private Account findAccount(String accountNo) {
        Optional<Account> account = accountRepository.findByAccountNo(accountNo.trim());
        if (!account.isPresent()) {
            throw new RecordNotFoundException("Account with account_no " + accountNo + " not found");
        }
        return account.get();
    }
}
