package com.project.motohotel.controller;

import com.project.motohotel.binding.ProfileEdit;
import com.project.motohotel.binding.UserAdd;
import com.project.motohotel.entity.User;
import com.project.motohotel.repository.RoleService;
import com.project.motohotel.repository.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService service;
    private RoleService roleService;

    private PasswordEncoder encoder;
    @Autowired
    public AdminController(UserService service, RoleService roleService, PasswordEncoder encoder) {
        this.service = service;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("/users/addUser")
    public String createUser(Model model){
        if(!model.containsAttribute("user")){
            model.addAttribute("user", new UserAdd());
        }
        model.addAttribute("roles", roleService.findAllRoles());
        return "create-user";
    }

    @GetMapping("/users/delete/{id}")

    public String deleteUser(@PathVariable String id){
        service.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/addUser")
    public String postAddUser(@ModelAttribute @Valid UserAdd user, BindingResult result,
                                     RedirectAttributes attributes){
        if(service.findByUsername(user.getUsername()) !=null){
            ObjectError invalidUsername = new ObjectError("globalError", "Потребител с това име вече съществува");
            result.addError(invalidUsername);
        }
        if(result.hasErrors()){
            attributes.addFlashAttribute("user",user);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            return "redirect:/admin/users/addUser";
        }

        service.addUser(user);
        attributes.addFlashAttribute("success", true);
        return "redirect:/admin/users/addUser";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        if(!model.containsAttribute("user")){
            User user = service.findById(id);
            model.addAttribute("user", new ProfileEdit(user.getId(),user.getUsername(),"",""));
        }

        return "profile";
    }

    @PostMapping("/users/edit/{id}")
    public String edit(@PathVariable String id, @Valid @ModelAttribute ProfileEdit profileEdit, BindingResult result, RedirectAttributes attributes,
                       Principal principal){
        if(service.findByUsername(profileEdit.getUsername()) !=null){
            ObjectError invalidUsername = new ObjectError("globalError", "Потребител с това име вече съществува");
            result.addError(invalidUsername);
        }
        String adminPassword = service.findByUsername(principal.getName()).getPassword();
        if(!encoder.matches(profileEdit.getAdminPassword(),adminPassword)){
            ObjectError invalidAdminPassword = new ObjectError("globalError", "Администраторската парола е грешна!");
            result.addError(invalidAdminPassword);
        }
        if(result.hasErrors()){
            attributes.addFlashAttribute("user",profileEdit);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            return "redirect:/admin/users/edit/" + id;
        }else{
            service.editUser(profileEdit.getUsername(), profileEdit.getPassword(), id);
        }

        return "redirect:/admin/users";
    }
}
