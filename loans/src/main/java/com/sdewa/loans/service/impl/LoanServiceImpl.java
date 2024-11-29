package com.sdewa.loans.service.impl;

import com.sdewa.loans.constants.LoanConstants;
import com.sdewa.loans.dto.LoansDto;
import com.sdewa.loans.entity.Loans;
import com.sdewa.loans.exception.LoanAlreadyExistsException;
import com.sdewa.loans.exception.ResourceNotFoundException;
import com.sdewa.loans.mapper.LoansMapper;
import com.sdewa.loans.repository.LoansRepository;
import com.sdewa.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoansRepository loansRepository;
    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        loansRepository.findByMobileNumber(mobileNumber)
            .ifPresent(existingLoan -> {
                throw new LoanAlreadyExistsException(
                        String.format("A loan is already registered for mobile number: %s", mobileNumber)
                );
            });
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        return loansRepository.findByMobileNumber(mobileNumber)
                .map((loan)->LoansMapper.mapToLoansDto(loan, new LoansDto()))
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Loan", "mobileNumber", mobileNumber)
                );
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        return  loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .map((loans)-> {
                    LoansMapper.mapToLoans(loansDto, loans);
                    loansRepository.save(loans);
                    return  true;
                }).orElseThrow(()-> new ResourceNotFoundException(
                        "Loan", "LoanNumber", loansDto.getLoanNumber()
                ));
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        return loansRepository.findByMobileNumber(mobileNumber)
            .map((loans)-> {
                loansRepository.deleteById(loans.getLoanId());
                return  true;
            })
            .orElseThrow(()-> new ResourceNotFoundException(
                    "Loan", "mobileNumber", mobileNumber
            ));
    }

}
