package com.controller;

import com.service.ArticleService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("examine")
    public Result examine(String id){
        return Result.SUCCESS(articleService.examine(id));
    }

    @GetMapping("updateThumbup")
    public Result updateThumbup(String id){
        return Result.SUCCESS(articleService.updateThumbup(id));
    }
}
