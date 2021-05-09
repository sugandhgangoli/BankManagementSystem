package com.example.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAddress;
import com.example.bank.repository.UserAddressServiceRepository;
import com.example.bank.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private UserAddressServiceRepository UserAddressServiceRepository;

	@Override
	public UserAddress createAddress(UserAddress address, User user) {
		address.setUser(user);
		return this.UserAddressServiceRepository.save(address);
	}

}
