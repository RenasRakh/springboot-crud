package SpringBoot.Springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import SpringBoot.Springbootcrud.model.User;
import SpringBoot.Springbootcrud.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(ModelMap model, Principal user) {
        User user2 = userService.getUserByUserName(user.getName());
        model.addAttribute("user", user2);
        return "/user/userPage";
    }
}
