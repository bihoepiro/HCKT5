package com.example.hcktn2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/types")
public class TypeGController {
    @Autowired
    private TypeGRepository typeGRepository;

    @GetMapping
    public ResponseEntity<List<TypeG>> persons() {
        List<TypeG> types = typeGRepository.findAll();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeG> person(@PathVariable Long id) {
        Optional<TypeG> type = typeGRepository.findById(id);
        if (type.isPresent()) {
            return new ResponseEntity<>(type.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> Person(@RequestBody TypeG type) {
        typeGRepository.save(type);
        return ResponseEntity.status(201).body("Created");
    }
}
