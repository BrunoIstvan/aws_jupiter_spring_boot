package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.PaymentCreditOrderDTO;
import br.com.bicmsystems.payments.entities.PaymentCreditOrderModel;
import br.com.bicmsystems.payments.repositories.PaymentCreditOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentCreditOrderService {

    @Autowired
    private PaymentCreditOrderRepository repository;

    public List<PaymentCreditOrderDTO> findByPaymentId(Long paymentId) {

        List<PaymentCreditOrderModel> models = repository.findByPaymentId(paymentId);
        List<PaymentCreditOrderDTO> result = models.stream()
                .map(PaymentCreditOrderService::mapper)
                .collect(Collectors.toList());
        return result;

    }

    public List<PaymentCreditOrderDTO> findByPvCode(String pvCode) {

        List<PaymentCreditOrderModel> models = repository.findByPvCode(pvCode);
        List<PaymentCreditOrderDTO> result = models.stream()
                .map(PaymentCreditOrderService::mapper)
                .collect(Collectors.toList());
        return result;

    }

    public List<PaymentCreditOrderDTO> findAll() {

        Iterable<PaymentCreditOrderModel> models = repository.findAll();
        List<PaymentCreditOrderModel> listModels = Lists.newArrayList(models);
        List<PaymentCreditOrderDTO> result = listModels.stream()
                .map(PaymentCreditOrderService::mapper)
                .collect(Collectors.toList());
        return result;
    }

    private static PaymentCreditOrderDTO mapper(PaymentCreditOrderModel model) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentCreditOrderDTO dto = mapper.convertValue(model, PaymentCreditOrderDTO.class);
//        return PaymentCreditOrderDTO.builder()
//                .amount(model.getAmount())
//                .netAmount(model.getNetAmount())
//                .paymentId(model.getPaymentId())
//                .pvCode(model.getPvCode())
//                .discount(model.getDiscount())
//                .type(model.getType())
//                .build();
        return dto;
    }

}
