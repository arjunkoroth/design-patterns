package com.design.patterns;

import com.design.patterns.factory.CustomerServiceFactory;
import com.design.patterns.factory.dto.CustomerDTO;
import com.design.patterns.factory.enums.CustomerType;
import com.design.patterns.factory.service.CustomerService;
import com.design.patterns.observer.domain.OrderDetails;
import com.design.patterns.observer.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class DesignPatternsApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerServiceFactory customerServiceFactory,
											   OrderService orderService) {
		return runner ->{
			//testCreationalPattern(customerServiceFactory);
			testObserverPattern(orderService);
		};
	}

	private void testCreationalPattern(CustomerServiceFactory customerServiceFactory) {
		CustomerDTO regularCustomer = new CustomerDTO("Alice");
		CustomerDTO premiumCustomer = new CustomerDTO("Bob");
		CustomerService regularCustomerService = customerServiceFactory.getCustomerService(CustomerType.REGULAR);
		CustomerService premiumCustomerService = customerServiceFactory.getCustomerService(CustomerType.PREMIUM);
		UUID regularCustomerServiceId = regularCustomerService.saveCustomer(regularCustomer);
		UUID premiumCustomerServiceId = premiumCustomerService.saveCustomer(premiumCustomer);
		log.info("Saved Customers {}, {}", regularCustomerServiceId, premiumCustomerServiceId);
	}

	private void testObserverPattern(OrderService orderService) {
		List<OrderDetails> orderDetailsList = getOrderDetailsFromFile();
		for (OrderDetails orderDetails: orderDetailsList)
			orderService.placeOrder(orderDetails);
	}

	private List<OrderDetails> getOrderDetailsFromFile() {
		try {
			File file = ResourceUtils.getFile("classpath:OrderDetails.json");
			return objectMapper.readValue(file, new TypeReference<List<OrderDetails>>() {
			});
		} catch (Exception e) {
			log.error("Error occurred while reading order details file: {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
