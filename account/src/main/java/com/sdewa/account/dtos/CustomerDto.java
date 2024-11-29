package com.sdewa.account.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(description = "Name of the Customer",example = "somename")
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5,max = 30, message = "name should between 5 to 30 character")
    private String name;

    @Schema(description = "Email Address of the customer", example = "tutr@example.com")
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid format")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "9345432123")
    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Please mobile number should only numeric with 10 digits")
    private  String mobileNumber;

    @Schema(description = "Account detail fo the customer")
    private  AccountDto accountDto;
}
