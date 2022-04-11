package com.bhub.dto;

import com.bhub.domain.BankData;
import com.bhub.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {
    private Long userId;
    private String corporateName;           // razão social
    private String phone;                   // telefone
    private String address;                 // endereço
    private LocalDateTime createdAt;        // data de cadastro
    private LocalDateTime updatedAt;        // data da última atualização de cadastro
    private Double declaredBilling;         // faturamento declarado
    private List<BankDataDto> bankData;     // dados bancários

    public UserDto(Long userId, String corporateName, String phone, String address, LocalDateTime createdAt, LocalDateTime updatedAt, Double declaredBilling, List<BankData> bankData) {
        List<BankDataDto> bankDataDto = new ArrayList<>();
        if(bankData != null){
            bankData.forEach(it -> bankDataDto.add(BankDataDto.toDto(it)));
        }
        this.userId = userId;
        this.corporateName = corporateName;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.declaredBilling = declaredBilling;
        this.bankData = bankDataDto;
    }

    public User toUserEntity() {

        return new User(
                userId,
                corporateName,
                phone,
                address,
                createdAt,
                updatedAt,
                declaredBilling
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getCorporateName(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeclaredBilling(),
                user.getBankData()
        );
    }

}
