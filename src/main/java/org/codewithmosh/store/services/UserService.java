package org.codewithmosh.store.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.codewithmosh.store.entities.*;
import org.codewithmosh.store.repositories.AddressRepository;
import org.codewithmosh.store.repositories.CategoryRepository;
import org.codewithmosh.store.repositories.ProductRepository;
import org.codewithmosh.store.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("Mosh")
                .email("mosh@example.com")
                .password("123456")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    @Transactional
    public void persistRelated() {
        var user = User.builder()
                .name("Mosh")
                .email("mosh@example.com")
                .password("123456")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("New York")
                .state("NY")
                .zip("10001")
                .build();

        var profile = Profile.builder()
                .bio("I am a software engineer")
                .phoneNumber("123-456-7890")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();


        user.addAddress(address);
        //user.setProfile(profile);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(2L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }


    @Transactional
    public void manageProducts() {
        productRepository.deleteById(3L);
    }


}
