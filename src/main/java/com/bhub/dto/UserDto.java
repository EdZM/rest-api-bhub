package com.bhub.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserDto {
    private Long userId;
    private String corporateName;           // razão social
    private String phone;                   // telefone
    private String address;                 // endereço
    private LocalDateTime createdAt;        // data de cadastro
    private Double declaredBilling;         // faturamento declarado
    private List<BankDataDto> bankDataDto;  // dados bancários


}
