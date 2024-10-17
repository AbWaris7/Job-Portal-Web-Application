package com.khan.programmer.Job.Portal.controller;

import com.khan.programmer.Job.Portal.entity.Users;
import com.khan.programmer.Job.Portal.entity.UsersType;
import com.khan.programmer.Job.Portal.services.UserService;
import com.khan.programmer.Job.Portal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {


    private final UsersTypeService usersTypeService;
    private final UserService userService;
    @Autowired
    public UsersController(UsersTypeService usersTypeService, UserService userService) {
        this.usersTypeService = usersTypeService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user",new Users());
        return "register";

    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users){

        userService.addNew(users);
        return "dashboard";
    }
}
