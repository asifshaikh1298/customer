package com.customer.customer_service.respository;

import com.customer.customer_service.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {


}
