package br.senac.tialejo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity @Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 200)
    @NotBlank(message = "Nome não pode estar em branco!")
    @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    private String nome;

    @Column(name = "avaliacao")
    @NotNull(message = "Avaliação não pode ser nula!")
    @DecimalMin(value = "1.0", message = "Avaliação mínima é 1.0")
    @DecimalMax(value = "5.0", message = "Avaliação máxima é 5.0")
    private double avaliacao;

    @Column(name = "descricao", length = 2000)
    @Size(max = 2000, message = "Descrição deve ter no máximo 2000 caracteres")
    private String descricao;

    @Column(name = "preco")
    @NotNull(message = "Preço não pode ser nulo!")
    private BigDecimal preco;

    @Column(name = "quantidade")
    @NotNull(message = "Quantidade não pode ser nula!")
    @Min(value = 0, message = "Quantidade mínima é 0")
    private int quantidade;

    @Column(name = "status")
    @NotNull(message = "Quantidade não pode ser nula!")
    private boolean status = false;

    public Produto() {
    }

    public Produto(Long id, String nome, double avaliacao, String descricao, BigDecimal preco, int quantidade, boolean status) {
        this.id = id;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.status = status;
    }

    public Produto(Long id, String nome, double avaliacao, String descricao, BigDecimal preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
