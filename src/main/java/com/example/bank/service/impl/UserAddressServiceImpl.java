package com.example.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAddress;
import com.example.bank.repository.UserAddressRepository;
import com.example.bank.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private UserAddressRepository userAddressRepository;

	@Override
	public UserAddress createAddress(UserAddress address, User user) {
		address.setUser(user);
		return this.userAddressRepository.save(address);
	}

}
