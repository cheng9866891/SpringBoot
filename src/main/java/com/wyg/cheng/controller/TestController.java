package com.wyg.cheng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {

    @RequestMapping("/cheng")
    public String index(){
        return "马琪吃大屁！！！！！";
    }
}
