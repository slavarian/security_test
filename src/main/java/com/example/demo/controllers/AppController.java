package com.example.demo.controllers;

import com.example.demo.models.Application;
import com.example.demo.models.MyUser;
import com.example.demo.services.AppService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private AppService service;

    @GetMapping("/default_page")
    public String welcom(){
        return "Страница не авторизованного пользователя";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allAplications(){
        return service.allAplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationByID(@PathVariable int id){
        return service.applicationById(id);
    }


    @PostMapping("/register")
    public String addUser(@RequestBody MyUser user) {
        service.addUser(user);
        return "Пользоваткль создан";
    }

}

