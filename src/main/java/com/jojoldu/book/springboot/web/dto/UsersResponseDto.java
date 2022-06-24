package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class UsersResponseDto {

    private Long id;
    private String email;
    private String name;
    private Role role;

    public UsersResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.role = entity.getRole();
    }
}
