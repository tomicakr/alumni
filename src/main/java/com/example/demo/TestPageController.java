package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestPageController {

    @RequestMapping("/turtle")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="Programmer") String name, Model model) {
        model.addAttribute("name", name);
        return "turtle";
    }

}