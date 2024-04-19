package br.senac.tialejo.repository;

import br.senac.tialejo.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}
