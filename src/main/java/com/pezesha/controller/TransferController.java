package com.pezesha.controller;

import com.pezesha.exception.BadRequestException;
import com.pezesha.model.Transfer;
import com.pezesha.payload.ApiResponse;
import com.pezesha.payload.TransferReq;
import com.pezesha.service.TransferService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
@CrossOrigin(origins = "*")
@RestController
@Validated
public class TransferController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private TransferService transferService;

    /**
     * API to transfer amount from one account to another
     *
     * @param transferReq request payload
     * @return ResponseEntity<ApiResponse>
     */

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @Valid
            @RequestBody TransferReq transferReq
    ) {

        try {
            Transfer transfer = transferService.transfer(
                    transferReq.getFromAccountNo(),
                    transferReq.getToAccountNo(),
                    transferReq.getAmount()
            );
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Transfer successful",
                            transfer
                    )
            );
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }

    }
}
