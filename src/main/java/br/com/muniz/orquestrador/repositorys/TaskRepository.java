package br.com.muniz.orquestrador.repositorys;

import br.com.muniz.orquestrador.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
