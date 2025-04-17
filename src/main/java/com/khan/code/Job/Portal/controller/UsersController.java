package com.khan.code.Job.Portal.controller;

import com.khan.code.Job.Portal.entity.Users;
import com.khan.code.Job.Portal.entity.UsersType;
import com.khan.code.Job.Portal.services.UserService;
import com.khan.code.Job.Portal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
    public String registration(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";

    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users user, Model model) {

        Optional<Users> usersOptional = userService.getUserByEmail(user.getEmail());
        if(usersOptional.isPresent()) {
            model.addAttribute("error", "Email already registered, try login or register with other email.");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }


        userService.addNew(user);
        System.out.println("User:: "+user);
        return "dashboard";
    }

}
