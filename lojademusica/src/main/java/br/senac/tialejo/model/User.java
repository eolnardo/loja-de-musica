package br.senac.tialejo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity @Table(name = "FUNCIONARIO")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    @NotBlank(message = "Nome não pode estar em branco!")
    private String name;
    @Column(name = "EMAIL")
    @NotBlank(message = "Email não pode estar em branco!")
    private String email;
    @Column(name = "TELEFONE")
    private Integer telefone;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SENHA")

    private String senha;
    @Column(name = "CONFIRMASENHA")
    private String confirmaSenha;


    public User() {
    }


    public User(Long id, String name, String email, Integer telefone, String role, String status, String senha, String confirmaSenha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.role = role;
        this.status = status;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;

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

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}
