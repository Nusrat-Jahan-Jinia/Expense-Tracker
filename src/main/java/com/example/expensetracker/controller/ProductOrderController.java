package com.example.expensetracker.controller;

import com.example.expensetracker.entity.*;
import com.example.expensetracker.repository.ProductOrderRepository;
import com.example.expensetracker.service.CustomerService;
import com.example.expensetracker.service.ProductOrderService;
import com.example.expensetracker.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.List;

@Controller
@RequestMapping("/orders")
public class ProductOrderController implements WebMvcConfigurer {

    private final CustomerService customerService;
    private final ProductService productService;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderService productOrderService;

    public ProductOrderController(
            CustomerService customerService,
            ProductService productService,
            ProductOrderRepository productOrderRepository,
            ProductOrderService productOrderService
    ) {
        this.customerService = customerService;
        this.productService = productService;
        this.productOrderRepository = productOrderRepository;
        this.productOrderService = productOrderService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/order/create/results").setViewName("order/results");
    }
    @GetMapping(value = "")
    public String getAllOrders(Model model) {
        List<ProductOrder> productOrders = productOrderService.getOrders();
        model.addAttribute("productOrders", productOrders);
        return "order/list";
    }

    @GetMapping(value = "/create")
    public String showAddOrderForm(Model model) {
        List<Customer> customers = customerService.getCustomers();
        List<Product> products = productService.getProducts();
        model.addAttribute("order", new ProductOrder());
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        return "order/create";
    }

    @PostMapping(value = "/create")
    public String addOrder(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
        return "redirect:/order/create/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        productOrderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        List<Customer> customers = customerService.getCustomers();
        List<Product> products = productService.getProducts();
        ProductOrder productOrder = productOrderService.getOrderById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", productOrder);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        return "order/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("order") ProductOrder productOrder, Model model) {
        productOrderRepository.save(productOrder);
        return "redirect:/orders";
    }

}

