package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.entities.PaymentModel;
import br.com.bicmsystems.payments.exceptions.PaymentNotFoundException;
import br.com.bicmsystems.payments.repositories.PaymentCustomRepository;
import br.com.bicmsystems.payments.repositories.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    private static PaymentDTO mapperToDTO(PaymentModel model) {
        return new ObjectMapper().convertValue(model, PaymentDTO.class);
    }

    private static PaymentModel mapperToModel(PaymentDTO dto) {
        return new ObjectMapper().convertValue(dto, PaymentModel.class);
    }

    public PaymentDTO save(PaymentDTO dto) {

        PaymentModel model = mapperToModel(dto);
        PaymentModel newModel = repository.save(model);
        return mapperToDTO(newModel);

    }

    public List<PaymentDTO> findByPvCode(String pvCode) {

        List<PaymentModel> payments = repository.findByPvCode(pvCode);
        List<PaymentDTO> result = payments.stream()
                .map(PaymentService::mapperToDTO)
                .collect(Collectors.toList());
        return result;

    }


    public List<PaymentDTO> findByPvCodeAndType(String pvCode, String type) {

        List<PaymentModel> payments = repository.findByPvCodeAndType(pvCode, type);
        List<PaymentDTO> result = payments.stream()
                .map(PaymentService::mapperToDTO)
                .collect(Collectors.toList());
        return result;

    }

    public List<PaymentDTO> findByPaymentId(Long paymentId) {

        List<PaymentModel> payments = repository.findByPaymentId(paymentId);
        List<PaymentDTO> result = payments.stream()
                .map(PaymentService::mapperToDTO)
                .collect(Collectors.toList());
        return result;

    }

    public PaymentDTO findByPaymentIdAndPvCode(Long paymentId, String pvCode) throws PaymentNotFoundException {

        Optional<PaymentModel> payment = repository.findByPaymentIdAndPvCode(paymentId, pvCode);
        if(payment.isPresent())
            return PaymentService.mapperToDTO(payment.get());
        throw new PaymentNotFoundException(
                String.format("Pagamento não encontrado para o paymentId %s e pvCode %s", paymentId, pvCode));

    }

    public List<PaymentDTO> findAll() {

        Iterable<PaymentModel> payments = repository.findAll();
        List<PaymentModel> listPayments = Lists.newArrayList(payments);
        List<PaymentDTO> result = listPayments.stream()
                .map(PaymentService::mapperToDTO)
                .collect(Collectors.toList());
        return result;
    }

}
