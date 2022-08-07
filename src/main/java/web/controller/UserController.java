package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    private UserController() {

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String redirectIndex() {
        return "redirect:index";
    }

    @GetMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String userInfoForm(ModelMap model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) userService.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user";
    }
}
