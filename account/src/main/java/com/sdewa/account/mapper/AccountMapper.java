package com.sdewa.account.mapper;

import com.sdewa.account.dtos.AccountDto;
import com.sdewa.account.entity.Account;

public class AccountMapper {

    public static AccountDto mappedToAccountDto(Account account, AccountDto accountDto){
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return  accountDto;
    }

    public  static  Account mapToAccounts(AccountDto accountDto, Account account){
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return  account;
    }
}
