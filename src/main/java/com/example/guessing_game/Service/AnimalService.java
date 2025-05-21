package com.example.guessing_game.Service;

import com.example.guessing_game.Model.Animal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AnimalService {

    private final List<Animal> animals = List.of(
            new Animal("Lion", "Savanna", "Carnivore", new String[]{"Africa"}, "Cub", "Fur"),
            new Animal("Penguin", "Antarctic", "Carnivore", new String[]{"Antarctica"}, "Chick", "Feathers"),
            new Animal("Elephant", "Savanna", "Herbivore", new String[]{"Africa", "Asia"}, "Calf", "Thick skin"),
            new Animal("Kangaroo", "Grasslands", "Herbivore", new String[]{"Australia"}, "Joey", "Fur"),
            new Animal("Zebra", "Savanna", "Herbivore", new String[]{"Africa"}, "Foal", "Fur"),
            new Animal("Tiger", "Forest", "Carnivore", new String[]{"Asia"}, "Cub", "Fur"),
            new Animal("Panda", "Forest", "Herbivore", new String[]{"Asia"}, "Cub", "Fur"),
            new Animal("Wolf", "Forest", "Carnivore", new String[]{"North America", "Europe", "Asia"}, "Pup", "Fur"),
            new Animal("Bald Eagle", "Forests, Mountains", "Carnivore", new String[]{"North America"}, "Eaglet", "Feathers"),
            new Animal("Grizzly Bear", "Forest, Mountains", "Omnivore", new String[]{"North America"}, "Cub", "Fur"),
            new Animal("Koala", "Forest", "Herbivore", new String[]{"Australia"}, "Joey", "Fur"),
            new Animal("Crocodile", "Rivers, Swamps", "Carnivore", new String[]{"Africa", "Australia", "Asia"}, "Hatchling", "Scales"),
            new Animal("Camel", "Desert", "Herbivore", new String[]{"Africa", "Asia"}, "Calf", "Fur"),
            new Animal("Parrot", "Rainforest", "Omnivore", new String[]{"South America", "Australia"}, "Chick", "Feathers"),
            new Animal("Chimpanzee", "Forest", "Omnivore", new String[]{"Africa"}, "Infant", "Fur"),
            new Animal("Rhinoceros", "Savanna, Grasslands", "Herbivore", new String[]{"Africa", "Asia"}, "Calf", "Thick skin"),
            new Animal("Peacock", "Forests", "Omnivore", new String[]{"Asia"}, "Chick", "Feathers"),
            new Animal("Komodo Dragon", "Islands", "Carnivore", new String[]{"Asia"}, "Hatchling", "Scales"),
            new Animal("Polar Bear", "Arctic", "Carnivore", new String[]{"Arctic"}, "Cub", "Fur"),
            new Animal("Narwhal", "Arctic Ocean", "Carnivore", new String[]{"Arctic"}, "Calf", "Smooth skin")
    );

    private Animal correctAnimal;


    public void generateCorrectAnimal(){
        Random random = new Random();
        correctAnimal = animals.get(2);
    }
    public Animal getCorrectAnimal(){

        return correctAnimal;
    }

    public Animal getGuessedAnimal(String guess){

        return animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(guess))
                .findFirst().orElse(null);
    }



    public boolean checkGuess (String guess){

        Animal guessedAnimal = animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(guess))
                .findFirst().orElse(null);

            return correctAnimal.getName().equalsIgnoreCase(guessedAnimal.getName());

        }


        public List<Animal> getAnimals(){
        return animals;
        }

        public int containsLocation(Animal guessedAnimal){
            String[] guessedLocations = guessedAnimal.getLocations();
            String[] correctLocations = correctAnimal.getLocations();

            int matchingLocations = 0;

            for (int i = 0; i < guessedLocations.length; i++) {
                for (int j = 0; j < correctLocations.length; j++) {
                    if(guessedLocations[i].equalsIgnoreCase(correctLocations[j])){
                        matchingLocations++;
                        break;
                    }
                }
            }

            if(matchingLocations == 0 ){
                guessedAnimal.setLocationInt(0);
                return guessedAnimal.getLocationInt();
            } else if(matchingLocations == correctLocations.length && matchingLocations == guessedLocations.length){
                guessedAnimal.setLocationInt(2);
                return guessedAnimal.getLocationInt();
            } else{
                guessedAnimal.setLocationInt(1);
                return guessedAnimal.getLocationInt();
            }

        }
    }








