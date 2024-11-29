package com.sdewa.account.controller;


import com.sdewa.account.constant.AccountConstant;
import com.sdewa.account.dtos.CustomerDto;
import com.sdewa.account.dtos.ErrorResponseDto;
import com.sdewa.account.dtos.ResponseDto;
import com.sdewa.account.services.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
    name ="Crud Rest api for account bank",
    description = "Crud Rest api in a bank to create, update, fetch and delete account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService  accountService;

    @GetMapping("/")
    public  String sayHallo(){return  "Hello World";}

    @Operation(
            summary = "Create Account Rest API",
            description = "Rest API to create new Customer and Account inside bank"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Https status CREATED"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP status Internet Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
       accountService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        AccountConstant.STATUS_201,
                        AccountConstant.MESSAGE_201
                ));
    }

    @Operation(
        summary = "Fetch Account Details Rest api",
        description = "Rest Api to fetch customer and account details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Http status ok"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto>fetchAccountDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNum
    ){
        var customerDto = accountService.fetchAccount(mobileNum);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
        summary = "Update Account Details Rest API",
        description = "Rest API to update Customer and account details based on account number"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Http status ok"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Exception Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP status internal server error",
            content = @Content(
                    schema = @Schema(implementation =  ErrorResponseDto.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetail(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdate = accountService.updateAccount(customerDto);
        if(isUpdate){
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstant.STATUS_200,
                            AccountConstant.MESSAGE_200
                    ));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(
                        AccountConstant.STATUS_500,
                        AccountConstant.MESSAGE_500
                ));
    }

    @Operation(
        summary = "Delete Account Rest API",
        description = "Rest api to delete account"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Http status ok"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Exception Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP status internal server error"
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        boolean isDelete = accountService.deleteAccount(mobileNumber);
        if(isDelete){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstant.STATUS_200,
                            AccountConstant.MESSAGE_200
                    ));

        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(
                        AccountConstant.STATUS_500,
                        AccountConstant.MESSAGE_500
                ));
    }
}
