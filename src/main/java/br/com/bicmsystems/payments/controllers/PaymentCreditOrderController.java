package br.com.bicmsystems.payments.controllers;

import br.com.bicmsystems.payments.dtos.PaymentCreditOrderDTO;
import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.services.PaymentCreditOrderService;
import br.com.bicmsystems.payments.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/payment-credit-order")
public class PaymentCreditOrderController {

    @Autowired
    private PaymentCreditOrderService service;

    @GetMapping("/daily")
    public ResponseEntity<?> getDaily(@RequestParam Long paymentId) {

        List<PaymentCreditOrderDTO> dtos = service.findByPaymentId(paymentId);
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/by-pv")
    public ResponseEntity<?> getDailyByPV(@RequestParam String pvCode) {

        List<PaymentCreditOrderDTO> dtos = service.findByPvCode(pvCode);
        return ResponseEntity.ok(dtos);

    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<PaymentCreditOrderDTO> dtos = service.findAll();
        return ResponseEntity.ok(dtos);

    }

}
