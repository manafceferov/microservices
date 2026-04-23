package com.ms.userservice.controller;

import com.ms.userservice.dto.UserCreateRequest;
import com.ms.userservice.dto.UserUpdateRequest;
import com.ms.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// MVC
@Controller
@RequestMapping("/users")
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest());
        return "users/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute UserCreateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "users/create";
        userService.create(request);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("userUpdateRequest", userService.getById(id));
        return "users/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute UserUpdateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "users/edit";
        userService.update(request);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/products")
    public String getUserWithProducts(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("products", userService.getUserWithProducts(id));
        return "users/products";
    }
}