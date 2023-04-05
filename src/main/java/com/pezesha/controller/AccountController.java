package com.pezesha.controller;

import com.pezesha.exception.BadRequestException;
import com.pezesha.model.Account;
import com.pezesha.payload.ApiResponse;
import com.pezesha.payload.JwtResponse;
import com.pezesha.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
@CrossOrigin(origins = "*")
@RestController
@Validated
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    /**
     * API to CREATE an account
     *
     * @param entity account entity
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(
            @Valid
            @RequestBody Account entity
    ) {
        try {
            if (entity.getBalance() < 0) {
                throw new BadRequestException("Amount should not be less then 0");
            }
            Account account = accountService.saveOrUpdate(entity);
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Accounts",
                            account
                    )
            );
        } catch (BadRequestException e) {
            LOGGER.error(">>> Error getting accounts {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
     * API to GET all accounts
     *
     * @return ResponseEntity<ApiResponse>
     */

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        try {
            List<Account> accounts = accountService.listAll();
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Accounts",
                            accounts
                    )
            );
        } catch (BadRequestException e) {
            LOGGER.error(">>> Error getting accounts {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
     * Get account By ID
     *
     * @param id account primary key id
     * @return ResponseEntity<ApiResponse>
     */

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(
            @PathVariable("id") long id
    ) {
        try {
            Account account = accountService.getById(id);
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Account",
                            account
                    )
            );
        } catch (BadRequestException e) {
            LOGGER.error(">>> Error getting accounts {}", e.getMessage());
            throw new BadRequestException("Error getting accounts", e);
        }
    }

    /**
     * API use to deposit amount to an account
     *
     * @param entity account entity
     * @return ResponseEntity<ApiResponse>
     */

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestBody Account entity
    ) {
        try {
            if (entity.getBalance() < 0) {
                throw new BadRequestException("Amount should not be less then 0");
            }
            Account account = accountService.deposit(entity.getAccountNo(), entity.getBalance());
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Accounts",
                            account
                    )
            );
        } catch (BadRequestException e) {
            LOGGER.error(">>> Error getting accounts {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
