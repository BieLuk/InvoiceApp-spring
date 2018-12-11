package pl.edu.wat.wcy.invoice.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDTO {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}