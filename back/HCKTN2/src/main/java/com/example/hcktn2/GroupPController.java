package com.example.hcktn2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupPController {
    @Autowired
    private GroupPRepository groupPRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TypeGRepository typeGRepository;

    @GetMapping
    public ResponseEntity<List<GroupP>> getAllGroups() {
        return ResponseEntity.ok(groupPRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupP> getGroupById(@PathVariable(value = "id") Long groupId) {
        return groupPRepository.findById(groupId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<List<Person>> getGroupsByPersonId(@PathVariable(value = "id") Long id) {
        Optional<GroupP> group = groupPRepository.findById(id);
        if(group.isPresent()){
            List<Person> persons = new ArrayList<>(group.get().getMembers());
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> GroupP(@RequestBody GroupP groupP) {
        GroupP newGroupP = groupP;
        // Obtener estudiantes existentes por sus IDs
        Set<Person> persons = new HashSet<>();
        for (Person person : groupP.getMembers()) {
            Long personId = person.getId();
            Person personExists = personRepository.findById(personId).orElse(null);
            if (personExists == null) {
                return ResponseEntity.badRequest().body("La persona con ID " + personId + " no existe.");
            }
            persons.add(personExists);
        }
        newGroupP.setMembers(persons);

        // Obtener estudiantes existentes por sus IDs
        Set<TypeG> types = new HashSet<>();
        for (TypeG type : groupP.getTypes()) {
            Long typeId = type.getId();
            TypeG typeExists = typeGRepository.findById(typeId).orElse(null);
            if (typeExists == null) {
                return ResponseEntity.badRequest().body("El tipo con ID " + typeId + " no existe.");
            }
            types.add(typeExists);
        }
        newGroupP.setMembers(persons);
        newGroupP.setTypes(types);

        groupPRepository.save(newGroupP);

        return ResponseEntity.ok("Grupo creado con éxito y se le asignaron las personas existentes.");
    }

}

