package BrightFund.com.Models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jakarta.websocket.OnMessage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Studentlogin {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Confirm email cannot be empty")
    private String confirmemail;

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp="^[0-9]{10}$", message="Mobile number must be 10 digits")
    private String mobile;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmpassword;


}
