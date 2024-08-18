package com.design.patterns.factory;

import com.design.patterns.factory.dto.CustomerDTO;
import com.design.patterns.factory.enums.CustomerType;
import com.design.patterns.factory.enums.PaymentStatus;
import com.design.patterns.factory.impl.CredPay;
import com.design.patterns.factory.impl.GooglePay;
import com.design.patterns.factory.impl.PayTm;
import com.design.patterns.factory.service.CustomerService;
import com.design.patterns.factory.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static com.design.patterns.factory.enums.PaymentStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class FactoryPatternTest {

	@Autowired
	PaymentServiceFactory paymentServiceFactory;

	@Autowired
	CustomerServiceFactory customerServiceFactory;

	@Test
	@DisplayName("Should Test Payment Service Factory")
	void test_payment_service_factory() {
		// Arrange
		PaymentService credPayService = paymentServiceFactory.getPaymentService(CredPay.BEAN_ID);
		PaymentService paytmService = paymentServiceFactory.getPaymentService(PayTm.BEAN_ID);
		PaymentService gPayService = paymentServiceFactory.getPaymentService(GooglePay.BEAN_ID);

		// Act
		PaymentStatus credPayStatus = credPayService.doPayment(BigDecimal.valueOf(500));
		PaymentStatus paytmStatus = paytmService.doPayment(BigDecimal.valueOf(1000));
		PaymentStatus gPayStatus = gPayService.doPayment(BigDecimal.valueOf(100));

		// Assert
		assertEquals(SUCCESS, credPayStatus);
		assertEquals(SUCCESS, paytmStatus);
		assertEquals(SUCCESS, gPayStatus);
	}

	@Test
	@DisplayName("Should save customer details to db")
	void test_customer_service_factory() {
		// Arrange
		CustomerDTO regularCustomer = new CustomerDTO("Arjun", "Test");
		CustomerDTO premiumCustomer = new CustomerDTO("Charlie", "Test");
		CustomerService regularCustomerService = customerServiceFactory.getCustomerService(CustomerType.REGULAR);
		CustomerService premiumCustomerService = customerServiceFactory.getCustomerService(CustomerType.PREMIUM);

		// Act
		UUID regularCustomerServiceId = regularCustomerService.saveCustomer(regularCustomer);
		UUID premiumCustomerServiceId = premiumCustomerService.saveCustomer(premiumCustomer);

		// Assert
		assertNotNull(regularCustomerServiceId);
		assertNotNull(premiumCustomerServiceId);
	}
}
