package com.example.guessing_game.Controller;

import com.example.guessing_game.Model.Animal;
import com.example.guessing_game.Model.User;
import com.example.guessing_game.Repository.UserRepository;
import com.example.guessing_game.Service.AnimalService;
import com.example.guessing_game.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    private final AnimalService animalService;
    private final List<Animal> previousGuesses = new ArrayList<>();

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public GameController(AnimalService animalService, UserRepository userRepository, UserService userService) {
        this.animalService = animalService;
        this.userRepository = userRepository;
        this.userService = userService;
        animalService.generateCorrectAnimal();
    }

    @GetMapping("/classicgame")
    public String initGame(Model model){


        boolean loggedInUser = userService.isLoggedIn();

        if (loggedInUser){
            Optional<User> optionalUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            User user = optionalUser.get();

            model.addAttribute("User", user);

        }

        Animal correctAnimal = animalService.getCorrectAnimal();
        model.addAttribute("correctAnimal", correctAnimal);
        model.addAttribute("isLoggedIn", loggedInUser);

        return "classicgame";
    }

    @PostMapping("/guess")
    public String checkGuess(Animal guess, Model model) {

        boolean isCorrect = animalService.checkGuess(guess.getName());
        Animal guessedAnimal = animalService.getGuessedAnimal(guess.getName());
        int locationInt = animalService.containsLocation(guessedAnimal);
        boolean loggedInUser = userService.isLoggedIn();
        if (guessedAnimal != null) {
            previousGuesses.add(guessedAnimal);
        }


        if(loggedInUser) {
            Optional<User> optionalUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            User user = optionalUser.get();
            model.addAttribute("User", user);
            if (isCorrect) {

                user.setPoints(user.getPoints() + 1);
                userRepository.save(user);
            }
        }
        model.addAttribute("animal", guessedAnimal);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("correctAnimal", animalService.getCorrectAnimal());
        model.addAttribute("previousGuesses", previousGuesses);
        model.addAttribute("locationInt", locationInt);
        model.addAttribute("isLoggedIn", loggedInUser);


        return "classicgame";
    }

    @GetMapping("/")
        public String mainPage(Model model){

        boolean loggedInUser = userService.isLoggedIn();

        if (loggedInUser){
            Optional<User> optionalUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

            User user = optionalUser.get();

            model.addAttribute("User", user);

        }

        model.addAttribute("isLoggedIn", loggedInUser);

        return "mainPage";
    }





}
