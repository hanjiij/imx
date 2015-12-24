package com.mx.web.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mx.web.bean.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findById(String firstName);
    public List<User> findByUsername(String lastName);

}