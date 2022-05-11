package org.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Status role;

}
