package org.codewithmosh.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {
    private PaymentService paymentService;

    @PostConstruct
    public void init() {
        System.out.println("OrderService PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("OrderService PreDestroy");
    }

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("OrderService Created");
    }

    public void placeOrder() {
       paymentService.processPayment(10);
    }
}
