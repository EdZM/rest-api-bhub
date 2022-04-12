package com.bhub.repository;

import com.bhub.domain.BankData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankDataRepository extends JpaRepository<BankData, Long> {

    @Query(value = "SELECT bank_data.* FROM bank_data WHERE bank_data.user_id = ?1", nativeQuery = true)
    List<BankData> findAllByUserId(Long userId);

    @Query(value = "SELECT bank_data.* FROM bank_data WHERE bank_data.account_number = ?1 AND bank_data.bank_branch_number = ?2", nativeQuery = true)
    Optional<BankData> findByAccountAndBranch(String accountNumber, String bankBranchNumber);


}
