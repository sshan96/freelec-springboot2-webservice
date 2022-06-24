package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.admin.AdminService;
import com.jojoldu.book.springboot.web.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String hello(Model model) {
        model.addAttribute("users", adminService.findAllDesc());

        return "admin";
    }

    @GetMapping("/users/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        UsersResponseDto dto = adminService.findById(id);
        model.addAttribute("user", dto);

        return "users-update";
    }
}
