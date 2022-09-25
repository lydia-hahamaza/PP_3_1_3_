package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping()
public class AdminController {
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin")
    public String pageForAdmin(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.listUser());
        model.addAttribute("userRole", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "newAdm";
    }

    @GetMapping("admin/new")
    public String pageCreateUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("userRole", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("admin/add")
    public String pageCreate(@ModelAttribute("user") User user, @RequestParam("userRole") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.passwordCoder(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/delete/{id}")
    public String pageDelete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PutMapping("admin/edit")
    public String pageEdit(User user, @RequestParam("userRole") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.passwordCoder(user);
        return "redirect:/admin";
    }
}