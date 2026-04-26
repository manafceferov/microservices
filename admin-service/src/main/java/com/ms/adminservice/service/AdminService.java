package com.ms.adminservice.service;

import com.ms.adminservice.client.OrderClient;
import com.ms.adminservice.client.ProductClient;
import com.ms.adminservice.client.UserClient;
import com.ms.adminservice.config.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserClient userClient;
    private final ProductClient productClient;
    private final OrderClient orderClient;

    public AdminService(UserClient userClient,
                        ProductClient productClient,
                        OrderClient orderClient) {
        this.userClient = userClient;
        this.productClient = productClient;
        this.orderClient = orderClient;
    }

    public Object getAllUsers() {
        return userClient.getAllUsers().getData();
    }

    public Object getUserById(Long id) {
        return userClient.getUserById(id).getData();
    }

    public Object changeUserRole(Long id, String role) {
        return userClient.changeRole(id, role).getData();
    }

    public void deleteUser(Long id) {
        userClient.deleteUser(id);
    }

    public Object getAllProducts() {
        return productClient.getAllProducts().getData();
    }

    public Object getProductById(Long id) {
        return productClient.getProductById(id).getData();
    }

    public void deleteProduct(Long id) {
        productClient.deleteProduct(id);
    }

    public Object getAllOrders() {
        return orderClient.getAllOrders().getData();
    }

    public Object changeOrderStatus(Long id, String status) {
        return orderClient.changeStatus(id, status).getData();
    }

    public void deleteOrder(Long id) {
        orderClient.deleteOrder(id);
    }
}