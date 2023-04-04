package com.project.motohotel.binding;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdd {

    @NotEmpty(message = "Трябва да бъде валидно")
    private String username;

    @NotEmpty(message = "Трябва да бъде валидно")
    private String password;

    private String role ;

}
