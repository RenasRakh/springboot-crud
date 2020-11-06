package SpringBoot.Springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import SpringBoot.Springbootcrud.model.Role;
import SpringBoot.Springbootcrud.model.User;
import SpringBoot.Springbootcrud.service.RoleService;
import SpringBoot.Springbootcrud.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin/")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleServiceImp;

    @GetMapping("/")
    public String admin(){

        return "redirect:/admin/users/";
    }

    @GetMapping("/users")
    public String allUsers(Model model){
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") int id){
        userService.delete(userService.getById(id));
        return "redirect:/admin/users/";
    }


    @GetMapping(value = {"/new"})
    public String addNewUserForm(Model model) {
        model.addAttribute("user", new User());

        return "admin/new_user";
    }

    @PostMapping("/add_user")
    public String addNewUser(@ModelAttribute("user") User user, @RequestParam(required=false,name = "role1") String role1, @RequestParam(required=false,name = "role2") String role2){
        if (role1==null&&role2==null){
            role2="ROLE_USER";
        }
        Set<String> rolesSet = new HashSet<>();
        rolesSet.add(role1);
        rolesSet.add((role2));
        user.setRoles(roleServiceImp.getRoleSet(rolesSet));
        userService.add(user);

        return "redirect:/admin/users/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id){
        User user = userService.getById(id);
        Set<Role> rolesSet = user.getRoles();

        if (rolesSet.contains(roleServiceImp.getRoleByName("ROLE_ADMIN"))){
            model.addAttribute("flag", true);
        }
        if (rolesSet.contains(roleServiceImp.getRoleByName("ROLE_USER"))){
            model.addAttribute("flag2", true);
        }
        model.addAttribute("user", userService.getById(id));

        return "admin/edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user, @RequestParam(required=false,name = "role1") String role1, @RequestParam(required=false,name = "role2") String role2){
        Set<String> rolesSet = new HashSet<>();
        rolesSet.add(role1);
        rolesSet.add((role2));
        user.setRoles(roleServiceImp.getRoleSet(rolesSet));
        userService.edit(id, user);
        return "redirect:/admin/users/";
    }






}
