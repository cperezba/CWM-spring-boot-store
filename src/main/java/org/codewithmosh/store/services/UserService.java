package org.codewithmosh.store.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.codewithmosh.store.entities.*;
import org.codewithmosh.store.repositories.*;
import org.codewithmosh.store.repositories.specifications.ProductSpec;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

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

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(new BigDecimal("10.00"), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("Product 1");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "price")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
       var products = productRepository.findProductsByCriteria(null, null, null, "Literature");
       products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice, String category) {
        Specification<Product> spec = Specification.where((root, query, cb) -> null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        if (category != null) {
            spec = spec.and(ProductSpec.hasCategory(category));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }

    public void showProfilesWithLoyaltyPointsGreaterThan(int loyaltyPoints) {
        var profiles = userRepository.findByLoyaltyPointsGreaterThanOrderByUserEmail(loyaltyPoints);
        profiles.forEach(u -> {
            System.out.println("Profile Id: " + u.getId());
            System.out.println("User Email: " + u.getEmail());
        });
    }
}
