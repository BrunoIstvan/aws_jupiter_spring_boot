package br.com.bicmsystems.payments.controllers;

import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.dtos.ValidateFieldDTO;
import br.com.bicmsystems.payments.services.ValidateFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/validate-fields")
public class ValidateFieldsController {

    @Autowired
    private ValidateFieldService service;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ValidateFieldDTO dto,
                                  BindingResult result) {
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(e ->
                e.getField() + " - " + e.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(service.save(dto));

    }

}
