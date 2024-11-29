package com.sdewa.account.services.impl;

import com.sdewa.account.constant.AccountConstant;
import com.sdewa.account.dtos.AccountDto;
import com.sdewa.account.dtos.CustomerDto;
import com.sdewa.account.entity.Account;
import com.sdewa.account.entity.Customer;
import com.sdewa.account.exception.CustomerAlreadyExistsException;
import com.sdewa.account.exception.ResourceNotFoundException;
import com.sdewa.account.mapper.AccountMapper;
import com.sdewa.account.mapper.CustomerMapper;
import com.sdewa.account.repository.AccountRepository;
import com.sdewa.account.repository.CustomerRepository;
import com.sdewa.account.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServicesImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) throws CustomerAlreadyExistsException {
        var customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        var optCustomer= customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobileNumber : "
                    + customerDto.getMobileNumber()
            );
        }
        var savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNum) {
        var customer = customerRepository.findByMobileNumber(mobileNum)
                .orElseThrow(()->new ResourceNotFoundException("Customer","mobileNum", mobileNum));
        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account","customer id","%d".formatted(customer.getCustomerId())));
        var customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mappedToAccountDto(account, new AccountDto()));

        return  customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate= false;
        var accountDto = customerDto.getAccountDto();
        if(accountDto != null){
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(()-> new ResourceNotFoundException(
                            "Account",
                            "AccountNumber",
                            String.valueOf(accountDto.getAccountNumber())
                    ));
            AccountMapper.mapToAccounts(accountDto, account);
            account = accountRepository.save(account);

            long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(()-> new ResourceNotFoundException(
                            "Customer",
                            "CustomerID",
                            String.valueOf(customerId)
                    ));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdate = true;

        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Customer",
                        "mobileNumber",
                        mobileNumber
                ));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Account createNewAccount(Customer customer){
        var newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randAccNumber = 1000_000+ + new Random().nextInt(9000_000);

        newAccount.setAccountNumber(randAccNumber);
        newAccount.setAccountType(AccountConstant.SAVING);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return  newAccount;

    }


}
