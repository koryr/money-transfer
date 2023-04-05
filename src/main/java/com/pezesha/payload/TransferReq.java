package com.pezesha.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
@Data
public class TransferReq {
    @NonNull
    @NotBlank(message = "Account From is mandatory")
    private String fromAccountNo;
    @NonNull
    @NotBlank(message = "AccountNo to is mandatory")
    private String toAccountNo;
    private float amount;

    public TransferReq() {
    }
}
