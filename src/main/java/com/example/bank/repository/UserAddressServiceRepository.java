package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.entity.UserAddress;

public interface UserAddressServiceRepository extends JpaRepository<UserAddress, Integer>{

}
