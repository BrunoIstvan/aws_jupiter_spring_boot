package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.PaymentDTO;
import br.com.bicmsystems.payments.entities.PaymentId;
import br.com.bicmsystems.payments.entities.PaymentModel;
import br.com.bicmsystems.payments.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    List<PaymentModel> expectedModel = Arrays.asList(
            PaymentModel.builder()
                    .id(PaymentId.builder()
                            .paymentId(1L)
                            .pvCode("1").build())
                    .bankCode(111)
                    .accountNumber(11111)
                    .agency(1)
                    .brandCode(1)
                    .amount(new BigDecimal(1000.0))
                    .discount(new BigDecimal(100))
                    .netAmount(new BigDecimal(900))
                    .type("CREDIT").build(),
            PaymentModel.builder()
                    .id(PaymentId.builder()
                            .paymentId(2L)
                            .pvCode("1").build())
                    .bankCode(111)
                    .accountNumber(11111)
                    .agency(1)
                    .brandCode(1)
                    .amount(new BigDecimal(5000.0))
                    .discount(new BigDecimal(100))
                    .netAmount(new BigDecimal(4900))
                    .type("CREDIT").build()
    );
    List<PaymentDTO> expectedDTO = Arrays.asList(
            PaymentDTO.builder()
                    .paymentId(1L)
                    .pvCode("1")
                    .bankCode(111)
                    .accountNumber(11111)
                    .agency(1)
                    .brandCode(1)
                    .amount((new BigDecimal(1000.0)).doubleValue())
                    .discount((new BigDecimal(100)).doubleValue())
                    .netAmount((new BigDecimal(900)).doubleValue())
                    .type("CREDIT")
                    .negotiations(new ArrayList<>()).build(),
            PaymentDTO.builder()
                    .paymentId(2L)
                    .pvCode("1")
                    .bankCode(111)
                    .accountNumber(11111)
                    .agency(1)
                    .brandCode(1)
                    .amount((new BigDecimal(5000.0)).doubleValue())
                    .discount((new BigDecimal(100)).doubleValue())
                    .netAmount((new BigDecimal(4900)).doubleValue())
                    .type("CREDIT")
                    .negotiations(new ArrayList<>()).build()
    );

    @Autowired
    private PaymentService paymentService;
    @MockBean
    private PaymentRepository paymentRepository;

    @Test
    void findByPvCode() {

        when(paymentRepository.findByPvCode(any())).thenReturn(expectedModel);
        List<PaymentDTO> result = paymentService.findByPvCode("1");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(paymentRepository, times(1)).findByPvCode(any());
        assertEquals(result.get(0).getPaymentId(), expectedDTO.get(0).getPaymentId());

    }

    @Test
    void findByPvCodeAndType() {

        when(paymentRepository.findByPvCodeAndType(any(), any())).thenReturn(expectedModel);
        List<PaymentDTO> result = paymentService.findByPvCodeAndType("1", "CREDIT");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(paymentRepository, times(1)).findByPvCodeAndType(any(), any());
        assertEquals(result.get(0).getPaymentId(), expectedDTO.get(0).getPaymentId());

    }

    @Test
    void findByPaymentId() {
    }

    @Test
    void findByPaymentIdAndPvCode() {
    }

    @Test
    void findAll() {
    }

    @TestConfiguration
    static class PaymentServiceConfiguration {
        @Bean
        public PaymentService paymentService() {
            return new PaymentService();
        }
    }

}