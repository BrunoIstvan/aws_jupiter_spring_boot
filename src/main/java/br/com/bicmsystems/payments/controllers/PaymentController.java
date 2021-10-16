package br.com.bicmsystems.payments.controllers;

import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.exceptions.PaymentNotFoundException;
import br.com.bicmsystems.payments.services.PaymentCustomService;
import br.com.bicmsystems.payments.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentCustomService customService;

    @GetMapping("/daily")
    public ResponseEntity<?> getPaymentsByPvCode(@RequestParam String pvCode) {
        return ResponseEntity.ok(service.findByPvCode(pvCode));
    }

    @GetMapping("/by-payment")
    public ResponseEntity<?> getPaymentByPaymentId(@RequestParam Long paymentId) {
        return ResponseEntity.ok(service.findByPaymentId(paymentId));
    }

    @GetMapping("/daily/{pvCode}/{type}")
    public ResponseEntity<?> getPaymentsByPvCodeAndType(@PathVariable String pvCode, @PathVariable String type) {
        return ResponseEntity.ok(service.findByPvCodeAndType(pvCode, type));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPaymentByPaymentIdAndPvCode(@RequestParam Long paymentId,
                                                            @RequestParam String pvCode) {
        try {
            PaymentDTO payment = service.findByPaymentIdAndPvCode(paymentId, pvCode);
            return ResponseEntity.ok(payment);
        } catch (PaymentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/batch")
    public ResponseEntity<?> findAllUsingBatchGetItem() {
        return ResponseEntity.ok(customService.getBatchItems());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PaymentDTO dto) {

        try {
            return ResponseEntity.ok(service.save(dto));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

    }


}
