package org.example.jee_lab.repositories;

import org.example.jee_lab.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
