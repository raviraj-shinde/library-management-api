package com.ravi.librarymanagement.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterRequestDTO {

    private String username;
    private String email;
    private String password;
}
