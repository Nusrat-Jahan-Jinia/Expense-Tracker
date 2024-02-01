package com.example.expensetracker.controller;

import ch.qos.logback.core.model.Model;
import com.example.expensetracker.entity.*;
import com.example.expensetracker.exception.CustomerOrderUpdateException;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductOrderRepository;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.ProductOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class ProductOrderController {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderService productOrderService;

    public ProductOrderController(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            ProductOrderRepository productOrderRepository,
            ProductOrderService productOrderService
    ) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;
        this.productOrderService = productOrderService;
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", productOrderRepository.findAll());
        System.out.println(productOrderRepository.findAll());
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        modelAndView.addObject("dto", new ProductOrder());
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("products", products);
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("order/create.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitForm(@RequestParam(name = "orderId", required = false) Integer orderId,
                             ProductOrder productOrder,
                             RedirectAttributes redirectAttributes) {

        // Update existing order
        if (orderId != null) {
            try {
                productOrderService.edit(productOrder, orderId);
                redirectAttributes.addFlashAttribute("successMessage", "Order updated successfully!");
            } catch (CustomerOrderUpdateException e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update order!");
            }
        } else {
            // Create new order
            productOrderRepository.save(productOrder);
            redirectAttributes.addFlashAttribute("successMessage", "Order created successfully!");
        }
//        productOrderRepository.save(productOrder);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        modelAndView.addObject("id", id);
        modelAndView.addObject("orderId", id);
        modelAndView.addObject("dto", productOrderService.getOrderById(id));

        modelAndView.addObject("customers", customers);
        modelAndView.addObject("products", products);
        modelAndView.addObject("method", "put");
        modelAndView.setViewName("order/edit.html");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteEntity(@PathVariable int id) {
        productOrderRepository.deleteById(id);
        return "redirect:/orders";
    }

    @PutMapping(value = "/{id}")
    public ModelAndView submitUpdate(@PathVariable("id") int customerOrderId, ProductOrder productOrder, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        productOrderService.edit(productOrder, customerOrderId);

        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

}
