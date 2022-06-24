package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.admin.AdminService;
import com.jojoldu.book.springboot.web.dto.UsersResponseDto;
import com.jojoldu.book.springboot.web.dto.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final AdminService adminService;


    @PutMapping("/api/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return  adminService.update(id, requestDto);
    }

    @GetMapping("api/v1/users/{id}")
    public UsersResponseDto findById(@PathVariable Long id) {
        return adminService.findById(id);
    }

/*    @DeleteMapping("/api/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        adminService.delete(id);
        return id;
    }*/
}

