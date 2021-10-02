package br.com.bicmsystems.payments.dtos;


import br.com.bicmsystems.payments.validators.EmailConstraint;
import br.com.bicmsystems.payments.validators.OnlyAlphanumericConstraint;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateFieldDTO {

    @OnlyAlphanumericConstraint(message = "Invalid name field")
    private String name;

    @EmailConstraint(message = "Invalid email field")
    private String email;

    @OnlyAlphanumericConstraint(message = "Invalid phoneNumber field")
    private String phoneNumber;

    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "Invalid CPF field")
    private String cpf;

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)", message = "Invalid CNPJ field")
    private String cnpj;

}
