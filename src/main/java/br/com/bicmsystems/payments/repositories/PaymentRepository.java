package br.com.bicmsystems.payments.repositories;


import br.com.bicmsystems.payments.entities.PaymentId;
import br.com.bicmsystems.payments.entities.PaymentModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface PaymentRepository
        extends CrudRepository<PaymentModel, PaymentId> {

    List<PaymentModel> findByPvCode(String pvCode);

    List<PaymentModel> findByPaymentId(Long paymentId);

    Optional<PaymentModel> findByPaymentIdAndPvCode(Long paymentId, String pvCode);

    List<PaymentModel> findByPvCodeAndType(String pvCode, String type);

}
