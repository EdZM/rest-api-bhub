package com.bhub.dto;

import com.bhub.domain.BankData;
import com.bhub.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDataDto {
    private Long bankDataId;
    private String bankBranchNumber;    // agência
    private String accountNumber;       // número da conta
    private String bankName;            // nome do banco

    public BankDataDto(Long bankDataId, String bankBranchNumber, String accountNumber, String bankName){
        this.bankDataId = bankDataId;
        this.bankBranchNumber = bankBranchNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public BankData toBankDataEntity() {
        return new BankData(
                bankDataId,
                bankBranchNumber,
                accountNumber,
                bankName
        );
    }

    public static BankDataDto toDto(BankData bankData) {
        return new BankDataDto(
                bankData.getId(),
                bankData.getBankName(),
                bankData.getBankBranchNumber(),
                bankData.getAccountNumber()
        );
    }


}
