package dev.rdziuba.aula_01.controller;

import dev.rdziuba.aula_01.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("products")
    public Product getProduct() {
        Product product = new Product();
        product.setId(12345);
        product.setName("Computer");
        product.setPrice(1500.0);

        return product;
    }

    @PostMapping("products")
    public String addNewProduct(@RequestBody Product product) {
        System.out.println("Product received:");
        System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());
        return "ok";
    }
}
