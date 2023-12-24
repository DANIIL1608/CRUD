package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "base";
    }

    @GetMapping("/show")
    public String getUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("singleUser", userService.findById(id));
        return "update";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("addUser") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("updatedUser") User user, @RequestParam("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        model.addAttribute("singleUser", userService.findById(id));
        userService.delete(id);
        return "redirect:/users";
    }
}
