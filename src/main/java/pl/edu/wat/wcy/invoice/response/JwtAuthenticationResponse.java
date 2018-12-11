package pl.edu.wat.wcy.invoice.response;

import lombok.Data;
import pl.edu.wat.wcy.invoice.dto.UserSimpleDTO;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserSimpleDTO userDTO;

    public JwtAuthenticationResponse(String accessToken, UserSimpleDTO userDTO) {
        this.accessToken = accessToken;
        this.userDTO = userDTO;
    }
}
