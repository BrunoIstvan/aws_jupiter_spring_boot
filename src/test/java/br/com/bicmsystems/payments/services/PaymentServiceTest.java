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

    @TestConfiguration
    static class PaymentServiceConfiguration {
        @Bean
        public PaymentService paymentService() { return new PaymentService(); }
    }

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    List<PaymentModel> expectedModel = Arrays.asList(
            new PaymentModel(new PaymentId(1L, "1"),
                    1, 1, new BigDecimal(1000.0), 233, 3,
                    new BigDecimal(100), new BigDecimal(900), "CREDIT"),
            new PaymentModel(new PaymentId(2L, "1"),
                    1, 1, new BigDecimal(5000.0), 233, 3,
                    new BigDecimal(100), new BigDecimal(4900), "CREDIT")
    );

    List<PaymentDTO> expectedDTO = Arrays.asList(
            new PaymentDTO(1L, "1", 1, 1, new BigDecimal(1000.0),
                    233, 3, new BigDecimal(100), new BigDecimal(900),
                    "CREDIT", new ArrayList<>()),
            new PaymentDTO(2L, "1", 1, 1, new BigDecimal(5000.0),
                    233, 3, new BigDecimal(100), new BigDecimal(4900),
                    "CREDIT", new ArrayList<>())
    );

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

}