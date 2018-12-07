package com.controller;

import com.service.RecruitService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recruid")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping("findByState")
    public Result findByState(String state) {
        return Result.SUCCESS(recruitService.findByState(state));
    }

    @GetMapping("findNewest")
    public Result findNewest() {
        return Result.SUCCESS(recruitService.findNewest());
    }
}
