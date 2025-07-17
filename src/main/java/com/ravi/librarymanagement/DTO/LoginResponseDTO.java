package com.ravi.librarymanagement.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder

public class LoginResponseDTO {
    private String token; //Others are optional, This is Important thing (-_-)
    private String username;
    private Set<String> role;
}
