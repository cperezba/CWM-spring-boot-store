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


        var tag = Tag.builder()
                .name("electronics")
                .build();

        user.addTag(tag);
        System.out.println(user);
    }
}
