package br.com.muniz.orquestrador.services.Task;

import br.com.muniz.orquestrador.models.TaskModel;
import br.com.muniz.orquestrador.repositorys.TaskRepository;
import br.com.muniz.orquestrador.exceptions.NotFoundedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTaskService {

    private final TaskRepository taskRepository;

    public void run(UUID id) {
        TaskModel taskModel = taskRepository.findById(id).orElseThrow(() -> new NotFoundedException("Task not found"));

        taskRepository.delete(taskModel);
    }
}
