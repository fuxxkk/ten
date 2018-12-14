package com.controller;

import com.entity.Label;
import com.entity.request.LabelPageRequest;
import com.service.LabelService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("label")
public class LableController {

    @Autowired
    private LabelService labelService;

    @PostMapping("findPage")
    public Result findPage(@RequestBody LabelPageRequest labelPageRequest) {
        return Result.SUCCESS(labelService.findPage(labelPageRequest));
    }

    @GetMapping("findAll")
    public Result findAll() {
        return Result.SUCCESS(labelService.findAll());
    }

}
