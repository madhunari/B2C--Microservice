package com.seller.Seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.Seller.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>  {

}
