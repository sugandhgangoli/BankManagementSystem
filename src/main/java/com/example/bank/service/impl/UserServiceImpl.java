package com.example.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAddress;
import com.example.bank.exception.APIException;
import com.example.bank.exception.ResourceNotFoundException;
import com.example.bank.repository.UserRepository;
import com.example.bank.service.UserAccountService;
import com.example.bank.service.UserAddressService;
import com.example.bank.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserAddressService userAddressService;

	@Override
	public List<User> getAllUser() {
		List<User> user = this.userRepository.findAll();
		return user;
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found" + userId));
	}

	@Override
	public User registerUser(User user) throws APIException {		
		try
		{
			UserAddress address = user.getUserAddress();
			user.addUserAddress(address);
			User createdUser =  this.userRepository.save(user);
			
			this.userAccountService.createAccount(createdUser);
			return createdUser;
		}
		catch(Exception ex)
		{
			throw new APIException(ex.getMessage());
		}
		  
	}

	@Override
	public User updateUserDetails(User user, int userId) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found" + userId));
		UserAddress address = user.getUserAddress();
		existingUser.setLastName(user.getLastName());
		existingUser.setEmailId(user.getEmailId());
		address.setAddressLine1(address.getAddressLine1());
		address.setAddressLine2(address.getAddressLine2());
		address.setCity(address.getCity());
		address.setState(address.getState());
		existingUser.addUserAddress(address);
		return this.userRepository.save(existingUser);

	}

	@Override
	public ResponseEntity<User> deleteUser(int userId) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found" + userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
