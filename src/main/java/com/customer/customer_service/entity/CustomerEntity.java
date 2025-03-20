package com.customer.customer_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMER_DETAILS")
@Data
public class CustomerEntity {

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    private String customerId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String  firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String  lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;

}
