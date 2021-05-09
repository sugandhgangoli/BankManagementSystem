package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer>{

}
