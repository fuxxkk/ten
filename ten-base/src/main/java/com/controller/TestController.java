package com.controller;

import com.entity.Label;
import com.service.TestService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("base")
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("hello")
    public Result hello(String arg) throws Exception {
        return Result.SUCCESS("hello:"+arg);
    }

    @GetMapping("findOne")
    public Label findOne(String id) {
        return testService.findOne(id);
    }
}
