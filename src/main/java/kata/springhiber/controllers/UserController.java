package kata.springhiber.controllers;

import kata.springhiber.model.User;
import kata.springhiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(("/users"))
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id")int id, Model model) {
        model.addAttribute("user",userService.getUser(id));
        return "user-info";
    }
    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user") User user){
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        userService.delete(id);
        return "redirect:/users";
    }
}
