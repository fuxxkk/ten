package com.controller;

import com.service.ProblemService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("findByLableId")
    public Result findByLableId(String labelId) {
        return Result.SUCCESS(problemService.findByLableId(labelId));
    }
}
