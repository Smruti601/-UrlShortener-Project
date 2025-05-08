package com.url.shortner_sb.Dtos;

import java.util.Set;
import lombok.Data;
@Data
public class RegisterRequests {
    private String username;
    private String email;
    private Set<String> role;
    private String password;
}
