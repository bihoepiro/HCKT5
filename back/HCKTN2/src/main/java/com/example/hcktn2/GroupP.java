package com.example.hcktn2;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "GroupP")
public class GroupP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "group_persons",
            joinColumns = { @JoinColumn(name = "id_group") },
            inverseJoinColumns = { @JoinColumn(name = "id_person") }
    )
    private Set<Person> members = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "group_types",
            joinColumns = { @JoinColumn(name = "id_group") },
            inverseJoinColumns = { @JoinColumn(name = "id_type") }
    )
    private Set<TypeG> types = new HashSet<>();

    public Set<TypeG> getTypes() {
        return types;
    }

    public void setTypes(Set<TypeG> types) {
        this.types = types;
    }

    public GroupP() {
    }

    public List<String> getNameTypes() {
        List<String> nameTypes = new ArrayList<>();
        for (TypeG type : types) {
            nameTypes.add(type.getName());
        }
        return nameTypes;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Set<Person> getMembers() {
        return members;
    }
    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public GroupP(String name, Long id, Set<Person> members, Set<TypeG> types) {
        this.name = name;
        this.id = id;
        this.members = members;
        this.types = types;
    }
}
