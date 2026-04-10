package org.codewithmosh.store;

import org.codewithmosh.store.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        //var user = new User(1L, "mosh", "mosh@codewithmosh.com", "mosh123");
        var user = User.builder()
            .id(1L)
            .name("mosh")
            .email("mosh@codewithmosh.com")
            .password("mosh123")
            .build();

        var profile = Profile.builder()
                .bio("I am a software developer")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("Anytown")
                .state("CA")
                .zip("12345")
                .build();


        //user.getAddresses().add(address);
        //address.setUser(user);
        user.addAddress(address);
        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);


        var category = Category.builder()
                .name("Electronics")
                .build();
        var product = Product.builder()
                .name("Laptop")
                .price((long) 999.99)
                .category(category)
                .build();

        category.getProducts().add(product);
        product.setCategory(category);

        System.out.println(product);
    }
}
