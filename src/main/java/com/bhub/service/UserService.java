package com.bhub.service;

import com.bhub.domain.BankData;
import com.bhub.domain.User;
import com.bhub.dto.BankDataDto;
import com.bhub.dto.UserDto;
import com.bhub.repository.BankDataRepository;
import com.bhub.repository.UserRepository;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankDataRepository bankDataRepository;

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long userId){
        return userRepository.findById(userId);
    }

    public User createUser(UserDto userDto){
        User createdUser = userRepository.saveAndFlush(userDto.toUserEntity());

        if(userDto.getBankData() != null){
            List<BankData> bankDataEnities = new ArrayList<>();
            userDto.getBankData().forEach(bankDataDto -> {
                BankData newBankData = bankDataDto.toBankDataEntity();
                newBankData.setUser(createdUser);
                bankDataEnities.add(newBankData);
            });
            createdUser.setBankData(bankDataEnities);
            userRepository.save(createdUser);
            bankDataRepository.saveAll(bankDataEnities);
        }

        return createdUser;
    }

}
