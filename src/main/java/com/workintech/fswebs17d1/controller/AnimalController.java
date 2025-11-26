package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Restful bir input alabilmesi için tanılmadık
@RequestMapping("/workintech/animal") //rotasını belirlemek için bunu kullanırız
public class AnimalController {
    private Map<Integer, Animal> animals = new HashMap<>();


    @PostConstruct
    public void loadAll() {
        System.out.println("postconstruct çalıştı.");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "maymun"));

    }
    //http://localhost:8585/workintech/animal
    @GetMapping
    public List<Animal> getAllAnimals() {
        System.out.println("----animals get all triggered!");
        return new ArrayList<>(animals.values());
    }

    //http://localhost:8585/workintech/animal/$id
    @GetMapping("{id}") //PathVariable soldaki id değerini alıp aşağıya veriyor
    public Animal getAnimalsById(@PathVariable("id") int id) { //path den bu id yi okuyacağız diyoruz
        return animals.get(id);
    }

    //http://localhost:8585/workintech/animal
    @PostMapping       //sign up sign in gibi işlemlerde post yapıyorduk, bunlarda path variable yok
    public void addAnimal(@RequestBody Animal newAnimal) { //Request in body sinde ne varsa buraya pass ediyoruz
        animals.put(newAnimal.getId(), newAnimal);
    }

    //http://localhost:8585/workintech/animal/$id
    @PutMapping("{id}") //id geldiği için PathVariable ekledik
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal updatedAnimal) {
        animals.put(id, updatedAnimal);
        return this.animals.get(id);
    }
    //http://localhost:8585/workintech/animal/$id
    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id) {
        animals.remove(id);
    }
}
