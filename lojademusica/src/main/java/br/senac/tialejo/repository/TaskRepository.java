package br.senac.tialejo.repository;


import br.senac.tialejo.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByEmail(String email);

}
