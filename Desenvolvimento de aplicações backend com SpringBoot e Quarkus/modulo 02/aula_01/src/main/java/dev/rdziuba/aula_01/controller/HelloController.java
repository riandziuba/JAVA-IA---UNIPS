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
}
