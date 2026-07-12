package dev.rdziuba.aula_01.controller;

import dev.rdziuba.aula_01.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    @Qualifier("Replace")
    private IMessageService messageService;

    @GetMapping("hello")
    public String sayHello() {
        return messageService.sayCustomMessage("Hello World");
    }
}
