package br.senac.tialejo.repository;


import br.senac.tialejo.model.Produto;
import br.senac.tialejo.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);

}
