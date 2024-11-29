package com.sdewa.account.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(
    name = "Response",
    description = "Schema to handle successfully response information"
)
@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {

    @Schema(description = "Status code in the response", example = "200")
    private String statusCode;

    @Schema(description = "Status message in the response ", example = "Request processed successfully")
    private String statusMsg;
}
