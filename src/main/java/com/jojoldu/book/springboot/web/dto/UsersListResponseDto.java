package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsersListResponseDto {

    private Long id;
    private String email;
    private String name;
    private LocalDateTime modifiedDate;
    private Role role;

    public UsersListResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.modifiedDate = entity.getModifiedDate();
        this.role = entity.getRole();
    }
}