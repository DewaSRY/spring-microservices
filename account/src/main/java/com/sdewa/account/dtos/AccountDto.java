package com.sdewa.account.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(
    name = "Account",
    description = "Schema to hold Account information"
)
@Getter
@Setter
public class AccountDto {

    @Schema(description = "Account Number of bank account", example = "3454433243")
    @NotEmpty(message = "Account Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private  long accountNumber;

    @Schema(description = "Account type of bank account",example = "Saving")
    @NotEmpty(message = "AccountType can not be a null or empty")
    private String accountType;

    @Schema(description = "Bank branch address", example = "123 New york")
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;

}
