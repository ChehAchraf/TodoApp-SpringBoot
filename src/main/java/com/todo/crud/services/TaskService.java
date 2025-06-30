package com.todo.crud.services;
import com.todo.crud.models.Task;
import com.todo.crud.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow( () -> new RuntimeException("Task not found") );
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow( () -> new RuntimeException("Task not found") );
    }

    public void editTaskTitle(Long id, String title) {
        Task task = taskRepository.findById(id).orElseThrow( () -> new RuntimeException("Task not found") );
        task.setTitle(title);
        taskRepository.save(task);
    }
}
