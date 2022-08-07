package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    private AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping(value = "/admin/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping(value = "/admin/user-create")
    public String createUserForm(User user) {
        return "admin/user-create";
    }

    @PostMapping(value = "/user-create")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/user-create";
        }
        userService.save(user);
        return "redirect:admin/users";
    }

    @GetMapping(value = "/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.findByID(id);
        model.addAttribute("user", user);
        return "admin/user-update";
    }

    @PostMapping(value = "/user-update")
    public String updateUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            String url = String.format("/admin/user-update/%s", user.getId());
            return "admin/user-update";
        }
        userService.edit(user);
        return "redirect:admin/users";
    }

    @GetMapping(value = "/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
