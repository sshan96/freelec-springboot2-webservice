package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private String email;
    private String name;
    private Role role;

    @Builder
    public UsersUpdateRequestDto(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
