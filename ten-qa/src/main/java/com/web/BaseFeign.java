package com.web;

import entity.Result;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(url = "http:localhost:9001/ten-base",name = "ten-base")
public interface BaseFeign {

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
     Result findAll();

}
