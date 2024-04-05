package com.cogent.ecommrestapi.payload;

import com.cogent.ecommrestapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private boolean error;
    private String message;
    private String token;
}
