package com.mang.example.security.forTest.getFormData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/testFormData")
public class TestGetMethodForm {

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "forTest/testFormData";
    }

    @ResponseBody
    @PostMapping("/addTestPost")
    public String addItemPost(@ModelAttribute Item item){

        StringBuilder tmp = new StringBuilder();
        tmp.append(item.getId()).append("\n");
        tmp.append(item.getItemName()).append("\n");
        tmp.append(item.getPrice()).append("\n");
        tmp.append(item.getQuantity()).append("\n");

        return tmp.toString();
    }


    @ResponseBody
    @GetMapping("/addTestGet")
    public String addItemGet(@ModelAttribute Item item){

        StringBuilder tmp = new StringBuilder();
        tmp.append(item.getId()).append("\n");
        tmp.append(item.getItemName()).append("\n");
        tmp.append(item.getPrice()).append("\n");
        tmp.append(item.getQuantity()).append("\n");

        return tmp.toString();
    }


}
