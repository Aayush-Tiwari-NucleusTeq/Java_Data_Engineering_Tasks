package com.user.add.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.add.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
