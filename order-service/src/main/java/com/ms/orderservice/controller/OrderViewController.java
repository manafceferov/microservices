package com.ms.orderservice.controller;

import com.ms.orderservice.dto.OrderCreateRequest;
import com.ms.orderservice.dto.OrderUpdateRequest;
import com.ms.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    private final OrderService orderService;

    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders/list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        return "orders/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("orderCreateRequest", new OrderCreateRequest());
        return "orders/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute OrderCreateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "orders/create";
        orderService.create(request);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderUpdateRequest", orderService.getById(id));
        return "orders/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute OrderUpdateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "orders/edit";
        orderService.update(request);
        return "redirect:/orders";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/status")
    public String changeStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.changeStatus(id, status);
        return "redirect:/orders";
    }

    @GetMapping("/user/{userId}")
    public String getByUserId(@PathVariable Long userId, Model model) {
        model.addAttribute("orders", orderService.getByUserId(userId));
        return "orders/list";
    }
}