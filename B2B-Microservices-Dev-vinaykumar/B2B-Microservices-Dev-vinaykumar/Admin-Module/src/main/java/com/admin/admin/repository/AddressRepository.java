package com.admin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.admin.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
