package org.codewithmosh.store.repositories;

import org.codewithmosh.store.dtos.ProductSummary;
import org.codewithmosh.store.dtos.UserSummary;
import org.codewithmosh.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    List<UserSummary> findByLoyaltyPointsGreaterThanOrderByUserEmail(@Param("loyaltyPoints") int loyaltyPoints);
}
