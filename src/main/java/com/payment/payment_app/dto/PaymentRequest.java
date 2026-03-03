package com.payment.payment_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String userId;        //추후 개선
    private String accountNumber;
    private Long amount;
    private String merchantName;
}