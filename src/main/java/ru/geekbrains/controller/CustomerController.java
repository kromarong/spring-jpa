package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.CustomerRepository;
import ru.geekbrains.persistence.ProductRepository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Customer;
import ru.geekbrains.persistence.entity.Product;

@Controller
@RequestMapping("customers")
public class CustomerController {

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public String buyProductFrom(@RequestParam("categoryId") Long productId, Long customerId, Model model) {
        Product product = productRepository.findById(productId);
        Customer customer = customerRepository.findById(customerId);
        customer.addProduct(product);
        model.addAttribute("customer", customer);
        return "customer";
    }


}
