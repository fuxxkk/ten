package com.web;

import entity.Result;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BaseFeign {

     @RequestLine("GET /findAll")
     Result findAll();

}
