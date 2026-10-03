package com.payment.payment_app.controller;

import com.payment.payment_app.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
@RequestMapping("/api/v1/app/payments")
@RequiredArgsConstructor
public class PaymentAppController {

    private final RestClient bankClient;

    @PostMapping("/send")
    public ResponseEntity<?> sendPayment(@RequestBody PaymentRequest request) {

        request.setUserId("user3");

        log.info("결제 앱 전송 데이터 - userId: {}, account: {}", request.getUserId(), request.getAccountNumber());

        try {
            // 1. Bank-Mock 서버로 결제 요청 전송
            ResponseEntity<Object> response = bankClient.post()
                    .uri("/api/v1/payments")
                    .body(request)
                    .retrieve()
                    .toEntity(Object.class);

            log.info("결제 앱: 은행 응답 수신 성공");
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

        } catch (Exception e) {
            log.error("결제 앱: 은행 통신 실패", e);
            return ResponseEntity.internalServerError().body("{\"status\":\"ERROR\", \"message\":\"은행 서버 연결 실패\"}");
        }
    }
}
