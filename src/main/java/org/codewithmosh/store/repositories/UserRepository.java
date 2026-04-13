package org.codewithmosh.store.repositories;

import org.codewithmosh.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
