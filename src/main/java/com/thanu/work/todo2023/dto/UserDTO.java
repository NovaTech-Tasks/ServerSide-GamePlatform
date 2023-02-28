package com.thanu.work.todo2023.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    @Null(message = "Id is automatically generated")
    private String id;

    @NotNull(message = "User name cannot be empty")
    @Pattern(regexp = "[A-Za-z ]+")
    private String userName;

    @NotNull(message = "Email cannot be an empty value")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be an empty value")
    @Length(min = 5)
    private String password;

}
