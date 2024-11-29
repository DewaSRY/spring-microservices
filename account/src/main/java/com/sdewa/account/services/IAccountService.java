package com.sdewa.account.services;

import com.sdewa.account.dtos.CustomerDto;

public interface IAccountService {

    void  createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNum);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}
