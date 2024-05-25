package com.project.plan.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TestController {
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(Model model) {
        return "test success";
    }
}
