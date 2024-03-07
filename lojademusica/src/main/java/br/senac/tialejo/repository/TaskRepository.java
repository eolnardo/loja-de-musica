package br.senac.tialejo.repository;


import br.senac.tialejo.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
