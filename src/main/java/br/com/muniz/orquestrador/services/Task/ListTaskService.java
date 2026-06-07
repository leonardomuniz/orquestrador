package br.com.muniz.orquestrador.services.Task;

import br.com.muniz.orquestrador.models.TaskModel;
import br.com.muniz.orquestrador.repositorys.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTaskService {

    private final TaskRepository taskRepository;

    public List<TaskModel> run() {
        return taskRepository.findAll();
    }
}
