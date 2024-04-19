package br.senac.tialejo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity @Table(name= "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "NAME")
    @NotBlank(message = "Nome não pode estar em branco!")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z]{2,}(?: [a-zA-Z]{2,})$", message = "O nome deve conter exatamente 2 palavras e cada palavra deve ter no mínimo 3 letras")
    private String name;
    @Column(name = "EMAIL")
    @NotBlank(message = "Email não pode estar em branco!")
    private String email;
    @Column(name = "TELEFONE")
    private Integer telefone;
    @Column(name = "ENDERECO")
    @NotBlank(message = "Endereço não pode estar em branco!")
    private String endereco;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "CPF")
    @NotBlank(message = "CPF não pode estar em branco!")
    private String cpf;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "CONFIRMASENHA")
    private String confirmaSenha;

    public Cliente() {
    }

    public Cliente(Long id, String name, String email, Integer telefone, String endereco, String genero, String cpf, String senha, String confirmaSenha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.genero = genero;
        this.cpf = cpf;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Integer getTelefone() {return telefone;}

    public void setTelefone(Integer telefone) {this.telefone = telefone;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getGenero() {return genero;}

    public void setGenero(String genero) {this.genero = genero;}

    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public String getConfirmaSenha() {return confirmaSenha;}

    public void setConfirmaSenha(String confirmaSenha) {this.confirmaSenha = confirmaSenha;}
}
