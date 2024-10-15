package com.khan.programmer.Job.Portal.controller;

import com.khan.programmer.Job.Portal.entity.Users;
import com.khan.programmer.Job.Portal.entity.UsersType;
import com.khan.programmer.Job.Portal.services.UsersTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsersController {


    private final UsersTypeService usersTypeService;
    @Autowired
    public UsersController(UsersTypeService usersTypeService) {
        this.usersTypeService = usersTypeService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user",new Users());
        return "register";

    }
}
