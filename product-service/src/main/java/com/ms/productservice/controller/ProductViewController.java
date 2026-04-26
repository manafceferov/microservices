package com.ms.productservice.controller;

import com.ms.productservice.dto.ProductCreateRequest;
import com.ms.productservice.dto.ProductUpdateRequest;
import com.ms.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// MVC
@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products/list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "products/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("productCreateRequest", new ProductCreateRequest());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute ProductCreateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "products/create";
        productService.create(request);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("productUpdateRequest", productService.getById(id));
        return "products/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute ProductUpdateRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "products/edit";
        productService.update(request);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/orders")
    public String getProductWithOrders(@PathVariable Long id, Model model) {
        model.addAttribute("result", productService.getProductWithOrders(id));
        return "products/product-orders";
    }
}