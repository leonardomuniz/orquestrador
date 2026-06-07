package br.com.muniz.orquestrador.controllers;

import br.com.muniz.orquestrador.DTOs.InputCreateTaskDTO;
import br.com.muniz.orquestrador.models.ApiResponse;
import br.com.muniz.orquestrador.models.TaskModel;

import br.com.muniz.orquestrador.services.Task.CreateTaskService;
import br.com.muniz.orquestrador.services.Task.DeleteTaskService;
import br.com.muniz.orquestrador.services.Task.ListTaskService;
import br.com.muniz.orquestrador.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final ListTaskService listTaskService;
    private final CreateTaskService createTaskService;
    private final DeleteTaskService deleteTaskService;

    @GetMapping
    public List<TaskModel> list() {
        List<TaskModel> taskModelList = listTaskService.run();

        return ResponseEntity.ok(taskModelList).getBody();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> save(@Valid @RequestBody InputCreateTaskDTO inputCreateTaskDTO, HttpServletRequest request) {
        createTaskService.run(inputCreateTaskDTO);

        HttpStatus status = HttpStatus.CREATED;
        ApiResponse<Object> response = ResponseUtil.success(null, "created with success", request.getRequestURI(), status.value());

        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable UUID id, HttpServletRequest request) {
        deleteTaskService.run(id);

        HttpStatus status = HttpStatus.OK;
        ApiResponse<Object> response = ResponseUtil.success(null, "Task deleted with success", request.getRequestURI(), status.value());

        return new ResponseEntity<>(response, status);
    }
}
