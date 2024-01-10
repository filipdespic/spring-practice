package com.example.controllers;

import com.example.exceptions.NotEnoughMoneyException;
import com.example.model.ErrorDetails;
import com.example.model.PaymentDetails;
import com.example.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDetails> makePayment(@RequestHeader String requestId, @RequestBody PaymentDetails payment){
        logger.info("Received request with ID  " + requestId + " ; Payment amount: " + payment.getAmount());
        payment.setId(UUID.randomUUID().toString());

        return ResponseEntity.status(HttpStatus.OK).header("requestId", requestId).body(payment);
    }
}
