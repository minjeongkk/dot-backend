package com.example.dot_backend.todo.controller;

import com.example.dot_backend.common.ApiResponse;
import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.dto.TodoResponseDto;
import com.example.dot_backend.todo.enums.State;
import com.example.dot_backend.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/saveTodo")
    public ResponseEntity<ApiResponse<Long>> saveTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
        Long todoId = todoService.saveTodo(todoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(todoId));
    }

    @GetMapping("/info/{idx}")
    public ResponseEntity<ApiResponse<TodoResponseDto>> findTodoById(@PathVariable Long idx) {
        TodoResponseDto todoResponseDto = todoService.findTodoById(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(todoResponseDto));
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<ApiResponse<String>> deleteTodo(@PathVariable Long idx) {
        todoService.deleteTodo(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("Success"));
    }

    @PostMapping("/modify/{idx}")
    public ResponseEntity<ApiResponse<Long>> modifyTodo(@PathVariable Long idx, @RequestBody TodoRequestDto todoRequestDto) {
        Long todoId = todoService.modifyTodo(idx, todoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(todoId));
    }

    @PostMapping("/modify/state/{idx}")
    public ResponseEntity<ApiResponse<Long>> modifyTodoState(@PathVariable Long idx, @RequestBody State state) {
        Long todoId = todoService.modifyTodoState(idx, state);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(todoId));
    }

    @GetMapping("/all/{idx}/{date}")
    public ResponseEntity<ApiResponse<List<TodoResponseDto>>> findAllTodoByUserIdAndDate(@PathVariable Long idx, @PathVariable(name="date") LocalDate todoDate) {
        List<TodoResponseDto> todoResponseDtoList = todoService.findAllTodoByUserIdAndTodoDate(idx, todoDate);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(todoResponseDtoList));
    }
}
