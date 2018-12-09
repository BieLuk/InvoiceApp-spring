package pl.edu.wat.wcy.invoice.payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 4)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;
}