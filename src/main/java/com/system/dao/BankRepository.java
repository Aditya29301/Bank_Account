package com.system.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.entity.BankAccount;

@Repository 
public interface BankRepository extends CrudRepository<BankAccount, Integer> {

}
