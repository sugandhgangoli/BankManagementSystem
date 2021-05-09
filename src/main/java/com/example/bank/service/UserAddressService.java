package com.example.bank.service;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAddress;

public interface UserAddressService {

	UserAddress createAddress(UserAddress address, User user);

	

}
