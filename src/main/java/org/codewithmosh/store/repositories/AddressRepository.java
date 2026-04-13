package org.codewithmosh.store.repositories;

import org.codewithmosh.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}