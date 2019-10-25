package com.miguel.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/v1/")
public class DemoController {

    @RequestMapping(value = "demo", method = GET)
    public String getResponse() {
        return "Hello World!";
    }
}
