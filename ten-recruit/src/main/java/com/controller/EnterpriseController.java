package com.controller;

import com.entity.Enterprise;
import com.service.EnterpriseService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("recruit")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/search/ishot")
    public Result findByIsHot() {
        List<Enterprise> byIsHot = enterpriseService.findByIsHot();
        return Result.SUCCESS(byIsHot);
    }
}
