package br.com.bicmsystems.payments.repositories;


import br.com.bicmsystems.payments.entities.PaymentCreditOrderId;
import br.com.bicmsystems.payments.entities.PaymentCreditOrderModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PaymentCreditOrderRepository
        extends CrudRepository<PaymentCreditOrderModel, PaymentCreditOrderId> {

    List<PaymentCreditOrderModel> findByPaymentId(Long paymentId);

    List<PaymentCreditOrderModel> findByPvCode(String pvCode);

}
