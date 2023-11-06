package com.example.hcktn2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TypeG")
public class TypeG {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "types")
    @JsonIgnore
    private Set<GroupP> groups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GroupP> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupP> groups) {
        this.groups = groups;
    }

    public TypeG() {
    }

    public TypeG(String name, Long id, Set<GroupP> groups) {
        this.name = name;
        this.id = id;
        this.groups = groups;
    }
}
