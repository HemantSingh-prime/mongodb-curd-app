package com.ps.cst.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ps.cst.customer.CustomerData;

@Repository
public interface CustomerRespository extends MongoRepository<CustomerData,String> {

	public Optional<CustomerData> findByCustId(String custId);
}
