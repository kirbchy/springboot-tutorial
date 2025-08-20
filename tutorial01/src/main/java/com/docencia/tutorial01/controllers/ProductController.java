package com.docencia.tutorial01.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class ProductController {

    private static final List<Map<String, Object>> products = new ArrayList<>(List.of(
        Map.of("id", "1", "name", "TV", "description", "Best TV", "price", 1900),
        Map.of("id", "2", "name", "iPhone", "description", "Best iPhone", "price", 1200),
        Map.of("id", "3", "name", "Chromecast", "description", "Best Chromecast", "price", 50),
        Map.of("id", "4", "name", "Glasses", "description", "Best Glasses", "price", 100)
    ));
    
    @GetMapping("/products")
    public String index(Model model) {
        model.addAttribute("title", "Products - Online Store");
        model.addAttribute("subtitle", "List of products");
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/products/{id}")
    public String show(@PathVariable String id, Model model) {
        int productId = Integer.parseInt(id) - 1;

        if (productId < 0 || productId >= products.size()) {
            return "redirect:/products";
        }

        Map<String, Object> product = products.get(productId);
        model.addAttribute("title", product.get("name") + " - Online Store");
        model.addAttribute("subtitle", product.get("name") + " - Product Information");
        model.addAttribute("product", product);
        return "product/show";
    }

    @GetMapping("/products/create")
    public String create(Model model) {
        model.addAttribute("title", "Create Product");
        model.addAttribute("subtitle", "Create a new product");
        model.addAttribute("productForm", new ProductForm());
        return "product/create";
    }

    @PostMapping("/products/save")
    public String save(@Valid @ModelAttribute("productForm") ProductForm productForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Create Product");
            return "product/create";
        }

        // Simulación de guardar el producto en la lista (sin persistencia en DB)
        Map<String, Object> newProduct = new HashMap<>();
        newProduct.put("id", String.valueOf(products.size() + 1));
        newProduct.put("name", productForm.getName());
        newProduct.put("description", "Best " + productForm.getName());
        newProduct.put("price", productForm.getPrice());
        products.add(newProduct);

        model.addAttribute("title", "Product Created");
        model.addAttribute("subtitle", "Confirmation");

        return "redirect:/products";
    }

}
