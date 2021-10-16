package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.CreditOrderDTO;
import br.com.bicmsystems.payments.entities.CreditOrderModel;
import br.com.bicmsystems.payments.enums.NegotiationType;
import br.com.bicmsystems.payments.repositories.CreditOrderRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditOrderService {

    @Autowired
    private CreditOrderRepository repository;

    public List<CreditOrderDTO> findByPvCode(String pvCode) {

        List<CreditOrderModel> creditOrders = repository.findByPvCode(pvCode);
        List<CreditOrderDTO> result = creditOrders.stream()
                .map(CreditOrderService::mapper)
                .collect(Collectors.toList());
        return result;

    }

    public List<CreditOrderDTO> findAll() {

        Iterable<CreditOrderModel> creditOrders = repository.findAll();
        List<CreditOrderModel> listCreditOrders = Lists.newArrayList(creditOrders);
        List<CreditOrderDTO> result = listCreditOrders.stream()
                .map(CreditOrderService::mapper)
                .collect(Collectors.toList());
        return result;

    }

    private static CreditOrderDTO mapper(CreditOrderModel model) {
        return CreditOrderDTO.builder()
                .creditOrderTimestamp(model.getCreditOrderTimestamp())
                .pvCode(model.getPvCode())
                .amount(model.getAmount())
                .negotiationType(NegotiationType.valueOf(model.getNegotiationType()))
                .build();
    }

}
