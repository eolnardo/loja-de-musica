package br.senac.tialejo.userDTO;

import jakarta.validation.constraints.NotBlank;

public record userRecordDTO(@NotBlank String name,@NotBlank String email, Integer telefone, String grupo, String status) {
}
