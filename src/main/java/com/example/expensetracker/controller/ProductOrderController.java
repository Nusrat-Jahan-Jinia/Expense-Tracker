package com.example.expensetracker.controller;

import com.example.expensetracker.entity.*;
import com.example.expensetracker.repository.ProductOrderRepository;
import com.example.expensetracker.service.CustomerService;
import com.example.expensetracker.service.ProductOrderService;
import com.example.expensetracker.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/orders")
public class ProductOrderController {

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
    public String addOrder(@Valid @ModelAttribute("order") ProductOrder productOrder, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Customer> customers = customerService.getCustomers();
            List<Product> products = productService.getProducts();
            model.addAttribute("customers", customers);
            model.addAttribute("products", products);
            return "order/create";
        }
        productOrderRepository.save(productOrder);
        return "redirect:/orders";
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

    @PostMapping("/edit/{id}")
    public String updateCategory(@Valid @ModelAttribute("order") ProductOrder productOrder, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Customer> customers = customerService.getCustomers();
            List<Product> products = productService.getProducts();
            model.addAttribute("customers", customers);
            model.addAttribute("products", products);
            return "order/edit";
        }
        productOrderRepository.save(productOrder);
        return "redirect:/orders";
    }

}

