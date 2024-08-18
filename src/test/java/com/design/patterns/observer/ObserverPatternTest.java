package com.design.patterns.observer;

import com.design.patterns.observer.domain.OrderDetails;
import com.design.patterns.observer.entity.OrderEntity;
import com.design.patterns.observer.entity.OrderStatus;
import com.design.patterns.observer.entity.PaymentStatus;
import com.design.patterns.observer.entity.repository.OrderEventsRepository;
import com.design.patterns.observer.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static com.design.patterns.observer.entity.OrderStatus.*;
import static com.design.patterns.observer.entity.PaymentStatus.FAILED;
import static com.design.patterns.observer.entity.PaymentStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ObserverPatternTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderEventsRepository orderEventsRepository;

    private static Stream<Arguments> provideInputForOrderDetails() {
        return Stream.of(
                Arguments.of("LED TV", 250, ORDER_PENDING, SUCCESS),
                Arguments.of("Smart Phone", 300, ORDER_PENDING, FAILED),
                Arguments.of("Washing Machine", 350, ORDER_FAILED, FAILED),
                Arguments.of("Laptop", 1000, ORDER_PLACED, SUCCESS)
        );
    }

    @BeforeEach
    void setup() {
        cleanUp();
    }

    @Transactional
    void cleanUp() {
        orderEventsRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideInputForOrderDetails")
    void should_save_order_details(String itemName, long amount, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        // Arrange
        OrderDetails orderDetails = OrderDetails.builder()
                .itemName(itemName)
                .amount(BigDecimal.valueOf(amount))
                .build();

        // Act
        orderService.placeOrder(orderDetails);

        // Assert
        List<OrderEntity> orderEntities = orderEventsRepository.findAll();
        orderEntities.forEach(orderEntity -> {
            assertEquals(itemName, orderEntity.getItemName());
            assertEquals(orderStatus, orderEntity.getOrderStatus());
            assertEquals(paymentStatus, orderEntity.getPaymentEntity().getPaymentStatus());
        });
    }
}
