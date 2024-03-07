package br.senac.tialejo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "FUNCIONARIO")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID")
    private Long id;
    @Column(name= "NAME")
    private String name;
    @Column(name= "EMAIL")
    private String email;


    public Task() {
    }

    public Task(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
