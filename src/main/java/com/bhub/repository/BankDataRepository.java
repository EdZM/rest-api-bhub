package com.bhub.repository;

import com.bhub.domain.BankData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDataRepository extends JpaRepository<BankData, Long> {
}
