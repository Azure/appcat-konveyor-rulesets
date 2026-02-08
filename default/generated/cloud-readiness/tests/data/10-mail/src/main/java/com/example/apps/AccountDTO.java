package com.example.apps;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDTO {

    @Size(min = 5, max = 50, message = "Username in range [5, 50] characters")
    @NotBlank(message = "Username can't be null")
    private String username;

    @Size(min = 5, max = 255, message = "Email address must be in range [5, 255] characters")
    @NotBlank(message = "Email address can't be null")
    @Email(message = "Email must be in format of xxx@aaa.bb")
    private String email;

    @Size(min = 5, max = 50, message = "password in range [5, 50] characters")
    @NotBlank(message = "Password can't be null")
    private String password;
}
