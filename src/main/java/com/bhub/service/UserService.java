package com.bhub.service;

import com.bhub.repository.BankDataRepository;
import com.bhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankDataRepository bankDataRepository;




}
