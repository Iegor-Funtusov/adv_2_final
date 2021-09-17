package ua.com.alevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/rest")
    public @ResponseBody ResponseEntity<List<User>> findAllRest() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/new")
    public String redirectToCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
