package com.controller;

import base.PageResult;
import com.entity.request.ProblemPageRequest;
import com.service.ProblemService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("findByLableId")
    public Result findByLableId(String labelId) {
        return Result.SUCCESS(problemService.findByLableId(labelId));
    }

    @PostMapping("findHotProblemByLableId")
    public PageResult findHotProblemByLableId(@RequestBody ProblemPageRequest problemPageRequest) {
        return problemService.findHotProblem(problemPageRequest);
    }
}
