package org.codewithmosh.store;

import org.codewithmosh.store.entities.*;
import org.codewithmosh.store.repositories.AddressRepository;
import org.codewithmosh.store.repositories.UserRepository;
import org.codewithmosh.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var service = context.getBean(UserService.class);

      service.manageProducts();

    }
}
