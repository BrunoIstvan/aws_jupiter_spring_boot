package br.com.bicmsystems.payments.controllers;


import br.com.bicmsystems.payments.dtos.CreditOrderDTO;
import br.com.bicmsystems.payments.services.CreditOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/credit-orders")
public class CreditOrderController {

    @Autowired
    private CreditOrderService service;

    @GetMapping("/{pvCode}")
    public ResponseEntity<?> findByPvCode(@PathVariable String pvCode) {

        List<CreditOrderDTO> result = service.findByPvCode(pvCode);
        return ResponseEntity.ok(result);

    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CreditOrderDTO> result = service.findAll();
        return ResponseEntity.ok(result);

    }

}
