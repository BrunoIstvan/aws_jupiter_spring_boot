package br.com.bicmsystems.payments.repositories;


import br.com.bicmsystems.payments.entities.CreditOrderModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CreditOrderRepository extends CrudRepository<CreditOrderModel, String> {

    List<CreditOrderModel> findByPvCode(String pvCode);

}
