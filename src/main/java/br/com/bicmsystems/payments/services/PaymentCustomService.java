package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.entities.PaymentModel;
import br.com.bicmsystems.payments.repositories.PaymentCustomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentCustomService {

    private final PaymentCustomRepository customRepository;

    private static PaymentDTO mapperToDTO(PaymentModel model) {
        return new ObjectMapper().convertValue(model, PaymentDTO.class);
    }

    private static PaymentModel mapperToModel(PaymentDTO dto) {
        return new ObjectMapper().convertValue(dto, PaymentModel.class);
    }

    public List<PaymentDTO> getBatchItems() {

        List<PaymentModel> payments = customRepository.getPaymentByBatchGet();
        List<PaymentDTO> result = payments.stream()
                .map(PaymentCustomService::mapperToDTO)
                .collect(Collectors.toList());
        return result;
    }

}
