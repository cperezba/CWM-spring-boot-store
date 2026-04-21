package org.codewithmosh.store;

import org.codewithmosh.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var service = context.getBean(UserService.class);

      //service.showProfilesWithLoyaltyPointsGreaterThan(2);

        //service.fetchProducts();

//        service.fetchProductsByCriteria();

        service.fetchProductsBySpecifications(null, null, null, "Literature");

//    service.fetchSortedProducts();

//    service.fetchPaginatedProducts(0, 10);
    }
}
