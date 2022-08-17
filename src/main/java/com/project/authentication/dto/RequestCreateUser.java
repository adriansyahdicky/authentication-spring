package com.project.authentication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestCreateUser {
    private String name;
    private String username;
    private String password;
}
