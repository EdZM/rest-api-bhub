package com.bhub.service;

import com.bhub.domain.BankData;
import com.bhub.domain.User;
import com.bhub.dto.BankDataDto;
import com.bhub.dto.UserDto;
import com.bhub.repository.BankDataRepository;
import com.bhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
                bankDataEnities.add(newBankData);
            });
            bankDataRepository.saveAll(bankDataEnities);
            createdUser.setBankData(bankDataEnities);
            userRepository.save(createdUser);

        }

        return createdUser;
    }

    public BankData createUserBankData(Long userId, BankDataDto bankDataDto) throws Exception {
        Optional<BankData> userBankData = bankDataRepository.findByAccountAndBranch(bankDataDto.getAccountNumber(), bankDataDto.getBankBranchNumber());
        if(userBankData.isPresent()){
            throw new Exception("Dados bancários já existem!");
        } else {
            Optional<User> user = userRepository.findById(userId);
            if(user.isEmpty()){
                throw new Exception("Usuário não encontrado!");
            }

            if(bankDataDto.getBankBranchNumber() == null || bankDataDto.getAccountNumber() == null){
                throw new Exception("Os números de conta e agência não podem ser nulos!");
            }

            BankData newBankData = bankDataDto.toBankDataEntity();
            newBankData.setUser(user.get());
            userRepository.save(user.get());
            bankDataRepository.save(newBankData);
            return newBankData;
        }
    }

    public User updateUser(Long userId, UserDto userDto) throws Exception {
        Optional<User> userToBeUpdated = findById(userId);
        if(userToBeUpdated.isEmpty()){
            throw new Exception("Usuário não encontrado!");
        }
        User updatedUserInfo = userDto.toUserEntity();
        userToBeUpdated.get().setCorporateName(updatedUserInfo.getCorporateName());
        userToBeUpdated.get().setPhone(updatedUserInfo.getPhone());
        userToBeUpdated.get().setAddress(updatedUserInfo.getAddress());
        userToBeUpdated.get().setDeclaredBilling(updatedUserInfo.getDeclaredBilling());
        return userRepository.save(userToBeUpdated.get());
    }

    public BankData updateUserBankData(Long bankDataId, BankDataDto bankDataDto) throws Exception {
        Optional<BankData> bankData = bankDataRepository.findById(bankDataId);
        if(bankData.isEmpty()){
            throw new Exception("Dados bancários inexistentes!");
        }

        if(bankDataDto.getBankBranchNumber() == null || bankDataDto.getAccountNumber() == null){
            throw new Exception("Os novos números de conta e agência não podem ser nulos!");
        }

        bankData.get().setBankBranchNumber(bankDataDto.getBankBranchNumber());
        bankData.get().setAccountNumber(bankDataDto.getAccountNumber());
        bankData.get().setBankName(bankDataDto.getBankName());
        return bankDataRepository.save(bankData.get());

    }


    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void deleteUserBankData(BankData bankData){
        bankDataRepository.delete(bankData);
    }


}
