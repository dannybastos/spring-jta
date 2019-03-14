package com.example.spring.jta.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.jta.bank.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

}
