package com.todo.crud.controller;

import com.todo.crud.models.Task;
import com.todo.crud.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskservice;

    public TaskController(TaskService taskservice) {
        this.taskservice = taskservice;
    }
    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskservice.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String CreateTask(@RequestParam String title) {
        taskservice.createTask(title);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskservice.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskservice.toggleTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}/edit/")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskservice.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

    @PostMapping("/task/{id}/edit/")
    public String editTaskTitle(@PathVariable Long id , @RequestParam String title){
        taskservice.editTaskTitle(id,title);
        return "redirect:/tasks";
    }

}
