package com.bhub.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bank_data")
@Getter
@Setter
@NoArgsConstructor
public class BankData {

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_branch_number", nullable = false)
    private String bankBranchNumber;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public BankData(Long id,  String bankBranchNumber, String accountNumber, String bankName){
        this.id = id;
        this.bankBranchNumber = bankBranchNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

}
