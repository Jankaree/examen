package com.example.guessing_game.Controller;

import com.example.guessing_game.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.guessing_game.Model.Animal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalController {

    private  final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    @GetMapping("/api/animals")
    @ResponseBody
    public List<String> getAnimals(){
        return animalService.getAnimals().stream()
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

}
