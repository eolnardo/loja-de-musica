package br.senac.tialejo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "image_table")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private long image_id;
    @Lob
    private Blob image;
    private Date date = new Date();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Produto produto;
}
