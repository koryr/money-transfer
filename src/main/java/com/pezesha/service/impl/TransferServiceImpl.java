package com.pezesha.service.impl;

import com.pezesha.exception.InsufficientBalanceException;
import com.pezesha.exception.RecordNotFoundException;
import com.pezesha.model.Account;
import com.pezesha.model.Transfer;
import com.pezesha.repository.AccountRepository;
import com.pezesha.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
@Service
public class TransferServiceImpl implements TransferService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Transfer transfer(String fromAccountNo, String toAccountNo, float amount) {
        if (amount <= 0) {
            throw new RecordNotFoundException("Amount should be greater then 0");
        }
        Optional<Account> fromAccount = accountRepository.findByAccountNo(fromAccountNo);
        if (!fromAccount.isPresent()) {
            throw new RecordNotFoundException("Account with account "+fromAccountNo+" not found");
        }

        Optional<Account> toAccount = accountRepository.findByAccountNo(toAccountNo);
        if (!toAccount.isPresent()) {
            throw new RecordNotFoundException("Account with account "+toAccountNo+" not found");
        }

        Account from = fromAccount.get();
        Account to = toAccount.get();
        Account fromAcctInfo = new Account();
        Account toAcctInfo = new Account();

        try {
            from.subtractFromBalance(amount);
            to.addToBalance(amount);

            fromAcctInfo = accountRepository.saveAndFlush(from);
            toAcctInfo = accountRepository.saveAndFlush(to);

            LOGGER.info(amount+" transferred from account "+fromAccountNo+" to account "+toAccountNo);

            return new Transfer(fromAccountNo, toAccountNo, amount,  "Transfer Complete", fromAcctInfo, toAcctInfo);

        } catch (InsufficientBalanceException e) {

            LOGGER.info("Insufficient balance in account "+fromAccountNo+", aborting transfer ...");
            throw new InsufficientBalanceException("Insufficient balance in account "+fromAccountNo+", aborting transfer ...");
        }
    }
}
