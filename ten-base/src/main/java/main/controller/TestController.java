package main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("base")
@RestController
public class TestController {

    @GetMapping("hello")
    public String hello(String arg) {
        return "hello:"+arg;
    }
}
