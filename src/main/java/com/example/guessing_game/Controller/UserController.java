package com.example.guessing_game.Controller;

import com.example.guessing_game.Model.User;
import com.example.guessing_game.Repository.UserRepository;
import com.example.guessing_game.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        Boolean messageBoolean = true;
        Optional<User> optionalUser;

        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            model.addAttribute("usernameTaken", "This username already exist");
            model.addAttribute("messageBoolean", messageBoolean);
            return "register";
        }else {

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            model.addAttribute("userCreated", "User Created");
            model.addAttribute("messageBoolean", messageBoolean);
            return "loginPage";
        }




    }

    @GetMapping("/loginPage")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/user/{username}")
    public String userPage(@PathVariable String username, Model model){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        boolean loggedInUser = userService.isLoggedIn();

        if(optionalUser.isPresent()){
            model.addAttribute("User", optionalUser.get());
        }


        model.addAttribute("isLoggedIn", loggedInUser);


       return "userPage";
    }

    @GetMapping("/perform_logout")
    public String loggingout() {

        return "perform_logout";
    }




}
