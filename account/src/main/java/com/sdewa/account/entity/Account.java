package com.sdewa.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @Column(name = "account_number")
    private  long accountNumber;

    @Column(name = "account_type")
    private  String accountType;

    @Column(name = "mobile_number")
    private String branchAddress;

}
