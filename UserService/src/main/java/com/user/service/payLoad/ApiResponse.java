package com.user.service.payLoad;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {

    private String massage;
    private boolean success;
    private HttpStatus status;

}
