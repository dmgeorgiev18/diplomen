package com.project.motohotel.binding;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEdit {
    private String id;
    @NotEmpty(message = "Трябва да бъде валидно")
    private String username;
    @NotEmpty(message = "Трябва да бъде валидно")
    private String password;
    @NotEmpty(message = "Трябва да бъде валидно")
    private String adminPassword;
}
