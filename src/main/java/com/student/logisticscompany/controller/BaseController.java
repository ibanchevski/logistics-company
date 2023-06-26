package com.student.logisticscompany.controller;

import com.student.logisticscompany.entity.RoleEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class BaseController {
    private final UserService userService;

    // Index page always redirects to home page
    @GetMapping
    public String getIndexPage(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    /**
     * Register a new user
     * NOTE: This is unauthorised route and always registers CLIENT user
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity user) {
        RoleEntity role = new RoleEntity();
        role.setAuthority("USER");
        user.setAuthorities(Set.of(role));

        this.userService.registerNewUser(user);

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String getHome(Model model, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", user.getName());
        return "home";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(Model mode) {
        return "unauthorized";
    }
}
