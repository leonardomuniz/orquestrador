package br.com.muniz.orquestrador.services.Task;

import br.com.muniz.orquestrador.DTOs.InputCreateTaskDTO;
import br.com.muniz.orquestrador.models.TaskModel;
import br.com.muniz.orquestrador.repositorys.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskService {
    private final TaskRepository taskRepository;

    public void run(InputCreateTaskDTO input) {
        TaskModel taskModel = TaskModel.builder()
                .name(input.name())
                .description(input.description())
                .cron(input.cron())
                .active(input.active())
                .url(input.url())
                .build();

        taskRepository.save(taskModel);
    }
}
