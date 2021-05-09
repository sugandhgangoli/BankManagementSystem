package com.example.bank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.bank.entity.User;
import com.example.bank.exception.APIException;

public interface UserService {

	List<User> getAllUser();

	User getUserById(int userId);

	User registerUser(User user) throws APIException;

	User updateUserDetails(User user, int userId);

	ResponseEntity<User> deleteUser(int userId);


}
