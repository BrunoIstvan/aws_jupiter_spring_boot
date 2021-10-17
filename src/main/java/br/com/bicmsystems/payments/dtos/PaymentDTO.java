package br.com.bicmsystems.payments.dtos;

import br.com.bicmsystems.payments.validators.OnlyAlphanumericConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentDTO {

    private Long paymentId;
    @OnlyAlphanumericConstraint
    private String pvCode;
    private Integer accountNumber;
    private Integer agency;
    private Integer bankCode;
    private Integer brandCode;
    private Double amount;
    private Double discount;
    private Double netAmount;
    @OnlyAlphanumericConstraint
    private String type;

    @JsonIgnore
    private List<PaymentDTO> negotiations;

}
