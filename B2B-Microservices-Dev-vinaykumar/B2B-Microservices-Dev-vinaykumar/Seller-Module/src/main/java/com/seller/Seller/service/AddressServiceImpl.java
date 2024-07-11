package com.seller.Seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.seller.Seller.model.Address;
import com.seller.Seller.repository.AddressRepository;


@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressrepo;


	@Override
	public Address addAddress(Address address) {
		return addressrepo.save(address);
	}

}
