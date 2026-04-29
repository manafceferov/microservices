package com.ms.userservice.controller;

import com.ms.userservice.cart.dto.CartItem;
import com.ms.userservice.client.OrderClient;
import com.ms.userservice.client.ProductClient;
import com.ms.userservice.dto.login.LoginRequest;
import com.ms.userservice.dto.order.OrderCreateRequest;
import com.ms.userservice.dto.user.UserCreateRequest;
import com.ms.userservice.dto.user.UserResponse;
import com.ms.userservice.dto.user.UserUpdateRequest;
import com.ms.userservice.service.AttachmentService;
import com.ms.userservice.service.AuthService;
import com.ms.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserViewController {

    private final UserService userService;
    private final AuthService authService;
    private final AttachmentService attachmentService;
    private final ProductClient productClient;
    private final OrderClient orderClient;

    public UserViewController(UserService userService,
                              AuthService authService,
                              AttachmentService attachmentService,
                              ProductClient productClient, OrderClient orderClient) {
        this.userService = userService;
        this.authService = authService;
        this.attachmentService = attachmentService;
        this.productClient = productClient;
        this.orderClient = orderClient;
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";
        model.addAttribute("username", username);
        model.addAttribute("products", productClient.getAllProducts().getData());
        return "home/index";
    }

    // Login səhifəsi
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequest request,
                        BindingResult result,
                        HttpSession session,
                        Model model) {
        if (result.hasErrors()) return "auth/login";
        try {
            var response = authService.login(request);
            session.setAttribute("token", response.getToken());
            session.setAttribute("username", response.getUsername());
            session.setAttribute("userId", response.getUserId());
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

    // Register səhifəsi
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserCreateRequest request,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) return "auth/register";
        try {
            authService.register(request);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Long userId = (Long) session.getAttribute("userId");
        if (username == null) return "redirect:/login";
        UserResponse user = userService.getById(userId);
        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setId(user.getId());
        updateRequest.setUsername(user.getUsername());
        updateRequest.setEmail(user.getEmail());
        updateRequest.setFirstName(user.getFirstName());
        updateRequest.setLastName(user.getLastName());
        updateRequest.setPhoneNumber(user.getPhoneNumber());
        updateRequest.setBirthDate(user.getBirthDate());
        updateRequest.setGender(user.getGender());
        String avatarUrl = attachmentService.getFileUrl(userId, "USER");
        model.addAttribute("avatarUrl", avatarUrl);
        model.addAttribute("user", user);
        model.addAttribute("userUpdateRequest", updateRequest);
        return "profile/index";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute UserUpdateRequest request,
                                BindingResult result,
                                HttpSession session,
                                @RequestParam(required = false) MultipartFile file,
                                Model model) {
        if (result.hasErrors()) return "profile/index";
        Long userId = (Long) session.getAttribute("userId");
        userService.update(request);
        if (file != null && !file.isEmpty()) {
            attachmentService.upload(userId, "USER", file);
        }
        session.setAttribute("username", request.getUsername());
        return "redirect:/profile";
    }

    @PostMapping("/orders/create")
    public String createOrder(@RequestParam Long productId,
                              @RequestParam Long userId,
                              @RequestParam Integer quantity,
                              HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        OrderCreateRequest request = new OrderCreateRequest();
        request.setUserId(userId);
        request.setProductId(productId);
        request.setQuantity(quantity);
        orderClient.createOrder(request);
        return "redirect:/profile/orders";
    }

    @GetMapping("/profile/orders")
    public String myOrders(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        Object result = userService.getUserWithOrders(userId);
        java.util.Map<String, Object> map = (java.util.Map<String, Object>) result;
        model.addAttribute("orders", map.get("orders"));
        return "profile/orders";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Integer quantity,
                            HttpSession session) {
        var productResponse = productClient.getProductById(productId);
        java.util.Map<String, Object> product = (java.util.Map<String, Object>) productResponse.getData();

        java.util.List<CartItem> cart = (java.util.List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new java.util.ArrayList<>();

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem item = new CartItem();
            item.setProductId(productId);
            item.setProductName((String) product.get("name"));
            item.setPrice(((Number) product.get("price")).doubleValue());
            item.setQuantity(quantity);
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";
        java.util.List<CartItem> cart = (java.util.List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new java.util.ArrayList<>();
        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart/index";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        java.util.List<CartItem> cart = (java.util.List<CartItem>) session.getAttribute("cart");
        if (cart != null) cart.removeIf(item -> item.getProductId().equals(productId));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        java.util.List<CartItem> cart = (java.util.List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) return "redirect:/cart";

        for (CartItem item : cart) {
            OrderCreateRequest request = new OrderCreateRequest();
            request.setUserId(userId);
            request.setProductId(item.getProductId());
            request.setQuantity(item.getQuantity());
            orderClient.createOrder(request);
        }

        session.removeAttribute("cart");
        return "redirect:/profile/orders";
    }
}